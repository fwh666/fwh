package com.fuwenhao.controller;

import com.fuwenhao.protocol.People;
import com.fuwenhao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by FWH on 2018-05-04.
 */
@Controller
public class testController {
    @Autowired
    private TestService testService;

    @RequestMapping("/queryTest")
    public ModelAndView queryTest(ModelAndView model) {
        People people = new People();
        people.setName("fuwenhao");
        People query = testService.query(people);
        return model.addObject("peopleTest", query);
    }

    @RequestMapping(value = "/insertTest")
    public void insertTest() {
        People people = new People();
        people.setName("fuwenhao");
        people.setAge(18);
        testService.insertTest(people);
    }

    @RequestMapping("/deleteTest")
    public void deleteTest() {
        People people = new People();
        people.setName("fuwenhao");
        testService.deleteTest(people.getName());
    }

    @RequestMapping("insert")
    public void insert() {
        String s = "fuwenhao";
        Boolean isno = testService.insert(s);
    }

    @RequestMapping(value = "/fuwenhao")
    public Model fuwenhao(Model model) {
        return model.addAttribute("fuwenhao");
    }

}
