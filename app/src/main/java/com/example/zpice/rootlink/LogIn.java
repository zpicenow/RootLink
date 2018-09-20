package com.example.zpice.rootlink;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.IOException;
import java.util.List;

import static com.example.zpice.rootlink.MainActivity.handler;


public class LogIn extends Activity {
    private String USER, PASSWORD;
    private CheckBox rememberBox;
    public static String TOKEN = "";

    public static String COOKIE = "";
    private boolean isRemember;
    private EditText editTextId;
    private EditText editTextKey;
    private ProgressBar progressBar;

    //非主线程不允许修改UI，使用handle触发主线程修改
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x001) {
                Toast.makeText(LogIn.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();

            }
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


        rememberBox = findViewById(R.id.id_cb_login_rememberpassword);

        editTextId =  findViewById(R.id.id_et_login_username);
        editTextKey = findViewById(R.id.id_et_login_password);
        progressBar = findViewById(R.id.id_pb_login_progressbar);

        if (USER != null && PASSWORD != null) {
            editTextId.setText(USER);
            editTextKey.setText(PASSWORD);
        }



    }


    public void log_sign(View view) {

    }

    public void log(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        USER = String.valueOf(editTextId.getText());
        PASSWORD = String.valueOf(editTextKey.getText());
        isRemember = rememberBox.isChecked();
        final String param = "username=" + USER + "&password=" + PASSWORD + "&rememberMe=" + isRemember;
        Log.i("-=-=-=",param);
        
        //主线程不允许网络通信，创建线程服务
        new Thread(){
            @Override
            public void run() {
                if (USER != null && PASSWORD != null) {
                    try {
                        COOKIE = MyHttpUrlConn.Post("http://www.rootlink.cn/api/login", param, "utf-8", false, "");
                        if (COOKIE == null) {
                            //非主线程不允许修改UI，使用handle触发主线程修改
                            handler.sendEmptyMessage(0x001);
                        } else {

                        Log.i("-=-=-=",TOKEN);
                        }



                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

    }

}
