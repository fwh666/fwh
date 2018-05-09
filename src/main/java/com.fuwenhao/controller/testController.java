package com.fuwenhao.controller;

import com.fuwenhao.protocol.People;
import com.fuwenhao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by FWH on 2018-05-04.
 */
@Controller
public class testController {
    @Autowired
    private TestService testService;
    @RequestMapping(value = "/test")
    public  void  test (){
        People people = new People();
        people.setName("fuwenhao");
        people.setAge(18);
        testService.insertTest(people);
    }

    @RequestMapping("/query")
    public String query(){
        People people = new People();
        people.setName("aa");
        return testService.query(people);
    }

    @RequestMapping("insert")
    public void insert(){
        String s ="fuwenhao";
       Boolean isno = testService.insert(s);
        System.out.println(isno);
    }

    @RequestMapping(value = "/fuwenhao")
    public String fuwenhao(){
        return "fuwenhao";
    }
}
