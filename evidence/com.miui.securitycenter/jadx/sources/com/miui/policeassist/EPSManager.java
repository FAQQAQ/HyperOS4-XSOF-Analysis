package com.miui.policeassist;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.miui.common.utils.j0;
import com.miui.earthquakewarning.model.SaveAreaResult;
import com.miui.earthquakewarning.utils.Utils;
import com.miui.maml.data.VariableNames;
import com.miui.permission.PermissionContract;
import com.miui.securitycenter.Application;
import com.miui.warningcenter.policeassist.PaCommon;
import com.miui.warningcenter.policeassist.PaUtils;
import gk.l;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class EPSManager {
    private static final String EPS_PRIVACY_VERSION = "v20250117\n";
    private static final String GPS_COORDINATE_SYSTEM_TYPE = "1";
    private static final String LOCATION_EXCEPTION = "0";
    private static final String LOCATION_GPS = "1";
    private static final String LOCATION_NET = "2";
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY = 1000;
    private static final String TAG = "EPSManager";

    private EPSManager() {
    }

    private static String buildSortedQueryString(Map<String, String> map) {
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb2.append(entry.getKey());
            sb2.append("=");
            sb2.append(entry.getValue());
            sb2.append("&");
        }
        sb2.append("key");
        sb2.append("=");
        sb2.append(PaCommon.EPS_PRIVATE_KEY);
        return getSm3Hex(sb2.toString());
    }

    private static EPSRequestBean createBean(Location location, String str, String str2, String str3, long j10, String str4, String str5) {
        EPSRequestBean ePSRequestBean = new EPSRequestBean();
        ePSRequestBean.setSource(PaCommon.EPS_XIAOMI_CHANNEL);
        ePSRequestBean.setCaller(getSm3Hex(str));
        if (TextUtils.equals(str3, "0") || TextUtils.equals(str3, "")) {
            ePSRequestBean.setCalled(str2);
        } else {
            ePSRequestBean.setCalled("0" + str3 + str2);
        }
        ePSRequestBean.setDevice(str5);
        ePSRequestBean.setMethod(getLocationMethod(location.getProvider()));
        ePSRequestBean.setType("1");
        ePSRequestBean.setLng(Double.valueOf(location.getLongitude()));
        ePSRequestBean.setLat(Double.valueOf(location.getLatitude()));
        if (location.hasAccuracy()) {
            if (Build.VERSION.SDK_INT >= 34) {
                ePSRequestBean.sethAccuracy(Double.valueOf(location.getAccuracy()));
            }
            ePSRequestBean.setvAccuracy(Double.valueOf(location.getVerticalAccuracyMeters()));
        }
        if (location.hasAltitude()) {
            ePSRequestBean.setAltitude(Double.valueOf(location.getAltitude()));
        }
        ePSRequestBean.setAddress(str4);
        ePSRequestBean.setTime(Long.valueOf(j10));
        ePSRequestBean.setExtra("");
        ePSRequestBean.setSign(buildSortedQueryString(toTreeMap(ePSRequestBean)));
        return ePSRequestBean;
    }

    public static boolean getEPSPoliceAssistToggle(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), PaCommon.KEY_OPEN_POLICE_ASSIST_EPS, 0) == 1;
    }

    private static String getLocationMethod(String str) {
        if (TextUtils.equals("gps", str)) {
            return "1";
        }
        return TextUtils.equals("network", str) ? "2" : "0";
    }

    private static String getSm3Hex(String str) {
        return Sm3Utils.sm3Hex(str.getBytes(StandardCharsets.UTF_8));
    }

    private static boolean isNewerVersion(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str) || str.compareToIgnoreCase(str2) < 0) ? false : true;
    }

    public static void postEPS(Location location, String str, String str2, String str3, long j10, String str4) throws Throwable {
        if (miui.os.Build.IS_INTERNATIONAL_BUILD || PaUtils.CALL_NUMBER_12122.equals(str2)) {
            return;
        }
        if (getEPSPoliceAssistToggle(Application.B().getApplicationContext())) {
            postMessage(createBean(location, str, str2, String.valueOf(str3), j10, str4, j0.d(Application.B())));
        } else if (PaUtils.getPoliceAssistToggle(Application.B().getApplicationContext()) && isNewerVersion(readData(Application.B().getApplicationContext(), "privacy_version", Utils.POLICE_ASSIST_POLICE_NAME), EPS_PRIVACY_VERSION)) {
            setEPSPoliceAssistToggle(Application.B().getApplicationContext(), true);
            postMessage(createBean(location, str, str2, String.valueOf(str3), j10, str4, j0.d(Application.B())));
        }
    }

    private static void postMessage(EPSRequestBean ePSRequestBean) throws Throwable {
        try {
            String json = new Gson().toJson(ePSRequestBean);
            int i10 = 0;
            while (i10 < 3) {
                String strO = l.O(json, PaCommon.EPS_NET_ADDRESS);
                if (!TextUtils.isEmpty(strO) && new JSONObject(strO).getInt("status") == 200) {
                    return;
                }
                i10++;
                if (i10 < 3) {
                    Thread.sleep(1000L);
                }
            }
        } catch (Exception e10) {
            Log.e(TAG, "postMessage Exception:", e10);
        }
    }

    private static String readData(Context context, String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(context.getFilesDir().getPath());
        String str3 = File.separator;
        sb2.append(str3);
        sb2.append("privacypolicy");
        sb2.append(str3);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str);
        String string = sb2.toString();
        return !new File(string).exists() ? "" : readFileLegacy(string);
    }

    private static String readFileLegacy(String str) throws Throwable {
        StringBuilder sb2 = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            sb2.append(line);
                            sb2.append("\n");
                        } catch (IOException e10) {
                            e = e10;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return sb2.toString();
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                } catch (IOException e12) {
                    e12.printStackTrace();
                }
            } catch (IOException e13) {
                e = e13;
            }
            return sb2.toString();
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static void setEPSPoliceAssistToggle(Context context, boolean z10) {
        Settings.Secure.putInt(context.getContentResolver(), PaCommon.KEY_OPEN_POLICE_ASSIST_EPS, z10 ? 1 : -1);
    }

    private static Map<String, String> toTreeMap(EPSRequestBean ePSRequestBean) {
        TreeMap treeMap = new TreeMap();
        treeMap.put(SaveAreaResult.Columns.source, ePSRequestBean.getSource());
        treeMap.put("caller", ePSRequestBean.getCaller());
        treeMap.put("called", ePSRequestBean.getCalled());
        treeMap.put("device", ePSRequestBean.getDevice());
        treeMap.put(PermissionContract.Method.ReadClipboardTipSetting.EXTRA_METHOD, ePSRequestBean.getMethod());
        treeMap.put("type", ePSRequestBean.getType());
        treeMap.put("lng", String.valueOf(ePSRequestBean.getLng()));
        treeMap.put("lat", String.valueOf(ePSRequestBean.getLat()));
        treeMap.put("hAccuracy", String.valueOf(ePSRequestBean.gethAccuracy()));
        treeMap.put("vAccuracy", String.valueOf(ePSRequestBean.getvAccuracy()));
        treeMap.put("altitude", String.valueOf(ePSRequestBean.getAltitude()));
        treeMap.put("address", ePSRequestBean.getAddress());
        treeMap.put(VariableNames.VAR_TIME, String.valueOf(ePSRequestBean.getTime()));
        treeMap.put("extra", ePSRequestBean.getExtra());
        return treeMap;
    }
}
