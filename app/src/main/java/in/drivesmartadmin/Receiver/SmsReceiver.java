package in.drivesmartadmin.Receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import in.drivesmartadmin.Db.DbHelper;
import in.drivesmartadmin.Service.ServerRequest;

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

                if (!messageBody.startsWith("I am lost here")){
                    Log.d(TAG,"Hey This message is not for us.");
                    return;
                }
                String sender = message.getOriginatingAddress();
                String sendTime = String.valueOf(message.getTimestampMillis());

                Log.d(TAG,""+sender+" "+messageBody);
                dbHelper = new DbHelper(context);
                dbHelper.addEntryToDb(sender,messageBody,sendTime);


                int latStartIndex = messageBody.indexOf(":")+1;
                String sub = messageBody.substring(latStartIndex);

                String latlng[] = sub.split(" ");

                Log.d(TAG,"latitude"+latlng[1]);
                Log.d(TAG,"longitude"+latlng[2]);

                Bundle bundle = new Bundle();

                bundle.putString("phone",sender);
                bundle.putString("latitude",latlng[1]);
                bundle.putString("longitude",latlng[2]);
                bundle.putString("timeStamp","23-12-2015");

                Intent startServerRequest = new Intent(context, ServerRequest.class);
                startServerRequest.putExtras(bundle);
                context.startService(startServerRequest);
            }
        }

    }
}
