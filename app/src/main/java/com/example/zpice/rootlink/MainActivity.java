package com.example.zpice.rootlink;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    static TextView[] showTV = new TextView[5];

    static String cookie;
    static String[] values = {"", "", "", "",""};
    int[] tvIds = {R.id.phTV, R.id.wenDuTV, R.id.yanDuTV, R.id.zhuoDuTV,R.id.countTV};
    static String[] sensorIds = {
            "sensorId=d3523470-7682-4b67-b645-3d13883152a5",
            "sensorId=cd99a95d-5aa0-422d-8b4c-a3cbb52a90a1",
            "sensorId=5ec4f318-1f01-4a2c-a682-a60cd7e289e5",
            "sensorId=d1ec2ff7-f48f-429d-8993-7174b92c4084",
            "sensorId=fd877998-8d69-47df-bb13-0dddca19bea5"};

    @SuppressLint("HandlerLeak")
    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0x123) {
                for (int i = 0; i < 5; i++) {
                    while (true) {
                        if (values[i] != "") {
                            double temp = 0.00;
                            switch (i) {
                                case 0:
                                    temp = Integer.parseInt(values[i]) / 100.00;
                                    DecimalFormat df = new DecimalFormat("0.00");
                                    showTV[i].setText(df.format(temp));

                                    break;
                                case 1:
                                    temp = Integer.parseInt(values[i]) / 10.0;
                                    DecimalFormat df1 = new DecimalFormat("0.0");
                                    showTV[i].setText(df1.format(temp));
                                    break;
                                case 2:
                                    temp = Integer.parseInt(values[i]) / 1000.000;
                                    DecimalFormat df2 = new DecimalFormat("0.000");
                                    showTV[i].setText(df2.format(temp));
                                    break;
                                case 3:
                                    temp = Integer.parseInt(values[i]) / 100.00;
                                    DecimalFormat df3 = new DecimalFormat("0.0");
                                    showTV[i].setText(df3.format(temp));
                                    break;
                                case 4:
                                    showTV[i].setText(values[i]);
                                    break;
                            }
                            break;
                        }
                    }
                }











                }
            }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.setAttributes(params);

        setContentView(R.layout.activitytest);

//        update = findViewById(R.id.button);

        for (int i = 0; i < 5; i++) {
            showTV[i] = findViewById(tvIds[i]);
        }

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    update();
                    System.out.println("update");
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }


    public static void update() {
        new Thread() {
            @Override
            public void run() {
                try {
                    cookie = MyHttpUrlConn.Post("http://www.rootlink.cn/api/login", "username=769484623&password=11223388", "utf-8", false, "");
                    String[] responses = new String[5];
                    for (int i = 0; i < 5; i++) {
                        responses[i] = MyHttpUrlConn.Get("http://www.rootlink.cn/api/sensor/getValue", sensorIds[i], "utf-8", cookie);
                    }
                    String[][] dataStrings = new String[5][];
                    for (int i = 0; i < 5; i++) {
                        dataStrings[i] = responses[i].split("\"");
                        values[i] = dataStrings[i][9];
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        handler.sendEmptyMessage(0x123);
    }

}

