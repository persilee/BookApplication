package net.lishaoy.bookapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class BookListActivity extends AppCompatActivity {

    private static final String TAG = "BookListActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        String url = "http://www.imooc.com/api/teacher?type=10";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i(TAG, "onSuccess: ");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "onSuccess: " + new String(responseBody));
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onCreate: onFinish");
                super.onFinish();
            }
        });
    }

    public static void start(Context context){
        Intent intent = new Intent(context, BookListActivity.class);
        context.startActivity(intent);
    }
}
