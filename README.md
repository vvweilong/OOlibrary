# OOlibrary
#### 控件库
* github上开源项目[android UI控件库](https://github.com/Trinea/android-open-project#十五ui-style)
* [android UI控件库2](https://github.com/wasabeef/awesome-android-ui)


#### app
    sample 项目 包含用法 和 测试工程

#### autoUpdatelib 
    初步的自动升级lib
    功能：1、根据提供最新的versionCode 判断是否需要更新
          2、根据给定的最新包下载地址 执行下载 并在下载完成后自动弹出安装界面
          
#### basePullrefreshlib 
    基础下拉刷新lib库 
#### cusplib 
    BLE低功耗蓝牙lib
#### MPChartLib 
    一个绚丽的图表lib 
#### okhttplib 
    对okhttp 的封装项目lib
    简单对 get、post、进行了封装对statecode 进行区分 
    分别回调在 
          requestSuccess（请求成功） 返回json 字符串
          serverError（500 服务器错误） 
          timeout（超时请求） 
          netError（本地网络错误） 
          paramerError（请求参数有误）
          unKnowError(未知错误)
    目前回调在子线程中
    
#### phonedevicetools 
    本地工具
    PhoneInforTool：主要是对本地信息的获取功能，如 app版本信息，手机系统信息、厂商信息、硬件参数、屏幕分辨率、缩放比例、像素密度；
    FileTool：主要是对本地文件进行操作 如 读取、存储、复制、删除文件，获取手机 SD卡路径、内部存储器路径、缓存路径等；
    SharePreferenceTool：轻量缓存工具 主要存储一下登录信息 或 采用json格式字符串保存一些 较为复杂的配置设置对象
    StringFormateTool: 字符串处理工具  主要是对 APP一些常用的 String检查，如格式 长度，是否全是数字 或字母 是否是汉字
    
    
    
#### qrzxing or zXingProj 
    二维码lib 
    主要是二维码的扫描获取功能 
