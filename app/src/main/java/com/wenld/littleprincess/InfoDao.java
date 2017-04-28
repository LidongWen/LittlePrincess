package com.wenld.littleprincess;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * <p/>
 * Author: wenld on 2017/4/28 14:43.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class InfoDao implements Serializable{
    public String name;
    public String memo;
    public String imgUrl;
    @DrawableRes
    public int imgResId;

    public InfoDao(String name, String memo, String imgUrl) {
        this.name = name;
        this.memo = memo;
        this.imgUrl = imgUrl;
    }

    public InfoDao(String name, String memo, int imgResId) {
        this.name = name;
        this.memo = memo;
        this.imgResId = imgResId;
    }
}
