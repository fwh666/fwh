package com.fuwenhao.interviewTest.SpringTansactionDemo;

import com.fuwenhao.dao.TestDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * create by fwh on 2018/5/26 下午9:10
 * Spring事务不回滚情景模拟：
 *      插入父类和子类，子类抛异常。
 *
 *最终希望结果：
 *      父类插入成功，子类异常回滚。
 *
 */
@Service
@Component
public class SpringTransactionServiceImpl implements  SpringTransactionService{

    private static Logger logger = LogManager.getLogger(SpringTransactionService.class);
    @Autowired
    private TestDao testDao;

    @Transactional
    @Override
    public void insertParent() {
        try {
            /*捕获了子类插入后的异常，但是数据依然能插入；不符预期。
              * 解决方法：
              *     原理：采用代理模式，去调用代理的子类来插入，如有异常则回滚，不影响父类继续插入操作。
              *     步骤：先暴露接口在spring配置文件中；然后在上下文中获取。
              *         <aop:aspectj-autoproxy expose-proxy="true"/>
             */
//            insertChild();
            ((SpringTransactionService)AopContext.currentProxy()).insertChild();//注意：此处采用动态代理模式处理，还有其他相关实现方法解决此类问题。
        }catch (Exception e){
            logger.error("异常为："+e);
        }
        String parent ="parent";
        testDao.insert(parent);
        System.out.println("====插入父类成功");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) //创建个新事务
    /*
    / request_new如果当前存在事务，则挂起当前事务，并开始新的事务继续执行，新事务执行完毕后再执行之前的事务。
        如果不存在，则直接开启新事务。
     */
    @Override
    public void insertChild() {
        String child ="child";
        testDao.insert(child);
        int ex = 1/0;//测试抛出异常，插入的数据回滚。
        System.out.println("====插入子类成功");

    }
}
