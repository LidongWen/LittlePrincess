# LittlePrincess 
# 博客地址：http://www.jianshu.com/p/9e1daed3a546
这篇文章的套路如下，带你装逼带你飞。

> 1. 概念
> 2. 如何使用
>     - 设置 相同的名称
>     - 启动 startActivity
>     - Fragment to Fragment
>     - 设置多组元素
> 3. 自定义过度动画
> 5. 源码解读
> 4. 如何兼容低版本

先看一下 效果吧！
![系统自带过渡动画](http://upload-images.jianshu.io/upload_images/1599843-a9ad0212658d39bd.gif?imageMogr2/auto-orient/strip) ![自定义过渡动画](http://upload-images.jianshu.io/upload_images/1599843-59d451708d9eb583.gif?imageMogr2/auto-orient/strip)



# 1.概念
在5.0之前，不同活动或片段的过渡是进入和退出动画,视图层次结构彼此独立的转换。   
过渡动画则是：把两个activity当中的相同元素关联起来做连贯的动画; 从而达到不同视图之间的元素关联达到 酷炫、爆炸等酷炫而又优雅地效果。  
引导用户视觉。 


> **共享元素类型：**  
>
> explode：从场景的中心移入或移出  
> slide：从场景的边缘移入或移出  
> fade：调整透明度产生渐变效果  


> **动画效果：**
> 
> changeBounds -  改变目标视图的布局边界  
> changeClipBounds - 裁剪目标视图边界   
> changeTransform - 改变目标视图的缩放比例和旋转角度  
> changeImageTransform - 改变目标图片的大小和缩放比例


# 2. 如何使用
## 2.1 设置主题
先要在系统主题中设置主题，或者在java代码中设置支持过渡动画的主题，我们在页面切换时才有过渡动画的效果，有以下两种方式：
### 2.1.1 方法一  
``` 
getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);  

// 设置一个exit transition
getWindow().setExitTransition(new Explode());
```
可以通过如下方法在代码总设置进入与退出时 Transition 效果：

- Window.setEnterTransition()：普通transition的进入效果  
- Window.setExitTransition()：普通transition的退出效果  
- Window.setSharedElementEnterTransition()：共享元素transition的进入效果   
- Window.setSharedElementExitTransition()：共享元素transition的退出效果  

### 2.1.2 方法二
在xml中设置 默认配置
```
<!-- Base application theme. -->
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="android:windowContentTransitions">true</item>
    
        <!-- 指定进入和退出transitions -->
    <item name="android:windowEnterTransition">@transition/explode</item>
    <item name="android:windowExitTransition">@transition/explode</item>
    
        <!-- 指定shared element transitions -->
    <item name="android:windowSharedElementEnterTransition">
        @transition/change_image_transform</item>
    <item name="android:windowSharedElementExitTransition">
        @transition/change_image_transform</item>
    ...
</style>
```


## 2.2 设置 相同的名称

共享元素切换，首先要保证两个页面内view的`transitionName`是一致的  
设置：`android：transitionName `  
如下：  
`MainActivity.xml`

```
<android.support.v7.widget.CardView
  ...>
      <ImageView
          android:id="@+id/ivProfile"
          android:transitionName="profile"
          android:scaleType="centerCrop"
          android:layout_width="match_parent"
          android:layout_height="160dp" />
      ...
</android.support.v7.widget.CardView>
```

`DetailActivity.xml`
```
<LinearLayout
  ...>
      <ImageView
          android:id="@+id/ivProfile"
          android:transitionName="profile"
          android:scaleType="centerCrop"
          android:layout_width="match_parent"
          android:layout_height="380dp" />
      ...
</LinearLayout>
```

## 2.3 启动 startActivity

第一个Acitivty执行页面跳转时需要加上 `ActivityOptionsCompat.toBundle()` 才会显示出过渡动画的效果

```
Intent intent = new Intent(this, DetailsActivity.class);
// Pass data object in the bundle and populate details activity.
intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);

//
getWindow().setExitTransition(new Explode());  //设置页面切换效果
//第一次进入时使用
getWindow().setEnterTransition(new explode);
//再次进入时使用
getWindow().setReenterTransition(new explode);

Transition ts = new ChangeClipBounds();         //设置元素动画
ts.setDuration(3000);
getWindow().setSharedElementExitTransition(ts);

ActivityOptionsCompat options = ActivityOptionsCompat.
    makeSceneTransitionAnimation(this, (View)ivProfile, "profile");    
startActivity(intent, options.toBundle());
```

从第二个Activity反转场景转换动画，请调用Activity.supportFinishAfterTransition（）而不是Activity.finish（），如果你使用了toolbar的返回按钮行为，也需要去覆盖它
```
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            supportFinishAfterTransition();
            return true;
    }
    return super.onOptionsItemSelected(item);
}
```

## 2.4 Fragment to Freagment
```
// Get access to or create instances to each fragment
FirstFragment fragmentOne = ...;
SecondFragment fragmentTwo = ...;
// Check that the device is running lollipop
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    // Inflate transitions to apply
    Transition changeTransform = TransitionInflater.from(this).
          inflateTransition(R.transition.change_image_transform);
    Transition explodeTransform = TransitionInflater.from(this).
          inflateTransition(android.R.transition.explode);
 
    // Setup exit transition on first fragment
    fragmentOne.setSharedElementReturnTransition(changeTransform);
    fragmentOne.setExitTransition(explodeTransform);

    // Setup enter transition on second fragment
    fragmentTwo.setSharedElementEnterTransition(changeTransform);
    fragmentTwo.setEnterTransition(explodeTransform);

    // Find the shared element (in Fragment A)
    ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);

    // Add second fragment by replacing first 
    FragmentTransaction ft = getFragmentManager().beginTransaction()
            .replace(R.id.container, fragmentTwo)
            .addToBackStack("transaction")
            .addSharedElement(ivProfile, "profile");
    // Apply the transaction
    ft.commit();
}
else {
    // Code to run on older devices
}
```

## 2.6 设置多组元素

如最开始的展示效果，可以知道，我们是可以设置多组元素的关联并且对每个元素可以执行不同的过渡动画的，它的方式如下：

```
Intent intent = new Intent(context, DetailsActivity.class);
intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
Pair<View, String> p1 = Pair.create((View)ivProfile, "profile");
Pair<View, String> p2 = Pair.create(vPalette, "palette");
Pair<View, String> p3 = Pair.create((View)tvName, "text");
ActivityOptionsCompat options = ActivityOptionsCompat.
    makeSceneTransitionAnimation(this, p1, p2, p3);
startActivity(intent, options.toBundle());
```
注意：默认情况下android.util.Pair将被导入，但是我们要选择android.support.v4.util.Pair类。

这里要避免元素过多导致分散注意力的动画，这样可能会失去过渡动画的意义。

# 3.自定义过度动画
本质上就是重写 Transition；
>  **套路如下：**
>  1、继承 Visibility 或者 Transition
>  2、自定义动画：实现 VIsibility/Transition内方法
>  3、在页面中将自定义Transition设入：           `getWindow().setEnterTransition`或者`setSharedElementEnterTransition`

看一下自定义的效果  
![自定义过渡动画](http://upload-images.jianshu.io/upload_images/1599843-59d451708d9eb583.gif?imageMogr2/auto-orient/strip)


## 3.1 一些概念：
 1.  Visibility extends Transition  页面切换转场动画类；
 2.  shareView 的移动的轨迹路径PathMotion类
```
ArcMotion arcMotion = new ArcMotion();
arcMotion.setMinimumHorizontalAngle(50f);
arcMotion.setMinimumVerticalAngle(50f);
```
例子：https://github.com/LidongWen/LittlePrincess/blob/master/app/src/main/java/com/wenld/littleprincess/transition/ChangePosition.java

## 3.2 自定义动画
继承 Visibility:  
```
public void captureStartValues(TransitionValues transitionValues) 这里保存计算动画初始状态的一个属性值
public void captureEndValues(TransitionValues transitionValues) 这里保存计算动画结束状态的一个属性值 

public Animator onAppear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues)
如果是进入动画 即显示某个 View 则会执行这个方法  
public Animator onDisappear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues)
如果是退出 ,  VIew 则会执行这个方法

```
例子：https://github.com/LidongWen/LittlePrincess/blob/master/app/src/main/java/com/wenld/littleprincess/transition/CommentEnterTransition.java


继承 Transition:  
```
    
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        //初始值保存
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) { 
    //结束值保存
    }
    
    createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues){
  //创建执行动画
  }
```
例子：https://github.com/LidongWen/LittlePrincess/blob/master/app/src/main/java/com/wenld/littleprincess/transition/ChangeColor.java

## 3.3 调用
```
            getWindow().setEnterTransition(new CommentEnterTransition(this, toolbar, bottom_aty_love));//设置
            getWindow().setSharedElementEnterTransition(buildShareElemEnterSet());//设置进入转换动画
            getWindow().setSharedElementReturnTransition(buildShareElemReturnSet());//设置退出转换动画
```

自定义过渡动画的Activity：https://github.com/LidongWen/LittlePrincess/blob/master/app/src/main/java/com/wenld/littleprincess/activity/LoveActivity.java

# 4. 源码解读

## 带着几个疑问：
-  Transition内方法在哪里被调用
-  看一个 系统 的 转换类写法 ，这边选择`Explode`  
     
1. 在 `TransitionKitKat` 内被调用
```
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        android.transition.TransitionValues internalValues =
                new android.transition.TransitionValues();
        copyValues(transitionValues, internalValues);
        mTransition.captureEndValues(internalValues);
        copyValues(internalValues, transitionValues);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        android.transition.TransitionValues internalValues =
                new android.transition.TransitionValues();
        copyValues(transitionValues, internalValues);
        mTransition.captureStartValues(internalValues);
        copyValues(internalValues, transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
            TransitionValues endValues) {
        android.transition.TransitionValues internalStartValues;
        android.transition.TransitionValues internalEndValues;
        if (startValues != null) {
            internalStartValues = new android.transition.TransitionValues();
            copyValues(startValues, internalStartValues);
        } else {
            internalStartValues = null;
        }
        if (endValues != null) {
            internalEndValues = new android.transition.TransitionValues();
            copyValues(endValues, internalEndValues);
        } else {
            internalEndValues = null;
        }
        return mTransition.createAnimator(sceneRoot, internalStartValues, internalEndValues);
    }
```

## Explode源码
1. Explode结构如下：

```
public class Explode extends Visibility {
    public Explode() {
        setPropagation(new CircularPropagation());
    }
    private void captureValues(TransitionValues transitionValues) {
    }

//初始值
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }
//结束值
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        captureValues(transitionValues);
    }
// 进入时被调用
    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view,
            TransitionValues startValues, TransitionValues endValues) {
            //执行动画
        return TranslationAnimationCreator.createAnimation(view, endValues, bounds.left, bounds.top,
                startX, startY, endX, endY, sDecelerate, this);
    }
//退出页面时调用
    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view,
            TransitionValues startValues, TransitionValues endValues) {

        return TranslationAnimationCreator.createAnimation(view, startValues,
                viewPosX, viewPosY, startX, startY, endX, endY, sAccelerate, this);
    }

    private void calculateOut(View sceneRoot, Rect bounds, int[] outVector) {
    }
}
```
 太简单了，，就是调用 `TranslationAnimationCreator.createAnimation` 创建动画....,我们上面的自定义过渡动画就是跟 这货学习到....



# 5. 兼容低版本
到了这里，        送大家一首歌：[感觉身体被掏空.......](http://music.163.com/#/song?id=422427950)
由于复杂度的原因，
低版本兼容还是说下思路吧，以后有时间或者有明确需求在去实现：
思路：
 1. 将第一个页面中共享元素View拿出来设置到`window`当中；
 2. 将第二个页面中保存第一个页面共享元素的数据，与第二个界面内的共享元素数据结合统一设置好进入动画与退出动画；
3. 执行进入动画；
4、执行退出动画

[大玩一发——MaterialDesign元素转换动画兼容低版本]()（有可能不会写！）

参考：
- https://github.com/codepath/android_guides/wiki/Shared-Element-Activity-Transition
- http://www.bkjia.com/Androidjc/900882.html
- http://www.jianshu.com/p/37e94f8b6f59
- http://www.jianshu.com/p/cc6cb29ec298
- http://www.open-open.com/lib/view/open1477879867267.html


## demo地址：[戳我!!!](https://github.com/LidongWen/LittlePrincess) 

-----

希望我的文章不会误导在观看的你，如果有异议的地方欢迎讨论和指正。
如果能给观看的你带来收获，那就是最好不过了。

##### 人生得意须尽欢, 桃花坞里桃花庵
##### 点个关注呗，[对，不信你点试试？](http://www.jianshu.com/users/99f514ea81b3/timeline)
