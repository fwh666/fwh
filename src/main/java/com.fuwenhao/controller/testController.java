package com.fuwenhao.controller;

import com.fuwenhao.protocol.People;
import com.fuwenhao.service.TestService;
import org.junit.Test;
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
    @ResponseBody
    @RequestMapping(value = "/test", method = POST)
    @Test
    public  void  test (){
        People People = new People();
        People.setName("fuwenhao");
        People.setAge(18);
        testService.insertTest(People);
    }
    @RequestMapping( value = "/helloworld")
    public String helloWorld(Model model){
        model.addAttribute("helloworld","Hi Spring MVC");
        return  "helloworld/helloworld";
    }
}
