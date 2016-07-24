package in.drivesmartadmin.Db;

import android.provider.BaseColumns;

/**
 * Created by Brekkishhh on 24-07-2016.
 */
public class Schema {

    public Schema() {
    }

    public static abstract class DbEntry implements BaseColumns{

        public static final String TABLE_NAME = "sms_records";
        public static final String COLUMN_SENDER_NUMBER = "sender_number";
        public static final String COLUMN_SENDER_MESSAGE = "sender_message";
        public static final String COLUMN_SENDER_MESSAGE_TIME = "sender_message_time";


    }
}
