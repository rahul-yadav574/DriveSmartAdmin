package in.drivesmartadmin;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Brekkishhh on 24-07-2016.
 */
public class SmsReceiver extends BroadcastReceiver {

    public SmsReceiver() {
    }

    private static final String TAG = "SmsReceiver";

    @TargetApi(19)
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            for (SmsMessage message : Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                String messageBody = message.getMessageBody();
                String sender = message.getOriginatingAddress();

                Log.d(TAG,""+sender+" "+messageBody);
            }
        }

    }
}
