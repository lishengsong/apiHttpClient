# api+httpclient

阅读文档：http://blog.csdn.net/column/details/httpclient-arron.html

```
首先：
lsof -i tcp:port（port是具体端口，比如1099）
lsof（list open files）是一个列出当前系统打开文件的工具。在linux／mac环境下，任何事物都以文件的形式存在，通过文件不仅仅可以访问常规数据，还可以访问网络连接和硬件。
参考：
lsof命令详解

然后：
kill －9 pid ，pid是具体的进程id
SIGNKILL（9） 的效果是立即杀死进程. 该信号不能被阻塞, 处理和忽略。
SIGNTERM（15） 的效果是正常退出进程，退出前可以被阻塞或回调处理。并且它是Linux缺省的程序中断信号。
 ```
 
 
