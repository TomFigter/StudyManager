




基础
|_ Android消息处理机制核心类
|  |_ Looper | Handler | Message；
|  |_ 其中还有一个Message Queue(消息队列),但Message Queue 被封装到了Looper里,我们不会直接与MessageQueue打交道,故每将其设为核心类
|----------------------------------------------------------------
|_线程魔术师Looper
|  |_Java源码解析
|  |  |_.\LooperThread.java -> 是一个Looper线程的简单实现
|  |  |_.\Looper.java -> 是对Looper源码的基础分析
|  |_对Looper基本总结
|  |  |_<1>每个线程有且只有一个Looper对象,他是一个ThreadLocal
|  |  |_<2>Looper中包装了一个消息队列MQ(MessageQueue),loop()调用后不断从队列中取出消息执行
|  |  |_<3>Looper可以使一个线程变成Looper线程
|-------------------------------------------------------------------
|_异步处理大师Handler
|
|  |_Java源码解析
|  |_.\Handler.java ->对Handler源码进行分析
|
|  |_基础作用
|  |  |_往MQ上添加消息和处理消息,即通知MQ需要执行一个任务,并在loop到自己后执行该任务,整个过程异步进行
|
|  |_特别说明
|  |  |_一个线程中可以有多个Handler,但只能有一个Looper
|
|  |_Handler发送消息
|  |
|  |  |_基础用法
|  |  |  |_ post(Runnable)
|  |  |  |_ postAtTime(Runnable, long)
|  |  |  |_ sendEmptyMessage(int)
|  |  |  |_ postDelayed(Runnable, long)
|  |  |  |_ sendMessage(Message)
|  |  |  |_ sendMessageAtTime(Message, long)
|  |  |  |_ sendMessageDelayed(Message, long)
|  |  |  |_这些方法是向MQ上发送消息,直观上理解Handler发消息分为两种方式 <1>Runnable对象 <2>Message对象
|  |  |  |_但其实post发出的Runnable对象最后都被封装成Message对象
|  |
|  |  |_通过Handler发出的message有如下特点
|  |  |  |_<1> message.target为该handler对象，这确保了looper执行到该message时能找到处理它的handler，即loop()方法中的关键代码
|  |  |  |  |_ msg.target.dispatchMessage(msg);
|  |  |  |_<2> post发出的message，其callback为Runnable对象
|
|  |_Handler处理消息
|  |  |_基础用法
|  |  |  |_ 核心方法 dispatchMessage(Message msg)
|  |  |  |_ 钩子方法 handleMessage(Message msg)
----------------------------------------------------------------------------------------------------
Handler小细节方法讲解
|_handler.obtainMessage() -> 作用是从当前的Handler中获取指定的Message以供再次使用