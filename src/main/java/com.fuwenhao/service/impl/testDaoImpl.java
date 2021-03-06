package com.fuwenhao.service.impl;

import com.fuwenhao.dao.TestDao;
import com.fuwenhao.protocol.People;
import com.fuwenhao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by FWH on 2018-05-04.
 */
@Service
@Transactional
public class testDaoImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Override
    public void insertTest(People test) {
        testDao.insertTest(test);
    }

    @Override
    public People query(People people) {
        return testDao.query(people);
    }

    @Override
    public Boolean insert(String s) {
        return testDao.insert(s);
    }

    @Override
    public void deleteTest(String name) {
        testDao.deleteTest(name);
    }
}
