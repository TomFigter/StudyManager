Thread细节小知识点
<1>join()方法的使用
|  |_基础定义
|  |  |_主线程创建并启动子线程，如果子线程中需进行大量耗时运算,主线程往往将早于子线程之前结束.
|  |  |_若主线程想等待子线程执行完成后再结束.就需要调用子线程的join();join()作用等待线程对象销毁
|  |_调用实践
|  |  |_./JoinThreadDemo.java
<2>ThreadLocal类的使用
|  |_基础定义
|  |  |_变量值的共享可以使用public static变量的形式,所有的线程都使用同一个public static变量.若想实现
|  |  |_每个线程都有自己的共享变量该如何解决? JDK提供ThreadLocal解决此问题.
|  |  |_ThreadLocal主要解决的就是每个线程绑定自己的值,可以将ThreadLocal类比喻成全局存放数据的盒子,
|  |  |_盒子中可以存储每个线程的私有数据.
<3>Lock类的使用
|  |_基础定义
|  |  |_与synchronized线程锁功能相同,并且扩张功能更强大.它的子类ReentrantLock是具体功能的实现

