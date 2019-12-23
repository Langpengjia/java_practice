package pers.lpj;

/**
 * @classname: MyTest1
 * @description:
 * @author: Desire
 * @date: 2019-12-20 10:54
 */
public class MyTest1 {

    /**
     * JVM 由几个部分组成   类加载器引擎，运行时数据区，本地方法引擎 等等.
     *
     * java 中的类型的 加载 连接 初始化（包括动态代理的类） 是在 JVM 运行时完成的
     *
     * java 类的生命周期 JVM 分为几个阶段
     * 1 加载 <将可执行的字节码文件从硬盘加载至内存中>
     *      * 类加载器
     *      *   1 java 自带的类加载器 <所有加载器不是继承关系-而是包含关系>
     *      *      a 根(启动类加载)类加载器 （Bootstrap ClassLoader）<没有父加载器，其他的加载器有且只有一个父加载器>
     *                  负责加载系统类库（例如 java.lang）
     *      *      b 扩展类加载器 （Extention ClassLoader）<父加载器是根类加载器>: jre包下等等
     *      *      a 应用（系统）类加载器 （System ClassLoader）<父加载器是扩展类加载器>
     *      *
     *      *   2 用户自定义的类加载器 （一定是ClassLoader的子类）<父加载器是应用（系统）类加载器>
     *      *
     *      * JVM 允许类加载器预料到某个类会被使用的情况下预先加载它，
     *      * 在加载过程中如果出现.class文件缺失或者其他错误时，类加载器
     *      * 要在程序首次使用时报告此错误
     *
     * 2 连接 <完成类的 验证 准备 解析 > (将已经加载到内存的类二进制文件合并到虚拟机的运行时环境当中)
     *     a. 验证 完成对字节码文件的校验（是否符合java字节码规范）
     *     b. 准备 为类的中静态变量分配空间并赋予<初始值>(string null, boolean = false, int = 0)
     *     c. 解析 将类中的 <符号引用> 转化为 <直接引用>
     * 3 初始化 [类的初始化条件:当一个类或者接口被主动调用的时候会被初始化；子类被主动调用的时候回导致该类的所有父类
     *          或者接口会被初始化],注意！！！！！--- 初始化时是按照代码顺序初始化的
     *
     *          类或者接口主动使用的情况(其余都是被动使用)：
     *              1. 创建类的实例（new） **<首次><主动>使用 (同一个类 new 多次只会初始化一次)
     *              2. 访问类或接口的静态变量或静态方法
     *              3.
     *              4.
     *              5.
     *              6.
     *              7.
     *
     * 4 执行(类的实例化 为对象在heap上分配内存)
     * 5 销毁（卸载类）
     *
     * jvm 启动参数设置规则
     *   1. -XX:+<option> 增加一个启动参数
     *   2. -XX:-<option> 移除一个启动参数
     *   3. -XX:<option>=<value> 为一个启动参数赋值
     *
     *
     */


    // -XX:+TraceClassLoading   （将已经初始化话的类的信息在控制台中打印出来）
    public static void main(String[] args) {
        /**
         * 因为child 调用的str 是父类的变量 所以 child类不会初始化 只会初始化 parent
         */
        System.out.println(Child.str);

        /**
         * 因为child 调用 cstr，会对child类完成初始化，其所有的父类和接口都会被初始化 所以Patent 也会完成初始化
         */
//        System.out.println(Child.cstr);

    }

}


class Parent {
    public static String str = " Parent Str !";

    static {
        System.out.println(" Parent static block !  ");
    }
}

class Child extends Parent {

    public static String cstr = " Parent Str !";

    static {
        System.out.println(" Child static block !  ");
    }
}