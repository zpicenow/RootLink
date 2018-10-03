package com.example.zpice.rootlink;

import android.app.Dialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Message extends AppCompatActivity {

    private LinearLayout linearLayoutTake;



    private ImageView imageViewReturn;
    private ImageView imageViewMenu;
    private TextView textViewTitle;



    private LinearLayout linearLayout1;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView1;
    Message.MyListener myListener = new Message.MyListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toast.makeText(this, "网络连接过慢", Toast.LENGTH_SHORT).show();


        linearLayoutTake = findViewById(R.id.messageTakeView);

        linearLayout1 = findViewById(R.id.id_ll_tabLayout1);
        linearLayout1.setOnClickListener(myListener);
        linearLayout3 = findViewById(R.id.id_ll_tabLayout3);
        linearLayout3.setOnClickListener(myListener);
        linearLayout4 = findViewById(R.id.id_ll_tabLayout4);
        linearLayout4.setOnClickListener(myListener);
        imageView1 = findViewById(R.id.id_iv_tabImg1);
        imageView2 = findViewById(R.id.id_iv_tabImg2);
        imageView3 = findViewById(R.id.id_iv_tabImg3);
        imageView4 = findViewById(R.id.id_iv_tabImg4);

        init();


        textViewTitle = findViewById(R.id.id_tv_tile);
        textViewTitle.setText("设备");
        imageViewReturn = findViewById(R.id.id_iv_title_return);
        imageViewReturn.setOnClickListener(myListener);
        imageViewMenu = findViewById(R.id.id_iv_title_home);
        imageViewMenu.setOnClickListener(myListener);





        final WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.loadUrl("http://www.rootlink.cn/blog");

        //隐藏网页标题栏
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {

                linearLayoutTake.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                webView.setVisibility(View.VISIBLE);

                super.onPageFinished(view, url);


                //编写 javaScript方法
                String javascript =  "javascript:function hideOther() {" +
                        "document.getElementsByTagName('body')[0].innerHTML;" +
                        "document.getElementsByTagName('div')[3].style.display='none';" +
                        "document.getElementsByTagName('div')[5].style.display='none';" +
                        "document.getElementsByClassName('dropdown')[0].style.display='none';" +
                        "document.getElementsByClassName('min')[0].remove();" +
                        "var divs = document.getElementsByTagName('div');" +
                        "var lastDiv = divs[divs.length-1];" +
                        "lastDiv.remove();" +
                        "document.getElementsByClassName('showme')[0].remove();" +
                        "document.getElementsByClassName('nei-t3')[1].remove();}";

                //创建方法
                view.loadUrl(javascript);

                //加载方法
                view.loadUrl("javascript:hideOther();");
            }
        });









    }

    private void init() {
        imageView1.setImageResource(R.drawable.home_gray);
        imageView2.setImageResource(R.drawable.message_blue);
        imageView3.setImageResource(R.drawable.file_gray);
        imageView4.setImageResource(R.drawable.product_gray);
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_ll_tabLayout1:
                    startActivity(new Intent(Message.this, Home.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                    finish();
                    break;
                case R.id.id_ll_tabLayout3:
                    startActivity(new Intent(Message.this, File.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                    finish();
                    break;
                case R.id.id_ll_tabLayout4:
                    startActivity(new Intent(Message.this, Product.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                    finish();
                    break;




                case R.id.id_iv_title_return:
                    finish();
                    break;
                case R.id.id_iv_title_home:
                    setDialog();


                    break;
                case R.id.id_menu_message:
                    startActivity(new Intent(Message.this, Message.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    break;
                case R.id.id_menu_file:
                    startActivity(new Intent(Message.this, File.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                    finish();
                    break;
                case R.id.id_menu_logout:

                    //注销登陆，待补充
                    break;
                case R.id.id_menu_cancel:
                    new Thread () {
                        public void run () {
                            try {
                                Instrumentation inst= new Instrumentation();
                                inst.sendKeyDownUpSync(KeyEvent. KEYCODE_BACK);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();


                    break;
            }
        }
    }

    private void setDialog(){
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);

        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.dialog_bottom, null);
        //初始化视图
        root.findViewById(R.id.id_menu_file).setOnClickListener(myListener);
        root.findViewById(R.id.id_menu_logout).setOnClickListener(myListener);
        root.findViewById(R.id.id_menu_message).setOnClickListener(myListener);
        root.findViewById(R.id.id_menu_cancel).setOnClickListener(myListener);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

}
