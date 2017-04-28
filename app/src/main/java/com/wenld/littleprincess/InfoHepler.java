package com.wenld.littleprincess;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Author: wenld on 2017/4/28 14:45.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class InfoHepler {
    private static List<InfoDao> list = new ArrayList<>();

    static {
        for (int i = 0; i < 3; i++) {
            list.add(new InfoDao("小公主", "" +
                    "西施越溪女，出自苎萝山。\n" +
                    "秀色掩今古，荷花羞玉颜。\n" +
                    "浣纱弄碧水，自与清波闲。\n" +
                    "皓齿信难开，沉吟碧云间。\n" +
                    "勾践徵绝艳，扬蛾入吴关。\n" +
                    "提携馆娃宫，杳渺讵可攀。\n" +
                    "一破夫差国，千秋竟不还。"
                    , R.mipmap.our));

            list.add(new InfoDao("飘飘仙子", "届笑春桃兮，\n云堆翠髻；\n唇绽樱颗兮，\n榴齿含香"
                    , R.mipmap.princess));
            list.add(new InfoDao("笑颜如花绽，玉音婉转流", "北方有佳人，\n绝世而独立 "
                    , R.mipmap.smile));
        }
    }

    public static List<InfoDao> getList() {
        return list;
    }
}
