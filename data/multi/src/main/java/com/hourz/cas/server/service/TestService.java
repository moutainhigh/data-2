/**
 * 
 */
package com.hourz.cas.server.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hourz.cas.server.config.TargetDataSource;
import com.hourz.cas.server.controller.Demo;
import com.hourz.cas.server.service.impl.TestDao;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-18
 */
@Service
public class TestService {

    @Resource
    private TestDao testDao;

    /**
     * 不指定数据源使用默认数据源
     * @return
     */
    public List<Demo> getList(){
        return testDao.getList();
    }

    /**
     * 指定数据源
     * @return
     */
    @TargetDataSource(name = "")
    public List<Demo> getListByDs1(){
        return testDao.getListByDs1();
    }

}
