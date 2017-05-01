package com.wenld.littleprincess.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.TextView;

import com.wenld.littleprincess.R;
import com.wenld.littleprincess.transition.ChangeColor;
import com.wenld.littleprincess.transition.ChangePosition;
import com.wenld.littleprincess.transition.CommentEnterTransition;
import com.wenld.littleprincess.transition.ShareElemEnterRevealTransition;
import com.wenld.littleprincess.transition.ShareElemReturnChangePosition;
import com.wenld.littleprincess.transition.ShareElemReturnRevealTransition;

/**
 * Created by wenld on 2017/5/1.
 */

public class LoveActivity extends AppCompatActivity {
    private Toolbar toolbar;
    View layout;
    private TextView main_activity_love;
    private TextView bottom_aty_love;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new CommentEnterTransition(this, toolbar, bottom_aty_love));
            getWindow().setSharedElementEnterTransition(buildShareElemEnterSet());
            getWindow().setSharedElementReturnTransition(buildShareElemReturnSet());

        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout=findViewById(R.id.layout);
        main_activity_love = (TextView) findViewById(R.id.main_activity_love);
        bottom_aty_love = (TextView) findViewById(R.id.bottom_aty_love);
    }
    /**
     * 分享 元素 进入动画
     * @return
     */
    private TransitionSet buildShareElemEnterSet() {
        TransitionSet transitionSet = new TransitionSet();
//
        Transition changePos = new ChangePosition();
        changePos.setDuration(300);
        changePos.addTarget(layout);
        transitionSet.addTransition(changePos);

        Transition revealTransition = new ShareElemEnterRevealTransition(layout);
        transitionSet.addTransition(revealTransition);
        revealTransition.addTarget(layout);
        revealTransition.setInterpolator(new FastOutSlowInInterpolator());
        revealTransition.setDuration(300);
////
        ChangeColor changeColor = new ChangeColor(getResources().getColor(R.color.colorAccent), Color.TRANSPARENT);
        changeColor.addTarget(layout);
        changeColor.setDuration(350);
        transitionSet.addTransition(changeColor);

        transitionSet.setDuration(900);

        return transitionSet;
    }

    /**
     * 分享元素返回动画
     * @return
     */
    private TransitionSet buildShareElemReturnSet() {
        TransitionSet transitionSet = new TransitionSet();

        Transition changePos = new ShareElemReturnChangePosition();
        changePos.addTarget(layout);
        transitionSet.addTransition(changePos);

        ChangeColor changeColor = new ChangeColor(Color.TRANSPARENT,getResources().getColor(R.color.colorAccent));
        changeColor.addTarget(layout);
        transitionSet.addTransition(changeColor);


        Transition revealTransition = new ShareElemReturnRevealTransition(layout);
        revealTransition.addTarget(layout);
        transitionSet.addTransition(revealTransition);

        transitionSet.setDuration(900);

        return transitionSet;
    }
    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
//        super.onBackPressed();
    }
}
