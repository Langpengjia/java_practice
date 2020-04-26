package pers.lpj;

import sun.applet.AppletClassLoader;

/**
 * @classname: MyTest7
 * @description:考察不同类加载器的返回
 * @author: Desire
 * @date: 2019-12-24 10:59
 */
public class MyTest7 {

    public static void main(String[] args) {

        try {
            //检验类加载器
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader==null);

            ClassLoader classLoader1 = Class.forName("pers.lpj.D").getClassLoader();
            System.out.println(classLoader1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}

class D{



}
