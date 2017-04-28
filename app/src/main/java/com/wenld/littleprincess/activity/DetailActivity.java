package com.wenld.littleprincess.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenld.littleprincess.InfoDao;
import com.wenld.littleprincess.InfoHepler;
import com.wenld.littleprincess.R;

import java.util.Random;

/**
 * <p/>
 * Author: wenld on 2017/4/28 14:22.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class DetailActivity extends BaseActivity {

    private ImageView iv;
    private TextView tvTitle;
    private TextView tvMemo;

    InfoDao infoDao;

    CollapsingToolbarLayout collapsingToolbar;
    Random random;

    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        setImmerseLayout(findViewById(R.id.toolbar));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });

        iv = (ImageView) findViewById(R.id.iv_activity_detail);
        tvTitle = (TextView) findViewById(R.id.tv_title_activity_detail);
        tvMemo = (TextView) findViewById(R.id.tv_memo_activity_detail);

        infoDao = InfoHepler.getList().get(getIntent().getIntExtra("position", 0));

    }

    @Override
    protected void initData() {
        random = new Random();
        bindUI();
    }

    private void bindUI() {
        if (infoDao != null) {
            iv.setImageDrawable(getResources().getDrawable(infoDao.imgResId));
            tvTitle.setText(infoDao.name);
            collapsingToolbar.setTitle(infoDao.name);
            tvMemo.setText(infoDao.memo);
        }
    }

    @Override
    protected void initListener() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = random.nextInt(InfoHepler.getList().size() - 1);
                infoDao = InfoHepler.getList().get(a);
                bindUI();
                if (a / 2 == 0) {
                    createCircularReveal();
                } else {
                    createCircularReveal_1();
                }
//                Snackbar.make(view, "My Little Princess , I am love you so much!", Snackbar.LENGTH_LONG)
//                        .setAction("My Heart", null).show();
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void createCircularReveal() {
        Animator animator = ViewAnimationUtils.createCircularReveal(iv, 0, 0, 0, (float) Math.hypot(iv.getWidth(), iv.getHeight()));
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void createCircularReveal_1() {
        Animator animator = ViewAnimationUtils.createCircularReveal(iv, iv.getWidth() / 2, iv.getHeight() / 2, 0, iv.getHeight());
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }
}
