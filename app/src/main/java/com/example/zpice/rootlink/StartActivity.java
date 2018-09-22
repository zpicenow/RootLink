package com.example.zpice.rootlink;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import java.io.IOException;


public class StartActivity extends Activity   {

    String sendTOKEN;
    String sendCOOKIE;
    String COOKIE = "";
    String TOKEN = "";
    private ImageView welcomeImg;
    //利用SharedPreferences将数据存储在本地，sp_name是文件名
    SharedPreferences sharedPreferences;
    SharedPreferences getsharedPreferences;
    SharedPreferences.Editor editor;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x001) {
                startActivity(new Intent(StartActivity.this, Home.class));
                finish();

            } else if (msg.what == 0x322) {
                Log.i("-------loginLog", "has been start");




                sendCOOKIE = msg.getData().getString("COOKIE");
                sendTOKEN = msg.getData().getString("TOKEN");






            }
        }
    };





    @SuppressLint({"CommitPrefEdits", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        init();











        welcomeImg = findViewById(R.id.id_iv_welcome);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(2000);// 设置动画显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());

    }

    private void init() {
        //利用SharedPreferences将数据存储在本地，sp_name是文件名
        sharedPreferences = getSharedPreferences("sp_name", Context.MODE_PRIVATE);


        //获取SharedPreferences实例
        getsharedPreferences = getSharedPreferences("sp_name", Context.MODE_PRIVATE);
        TOKEN = getsharedPreferences.getString("TOKEN", "");
        COOKIE = getsharedPreferences.getString("COOKIE", "");
        Log.i("-=-=-=init this", TOKEN + " " + COOKIE);


        //获取Editor实例
        editor = sharedPreferences.edit();
        if (LogIn.ISSTORE) {

            editor.putString("COOKIE", LogIn.COOKIE);
            editor.putString("TOKEN", LogIn.TOKEN);
            //异步提交
            editor.apply();
        }
    }

    private class AnimationImpl implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.start_pic);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void skip() {
        Log.i("--232423-2-4-32-", TOKEN);
        if (!TOKEN.equals("")) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        TOKEN = MyHttpUrlConn.sendPost("http://www.rootlink.cn/api/login", "token=" + TOKEN);
                        COOKIE = MyHttpUrlConn.Post("http://www.rootlink.cn/api/login", "token=" + TOKEN, "utf-8", false, "");
                        handler.sendEmptyMessage(0x001);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {

            startActivity(new Intent(this, LogIn.class));
            finish();
        }
    }
}
