/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wenld.littleprincess;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author drakeet
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    RecyclerView.LayoutManager layoutManager;
    private SpanSizeLookup spanSizeLookup;


    public GridItemDecoration(int space) {
        this.space = space;
    }


    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanSizeLookup = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
//            outRect.left = space;
            outRect.top = space;
            if ((position + 1) % spanCount != 0) {
                if ((position) % spanCount == 0) {
                    outRect.right = space / 2;
                    outRect.left=space;
                } else {
                    outRect.left = space / 2;
                }
            } else {
                outRect.left = space / 2;
                outRect.right = space;
            }
        } else {

        }

    }
}
