package jo;

import lp.n;

/* JADX INFO: loaded from: classes4.dex */
public interface a {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0456a f33526u = C0456a.f33527a;

    /* JADX INFO: renamed from: jo.a$a, reason: collision with other inner class name */
    public static final class C0456a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ C0456a f33527a = new C0456a();

        private C0456a() {
        }

        public final String a() {
            return "SELECT * FROM fraud_phone WHERE call_start_time > " + (System.currentTimeMillis() - 259200000) + " ORDER BY call_start_time DESC";
        }

        public final String b() {
            return "SELECT * FROM fraud_phone WHERE call_start_time > " + (System.currentTimeMillis() - 259200000) + " AND is_fraud_call = 1 ORDER BY call_start_time DESC";
        }

        public final String c() {
            return "SELECT * FROM fraud_phone WHERE call_start_time > " + (System.currentTimeMillis() - 259200000) + " AND is_chinese_number = 0 ORDER BY call_start_time DESC";
        }

        public final String d() {
            return "SELECT * FROM fraud_phone WHERE call_start_time > " + (System.currentTimeMillis() - 259200000) + " AND is_virtual_operator = 1 ORDER BY call_start_time DESC";
        }

        public final String e() {
            return "SELECT * FROM voip_record WHERE voip_record_start_time > " + (System.currentTimeMillis() - 259200000) + " ORDER BY voip_record_start_time DESC";
        }

        public final String f() {
            return "SELECT * FROM call_forwarding WHERE timestamp > " + (System.currentTimeMillis() - 259200000) + " AND reason = 0 AND enabled = 1 ORDER BY timestamp DESC";
        }

        public final String g() {
            return "SELECT * FROM voip_record WHERE voip_record_start_time > " + (System.currentTimeMillis() - 259200000) + " AND is_fraud_call = 1 ORDER BY voip_record_start_time DESC";
        }

        public final String h(String str, String str2) {
            n.f(str, "tableName");
            n.f(str2, "columnName");
            return "SELECT * FROM " + str + " WHERE " + str2 + " > " + (System.currentTimeMillis() - 259200000) + " ORDER BY " + str2 + " DESC";
        }
    }
}
