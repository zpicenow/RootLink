package com.example.zpice.rootlink;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

public class Tab extends Activity {
    private int index;
    private ImageView imageViewHome;
    private ImageView imageViewMess;
    private ImageView imageViewFile;
    private ImageView imageViewPro;

    Tab(int i) {
        index = i;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        imageViewHome = findViewById(R.id.id_iv_tabImg1);
        imageViewMess = findViewById(R.id.id_iv_tabImg2);
        imageViewFile = findViewById(R.id.id_iv_tabImg3);
        imageViewPro = findViewById(R.id.id_iv_tabImg4);
    }

    public void tab1() {

    }
    public void tab2() {
        imageViewHome.setImageResource(R.drawable.home_gray);
        imageViewMess.setImageResource(R.drawable.message_blue);
        imageViewFile.setImageResource(R.drawable.file_gray);
        imageViewPro.setImageResource(R.drawable.product_gray);
    }
    public void tab3() {
        imageViewHome.setImageResource(R.drawable.home_gray);
        imageViewMess.setImageResource(R.drawable.message_gray);
        imageViewFile.setImageResource(R.drawable.file_blue);
        imageViewPro.setImageResource(R.drawable.product_gray);
    }
    public void tab4() {
        imageViewHome.setImageResource(R.drawable.home_gray);
        imageViewMess.setImageResource(R.drawable.message_gray);
        imageViewFile.setImageResource(R.drawable.file_gray);
        imageViewPro.setImageResource(R.drawable.product_blue);
    }

    public void homeClick(View view) {
        if (index != 1) {


        }
    }

    public void messageClick(View view) {
        if (index != 2) {

        }
    }

    public void fileClick(View view) {
        if (index != 3) {

        }
    }

    public void productClick(View view) {
        if (index != 4) {
            startActivity(new Intent(this, Product.class));
            finish();
        }
    }
}
