package com.wenld.littleprincess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p/>
 * Author: wenld on 2017/4/25 17:03.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {


    public static final int LINEAR_DIVIDER_HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int LINEAR_DIVIDER_VERTICAL = LinearLayoutManager.VERTICAL;


    @IntDef({
            LINEAR_DIVIDER_HORIZONTAL,
            LINEAR_DIVIDER_VERTICAL
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    @Orientation
    private int mOrientation;
    private Drawable mDivider;
    private int mDividerHeight;

    public LinearDividerItemDecoration(Context context, @Orientation int orientation) {
        resolveDivider(context);
        setOrientation(orientation);
    }

    private void resolveDivider(Context context) {
        mDivider = new ColorDrawable(0xFFEEEEEE);
        mDividerHeight = dip2px(context, 5);
    }

    public void setOrientation(@Orientation int orientation) {
        mOrientation = orientation;
    }

    public LinearDividerItemDecoration setmDividerHeight(int dividerHeight) {
        this.mDividerHeight = dividerHeight;
        return this;
    }

    public LinearDividerItemDecoration setDivider(Drawable divider) {
        this.mDivider = divider;
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LINEAR_DIVIDER_VERTICAL) {
            drawVerticalDividers(c, parent);
        } else {
            drawHorizontalDividers(c, parent);
        }
    }

    public void drawVerticalDividers(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final Drawable divider = getDivider(parent, params.getViewAdapterPosition());
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDividerHeight;

//            mDividerOffsets.put(params.getViewAdapterPosition(), divider.getIntrinsicHeight());

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    public void drawHorizontalDividers(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final Drawable divider = getDivider(parent, params.getViewAdapterPosition());
            final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDividerHeight;

//            mDividerOffsets.put(params.getViewAdapterPosition(), divider.getIntrinsicHeight());

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int adapterPosition = parent.getChildAdapterPosition(view);
        if (adapterPosition == parent.getAdapter().getItemCount() - 1) {
            return;
        }

        if (mOrientation == LINEAR_DIVIDER_VERTICAL) {
            outRect.set(0, 0, 0, mDividerHeight);
        } else {
            outRect.set(0, 0, mDividerHeight, 0);
        }
    }

    private Drawable getDivider(RecyclerView parent, int adapterPosition) {
//        final RecyclerView.Adapter adapter = parent.getAdapter();
//        final int itemType = adapter.getItemViewType(adapterPosition);
//        final DrawableCreator drawableCreator = mTypeDrawableFactories.get(itemType);
//
//        if (drawableCreator != null) {
//            return drawableCreator.create(parent, adapterPosition);
//        }

        return mDivider;
    }

//    public void registerTypeDrawable(int itemType, DrawableCreator drawableCreator) {
//        mTypeDrawableFactories.put(itemType, drawableCreator);
//    }

//    public interface DrawableCreator {
//        Drawable create(RecyclerView parent, int adapterPosition);
//    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
