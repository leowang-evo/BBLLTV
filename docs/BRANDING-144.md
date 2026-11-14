# BBLLTV 图标与关于页（144）

2026-11-14，用户确认使用预览图标并授权打包。应用名称统一为 BBLLTV；包名、验证签名、偏好数据和业务代码保持不变。

## 资源

- `docs/branding/bblltv-approved-icon.png`：确认的 1254×1254 原图，使用内置 image_gen 生成。
- `app/src/main/res/mipmap-nodpi/bblltv_icon_artwork.png`：原图等比缩小为 512×512，用于关于页及自适应图标前景。
- `ic_launcher.png`、`ic_launcher_round.png`：兼容旧版 Android 的同版图标。
- API 26 及以上使用深蓝背景和内缩前景，保留系统圆形/圆角裁切空间。
- `ic_launcher_tv.png`：640×360 电视横幅，组合确认的图标和 BBLLTV 名称，保持图标原比例。

关于页显示原项目 https://github.com/xiaye13579/BBLL 与新项目 https://github.com/leowang-evo/BBLLTV 。本次仅更新品牌资源、关于文案和应用标签，不修改播放、账号及导航代码。

## 原始生成提示词

工具：内置 image_gen；非 CLI；不透明背景。原图及其配色、图形已由用户确认，打包时仅缩放及适配资源。

> Use case: logo-brand. Create a single polished original Android TV application icon for BBLLTV, a third-party Bilibili television client. This is a preview of the icon only, not a UI screenshot or a presentation. Square 1024x1024 canvas, one generously rounded dark midnight-navy app tile filling the canvas, with a single bold compact TV-and-play emblem centered. The television screen has a thick smooth cyan-blue rounded rectangular frame in a wide-screen proportion; its center contains a clean white right-pointing play triangle, with a restrained coral-pink accent integrated into one corner of the frame. The play symbol and TV outline form a balanced coherent original mark. Very simple geometric silhouette, crisp vector-like edges, consistent rounded corners, carefully balanced optical spacing, strong foreground contrast, recognizable at 48 pixels across a living room. Flat premium visual style with a very subtle smooth depth to the tile only. Main emblem stays inside central 65 percent safe zone for adaptive launcher cropping. No words, no letters, no slogan, no watermark, no surrounding scene, no phone mockup, no extra icons, no antennas or cartoon face, no reproduction of the official Bilibili or BBLL logo. Show just one final icon, front-on.
