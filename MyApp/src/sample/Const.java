package sample;

public class Const {
    public static final String CLIENT_TABLE = "Client";

    public static final String CLIENT_ID = "Client_ID";
    public static final String CLIENT_NAME = "Client_name";
    public static final String CLIENT_SURNAME = "Client_surname";
    public static final String CLIENT_TELEPHONE = "Client_telephone";
    public static final String CLIENT_ADDRESS= "Client_address";

    public static final String TRANSPORT_TABLE = "Vehicle";

    public static final String TRANSPORT_ID = "Vehicle_ID";
    public static final String TRANSPORT_NUMBER = "Vehicle_registatin_number";
    public static final String TRANSPORT_MODEL = "Vehicle_model";
    public static final String TRANSPORT_COLOR = "Vehicle_color";
    public static final String TRANSPORT_YEAR = "Vehicle_year_of_issue";
    public static final String TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID = "Transport_service_Transport_service_ID";

    public static final String CLIENT_RECORD_TABLE = "Client_record";

    public static final String CLIENT_RECORD_ID= "Client_record.Client_record_ID";
    public static final String CLIENT_RECORD_DATA = "Client_record.Client_record_data";
    public static final String CLIENT_CLIENT_ID = "Client_record.Client_Client_ID";
    public static final String TRANSPORT_TRANSPORT_ID = "Client_record.Vehicle_Vehicle_ID";
    public static final String TRANSPORT_TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID= "Client_record.Vehicle_Transport_service_Transport_service_ID";
    public static final String CHECK_CHECK_ID = "Client_record.Check_Check_ID";
    public static final String DIAGNOSTIC_CARD_DIAGNOSTIC_CARD_ID = "Client_record.Diagnostic_card_Diagnostic_card_ID";
    public static final String MASTER_MASTER_ID = "Client_record.Master_Master_ID";
    public static final String CLIENT_RECORD_COMMENT = "Client_record.Client_record_comment";

    public static final String MASTER_TABLE = "Master";

    public static final String MASTER_ID = "Master_ID";
    public static final String MASTER_NAME = "Master_name";
    public static final String MASTER_SURNAME = "Master_surname";
    public static final String MASTER_TELEPHONE = "Master_telephone";

    public static final String CHECK_TABLE = "_Check";

    public static final String CHECK_ID = "Check_ID";
    public static final String CHECK_DATA = "Check_data";
    public static final String CHECK_TOTAL_PRICE = "Check_total_price";

    public static final String DIAGNOSTIC_CARD_TABLE = "Diagnostic_card";

    public static final String DIAGNOSTIC_CARD_ID = "Diagnostic_card.Diagnostic_card_ID";
    public static final String DIAGNOSTIC_CARD_COMMENTS = "Diagnostic_card.Diagnostic_card_comments";
    public static final String DC_MASTER_MASTER_ID= "Diagnostic_card.Master_Master_ID";

    public static final String TRANSPORT_SERVICE_TABLE = "Transport_service";

    public static final String TRANSPORT_SERVICE_ID = "Transport_service_ID";
    public static final String TRANSPORT_SERVICE_RECOMMENDATION = "Transport_service_recommendation";
    public static final String TRANSPORT_SERVICE_LAST_DATA = "Transport_service_last_date";
    public static final String TRANSPORT_SERVICE_NEXT_DATA = "Transport_service_next_data";
    public static final String WORK_DONE_WORK_DONE_ID= "Work_done_Work_done_ID";


}
