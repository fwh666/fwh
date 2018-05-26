package com.fuwenhao.interviewTest.SpringTansactionDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create by fwh on 2018/5/26 下午9:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/config/spring/spring-jdbc.xml")
//@Controller
public class SpringTransctionTest {
    @Autowired
    private SpringTransactionService springTransactionService;
    @Test
    public void parentTest(){
        springTransactionService.insertParent();
    }
    @Test
    private void childTest(){
        springTransactionService.insertChild();
    }
}
