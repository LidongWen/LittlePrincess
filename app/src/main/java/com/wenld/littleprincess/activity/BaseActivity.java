package com.wenld.littleprincess.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by wenld on 2017/4/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutID());
        initView();
        initData();
        initListener();
    }
    protected abstract int getContentLayoutID();
    protected abstract void initView();    protected abstract void initData();
    protected abstract void initListener();




    protected View findVByID(@IdRes int id){
       return findViewById(id);
    }


    protected void setImmerseLayout(View view) {//设置沉浸式（4.4以上）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setImmerseLayout();
            int statusBarHeight = getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    protected void setImmerseLayout() {//设置沉浸式（4.4以上）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置状态栏的颜色，目前只是在4.4以上有效
     */
    public void setStatusBarView(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(false);
            tintManager.setTintColor(color);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
