package com.example.zpice.rootlink;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.IOException;
import java.util.List;


public class LogIn extends Activity {
    private boolean ISEDA = false;
    private String USER, PASSWORD;

    private EditText editTextId;
    private EditText editTextKey;
    private Button buttonSign;
    private Button buttonLog;
    private Button buttonForget;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        if (ISEDA) {
            forget(getCurrentFocus());
        }




        editTextId = (EditText) findViewById(R.id.editTextId_id);
        editTextKey = (EditText) findViewById(R.id.editTextKey_id);
        buttonSign = (Button) findViewById(R.id.btn_sign);
        buttonLog = (Button) findViewById(R.id.btn_log);
        buttonForget = (Button) findViewById(R.id.btn_forget);
        progressBar = (ProgressBar) findViewById(R.id.id_bar_login);

        if (USER != null && PASSWORD != null) {
            editTextId.setText(USER);
            editTextKey.setText(PASSWORD);
        }



    }


    public void log_sign(View view) {
    }

    public void log(View view) {
        USER = String.valueOf(editTextId.getText());
        PASSWORD = String.valueOf(editTextKey.getText());
        if (USER != null && PASSWORD != null) {
            try {
                MyHttpUrlConn.Post("http://www.rootlink.cn/api/login", "username=769484623&password=11223388", "utf-8", false, "");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void forget(View view) {
        startActivity(new Intent(this, MainActivity.class));
        ISEDA = true;
        finish();
    }
}
