package in.drivesmartadmin.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brekkishhh on 24-07-2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SmsRecord.db";
    private static final String TAG  = "DbHelper";

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbUtils.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEntryToDb(String senderNumber,String senderMessage,String timeStamp){

        SQLiteDatabase db =  getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Schema.DbEntry.COLUMN_SENDER_NUMBER,senderNumber);
        values.put(Schema.DbEntry.COLUMN_SENDER_MESSAGE,senderMessage);
        values.put(Schema.DbEntry.COLUMN_SENDER_MESSAGE_TIME,timeStamp);
        db.insert(Schema.DbEntry.TABLE_NAME,null,values);
    }


    public List<Message> retrieveListFromDb(){

        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {Schema.DbEntry.COLUMN_SENDER_NUMBER, Schema.DbEntry.COLUMN_SENDER_MESSAGE};

        Cursor readCursor = db.query(Schema.DbEntry.TABLE_NAME,
                projection,null,null,null,null,null);

        readCursor.moveToFirst();        //now the cursor points to the first row


        int totalRows = readCursor.getCount();

        List<Message> results = new ArrayList<>();

        while (totalRows>0){
            totalRows--;
            String senderNumber = readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.COLUMN_SENDER_NUMBER));
            String senderMessage = readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.COLUMN_SENDER_MESSAGE));
            String timeStamp = readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.COLUMN_SENDER_MESSAGE_TIME));
            results.add(new Message(senderNumber,senderMessage,timeStamp));
            readCursor.moveToNext();
        }

        readCursor.close();

        return results;

    }

    public class Message {
        private String phone;
        private String message;
        private String time;

        public Message(String phone, String message,String time) {
            this.message = message;
            this.phone = phone;
            this.time = time;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getMessage() {
            return this.message;
        }

        public String getTime(){return this.time;}
    }
}
