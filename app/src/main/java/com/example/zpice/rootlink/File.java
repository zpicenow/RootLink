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
import android.view.ViewGroup;
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

public class File extends AppCompatActivity implements View.OnClickListener{



    private ImageView imageViewReturn;
    private ImageView imageViewMenu;
    private TextView textViewTitle;


    private WebView webView;
    private LinearLayout textView;
    private TextView filePart1;
    private TextView filePart2;


    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout4;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView1;
    File.MyListener myListener = new File.MyListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


        webView = findViewById(R.id.fileWebView);
        textView = findViewById(R.id.fileTakeView);
        filePart1 = findViewById(R.id.filePart1);
        filePart1.setOnClickListener( this);
        filePart2 = findViewById(R.id.filePart2);
        filePart2.setOnClickListener(this);

        filePart1.performClick();



        linearLayout1 = findViewById(R.id.id_ll_tabLayout1);
        linearLayout1.setOnClickListener(myListener);
        linearLayout2 = findViewById(R.id.id_ll_tabLayout2);
        linearLayout2.setOnClickListener(myListener);
        linearLayout4 = findViewById(R.id.id_ll_tabLayout4);
        linearLayout4.setOnClickListener(myListener);
        imageView1 = findViewById(R.id.id_iv_tabImg1);
        imageView2 = findViewById(R.id.id_iv_tabImg2);
        imageView3 = findViewById(R.id.id_iv_tabImg3);
        imageView4 = findViewById(R.id.id_iv_tabImg4);

        init();


        textViewTitle = findViewById(R.id.id_tv_tile);
        textViewTitle.setText("数据");
        imageViewReturn = findViewById(R.id.id_iv_title_return);
        imageViewReturn.setOnClickListener(myListener);
        imageViewMenu = findViewById(R.id.id_iv_title_home);
        imageViewMenu.setOnClickListener(myListener);




        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);



    }

    private void init() {
        imageView1.setImageResource(R.drawable.home_gray);
        imageView2.setImageResource(R.drawable.message_gray);
        imageView3.setImageResource(R.drawable.file_blue);
        imageView4.setImageResource(R.drawable.product_gray);
    }

    public void more(View view) {
        Toast.makeText(this, "更多内容敬请期待", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.filePart1:
                textView.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);

                webView.loadUrl("http://www.rootlink.cn/doc/SDK/quickstart/register.html");

                //隐藏网页标题栏
                webView.setWebViewClient(new WebViewClient(){


                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {

                        if (url != null && url.contains("register")) {
                            //实现我们自己的处理，例如跳转到另一个Activity
                            startActivity(new Intent(File.this, Sign.class));

                        } else if (url != null && url.contains("upload.html")) {
                            filePart2.performClick();


                        } else {

                        }

                        return true;

                    }



                    @Override
                    public void onPageFinished(WebView view, String url) {
                        textView.setVisibility(View.INVISIBLE);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(0,0));

                        webView.setVisibility(View.VISIBLE);

                        super.onPageFinished(view, url);


                        //编写 javaScript方法
                        String javascript =  "javascript:function hideOther() {" +
                                "document.getElementsByTagName('body')[0].innerHTML;" +
                                "document.getElementsByClassName('nav')[0].style.display='none';" +
//                                "document.getElementsByClassName('nav-down')[0].style.display='none';" +
                                "document.getElementsByClassName('min')[0].remove();" +
                                "var divs = document.getElementsByTagName('div');" +
                                "var lastDiv = divs[divs.length-1];" +
                                "lastDiv.remove();" +
                                "document.getElementsByClassName('aside___2qnoh')[0].style.display='none';" +
                                "document.getElementsByClassName('nei-t3')[1].remove();}";

                        //创建方法
                        view.loadUrl(javascript);

                        //加载方法
                        view.loadUrl("javascript:hideOther();");
                    }
                });

                break;
            case R.id.filePart2:
                textView.setVisibility(View.VISIBLE);
                textView.setLayoutParams(new LinearLayout.LayoutParams(0,200));

                webView.setVisibility(View.INVISIBLE);


                webView.loadUrl("http://www.rootlink.cn/doc/SDK/quickstart/upload.html");

                //隐藏网页标题栏
                webView.setWebViewClient(new WebViewClient(){


                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {

                        if (url != null && url.contains("register.html")) {
                            filePart1.performClick();
                            //实现我们自己的处理，例如跳转到另一个Activity

                        } else if (url != null && url.contains("upload.html")) {

                            filePart2.performClick();


                        } else {

                        }

                        return true;

                    }



                    @Override
                    public void onPageFinished(WebView view, String url) {
                        textView.setVisibility(View.INVISIBLE);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(0,0));

                        webView.setVisibility(View.VISIBLE);

                        super.onPageFinished(view, url);


                        //编写 javaScript方法
                        String javascript =  "javascript:function hideOther() {" +
                                "document.getElementsByTagName('body')[0].innerHTML;" +
//                                "document.getElementsByClassName('nav-down')[0].style.display='none';" +
                                "document.getElementsByClassName('nav')[0].style.display='none';" +
                                "document.getElementsByClassName('min')[0].remove();" +
                                "var divs = document.getElementsByTagName('div');" +
                                "var lastDiv = divs[divs.length-1];" +
                                "lastDiv.remove();" +
                                "document.getElementsByClassName('aside___2qnoh')[0].style.display='none';" +
                                "document.getElementsByClassName('nei-t3')[1].remove();}";

                        //创建方法
                        view.loadUrl(javascript);

                        //加载方法
                        view.loadUrl("javascript:hideOther();");
                    }
                });
                break;




        }





    }



    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_ll_tabLayout1:
                    startActivity(new Intent(File.this, Home.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    break;
                case R.id.id_ll_tabLayout2:
                    startActivity(new Intent(File.this, Message.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    Toast.makeText(File.this, "网络连接过慢", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.id_ll_tabLayout4:
                    startActivity(new Intent(File.this, Product.class));
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
                    startActivity(new Intent(File.this, Message.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    break;
                case R.id.id_menu_file:
                    startActivity(new Intent(File.this, File.class));
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
