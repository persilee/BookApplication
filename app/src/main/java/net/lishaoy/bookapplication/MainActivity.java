package net.lishaoy.bookapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.ref.WeakReference;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {

    public static final int CODE = 1001;
    public static final int TOTAL_TIME = 3000;
    public static final int INT_TIME = 1000;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.time_text_btn);

        final MyHandler handler = new MyHandler(this);
        Message message = Message.obtain();
        message.what = CODE;
        message.arg1 = TOTAL_TIME;
        handler.sendMessage(message);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookListActivity.start(MainActivity.this);
                MainActivity.this.finish();
                handler.removeMessages(CODE);
            }
        });

    }

    public static class MyHandler extends Handler{

        public final WeakReference<MainActivity> weakReference;
        public MyHandler(MainActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MainActivity activity = weakReference.get();
            if(message.what == CODE){
                if(activity != null) {
                    int time = message.arg1;
                    //更新UI
                    activity.textView.setText(time/INT_TIME + activity.getString(R.string.m_text));

                    //发送倒计时
                    Message message1 = Message.obtain();
                    message1.what = CODE;
                    message1.arg1 = time - 1000;

                    if(time > 0){
                        sendMessageDelayed(message1, INT_TIME);
                    }else {
                        BookListActivity.start(activity);
                        activity.finish();
                    }
                }
            }
        }
    }
}
