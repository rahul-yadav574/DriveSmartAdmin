package in.drivesmartadmin.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Brekkishhh on 25-07-2016.
 */
public class ServerRequest extends IntentService {

    private static final String TAG = "ServerRequest";

    public ServerRequest() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG,"Server Request Service Called");
        Bundle bundle = intent.getExtras();
        String phoneNumber = bundle.getString("phone");
        String lat = bundle.getString("latitude");
        String lng = bundle.getString("longitude");
        String timeStamp = bundle.getString("timeStamp");

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormEncodingBuilder()
                .add("phone",phoneNumber)
                .add("latitude",lat)
                .add("longitude",lng)
                .add("timeStamp",timeStamp)
                .build();

        Request request = new Request.Builder()
                .url("")          //Here Goes The Url Once it is ready
                .post(requestBody)
                .build();



        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG,"Unable To Connect To Host" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG,"Server SuccessFully Called "+response.body().string());
            }
        });
    }
}
