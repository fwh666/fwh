package com.fuwenhao.interviewTest.JvmDemo;

import org.junit.Test;

import java.io.*;

/**
 * create by fwh on 2018/5/23 下午8:52
 * 自定义自己的类加载器；
 * 1。需要定义find方法
 * 2。定义加载器路径
 * 3。
 */
public class MyClassLoaderDemo extends ClassLoader {
    private String path;//类加载路径
    private String name;//类名称

    //调用父类的构造方法
    public MyClassLoaderDemo(ClassLoader parent, String name, String path) {
        super(parent);//显示指定父类加载器
        this.path = path;
        this.name = name;
    }

    public MyClassLoaderDemo(String name, String path) {
        super();//如果super里面的内容为空的话，表示让系统类加载器成为父类加载器；
        this.path = path;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyClassLoaderDemo{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 加载我们自定义的类
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = readClassFileToByteArray(name);
        this.defineClass(name, data, 0, data.length);
        return super.findClass(name);
    }

    /**
     * 获取。class字节数组
     *
     * @param name
     * @return
     */
    private byte[] readClassFileToByteArray(String name) {
        InputStream is = null;
        byte[] returnData = null;
//        name =name.replaceAll("",)
        String filePath = this.path + name + ".class";
        File file = new File(filePath);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(file);
            int tmp = 0;
            while ((tmp=is.read())!=-1){
                os.write(tmp);
            }
            returnData = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }
}
//测试自定义加载器方法
/*
class Test1{
    @Test
    public void test1(){
        MyClassLoaderDemo loaderDemo = new MyClassLoaderDemo("zhangfei","/Users/fwh/A_FWH/GItHub/fwh/target/classes/com/fuwenhao/interviewTest/JvmDemo/Demo.class");
        try {
            Class<?> c= loaderDemo.loadClass("Demo");
            c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}*/
