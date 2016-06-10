# AndroidExerciseDemos

### 更新日志：

- 2016.06.10

更新了徐宜生《 Android 群英传》中使用 Bmob 创建移动后端服务的 Demo ，即 BmobSerDemo 。(`注：1.建议仍然使用书上 Demo 提供的 SDK ，去官网下载的最新 SDK 使用时，报“java.lang.UnsatisfiedLinkError”，并且不好解决；2.使用真机运行，模拟器运行报异常；3.使用自己创建应用时的 Application ID ，并且 AndroidManifest.xml 文件需配置好；4.测试应用，如 "name" 和 "feedback" 栏分别输入 "zebron" 和 “good” ，依次测试就好；4.设置 Notification 相关属性下面的代码，官方已弃用，使用新代码代替，即`

`Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("Bmob Test");
            builder.setContentText(message);
            builder.setSmallIcon(R.drawable.ic_launcher);
            Notification notification = builder.getNotification();
            manager.notify(R.drawable.ic_launcher, notification);`)

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



