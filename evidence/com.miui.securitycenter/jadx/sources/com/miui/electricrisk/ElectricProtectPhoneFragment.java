package com.miui.electricrisk;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import androidx.activity.result.ActivityResult;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import com.miui.powercenter.bean.StatusBarGuideParams;
import com.miui.securitycenter.R;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.Executors;
import miuix.appcompat.app.AlertDialog;
import miuix.preference.PreferenceFragment;

/* JADX INFO: loaded from: classes2.dex */
public class ElectricProtectPhoneFragment extends PreferenceFragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CheckBoxPreference f16064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f16065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AlertDialog f16066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CheckBoxPreference f16067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final androidx.activity.result.b f16068e = registerForActivityResult(new c.c(), new androidx.activity.result.a() { // from class: com.miui.electricrisk.s
        @Override // androidx.activity.result.a
        public final void a(Object obj) {
            this.f16125a.G0((ActivityResult) obj);
        }
    });

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final androidx.activity.result.b f16069f = registerForActivityResult(new c.c(), new androidx.activity.result.a() { // from class: com.miui.electricrisk.t
        @Override // androidx.activity.result.a
        public final void a(Object obj) {
            this.f16126a.H0((ActivityResult) obj);
        }
    });

    public static class AboutInfoFragment extends PreferenceFragment {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        CharSequence f16070a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        CountDownTimer f16071b;

        class a extends CountDownTimer {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Button f16072a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(long j10, long j11, Button button) {
                super(j10, j11);
                this.f16072a = button;
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (AboutInfoFragment.this.isDetached()) {
                    return;
                }
                this.f16072a.setText(AboutInfoFragment.this.getString(R.string.ai_guard_settings_revoke_dialog_btn_pos));
                this.f16072a.setContentDescription(AboutInfoFragment.this.getString(R.string.ai_guard_settings_revoke_dialog_btn_pos));
                this.f16072a.setEnabled(true);
                if (this.f16072a.isAccessibilityFocused()) {
                    this.f16072a.sendAccessibilityEvent(128);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j10) {
                if (AboutInfoFragment.this.isDetached()) {
                    return;
                }
                int i10 = (int) ((j10 + 999) / 1000);
                this.f16072a.setText(AboutInfoFragment.this.getString(R.string.ai_guard_settings_revoke_dialog_btn_stateful_pos, Integer.valueOf(i10)));
                this.f16072a.setContentDescription(AboutInfoFragment.this.getResources().getQuantityString(R.plurals.privacy_revoke_dialog_positive_text_numbers_description, i10, Integer.valueOf(i10)));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean A0(Preference preference) {
            Locale locale = Locale.getDefault();
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://privacy.mi.com/AIalert/%s", locale.getLanguage() + "_" + locale.getCountry()))));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean B0(Preference preference) {
            F0();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean C0(Preference preference) {
            try {
                startActivity(new Intent().setClassName("com.miui.bugreport", "com.miui.bugreport.ui.FeedbackActivity"));
                return true;
            } catch (Exception unused) {
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void D0(DialogInterface dialogInterface) {
            CountDownTimer countDownTimer = this.f16071b;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.f16071b = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void E0(DialogInterface dialogInterface, int i10) {
            androidx.preference.g.c(requireContext()).edit().putBoolean("ai_guard_function_switch", false).apply();
            Settings.System.putInt(requireContext().getContentResolver(), "ai_guard_function_switch", 0);
            H0(getContext());
            G0(getContext());
            Settings.Secure.putInt(requireContext().getContentResolver(), "com.miui.guardprovider.AI_GUARD_PRIVACY_AGREEMENT", 0);
            getActivity().getSupportFragmentManager().a1();
        }

        private void F0() {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), 2131951666);
            builder.setTitle(R.string.ai_guard_settings_revoke_dialog_title);
            builder.setMessage(R.string.ai_guard_settings_revoke_dialog_message);
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.miui.electricrisk.a0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    dialogInterface.dismiss();
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.miui.electricrisk.b0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f16098a.D0(dialogInterface);
                }
            });
            builder.setPositiveButton(R.string.ai_guard_settings_revoke_dialog_btn_pos, new DialogInterface.OnClickListener() { // from class: com.miui.electricrisk.c0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    this.f16100a.E0(dialogInterface, i10);
                }
            });
            Button button = builder.show().getButton(-1);
            button.setEnabled(false);
            a aVar = new a(10000L, 1000L, button);
            this.f16071b = aVar;
            aVar.start();
        }

        static void G0(Context context) {
            context.stopService(new Intent(context, (Class<?>) AiGuardSceneService.class));
        }

        static void H0(Context context) {
            context.stopService(new Intent().setComponent(ComponentName.unflattenFromString("com.miui.guardprovider/.aiguard.AiGuardService")));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onCreatePreferences$0(Preference preference) {
            startActivity(new Intent("miui.intent.action.APP_PERM_EDITOR_PRIVATE").putExtra("extra_pkgname", "com.miui.guardprovider").setPackage(StatusBarGuideParams.MY_PACKAGE_NAME));
            return true;
        }

        @Override // androidx.fragment.app.Fragment
        public void onAttach(Context context) {
            super.onAttach(context);
            FragmentActivity activity = getActivity();
            this.f16070a = activity.getTitle();
            activity.setTitle(R.string.ai_guard_settings_preference_title);
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            setPreferencesFromResource(R.xml.eletric_phone_ai_guard_about, str);
            findPreference("permissions").setOnPreferenceClickListener(new b(new Preference.d() { // from class: com.miui.electricrisk.w
                @Override // androidx.preference.Preference.d
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f16129a.lambda$onCreatePreferences$0(preference);
                }
            }));
            findPreference("privacy_policy").setOnPreferenceClickListener(new b(new Preference.d() { // from class: com.miui.electricrisk.x
                @Override // androidx.preference.Preference.d
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f16130a.A0(preference);
                }
            }));
            findPreference("revoke_privacy_policy").setOnPreferenceClickListener(new b(new Preference.d() { // from class: com.miui.electricrisk.y
                @Override // androidx.preference.Preference.d
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f16131a.B0(preference);
                }
            }));
            findPreference("bugreport").setOnPreferenceClickListener(new b(new Preference.d() { // from class: com.miui.electricrisk.z
                @Override // androidx.preference.Preference.d
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f16132a.C0(preference);
                }
            }));
        }

        @Override // androidx.fragment.app.Fragment
        public void onDetach() {
            super.onDetach();
            getActivity().setTitle(this.f16070a);
            CountDownTimer countDownTimer = this.f16071b;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }
    }

    private static class a implements Preference.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference f16074a;

        /* JADX INFO: renamed from: com.miui.electricrisk.ElectricProtectPhoneFragment$a$a, reason: collision with other inner class name */
        class DialogInterfaceOnClickListenerC0237a implements DialogInterface.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ElectricProtectPhoneFragment f16075a;

            DialogInterfaceOnClickListenerC0237a(ElectricProtectPhoneFragment electricProtectPhoneFragment) {
                this.f16075a = electricProtectPhoneFragment;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i10) {
                try {
                    this.f16075a.startActivity(Intent.parseUri("#Intent;component=com.miui.yellowpage/com.miui.yellowpage.activity.SettingActivity;end", 0));
                } catch (Exception e10) {
                    Log.e("ElectricProtectPhoneFragment", "startActivity exception :" + e10);
                }
            }
        }

        class b implements DialogInterface.OnClickListener {
            b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i10) {
                m.v(false);
            }
        }

        @Override // androidx.preference.Preference.c
        public boolean onPreferenceChange(Preference preference, Object obj) {
            ElectricProtectPhoneFragment electricProtectPhoneFragment = (ElectricProtectPhoneFragment) this.f16074a.get();
            if (electricProtectPhoneFragment != null && electricProtectPhoneFragment.getActivity() != null && !electricProtectPhoneFragment.getActivity().isFinishing() && !electricProtectPhoneFragment.getActivity().isDestroyed() && preference == electricProtectPhoneFragment.f16064a) {
                if (!m.j() || !m.k(electricProtectPhoneFragment.getActivity())) {
                    electricProtectPhoneFragment.f16066c = new AlertDialog.Builder(electricProtectPhoneFragment.getActivity()).setTitle(R.string.eletric_no_grant_dialog_title).setMessage(R.string.eletric_no_grant_dialog_message_for_phone).setNegativeButton(electricProtectPhoneFragment.getResources().getString(R.string.eletric_no_grant_dialog_negative_button), new b()).setPositiveButton(electricProtectPhoneFragment.getResources().getString(R.string.eletric_no_grant_dialog_positive_button), new DialogInterfaceOnClickListenerC0237a(electricProtectPhoneFragment)).create();
                    electricProtectPhoneFragment.f16066c.show();
                    return false;
                }
                m.v(((Boolean) obj).booleanValue());
            }
            return true;
        }

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 2 */
        private a(ElectricProtectPhoneFragment electricProtectPhoneFragment) {
            this.f16074a = new WeakReference(electricProtectPhoneFragment);
        }
    }

    private void D0() {
        final Context context = getContext();
        if (context == null) {
            return;
        }
        gk.r.c(context, true);
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.miui.electricrisk.u
            @Override // java.lang.Runnable
            public final void run() {
                Context context2 = context;
                hi.e.b(context2, gk.r.f31419a, aj.y.a(context2));
            }
        });
    }

    private void E0() {
        findPreference("preference_category_ai_guard").setVisible(false);
        findPreference("preference_comment_ai_guard").setVisible(false);
        AboutInfoFragment.G0(getContext());
        AboutInfoFragment.H0(getContext());
    }

    private boolean F0() {
        return aj.z.D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G0(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            this.f16067d.setChecked(true);
            Settings.Secure.putInt(requireContext().getContentResolver(), "ai_guard_function_switch", 1);
            N0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H0(ActivityResult activityResult) {
        if (activityResult.getResultCode() == 1) {
            D0();
            this.f16068e.a(new Intent().setComponent(ComponentName.unflattenFromString("com.miui.guardprovider/.aiguard.PermissionRequestActivity")));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I0() {
        if (AiGuardUtils.isAiGuardSupported(getContext())) {
            findPreference("preference_category_ai_guard").setVisible(true);
            findPreference("preference_comment_ai_guard").setVisible(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J0(boolean z10) {
        if (!z10 || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.miui.electricrisk.v
            @Override // java.lang.Runnable
            public final void run() {
                this.f16128a.I0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean K0(int i10, Preference preference, Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            Settings.Secure.putInt(requireContext().getContentResolver(), "ai_guard_function_switch", 0);
            AboutInfoFragment.G0(getContext());
            AboutInfoFragment.H0(getContext());
            return true;
        }
        if (F0()) {
            this.f16068e.a(new Intent().putExtra("capabilities", i10).setComponent(ComponentName.unflattenFromString("com.miui.guardprovider/.aiguard.PermissionRequestActivity")));
        } else {
            Intent intent = new Intent("miui.intent.action.SYSTEM_PERMISSION_DECLARE");
            intent.setPackage(StatusBarGuideParams.MY_PACKAGE_NAME);
            intent.putExtra("all_purpose", getString(ll.b.f34603b ? R.string.cta_main_purpose_new : R.string.cta_main_purpose));
            intent.putExtra("app_name", getString(R.string.app_name_securitycenter));
            intent.putExtra("agree_desc", getResources().getString(R.string.cta_agree_desc));
            intent.putExtra("privacy_policy", gk.r.a());
            intent.putExtra("mandatory_permission", false);
            intent.putExtra("runtime_perm", new String[]{"android.permission-group.LOCATION"});
            intent.putExtra("runtime_perm_desc", new String[]{getResources().getString(R.string.cta_HIPS_Perm_Location_Desc)});
            this.f16069f.a(intent);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean L0(Preference preference) {
        getActivity().getSupportFragmentManager().q().x(android.R.id.content, AboutInfoFragment.class, null).i("about").k();
        return true;
    }

    public static ElectricProtectPhoneFragment M0() {
        return new ElectricProtectPhoneFragment();
    }

    private void N0() {
        Context contextRequireContext = requireContext();
        contextRequireContext.startService(new Intent(contextRequireContext, (Class<?>) AiGuardSceneService.class));
    }

    @Override // miuix.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        setPreferencesFromResource(R.xml.eletric_phone_mask, str);
        this.f16065b = new a();
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("key_protect_phone");
        this.f16064a = checkBoxPreference;
        checkBoxPreference.setOnPreferenceChangeListener(new b(this.f16065b));
        this.f16067d = (CheckBoxPreference) findPreference("ai_guard_function_switch");
        final int capabilities = AiGuardUtils.getCapabilities(getContext());
        if ((capabilities & 2) == 0) {
            this.f16067d.setSummary(R.string.ai_guard_settings_preference_summary_1);
        }
        boolean zIsAiGuardSupported = AiGuardUtils.isAiGuardSupported(getContext());
        Log.i("AiGuardDebug", "isAiGuardSupported=" + zIsAiGuardSupported + ", cachedCloud=" + AiGuardCloudController.loadCachedCloudData(getContext()) + ", capabilities=" + capabilities);
        if (!zIsAiGuardSupported) {
            E0();
            AiGuardCloudController.check(getContext(), new AiGuardCloudController.a() { // from class: com.miui.electricrisk.p
                @Override // com.miui.electricrisk.AiGuardCloudController.a
                public final void onResult(boolean z10) {
                    this.f16121a.J0(z10);
                }
            });
        }
        this.f16067d.setOnPreferenceChangeListener(new b(new Preference.c() { // from class: com.miui.electricrisk.q
            @Override // androidx.preference.Preference.c
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f16122a.K0(capabilities, preference, obj);
            }
        }));
        findPreference("ai_guard_about").setOnPreferenceClickListener(new Preference.d() { // from class: com.miui.electricrisk.r
            @Override // androidx.preference.Preference.d
            public final boolean onPreferenceClick(Preference preference) {
                return this.f16124a.L0(preference);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.f16064a.setChecked(m.l(getContext()));
        if (AiGuardUtils.isPreRequirementsMet(requireContext())) {
            return;
        }
        this.f16067d.setChecked(false);
        Settings.System.putInt(requireContext().getContentResolver(), "ai_guard_function_switch", 0);
    }

    static class b implements Preference.d, Preference.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Preference.d f16078a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Preference.c f16079b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f16080c;

        /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
        b(Preference.d dVar) {
            this.f16078a = dVar;
        }

        private boolean a() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime - this.f16080c < 200) {
                return false;
            }
            this.f16080c = jElapsedRealtime;
            return true;
        }

        @Override // androidx.preference.Preference.c
        public boolean onPreferenceChange(Preference preference, Object obj) {
            if (a()) {
                return this.f16079b.onPreferenceChange(preference, obj);
            }
            return false;
        }

        @Override // androidx.preference.Preference.d
        public boolean onPreferenceClick(Preference preference) {
            if (a()) {
                return this.f16078a.onPreferenceClick(preference);
            }
            return false;
        }

        b(Preference.c cVar) {
            this.f16079b = cVar;
        }
    }
}
