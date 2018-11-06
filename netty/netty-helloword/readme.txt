helloworld 参考 https://github.com/sanshengshui/netty-learning-example

扩展阅读：
一、CPU和IO读取的关系可以参考：http://blueskator.iteye.com/blog/2231755
CPU与IO设备间数据传输主要有四种方式：
1、查询控制方式：
CPU通过程序主动读取状态寄存器以了解接口情况，并完成相应的数据操作。查询操作需要在时钟周期较少的间隔内重复进行，因而CPU效率低。
2、中断控制方式：
当程序常规运行中，若外部有优先级更高的事件出现，则通过中断请求通知CPU，CPU再读取状态寄存器确定事件的种类，以便执行不同的分支处理。这种方式CPU效率高且实时性好。
3、DMA（Direct Memory Access）控制方式：
顾名思义，直接内存存取即数据传送的具体过程直接由硬件（DMA控制器）在内存和IO之间完成，CPU只在开始时将控制权暂时交予DMA，直到数据传输结束。这种方式传送速度比通过CPU快，尤其是在批量传送时效率很高。
4、通道控制方式：
基本方法同上述的DMA控制方式，只是DMA通过DMA控制器完成，通道控制方式有专门通讯传输的通道总线完成。效率比DMA更高。

我觉得NIO的方式是利用了第4种方式

二、理解高性能网络模型：https://www.jianshu.com/p/2965fca6bb8f
三、一文理解Netty模型架构：https://juejin.im/post/5bda4cc55188257f630dac07?utm_source=gold_browser_extension
