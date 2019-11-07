## 关机

按住电源键并选择关机。

如果屏幕处于无响应状态，那就按住电源键几秒钟，直到设备关闭。

## Fastboot Mode

按住电源键和音量 -，进入 Fastboot Mode，屏幕会显示设备的相关信息，以及屏幕顶部的开始按钮。

## Recovery Mode

可以通过音量 + 和音量 - 来浏览菜单选项，找到 Recovery Mode，然后按下电源键进入。

随后，白色的谷歌 logo 便会弹出，紧接着是 Android 机器人，其下方还会显示 “No Command” 字样。

## Sideload Mode

按住电源键和音量 + 约三秒钟，然后放开音量 +。

出现 Recovery Mode 菜单选项，然后你可以通过音量键来选择 Apply update from ADB 选项，并使用电源键确认。

手机通过 usb 连接到电脑，执行 `adb devices` 命令，能看到连接的设备名和 sideload 字样。

首先，去 https://developers.google.com/android/ota 下载手机对应的 OTA zip 文件。

然后，执行 `adb sideload ota_file.zip` 进行安装。

## 重启

选择 Reboot system now 选项，然后按下电源键。
