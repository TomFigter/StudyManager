Android注解

缺点:
    >性能低
    >运行时注解会因为java反射而引起较为严重的性能问题
    >编译时注解，不会对性能有任何影响的黑科技
优点:
    >极大的提高开发效率,避免编写重复、易错的代码.大部分编译时注解都可以替代java反射,利用可以直接调用的代码代替反
     射,极大的提升运行效率
         
    
  一、什么是注解? 
     |->1.注解分类
     |  |_标准Annotation
     |  |_元Annotation
     |  |_自定义Annotation
     |  | |_@Retention(RetentionPolicy.CLASS) ->用于源码、类文件阶段;即编写的java文件和编译后产生的class文件   
     |  | |_@Retention(RetentionPolicy.RUNTIME) ->用于源码、类文件和运行时阶段
     |  | |_@Retention(RetentionPolicy.SOURCE) ->仅用于源码阶段;即编写的java文件
     |----------------------------------------------------------------------------------------------
     |->2.自定义Annotation的区别
     |  |_首先RetentionPolicy类型是一种包含关系.另外什么阶段的RetentionPolicy就表示你可以在什么阶段处理它们.
     |  |__<1>RetentionPolicy.SOURCE修饰的注解可以在源码阶段时处理,但class文件或运行后APT就没办法对它处理了
     |  |__<2>RetentionPolicy.RUNTIME修饰的注解 '优先级最大';你可以在任何时候对它进行处理
     |----------------------------------------------------------------------------------------------  
     |->3.RetentionPolicy.RUNTIME为何不能代替RetentionPolicy.SOURCE?
     |  |__原因同修饰类成员时用的private还是public的道理一样.
     |----------------------------------------------------------------------------------------------
  
  二、APT框架   
               