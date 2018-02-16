package com.example.lulzyaka.apiconsumer;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by boo on 16.02.18.
 */

public class Click1 implements View.OnClickListener {
    private TextView text;
    private final String url = "https://api.coinmarketcap.com/v1/global/";
    private OkHttpClient client;

    public Click1(TextView text) {
        this.text = text;
        client = new OkHttpClient();
    }

    @Override
    public void onClick(View v) {
//        try {
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//            Response response = client.newCall(request).execute();
//            text.setText("hello " + response.body().string());
//        }
//        catch (Exception ex) {
//            text.setText("wow " + ex.getMessage());
//        }
//        Log.d("request", "Completed");
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                text.setText("error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    text.setText("rez:\n" + responseBody.string());
                }
            }
        });
    }
}
