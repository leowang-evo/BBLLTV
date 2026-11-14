# 142 修复记录

141 的页面回归只确认搜索结果、动态条目和直播分类卡片可见，漏测了从这些入口点击到实际解码，以及分类内部的房间分页。电视上已复现搜索点击后的“请求错误”；新增 EntryRoutesProbe 从实际页面卡片点击，检查真实播放器尺寸、播放推进、返回页面及直播房间分页。

## 视频缺少 cid

热门列表通常已经有 cid，而搜索及动态条目可能只有 aid/bvid。原 PlayerMetadataCallback 收到 pagelist 后只保存列表、刷新分集 UI，未将第一页 cid 补回模型，随后请求 cid=0。现在仅在原 cid 缺失时从有效分页补齐，已有多 P/历史选择保留。请求失败或没有有效页时显示错误并停止，不发无效播放请求。旧视频或旧 cid 的分页响应不会写回新播放状态。

## 直播分区

实测旧 second/getList 返回 -352，而 room/v3/area/getRoomList 带 sort_type=online、page_size=20、parent_area_id、area_id、page 可返回当前分区房间。缺失排序可能返回空列表，因此排序为显式参数。保留 getLiveCategoryDetailList 的公开 Java 签名，响应新增 count，按总数与当前页判断后续页。仅此接口显式设置直播 Referer/Origin，头拦截器尊重调用方的 Origin。

业务失败展示 RequestMessages 中的具体错误，不再当作空分区；正常空结果仍显示空态。分页回调带刷新代次和视图检查；销毁时解除适配器引用。公开分区重试不要求先登录。没有把首页推荐模块伪装成分类房间列表。

参考核对：[另一实现的直播列表接口研究](https://github.com/rongyuio/bilibili/blob/v0.13.0/live/live.go)。它指出旧 second/getList 的变化；实际采用的 room/v3 路径、排序及分页均以本机请求和电视实测为依据，未复制第三方业务代码。

## 焦点外观

首页标签保留原 tv_nav_background 的 20px 圆角，关闭额外 Ripple 和 Android 默认矩形焦点层；遥控器移动仍不确认，Center 才切页。返回图标在通用外壳、全部番剧、番剧详情和经典搜索页统一使用内缩 18px 的圆角背景，保留原点击范围及返回回调，不再使用越界圆形 Ripple。键盘删除键不在此改动范围。

## 检查口径

使用 benchmark 非调试构建、同签名版本142覆盖电视，不清数据。API21静态检查限定 NewApi/InlinedApi；完整旧资源/恢复源码的全量 lint 仍有历史问题，不能声称全量 lint 清零。网络测试新增本机分区契约，包括真实管理器 URL/参数、count 解析、直播 Origin/Referer；通过服务接口处只重写主机的测试代理保持所有请求在回环服务。测试不得输出真实 Cookie 或流地址。详细结果、截图和最终 APK 哈希在 dist 的交付记录。
