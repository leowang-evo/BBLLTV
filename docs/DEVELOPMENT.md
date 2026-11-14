# 开发、构建与验证

[返回项目首页](../README.md)。以下命令均在项目根目录执行，历史验证结论保留其对应版本范围；144 仅进行了品牌与关于页的针对性模拟器检查。

144 更新：应用名称统一为 BBLLTV，替换启动图标、圆形/自适应图标、电视启动横幅及关于页图标。“关于应用”补充项目介绍、原作者致谢及原项目、新项目地址，并调整版面以完整显示内容。仅修改品牌、关于页资源和应用标签，不更改播放、账号或导航逻辑。图标来源见 `docs/BRANDING-144.md`。

143 修复：首页内容区返回先回到当前顶部选项卡，再按返回才进入退出确认；番剧/影视索引首次进入定位首行与第一张海报，详情返回保留浏览位置。详情见 `docs/FIXES-143.md`。使用 `--home-navigation` 测试四个首页栏目、索引首屏、详情返回及筛选；`--observe-navigation` 仅记录旧版失败，不计作回归通过。`--reuse-installed` 只在安装包哈希完全匹配时跳过重复安装。

本项目用恢复或重新实现的 Java 业务源码，以及真实发布的第三方依赖构建 Android TV 客户端。正常构建不读取原 APK、原 DEX、声明桩、旧混合编译工程或从原 APK 提取的原生库。

这是可维护的功能重建工程，并非开发者原始源码逐字还原。当前包含 app 177、domain 306、network 13 个 Java 文件；ASimpleCache 的 1 个文件来自其公开上游源码，带有许可证和来源记录。其他 SDK 使用 Google Maven、Maven Central 和限定的 JitPack 依赖。AkDanmaku 的 libgdx.so 来自其官方发布 AAR，含电视所需 armeabi-v7a。

## 构建

需要 JDK 21、Python 3、Android SDK Platform 36 及对应构建工具。设置 JAVA_HOME、ANDROID_HOME 后运行：

```sh
python3 tools/run_gradle.py :app:assembleDebug
```

脚本优先使用本机 Gradle 9.5.0，否则使用项目携带的标准 Gradle Wrapper。Wrapper 分发包配置了官方 SHA256。首次构建需要网络下载公开依赖。产物为 `app/build/outputs/apk/debug/app-debug.apk`，默认包名 `com.damon.bblab.standalone`。

默认构建不需要仓库提供签名私钥，AGP 会使用本机自动生成的调试密钥。为覆盖此前安装的“BBLL Java恢复验证”，需由安装者在本地保留原有的 `keys/reconstruction-debug.keystore`，保持同一验证签名并运行：

```sh
python3 tools/run_gradle.py :app:exportStandaloneBuildAudit -PvalidationApplicationId=com.damon.bblab -PvalidationVersionCode=144
python3 tools/audit_standalone_apk.py
```

`keys/reconstruction-debug.keystore` 不随源码公开，也不会进入源码归档。缺少它时，新构建使用本机调试签名，不能直接覆盖不同签名的既有安装；请勿为覆盖安装而随意卸载并丢失数据。正式分发应配置由维护者保管的发布签名。更新源尚未配置；设置页会明确显示这一状态。

## 验证边界

`exportStandaloneBuildAudit` 记录真实 JavaCompile（含 bootstrapClasspath）、DEX、原生库及打包输入和哈希；审计脚本检查源文件新鲜度、依赖边界、APK 隐藏字节码及原生库与官方 AAR 的逐字节一致性。

`tools/verify_apk_isolation.py` 导出纯源码到外部临时目录，仅复用下载的公开 Maven 工件，不复用本项目的 class/DEX、Gradle transforms 或构建缓存，并重新编译整个 APK。隔离报告、输入清单及最终 APK 需一起保留。

播放器已在 Android 16 模拟器与 Android 11 雷鸟电视运行生成的 H.264 视频，并验证遥控器与菜单断言。网络测试与设备页面测试分开计数，详情见随产物提供的验证记录。真实服务端的接口限制、会员内容、账号写操作与互动视频分支必须分别记录验证结果，不能用本地媒体或按钮可见代替。

## 源码结构

- `app`：`ui/screen` 管理页面生命周期、分页及异步结果；`ui/navigation` 管理跳转与标签页；`ui/home`、`catalog`、`search`、`user`、`auth`、`live`、`settings` 保存各功能页面；`ui/adapter` 与 `ui/holder` 绑定列表；`ui/playback` 与 `playback` 协调播放器、媒体请求、字幕和弹幕；`platform` 适配 Android 与外部库。
- `domain`：API 数据模型及协议类型。保持服务端字段和既有序列化契约。
- `network`：类型化 API、签名、Cookie、响应转接、业务错误和直播 WebSocket。67 个转发回调共用一个实现，XML 和原始页面解析保留独立处理。
- `vendor/asimplecache`：有来源记录的上游缓存源码；其余第三方组件通过外部依赖使用。

播放控件的焦点路径由 `PlayerFocusNavigator` 维护。`RecyclerFocus` 只执行同一列表的最新焦点请求。播放设置通过统一的选项渲染和返回栈恢复原选项，动画结束不会覆盖用户已移动的焦点。搜索页面统一分页、丢弃过期响应，并显示空态、受限提示和手动重试。

132 个应用类完成职责命名与分包。历史源码与新路径的对应关系在 `docs/source-renames.tsv`。数据模型、协议和外部依赖的稳定字段名保持兼容。升级时通过页面状态版本迁移丢弃旧类名对应的 Fragment 状态，账号数据保留。

## 重复测试

```sh
python3 tools/check_network.py
python3 tools/verify_player_remote.py --device emulator-5566
BBLL_TEST_EMULATOR=emulator-5566 python3 tools/verify_application_ui.py guest
```

网络检查只访问本机回环服务。播放器检查需额外安装 ffmpeg，会生成 20 秒测试视频，通过真实 Media3 播放器执行按键和选项回调检查。默认目标是验证包 `com.damon.bblab`；先按上述验证包命令构建。页面脚本通过 `BBLL_TEST_EMULATOR` 选择设备，结果保存在 `app/build/ui-regression`。已登录读取用例可运行 `account`；`qr` 检查未登录的二维码与登录入口。

已授权的雷鸟电视检查可运行 `python3 tools/verify_player_remote.py --device 电视地址:5555 --allow-tv-installer`。该选项只支持已识别的 TCL 设备，安装后立即恢复安装验证器原状态，不卸载应用或清除账号数据。测试 APK 仅用于本地验证。

真实媒体检查可加 `--real-media`：使用当前已登录账号，读取热门与直播推荐，实际解码点播，测试遥控器暂停、进度条跳转、恢复、设置返回、会员可用清晰度、音频/AVC 编码、直播画质及后台恢复。该模式不依赖 ffmpeg；正常播放可能更新历史记录，不执行点赞、投币或关注操作。完整范围与未覆盖项见 `docs/VALIDATION.md`。


补充检查：`--rankings` 逐项检查音乐、游戏、知识、美食、纪录片的 API 内容、页面绑定和焦点；`--extended` 使用真实内容检查字幕、多 P、番剧入口、互动分支、弹窗、4K/HDR/杜比及音轨、续播和退出后的迟到响应。可用 `--case subtitles|multipart|series|interactive|dialogs|highProfiles|continuation|specialProfiles|lifecycle` 单独复测。受限内容以结果中的 `LIMITED` 单独记录，不能计为实际播放通过。账号写入接口在本机假账号 HTTP 服务中检查，不消耗真实账号硬币。

## 140 版刷新、菜单与返回

首页推荐、热门及排行榜的视频卡片采用后台差异计算，仅更新插入、删除、移动和内容变化的卡片。同一视频的焦点及纵向位置按视频身份恢复；手动刷新回到顶部，自动加载保留位置。最新一次刷新结果生效，分页追加到待提交的新列表，卸载页面后丢弃迟到结果。

弹幕总开关、顶部和底部弹幕在当前行一次确认即可切换；画质、速度、字幕、编码、音质及画面比例菜单默认聚焦当前可选值。播放返回依次退出设置子菜单、设置面板、控制区和播放页；从上次进度播放不再改变返回键含义，独立“从头播放”按钮负责重播。功能入口保留，本版没有扩展“继续观看”。详细行为见 `docs/UX-140.md`。

冷启动期间点击账号入口会等待在线校验，避免误跳到登录页；返回可取消等待中的打开操作。页面测试脚本在出现 `needs-review` 时以非零状态退出，防止把未通过用例当作整组通过。

```sh
python3 tools/verify_player_remote.py --device emulator-5566 --refresh-navigation
```

该探针检查真实列表通知、身份焦点和滚动锚点、并发刷新/分页、卸载取消，以及真实媒体中的菜单开关、默认焦点、从头播放和逐层返回。正常播放可能更新观看历史。本版设备验证使用模拟器，未连接电视或测量电视性能。

## 139 版首页与播放器

首页保留四列与四个内容页，增加带文字的侧栏、较短的焦点动效及按账号保存的本机“继续观看”。返回首页恢复原卡片焦点及滚动锚点；延迟缓存不会覆盖已加载的新数据。播放器保留原有全部按钮、功能及遥控器路径，增加焦点操作名称、当前画质与速度摘要，收窄设置面板并缩短滑入距离与动画。详细设计与验收范围见 `docs/UX-139.md`。

```sh
python3 tools/verify_player_remote.py --device emulator-5566 --home-ux
```

该探针验证实际首页焦点、滚动恢复、缓存竞态与本机续播的账号隔离、毫秒进度、选集身份及损坏数据处理，并实际点击续播入口请求、解码真实视频和检查返回焦点。此项会使用现有登录，正常播放可能更新服务端观看历史。临时测试记录使用独立账号键，并在结束后删除，不清除用户登录或观看记录。


## 非调试性能构建与设置（141 引入）

非调试验证包使用 `benchmark` 构建类型，`debuggable=false`、`profileable=true`，沿用本地验证签名、API 21 和现有账号数据。对比双方都关闭代码压缩，保持编译配置一致；这是性能验证构建，不是公开发布签名。

```sh
python3 tools/run_gradle.py :app:assembleBenchmark -PvalidationApplicationId=com.damon.bblab -PvalidationVersionCode=144
python3 tools/profile_tv_performance.py --device 电视地址:5555 --apk app/build/outputs/apk/benchmark/app-benchmark.apk --out app/build/performance
python3 tools/verify_settings_tv.py --device 电视地址:5555
python3 tools/verify_apk_isolation.py --version-code 144 --variant benchmark
```

首页相邻栏目按需加载；卡片封面使用视图裁剪代替位图圆角转换，文字更新不重启封面请求。图片内存缓存和位图池设定独立预算，回收卡片清理请求，退出播放移除视图树。设置页与所有选择弹窗统一使用电视卡片样式，标记当前值、进入时聚焦、取消不保存并恢复来源焦点；保留原有偏好键及保存回调。继续观看功能保持不变。设计、对比条件和验证边界见 `docs/UX-141.md`。

## 142：播放入口、直播分区与焦点修复

搜索与动态卡片的分 P 响应现在会补入缺失 cid，保留已有多 P/历史 cid；查询失败时显示具体错误，不再发送 cid=0 的播放请求。直播分区使用带明确排序的公开 room/v3/area/getRoomList 列表，按 count 分页，分开处理业务错误与空结果，并丢弃刷新或离开页面后的旧响应。首页未确认标签使用与选中状态一致的圆角焦点；返回图标使用按钮内的有界背景。详见 docs/FIXES-142.md。

`--entry-routes` 使用已有账号，实际点击搜索/动态视频、直播分区及直播间，检查解码推进、分区分页与返回，并截图记录未确认标签和返回按钮焦点。此模式需显式指定本次非调试 APK；正常播放会更新观看历史。
