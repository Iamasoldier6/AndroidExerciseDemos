# AndroidExerciseDemos

### 更新日志：

- 2016.08.17

将原单独的 Demo 控件迁移到该项目空间中，即之前的竖直上下滑动来切换界面的 IS-VerticalViewPager 。

- 2016.08.07

更新了 Scroller 的 Demo ScrollerTest ，实现一个简易版的 ViewPager 。

- 2016.08.03

更新了 ScrollDemo ，侧重比较了 scrollTo() 和 scrollBy() ，scrollTo() 方法是让 View 相对于初始位置滚动某段距离，scrollBy() 方法是让 View 相对于当前的位置滚动某段距离。

- 2016.08.02

更新了改进过的 Toast Demo ，将 Toast 的调用封装成为一个接口，避免多次操作，跟随着多次弹出 Toast 提示，优化用户体验，即 ToastImproDemo 。

- 2016.08.02

更新了对话框 Demo ，即 DialogDemo 。

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



