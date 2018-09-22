package com.example.zpice.rootlink;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Sign extends Activity {
    EditText editTextId, editTextKey, editTextKeyAgain, editTextMail,editTextCode;

    Button buttonSignAndLog,sendCodeButton;

    String stringId, stringKey, stringKeyAgain, stringMail,stringCode;
    private boolean isSendClickable = true;
    CheckBox checkBox;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x001) {

                    Toast.makeText(Sign.this, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sign.this, LogIn.class));
                    finish();

            } else if (msg.what == 0x002) {
                Toast.makeText(Sign.this, "验证码有误", Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        editTextId = (EditText) findViewById(R.id.editTextIdSign_id);
        editTextKey = (EditText) findViewById(R.id.editTextKeySign_id);
        editTextKeyAgain = (EditText) findViewById(R.id.id_et_KeyAgain);
        editTextMail = (EditText) findViewById(R.id.id_editTextMail);
        editTextCode = findViewById(R.id.id_et_signsurecode);

        buttonSignAndLog = (Button) findViewById(R.id.btn_signInSign);

        sendCodeButton = findViewById(R.id.id_sign_sendcode);

        checkBox = findViewById(R.id.id_cb_agreerule);

    }

    public void signInSign(View view) {
        init();
        if (checkBox.isChecked()) {
            //如果键盘显示则隐藏
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            if ((stringId.length() > 0) && (stringMail.length() >= 4) && (stringMail.contains("@"))
                    && (stringKey.length() > 0) && (stringKeyAgain.length() > 0) && (stringKey.equals(stringKeyAgain)) && stringCode.length() > 0) {
                new Thread(){
                    @Override
                    public void run() {
                        String coderuturn = MyHttpUrlConn.sendPost("http://www.rootlink.cn/api/register", "username=" + stringId
                                + "&password=" + stringKey + "&email=" + stringMail + "&code=" + stringCode);
                        Log.i("----coderutrun--", coderuturn);
                        if (coderuturn.split("\"")[4].contains("200")) {
                            handler.sendEmptyMessage(0x001);
                        }
                        else {
                            handler.sendEmptyMessage(0x002);
                        }
                    }
                }.start();


            } else {
                Toast.makeText(this, "请输入正确的格式", Toast.LENGTH_SHORT).show();
            }
        }



    }

    @SuppressLint("SetTextI18n")
    public void sign_send(View view) throws InterruptedException {


        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);



        editTextCode.setFocusable(true);
        editTextCode.setFocusableInTouchMode(true);
        editTextCode.requestFocus();
        init();
        if ((stringId.length() != 0) && (stringMail.length() >= 4) && (stringMail.contains("@"))
                && (stringKey.length() != 0) && (stringKeyAgain.length() != 0) && (stringKey.equals(stringKeyAgain))&&isSendClickable ){
            new Thread(){
                @Override
                public void run() {
                    String server = MyHttpUrlConn.sendGet("http://www.rootlink.cn/api/register/getCode", "email=" + stringMail);
                    Log.i("--email--", stringMail);
                    Log.i("---webserver----", server);
                }
            }.start();

            sendCodeButton.setBackgroundColor(getResources().getColor(R.color.gray));
            isSendClickable = false;
            myCountDownTimer.start();


            isSendClickable = true;
            sendCodeButton.setBackgroundColor(getResources().getColor(R.color.blue));


        } else {
            Toast.makeText(this, "请输入正确的邮箱地址", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        stringId = editTextId.getText().toString();
        stringKey = editTextKey.getText().toString();
        stringKeyAgain = editTextKeyAgain.getText().toString();
        stringMail = editTextMail.getText().toString();
        stringCode = editTextCode.getText().toString();
    }

    public void sign_rule(View view) {
        new AlertDialog.Builder(this).setIcon(R.drawable.root)
                .setTitle("服务条款")
                .setMessage(R.string.rulecontent)
                .setPositiveButton("我同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create()
                .show();
    }

    public void agreeRule(View view) {
        if (checkBox.isChecked()) {
            buttonSignAndLog.setBackgroundColor(getResources().getColor(R.color.blue));

        } else {
            buttonSignAndLog.setBackgroundColor(getResources().getColor(R.color.gray));

        }

    }
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            sendCodeButton.setClickable(false);
            sendCodeButton.setText(l/1000+"秒");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            sendCodeButton.setText("重新获取");
            //设置可点击
            sendCodeButton.setClickable(true);
        }
    }

}

