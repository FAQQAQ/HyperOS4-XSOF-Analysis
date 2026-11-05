package com.xiaomi.registration.autoregistration;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import i1.e;
import i1.g;
import i1.i;

/* JADX INFO: loaded from: classes.dex */
public class RegistrationJobService extends JobService {
    public final SparseArray a = new SparseArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f846b = new Handler();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f847c = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, boolean z2) {
        JobParameters jobParameters;
        i iVar;
        SparseArray sparseArray = this.a;
        if (sparseArray.size() == 0 || (jobParameters = (JobParameters) sparseArray.valueAt(0)) == null) {
            return;
        }
        jobFinished(jobParameters, false);
        sparseArray.removeAt(0);
        if (sparseArray.size() > 0) {
            c((JobParameters) sparseArray.valueAt(0));
        }
        e eVar = (e) jobParameters.getTransientExtras().getSerializable("REG_ENTITY");
        eVar.f1704d = z2;
        eVar.f1705e = str;
        eVar.f1706f = str2;
        Intent intent = new Intent("com.qualcomm.qti.action.AUTO_REGISTRATION");
        intent.putExtra("REG_ENTITY", eVar);
        intent.setFlags(268435456);
        intent.setClass(this, AutoRegReceiver.class);
        sendOrderedBroadcast(intent, null);
        if (!z2 || (iVar = (i) this.f847c.a) == null) {
            return;
        }
        Uri uri = Uri.parse("content://com.xiaomi.registration.service.GET_DEVICE_INFO");
        Context context = iVar.f1717d;
        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(uri);
        if (contentProviderClientAcquireContentProviderClient != null) {
            context.getContentResolver().call(uri, "get_device_info", "STORE_DEVICE_INFO", iVar.a("STORE_DEVICE_INFO"));
            contentProviderClientAcquireContentProviderClient.close();
        }
    }

    public final void c(JobParameters jobParameters) {
        Log.d("AutoReg_JobService", "performJob job size = " + this.a.size());
        e eVar = (e) jobParameters.getTransientExtras().getSerializable("REG_ENTITY");
        if (eVar == null) {
            return;
        }
        Log.d("AutoReg_JobService", "performJob RegistrationEntity: " + eVar);
        this.f847c = TextUtils.isEmpty(eVar.f1705e) ? new g(this, this, eVar.f1702b, eVar.f1703c) : new g(this, eVar.f1705e);
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        if (jobParameters.getJobId() == 2) {
            Log.d("AutoReg_JobService", "Network available, dispatching WORK_BOOT_BOOTSTRAP");
            AutoRegIntentService.b(this, 1);
            return false;
        }
        SparseArray sparseArray = this.a;
        sparseArray.put(jobParameters.getJobId(), jobParameters);
        Log.d("AutoReg_JobService", "onStartJob job size = " + sparseArray.size());
        if (sparseArray.size() == 1) {
            c(jobParameters);
        }
        return true;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        StringBuilder sb = new StringBuilder("onStopJob job size = ");
        SparseArray sparseArray = this.a;
        sb.append(sparseArray.size());
        Log.d("AutoReg_JobService", sb.toString());
        int iIndexOfValue = sparseArray.indexOfValue(jobParameters);
        if (iIndexOfValue <= -1) {
            return false;
        }
        sparseArray.remove(iIndexOfValue);
        return false;
    }
}
