数据结构
|_ThreadLocal的原理和使用场景
----------------------------------------------------------------------------------------------------

&ThreadLocal 
|__理解
|   ->ThreadLocal提供了线程本地的实例.它与普通变量的区别在于"每个使用该变量的线程都会初始化一个完全独立的实例副
|     本".ThreadLocal变量通常被private static 修饰,当一个线程结束时,它所使用的所有ThreadLocal相对的实例副本
|     都可被回收.
|---------------------------------------------------------------------------------------------------
|__总结
|   ->ThreadLocal适用于每个线程需要自己独立的实例且该实例需要在多个方法中被使用,即变量在线程间隔离而在方法或类间
|     共享的场景
|---------------------------------------------------------------------------------------------------
|_SimpleThreadLocalDemo 解析
|   |__CountDownLatch 
|   |   ->同步辅助类,在完成一组正在其他线程中执行的操作之前,它允许一个或多个线程一直等待,知道条件被满足.
|   |     -> latch=new CountDownLatch(LATCH_SIZE); LATCH_SIZE 指定执行线程数
|   |     -> latch.countDown() 每当一个线程执行结束都会调用此方法  
|   |     -> latch.await()  当指定线程在执行过程中,其他线程处于等待状态;直到指定线程数执行完毕等待的线程才被再
|   |     ->                次唤醒.
|   |-----------------------------------------------------------------------------------------------
|   |__HashTable
|   |   ->HashTable是HashMap线程安全的实现,HashTable使用synchronized来保证线程安全;但缺点是在线程竞争激烈的
|   |   ->情况下效率非常低下;因为线程访问同步时,其他线程访问HashTable的同步方法时可能会进入阻塞或轮询状态.
|   |__ConcurrentHashMap
|   |   ->HashTale虽然保证了线程的安全,但效率低下;为了提高并发效率所就以提出了ConcurrentHashMap所使用的锁分段
|   |   ->技术;首先将数据分成一段一段的存储,然后每一段数据配一把锁,当一个线程占用锁访问其中一个段数据时,其他段的
|   |   ->数据也能被其他线程访问.
|   |-----------------------------------------------------------------------------------------------
