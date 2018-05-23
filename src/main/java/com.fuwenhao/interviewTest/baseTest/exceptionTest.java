package com.fuwenhao.interviewTest.baseTest;

/**
 * 面试题
 * 异常输出顺序
 */
class ThreeException extends Exception {}

public class exceptionTest {
     static int count = 0;
        public static void main(String[] args){
            while(true) {
                try {
                    if (count++ == 0)
                        throw new ThreeException();
                    System.out.println("No Exception");
                } catch (ThreeException e) {
                    System.err.println("ThreeException");
                } catch (Exception e) {
                    System.err.println(" Exception");
                } finally {
                    System.err.println("Finally");
                    if (count == 2) break;
                }
            }
        }
}
