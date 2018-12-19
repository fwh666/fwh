package com.fuwenhao.interviewTest.ioDemo;

import org.junit.Test;

import java.io.*;

/**
 * create by fwh on 2018/12/19 下午5:31
 */
public class ObjectStream {
    /**
     * 读取磁盘文件测试类
     */
    @Test
    public void readFileTest() {
        File file = new File("/Users/fwh/Downloads/test.txt");
        try {
            Reader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileReviewTest() throws IOException {
        File file = new File("/Users/fwh/Downloads/test.txt");
        Reader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String data = null;
//        data = bufferedReader.readLine();//容易死循环
        while ((data=bufferedReader.readLine()) != null) {
            System.out.println(data);
        }
    }
}
