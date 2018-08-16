package app_utility;


public class StaticReferenceClass {
    public static final String LOGIN_URL = "http://192.168.1.46";//http://192.168.1.100:9040//
    public static final String SERVER_URL = "192.168.1.100";//"192.168.1.46";//"192.168.42.192";//"192.168.1.41";//https://"18.222.197.80";//"autochipars.odoo.com";//
    public static final int PORT_NO = 9040;//8069;//

    public static final String MODEL_FLEET_VEHICLE = "fleet.vehicle";

    public static final String DB_NAME = "ARS_9040";//"test_db";//"auto";//"ARS";//"chellaminfotech-ars-master-73173";//
    public static final String USER_ID = "vijay@gmail.com";//"vijay.eh4@gmail.com";//"admin";//"suresh";//
    public static final String PASSWORD = "a";//"autochip";//"autochip@505";//
    public static final String MODEL_NAME = "res.partner";

    public static final String MODEL_INSURANCE_HISTORY = "insurance.history";

    public static final String MODEL_EMISSION_HISTORY = "emission.history";

    public static final String MODEL_OWNER_HISTORY = "ownership.history";

    public static final String MODEL_SERVICE_HISTORY = "service.history";

    public static final String[] MODEL_INSURANCE_FIELDS = {"id", "insurance_doc_no", "vender_name", "insurance_start",
            "insurance_end", "set_reminder", "vehicle_id"};

    public static final String[] MODEL_EMISSION_FIELDS = {"id", "emission_doc_no", "agency_name", "emission_start",
            "emision_end", "set_reminder", "vehicle_id"};

    public static final String sAPP_PREFERENCES = "TrackProPreferences";

    public static final int REGISTER_IMAGE_REQUEST_CODE = 9101;

}

