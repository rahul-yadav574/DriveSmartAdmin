package in.drivesmartadmin;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import in.drivesmartadmin.Db.DbHelper;

/**
 * Created by Brekkishhh on 24-07-2016.
 */
public class SmsReceiver extends BroadcastReceiver {


    private static final String TAG = "SmsReceiver";
    private DbHelper dbHelper;

    @TargetApi(19)
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            for (SmsMessage message : Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                String messageBody = message.getMessageBody();
                String sender = message.getOriginatingAddress();
                String sendTime = String.valueOf(message.getTimestampMillis());

                Log.d(TAG,""+sender+" "+messageBody);

                dbHelper.addEntryToDb(sender,messageBody,sendTime);
                //TODO:  send these details to server ...wait server is not ready yet
            }
        }

    }
}
