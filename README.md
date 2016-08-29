# AndroidExerciseDemos

### 更新日志：

- 2016.08.28

使用 ZXing ，更新了扫描二维码功能的 ScannerTest 。

- 2016.08.26

更新了下拉刷新的实现 PullDownRefreshDemo ，可显示刷新后的文字与时间描述，可方便做后续的扩展。

- 2016.08.25

基于 360FloatWindowDemo ，更新了仿照 QQ 手机管家实现小火箭效果的 QQRocketDemo ，即实现小火箭加速功能，将小火箭拖动到发射台上发射就会出现一个火箭升空的动画。

- 2016.08.24

更新了简易的仿照 360 手机卫士悬浮窗效果的 360FloatWindowDemo 。`I. 主界面只有一个简单的按钮，点击按钮后，Activity 被关闭，小悬浮窗显示在桌面上，显示着当前内存使用的百分比；II. 小悬浮窗是可以自由拖动的，若打开了其它的应用程序，小悬浮窗会自动隐藏，回到桌面后小悬浮窗又会显示出来；III. 若点击小悬浮窗，会弹出大悬浮窗，大悬浮窗只有两个按钮，展示的时候手机的所有其它程序是不可点击的，因为焦点都在悬浮窗上。点击返回按钮会重新展示小悬浮窗，点击关闭悬浮窗按钮，Service 也会一起停掉。`

- 2016.08.23

更新了照片墙的实现 PictureWallDemo ，拖动着，有时会出现 OOM ，待优化中。文章见郭霖的[ Android 照片墙应用实现，再多的图片也不怕崩溃 ](http://blog.csdn.net/guolin_blog/article/details/9526203) 。

- 2016.08.22

更新圆环形进度更新圈，即 CusProgressCircle 。

- 2016.08.21

更新了简易布局 SimpleLayoutDemo ，自定义一个布局，深刻地理解 onLayout() 的过程，文章见郭霖的[ Android 视图绘制流程完全解析，带你一步步深入了解 View (二) ](http://blog.csdn.net/guolin_blog/article/details/16330267) ；

更新了简易绘制 CanvasDemo ，理解 Canvas 与 onDraw() ；

更新了简易的自定义计数器 CounterDemo ，文章见郭霖的[ Android 自定义 View 的实现方法，带你一步步深入了解 View (四)
 ](http://blog.csdn.net/guolin_blog/article/details/17357967)；
 
更新了简易的组合控件，即自定义标题栏 CustomTitleDemo ；

更新了继承控件 ExtendListViewDemo ，是对 ListView 的扩展，在 ListView 上滑动就可以显示出一个删除按钮，点击按钮就会删除相应的数据。

- 2016.08.20

更新了分析 LayoutInflater 原理的 LayoutInflaterDemo ，文章见郭霖的[ Android LayoutInflater 原理分析，带你一步步深入了解 View (一) ](http://blog.csdn.net/guolin_blog/article/details/12921889) 。

- 2016.08.18

更新自定义图片轮播控件，类似淘宝客户端中图片滚动播放器的效果，即 PicAutoSlideDemo（不过图片滚动到最后一张时，是迅速地回滚到第一张图片，再从头开始滚动。视觉体验差一些，`待优化`）。

- 2016.08.17

将 Repositories 中原单独的竖直上下滑动来切换界面的 IS-VerticalViewPager 控件迁移到该项目空间中，即新命名的 VerticalViewPagerDemo（本项目空间中的 `IS-VerticalViewPager` 是空文件夹，Deprecated）。

- 2016.08.07

更新了 Scroller 的 Demo ScrollerTest ，实现一个简易版的 ViewPager 。

- 2016.08.03

更新了 ScrollDemo ，侧重比较了 scrollTo() 和 scrollBy() ，scrollTo() 方法是让 View 相对于初始位置滚动某段距离，scrollBy() 方法是让 View 相对于当前的位置滚动某段距离。

- 2016.08.02

更新了对话框 Demo ，即 DialogDemo ；

更新了改进过的 Toast Demo ，将 Toast 的调用封装成为一个接口，避免多次操作，跟随着多次弹出 Toast 提示，优化用户体验，即 ToastImproDemo 。

- 2016.08.01

更新了博客《 Android 事件传递机制研究综述(一)》的 Demo ，即 BlogEventDemo 。

- 2016.06.17

更新了徐宜生《 Android 群英传》中 Activity 过渡动画 Demo ，即 ActivityTransiDemo ；

视图状态改变显示动画效果的 Demo ，即 AnimSelecDemo ；

5.0 系统下几种常用的 Notification 集合 Demo ，即 NotificationDemo 。

- 2016.06.16

更新了徐宜生《 Android 群英传》中裁剪效果 Demo ，即 ClipDemo 。

- 2016.06.15

更新了徐宜生《 Android 群英传》中图形特效处理的 Demo ，即 ImagMatrixDemo ；

使图片实现旗帜飞扬效果的 Demo ，即 FlagBitmapDemo ；

画笔特效处理的刮刮卡效果 Demo ，即 ScratchCardDemo ；

实现渲染 TileMode 效果的 Demo ，即 RoundShaderDemo ；

实现一个具有倒影效果的图片 Demo ，即 ReflectViewDemo ；

笔触效果绘制一个路径的 PathEffect Demo ，即 PathEffectDemo ；

使用 SurfaceView 绘制正弦曲线的 Demo ，即 SurfaceSinDemo ；

使用 SurfaceView 实现一个绘图板 Demo ，即 PaintBoardDemo 。

- 2016.06.14

更新了徐宜生《 Android 群英传》中模拟一个 4 * 5 的颜色矩阵 Demo ，即 SimuColorMatrixDemo ；

常用图像像素点处理效果的 Demo ，即 PixelsEffectDemo 。

- 2016.06.13

更新了徐宜生《 Android 群英传》中通过滑动 SeekBar ，实时修改图像色调、饱和度、亮度的 Demo ，即 SBarColorDemo 。

- 2016.06.12

更新了徐宜生《 Android 群英传》中计时器动画 Demo ，即 TimerAnimDemo ；

下拉展开动画 Demo ，即 PullDownAnimDemo ;

仪表盘绘制 Demo ，即 ClockCanvasDemo ；

初识 Layer 图层 Demo 。

- 2016.06.11

更新了徐宜生《 Android 群英传》中绘制半圆的 SVG 图形 Demo ，即 HalfCirSvgDemo ；

点击图像，两条横线 “二” 从中间折断并向中间折起，形成 “X” 的线图动画 Demo ，即 LineAnimSvgDemo ;

模拟地、月、日三个星体绕行轨迹的三球仪 Demo ，即 ThreeBallSvgDemo ；

绘制搜索框中一个放大镜的轨迹动画 Demo ，即 SearchBarSvgDemo ;

点击小红点，弹出菜单的灵动菜单 Demo ，即 PopMenuAnimDemo 。

- 2016.06.10

更新了徐宜生《 Android 群英传》中使用 Bmob 创建移动后端服务的 Demo ，即 BmobSerDemo 。(`注：1.建议仍然使用书上 Demo 提供的 SDK ，去官网下载的最新 SDK 使用时，报“java.lang.UnsatisfiedLinkError”，并且不好解决；2.使用真机运行，模拟器运行报异常；3.使用自己创建应用时的 Application ID ，并且 AndroidManifest.xml 文件需配置好；4.测试应用，如 "name" 和 "feedback" 栏分别输入 "zebron" 和 “good” ，依次测试就好；4.设置 Notification 相关属性下面的代码，官方已弃用，使用新代码代替，即`

`Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("Bmob Test");
            builder.setContentText(message);
            builder.setSmallIcon(R.drawable.ic_launcher);
            Notification notification = builder.getNotification();
            manager.notify(R.drawable.ic_launcher, notification);`)
 
使用 ViewDragHelper 的仿 QQ 侧滑菜单的 Demo ，即 QQDragDemo ;

一些简单的视图动画 Demo ，即 ViewAnimDemo ;

自定义动画，模拟电视机关闭的效果和类似推门的 3D 动画效果，即 CustomAnimDemo 。

- 2016.06.09

更新了徐宜生《 Android 群英传》中，实现类似在 PC 中某些音乐播放器上根据音量大小显示的音频条形图 Demo ，即 VolumeViewDemo ;

实现类似 Android 原生控件 ScrollView 的自定义 ViewGroup Demo ，即 CusScrollViewDemo ；

事件的拦截、分发及处理 Demo ，即 EventRejectDemo 。

- 2016.06.06

更新了徐宜生《 Android 群英传》中弧线展示图 Demo ，即 CirProViewDemo 。

- 2016.06.05

更新了徐宜生《 Android 群英传》中 自定义 View 一节对现有控件进行拓展的 Demo ，即 CusTextViewDemo ;

动态的文字闪动效果 Demo ，即 ShiTextViewDemo ;

创建复合控件，生成固定 UI 模版的 Demo , 即 TopBarDemo 。
(`注：1.布局文件自定义部分以 custom 为开头；2.由于 AS 和 API 更新等的缘故，按照书上所做，会提示 Attribute "title" and "titleTextColor" has already been defined , 那就在定义 attrs.xml 文件时重新命名这两个，如改成 "atitle" 和 "atitleTextColor" ，相应用到的地方亦作修改，可以解决 bug ，且不影响最终结果。`)

- 2016.06.02

临睡前完成了徐宜生《 Android 群英传》中 View 的测量 Demo ，即 ViewMeasureDemo 。

- 2016.05.23

更新了徐宜生《 Android 群英传》中 Android Scroll 分析章节的简易滑动 Demo ，即 DragViewDemo 。

- 2016.05.20

更新了徐宜生《 Android 群英传》中 RecyclerView 的使用 Demo ，即 RecyclerViewDemo ，具体分析见博客[ 从 ListView 到 RecyclerView 的用法浅析 ](http://iamasoldier6.com/2016/05/21/从-ListView-到-RecyclerView-的用法浅析/)。

- 2016.05.19

更新了徐宜生《 Android 群英传》中自动显示与隐藏布局 ListView 的使用 Demo ，即 ScroDisHidListViewDemo ，需要在真机上运行，模拟器体现不出效果；

模仿微信聊天布局 ListView 的使用 Demo ，即 ChatListViewDemo ；

动态改变点击 Item 布局的 ListView 使用 Demo ，即 FocusListViewDemo 。

- 2016.05.18

更新了[ 徐宜生](https://github.com/xuyisheng)《 Android 群英传》中动态修改 ListView 的使用 Demo ，即 AcModiListViewDemo ; 

使 ListView 具有弹性的使用 Demo ，即 FlexListViewDemo 。

- 2016.05.17

更新了郭霖《第一行代码》中 ListView 的使用 Demo ，即 ListViewDemo ，具体分析见博客[ 从 ListView 到 RecyclerView 的用法浅析 ](http://iamasoldier6.com/2016/05/21/从-ListView-到-RecyclerView-的用法浅析/)。

- 2016.05.05

关于 Volley 自定义 Request ，更新了 XMLRequest 的使用 Demo 和 GsonRequest 的使用 Demo 。具体分析见博客[ Android Volley 研究综述（三）](http://iamasoldier6.com/2016/05/05/Android-Volley-研究综述（三）/)。

- 2016.05.04

关于 Volley 加载网络图片，更新了 ImageRequest 的使用 Demo ，ImageLoader 的使用 Demo ，ImageLoaderCache 的使用 Demo 以及 NetworkImageView 的使用 Demo 。具体分析见博客[ Android Volley 研究综述（二）](http://iamasoldier6.com/2016/05/04/Android-Volley-研究综述（二）/)。

- 2016.04.29

之前看官方 [Android Training](http://developer.android.com/training/index.html) 看到了 [Volley](http://developer.android.com/training/volley/index.html) 一节，于是最近，做公司项目需求之余，将 Volley 着重研究了下。更新了 StringRequest 的使用 Demo 和 JsonRequest 的使用 Demo 。具体分析见博客 [ Android Volley 研究综述（一）](http://iamasoldier6.com/2016/04/30/Android-Volley-研究综述（一）/)。

- 2016.04.20

去年，学习[ 郭霖](http://blog.csdn.net/guolin_blog)《第一行代码》时，就接触了部分 Git 命令行操作，无奈许久未用，已淡忘。最近，工作之余，在研读官方 [Android Training ](http://developer.android.com/training/index.html)，已至`Adding Animations`一节，萌生从今往后，将 Android 学习过程中个人自觉有价值的代码统一放 Github 上的一个仓库里，于是，今天花了小半天将[ 廖雪峰的 Git 教程 ](http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)学习了一遍。一来练手学习过程中出现的有些许价值的 Android 小 Demo ；二来熟悉强大的 Git 和 Github ，增加 GitHub 上的活跃度；三来供有需要的人参考，与人分享交流。大抵就这些。



