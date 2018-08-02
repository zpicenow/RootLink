package com.example.zpice.rootlink;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Sign extends Activity {
    EditText editTextId, editTextKey, editTextKeyAgain, editTextMail;

    Button buttonSignAndLog;

    String stringId, stringKey, stringKeyAgain, stringMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        editTextId = (EditText) findViewById(R.id.editTextIdSign_id);
        editTextKey = (EditText) findViewById(R.id.editTextKeySign_id);
        editTextKeyAgain = (EditText) findViewById(R.id.id_editTextKeyAgain);
        editTextMail = (EditText) findViewById(R.id.id_editTextMail);

        buttonSignAndLog = (Button) findViewById(R.id.btn_signInSign);



    }

    public void signInSign(View view) {
        stringId = editTextId.getText().toString();
        stringKey = editTextKey.getText().toString();
        stringKeyAgain = editTextKeyAgain.getText().toString();
        stringMail = editTextMail.getText().toString();

        if ((stringId.length() != 0) && (stringMail.length() >= 4) && (stringMail.contains("@"))
                && (stringKey.length() != 0) && (stringKeyAgain.length() != 0) && (stringKey.equals(stringKeyAgain))) {





        } else{
            Toast.makeText(this, "请输入正确的格式", Toast.LENGTH_SHORT).show();
        }
    }
}