package com.wenld.littleprincess.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenld.littleprincess.InfoDao;
import com.wenld.littleprincess.InfoHepler;
import com.wenld.littleprincess.R;

/**
 * <p/>
 * Author: wenld on 2017/4/28 14:22.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class DetailActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tvTitle;
    private TextView tvMemo;

    InfoDao infoDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar =
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
        if (infoDao != null) {
            iv.setImageDrawable(getResources().getDrawable(infoDao.imgResId));
            tvTitle.setText(infoDao.name);
            toolbar.setTitle(infoDao.name);
            tvMemo.setText(infoDao.memo);
        }
    }
}
