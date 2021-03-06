package com.example.zpice.rootlink;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ImageView imageViewReturn;
    private ImageView imageViewMenu;

    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView1;


    private ViewPager vp;
    private LinearLayout ll_point;
    private TextView tv_desc;
    private int[] imageResIds; //存放图片资源id的数组
    private ArrayList<ImageView> imageViews; //存放图片的集合
    private int lastPosition;
    private boolean isRunning = false; //viewpager是否在自动轮询
    MyListener myListener = new MyListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        linearLayout2 = findViewById(R.id.id_ll_tabLayout2);
        linearLayout2.setOnClickListener(myListener);
        linearLayout3 = findViewById(R.id.id_ll_tabLayout3);
        linearLayout3.setOnClickListener(myListener);
        linearLayout4 = findViewById(R.id.id_ll_tabLayout4);
        linearLayout4.setOnClickListener(myListener);
        imageView1 = findViewById(R.id.id_iv_tabImg1);
        imageView2 = findViewById(R.id.id_iv_tabImg2);
        imageView3 = findViewById(R.id.id_iv_tabImg3);
        imageView4 = findViewById(R.id.id_iv_tabImg4);

        init();



        imageViewReturn = findViewById(R.id.id_iv_title_return);
        imageViewReturn.setOnClickListener(myListener);
        imageViewMenu = findViewById(R.id.id_iv_title_home);
        imageViewMenu.setOnClickListener(myListener);










        //使用M-V-C模型
        //V--view视图
        initViews();
        //M--model数据
        initData();
        //C--control控制器(即适配器)
        initAdapter();
        //开启图片的自动轮询
        new Thread(){
            @Override
            public void run() {
                isRunning = true;
                while(isRunning){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() { //在子线程中开启子线程
                            //往下翻一页（setCurrentItem方法用来设置ViewPager的当前页）
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();


    }

    private void init() {
        imageView1.setImageResource(R.drawable.home_blue);
        imageView2.setImageResource(R.drawable.message_gray);
        imageView3.setImageResource(R.drawable.file_gray);
        imageView4.setImageResource(R.drawable.product_gray);
    }

    public void messageClick(View view) {
        linearLayout2.performClick();
    }

    public void fileClick(View view) {
        linearLayout3.performClick();
    }


    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_ll_tabLayout2:
                    startActivity(new Intent(Home.this, Message.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    break;
                case R.id.id_ll_tabLayout3:
                    startActivity(new Intent(Home.this, File.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                    finish();
                    break;
                case R.id.id_ll_tabLayout4:
                    startActivity(new Intent(Home.this, Product.class));
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
                    startActivity(new Intent(Home.this, Message.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    break;
                case R.id.id_menu_file:
                    startActivity(new Intent(Home.this, File.class));
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






    /*
       初始化视图
    */
        private void initViews () {
            //初始化放小圆点的控件
            ll_point = findViewById(R.id.ll_point);
            //初始化ViewPager控件
            vp = findViewById(R.id.vp);
            //设置ViewPager的滚动监听
            vp.setOnPageChangeListener(this);
            //显示图片描述信息的控件
        }

    /*
      初始化数据
     */
        private void initData () {
            //初始化填充ViewPager的图片资源
            imageResIds = new int[]{R.drawable.rollerpic2, R.drawable.rollerpic3, R.drawable.rollerpic1};
            //图片的描述信息
            //保存图片资源的集合
            imageViews = new ArrayList<>();
            ImageView imageView;
            View pointView;
            //循环遍历图片资源，然后保存到集合中
            for (int i = 0; i < imageResIds.length; i++) {
                //添加图片到集合中
                imageView = new ImageView(this);
                imageView.setBackgroundResource(imageResIds[i]);
                imageViews.add(imageView);

                //加小白点，指示器（这里的小圆点定义在了drawable下的选择器中了，也可以用小图片代替）
                pointView = new View(this);
                pointView.setBackgroundResource(R.drawable.cirgray); //使用选择器设置背景
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(8, 8);
                if (i != 0) {
                    //如果不是第一个点，则设置点的左边距
                    layoutParams.leftMargin = 10;
                }
                pointView.setEnabled(false); //默认都是暗色的
                ll_point.addView(pointView, layoutParams);
            }
        }

    /*
      初始化适配器
     */
        private void initAdapter () {
            ll_point.getChildAt(0).setEnabled(true); //初始化控件时，设置第一个小圆点为亮色
            lastPosition = 0; //设置之前的位置为第一个
            vp.setAdapter(new Home.MyPagerAdapter());
            //设置默认显示中间的某个位置（这样可以左右滑动），这个数只有在整数范围内，可以随便设置
            vp.setCurrentItem(5000000); //显示5000000这个位置的图片
        }

        //界面销毁时，停止viewpager的轮询
        @Override
        protected void onDestroy () {
            super.onDestroy();
            isRunning = false;
        }

    /*
      自定义适配器，继承自PagerAdapter
     */
        class MyPagerAdapter extends PagerAdapter {


            //返回显示数据的总条数，为了实现无限循环，把返回的值设置为最大整数
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            //指定复用的判断逻辑，固定写法：view == object
            @Override
            public boolean isViewFromObject(View view, Object object) {
                //当创建新的条目，又反回来，判断view是否可以被复用(即是否存在)
                return view == object;
            }

            //返回要显示的条目内容
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //container  容器  相当于用来存放imageView
                //从集合中获得图片
                int newPosition = position % 3; //数组中总共有5张图片，超过数组长度时，取摸，防止下标越界
                ImageView imageView = imageViews.get(newPosition);
                //把图片添加到container中
                try {

                    container.addView(imageView);
                } catch (Exception e) {

                }
                //把图片返回给框架，用来缓存
                return imageView;
            }

            //销毁条目
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //object:刚才创建的对象，即要销毁的对象
                container.removeView((View) object);
            }
        }

        //--------------以下是设置ViewPager的滚动监听所需实现的方法--------
        //页面滑动

        @Override
        public void onPageScrolled ( int position, float positionOffset, int positionOffsetPixels){

        }

        //新的页面被选中
        @Override
        public void onPageSelected ( int position){
            //当前的位置可能很大，为了防止下标越界，对要显示的图片的总数进行取余
            int newPosition = position % 3;
            //设置小圆点为高亮或暗色
            ll_point.getChildAt(lastPosition).setEnabled(false);
            ll_point.getChildAt(newPosition).setEnabled(true);
            lastPosition = newPosition; //记录之前的点
        }

        //页面滑动状态发生改变
        @Override
        public void onPageScrollStateChanged ( int state){

        }


    }
