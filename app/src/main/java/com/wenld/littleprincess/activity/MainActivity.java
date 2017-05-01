package com.wenld.littleprincess.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RadioGroup;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.wenld.littleprincess.GridItemDecoration;
import com.wenld.littleprincess.InfoDao;
import com.wenld.littleprincess.InfoHepler;
import com.wenld.littleprincess.R;
import com.wenld.littleprincess.transition.CommentEnterTransition;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

public class MainActivity extends BaseActivity {
    public RecyclerView rlvAtyFilter;
    CommonAdapter adapter;
    RadioGroup group;
    FloatingActionButton fab;
    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fab = (FloatingActionButton) findViewById(R.id.fab);


        rlvAtyFilter = (RecyclerView) findViewById(R.id.rlv_aty_main);
        adapter = new CommonAdapter<InfoDao>(this, R.layout.list_items, InfoHepler.getList()) {

            @Override
            protected void convert(ViewHolder holder, InfoDao infoDao, int position) {

                holder.setImageResource(R.id.iv_photo, infoDao.imgResId);
                holder.setText(R.id.tv_title, infoDao.name);
                holder.setText(R.id.tv_memo, infoDao.memo);

                ViewHelper.setScaleX(holder.itemView, 0.8f);
                ViewHelper.setScaleY(holder.itemView, 0.8f);
                ViewPropertyAnimator.animate(holder.itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
                ViewPropertyAnimator.animate(holder.itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
            }
        };
        rlvAtyFilter.setLayoutManager(new GridLayoutManager(this, 2));
        rlvAtyFilter.addItemDecoration(new GridItemDecoration(dip2px(this, 5)));
        rlvAtyFilter.setAdapter(adapter);

        group = (RadioGroup)this.findViewById(R.id.radioGroup_0);
    }

    @Override
    protected void initData() {

    }


    protected void initListener() {
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<InfoDao>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, InfoDao infoDao, int position) {
//                changeShareElementEnterTransiton(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("position", position);
                ViewHolder viewHolder = (ViewHolder) holder;
                Pair<View, String> p1 = Pair.create(viewHolder.getView(R.id.iv_photo), getResources().getString(R.string.transition_img));
//                Pair<View, String> p2 = Pair.create(viewHolder.getView(R.id.tv_title), getResources().getString(R.string.transition_title));
//                Pair<View, String> p3 = Pair.create(viewHolder.getView(R.id.tv_memo), getResources().getString(R.string.transition_memo));
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1
                              /*  , p2, p3*/);//与xml文件对应
                ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, InfoDao o, int position) {
                return false;
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    switch (checkedId) {
                        case R.id.radio_1: getWindow().setExitTransition(new Explode());
                            break;
                        case R.id.radio_2:
                            getWindow().setExitTransition(new Slide());
                            break;
                        case R.id.radio_3:
                            getWindow().setExitTransition(new Fade());
                            break;
                        default:
                            getWindow().setExitTransition(null);
                            break;
                    }
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoveActivity.class);
                ActivityOptionsCompat options;
                Pair<View, String> p1=Pair.create((View)fab, getResources().getString(R.string.action_fab));
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1);
                ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
//                Snackbar.make(view, "My Little Princess , I am love you so much!", Snackbar.LENGTH_LONG)
//                        .setAction("My Heart", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    int transitionTime=30000;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_changeBounds) {
            Transition ts = new ChangeBounds();
            ts.setDuration(transitionTime);
            //设置忽略
//            ts.addTarget()
            getWindow().setSharedElementEnterTransition(ts);
            return true;
        }
        if (id == R.id.action_ChangeTransform) {
            Transition ts = new ChangeTransform();
            ts.setDuration(transitionTime);
            getWindow().setSharedElementEnterTransition(ts);
            return true;
        }
        if (id == R.id.activon_changeClipBounds) {
            Transition ts = new ChangeClipBounds();
            ts.setDuration(transitionTime);
            getWindow().setSharedElementEnterTransition(ts);
            return true;
        }
        if (id == R.id.action_changeImageTransform) {
            Transition ts = new ChangeImageTransform();
            ts.setDuration(transitionTime);
            getWindow().setSharedElementEnterTransition(ts);
            return true;
        }
        if (id == R.id.action_null) {
            getWindow().setSharedElementEnterTransition(null);
            return true;
        }
        if(id==R.id.customTransform){
            getWindow().setEnterTransition(new CommentEnterTransition(this, null, null));
            return true;
        }
//        if (id == R.id.activon_explode) {
//            getWindow().setExitTransition(new Explode());
//            return true;
//        }
//        if (id == R.id.action_slide) {
//
//            getWindow().setExitTransition(new Slide());
//            return true;
//        }
//        if (id == R.id.activon_fade) {
//            getWindow().setExitTransition(new Fade());
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
