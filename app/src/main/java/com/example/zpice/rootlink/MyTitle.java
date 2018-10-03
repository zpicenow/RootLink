//package com.example.zpice.rootlink;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.IOException;
//
//public class MyTitle extends Activity implements View.OnClickListener {
//
//    private ImageView imageViewReturn;
//    private ImageView imageViewMenu;
//    private TextView textViewTitle;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_title);
//
//        imageViewReturn = findViewById(R.id.id_iv_title_return);
//        imageViewReturn.setOnClickListener(this);
//        imageViewMenu = findViewById(R.id.id_iv_title_home);
//        imageViewMenu.setOnClickListener(this);
//
//
//    }
//
//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//
//            case R.id.id_iv_title_return:
//                finish();
//                break;
//            case R.id.id_iv_title_home:
//                setDialog();
//
//
//                break;
//            case R.id.id_menu_message:
//                startActivity(new Intent(Home.this, Message.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                finish();
//                break;
//            case R.id.id_menu_file:
//                startActivity(new Intent(Home.this, File.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//
//                finish();
//                break;
//            case R.id.id_menu_logout:
//
//                //注销登陆，待补充
//                break;
//            case R.id.id_menu_cancel:
//                Runtime runtime = Runtime.getRuntime();
//                try {
//                    runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                break;
//        }
//
//
//    }
//
//    private void setDialog(){
//        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
//
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
//                R.layout.dialog_bottom, null);
//        //初始化视图
//        root.findViewById(R.id.id_menu_file).setOnClickListener(myListener);
//        root.findViewById(R.id.id_menu_logout).setOnClickListener(myListener);
//        root.findViewById(R.id.id_menu_message).setOnClickListener(myListener);
//        root.findViewById(R.id.id_menu_cancel).setOnClickListener(myListener);
//        mCameraDialog.setContentView(root);
//        Window dialogWindow = mCameraDialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        mCameraDialog.show();
//    }
//
//
//
//
//}
