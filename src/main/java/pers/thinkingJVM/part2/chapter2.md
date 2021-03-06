# Java 内存区域与内存溢出异常
## JVM 构成（Hotspots虚拟机--JVM规范）
### 类加载子系统
#### 三种类加载器
- **根（启动）类加载器 _Bootstrap ClassLoader_**    
&emsp;加载 rt.jar包内的的类(java.lang包下等),由C++实现,没有父加载器,当试图获取这个类加载器时，返回Null
- **扩展类加载器 _Extention ClassLoader_**    
&emsp;加载 jri/ext 或 用户自定义指定目录下的 jar （ExtClassLoader.class）父加载器是Bootstrap ClassLoader,当试图获取这个类加载器时，返回 ExtClassLoader.class
- **应用（系统）类加载器 _Application ClassLoader_**    
&emsp;加载 classpath下 或 用户自定义的类；父加载器是Extention ClassLoader，当试图获取这个类加载器时，返回AppClassLoader.class

### 运行时数据区
#### 方法区<Method Areas>（线程共享）
	jdk1.8之前 --由永久代实现，存在常量池
	jdk1.8之后 --由元数据区<Metadata Areas>实现，存在常量池
	存放类和方法的定义，在常量池中存在类的常量，每个类都拥有其独立的常量池
	设置元空间初始值： -XX: MetaspaceSize=  以字节为单位
	设置元空间最大值： -XX: MaxMetaspaceSize=  默认是-1，即不限制

#### 虚拟机栈<JVM Stack>（线程私有）
	描述Java方法执行的线程内存模型
	由大量的栈帧<Frame>构成，每个方法执行的时候创建一个栈帧
	存储局部变量表、操作数栈、动态连接、方法出口等信息

##### 局部变量表
	存放编译期可知的各种JVM基本数据类型、对象引用（reference类型，并不等于对象本身，
	可能是一个指向对象起始地址的引用指针，也可能是指向一个对象的句柄）<HotSpots中并不存在实际的句柄>
	以局部变量槽<Slot>来表示 一个槽32位    long double 64位



##### 本地方法栈<Native Method Stack>（线程共享，一般不考虑）
    调用环境中的本地库方法，因系统而异
#### 堆<Heap>（线程共享）
    存放已实例化的类和数组对象
##### 程序计数器<PC Register>（线程私有）
    JVM中最小的一块内存区域，唯一没有规定任何OutOfMemoryError的区域
    每一个线程都存在一个独立的程序计数器，来保证线程的正确执行；在多线程的情况中，存在线程切换的情况，
    一个处理器或者一个内核（多核处理器）都指挥执行一条线程的指令，程序计数器保证线程切换后能回到
    正确的位置，各条线程之间计数器互补影响，独立存储

### 执行引擎（线程共享）

### 本地库接口（线程共享）

### 本地方法库

## 对象的实例真的只存在于堆\<Head>中么?

### 栈上分配（逃逸分析）
    在虚拟机栈中开辟一段小空间，存储线程局部的实例对象，通常这些实例对象不会太大
    开启命令：
### TLAB(thread local allocation buffer)
    在堆中开辟一些线程独有的空间存储对象实例，避免对象实例由于在堆共享内存中进行同步操作造成额外开销
    开启命令：
