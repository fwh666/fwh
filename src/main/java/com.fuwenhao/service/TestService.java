package com.fuwenhao.service;

import com.fuwenhao.protocol.People;

/**
 * Created by FWH on 2018-05-04.
 */

public interface TestService {
    void insertTest (People test);
    String query(People people);
    Boolean insert (String s);
}
