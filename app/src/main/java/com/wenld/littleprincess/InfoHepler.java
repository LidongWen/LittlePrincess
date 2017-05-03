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
        list.add(new InfoDao("小公主", "" +
                "西施越溪女，出自苎萝山。\n" +
                "秀色掩今古，荷花羞玉颜。\n" +
                "浣纱弄碧水，自与清波闲。\n" +
                "皓齿信难开，沉吟碧云间。\n" +
                "勾践徵绝艳，扬蛾入吴关。\n" +
                "提携馆娃宫，杳渺讵可攀。\n" +
                "一破夫差国，千秋竟不还。"
                , "http://upload-images.jianshu.io/upload_images/1599843-49d239f53a802c29.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));

        list.add(new InfoDao("飘飘仙子", "届笑春桃兮，\n云堆翠髻；\n唇绽樱颗兮，\n榴齿含香"
                , "http://upload-images.jianshu.io/upload_images/1599843-dda6c84743b8a3db.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));
        list.add(new InfoDao("笑颜如花绽，玉音婉转流", "      北方有佳人，绝世而独立。\n" +
                "\n" +
                "      一顾倾人城，再顾倾人国。\n" +
                "\n" +
                "      宁不知倾城与倾国？佳人难再得！"
                , "http://upload-images.jianshu.io/upload_images/1599843-0f033f7372371375.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));

        list.add(new InfoDao("\"此女只应天上有，人家难得几回闻！\"", "\n此女只应天上有，人家难得几回闻！\n\n云想衣裳花想容，春风拂槛露华浓。\n\n 若非群玉山头见，会向瑶台月下逢。 "
                , "http://upload-images.jianshu.io/upload_images/1599843-5314fea8f12c5f63.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));


        list.add(new InfoDao("回眸一笑百魅生，六宫粉黛无颜色", "汉皇重色思倾国，御宇多年求不得。\n" +
                "杨家有女初长成，养在深闺人未识。\n" +
                "天生丽质难自弃，一朝选在君王侧。\n" +
                "回眸一笑百媚生，六宫粉黛无颜色。\n" +
                "春寒赐浴华清池，温泉水滑洗凝脂。\n" +
                "侍儿扶起娇无力，始是新承恩泽时。\n" +
                "云鬓花颜金步摇，芙蓉帐暖度春宵。\n" +
                "春宵苦短日高起，从此君王不早朝。\n" +
                "承欢侍宴无闲暇，春从春游夜专夜。 "
                , "http://upload-images.jianshu.io/upload_images/1599843-ec894dd70d401625.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));

        list.add(new InfoDao("执子之手，与子偕老", "击鼓其镗，踊跃用兵。土国城漕，我独南行。 　　\n" +
                "\n" +
                "\n" +
                "从孙子仲，平陈与宋。　　\n" +
                "不我以归，忧心有忡。 " +
                "\n" +
                "爰居爰处？爰丧其马？ 　　\n" +
                "于以求之？于林之下。" +
                "\n" +
                "死生契阔，与子成说。　　\n" +
                "执子之手，与子偕老。 " +
                "\n" +
                "于嗟阔兮，不我活兮。\n" + "于嗟洵兮，不我信兮。"
                , "http://upload-images.jianshu.io/upload_images/1599843-630b771feb023dbd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));
        list.add(new InfoDao("秀色掩今古，荷花羞玉颜", "　　西施越溪女，出自苎萝山。\n" +
                "　　秀色掩今古，荷花羞玉颜。\n" +
                "　　浣纱弄碧水，自与清波闲。\n" +
                "　　皓齿信难开，沉吟碧云间。\n" +
                "　　勾践徵绝艳，扬蛾入吴关。\n" +
                "　　提携馆娃宫，杳渺讵可攀。\n" +
                "　　一破夫差国，千秋竟不还。 "
                , "http://upload-images.jianshu.io/upload_images/1599843-16ae9a99908c67f5.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));


    }

    public static List<InfoDao> getList() {
        return list;
    }
}
