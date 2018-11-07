package com.casic.taskexcute.server.collect;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.casic.taskexcute.server.collect.common.BeanContext;
import com.casic.taskexcute.server.collect.common.DbDataEntity;
import com.casic.taskexcute.server.collect.service.DataBaseInfoService;
import com.casic.taskexcute.server.collect.service.DataComparisonService;
import com.casic.taskexcute.server.collect.service.DataListMiddleService;
import com.casic.taskexcute.server.collect.service.DataListSourceService;
import com.casic27.sjrh.pojo.RwglCjxx;

/**
 * 转化任务多线程
 * @author hourz
 * @since 2018-07-24
 */
@Component
@RestController
public class CJ_Tranformator_Thread implements Runnable {

	protected Logger logger = LoggerFactory.getLogger(CJ_Tranformator_Thread.class);
	
	// 定位线程完成次数
	public static AtomicLong completedTask = new AtomicLong(0);
	// 接受时间
	private String receiveTime;
	// 任务名称
	private String taskname;
	// 采集任务对象
	private RwglCjxx rwglCjxx;
	
	@Autowired
	DataBaseInfoService dataBaseInfoService;
	@Autowired
	DataComparisonService dataComparisonService;
	@Autowired
	DataListMiddleService dataListMiddleService;
	@Autowired
	DataListSourceService dataListSourceService;
	
	public CJ_Tranformator_Thread() {
		super();
	}

	/**
	 * 构造器
	 * @param receiveTime 接受时间
	 * @param taskname 任务名称
	 * @param rwglCjxx 采集任务信息
	 */
	public CJ_Tranformator_Thread(String receiveTime, String taskname, RwglCjxx rwglCjxx) {
		super();
		this.receiveTime = receiveTime;
		this.taskname = taskname;
		this.rwglCjxx = rwglCjxx;
		this.dataBaseInfoService = BeanContext.getApplicationContext().getBean(DataBaseInfoService.class);
		this.dataComparisonService = BeanContext.getApplicationContext().getBean(DataComparisonService.class);
		this.dataListMiddleService = BeanContext.getApplicationContext().getBean(DataListMiddleService.class);
		this.dataListSourceService = BeanContext.getApplicationContext().getBean(DataListSourceService.class);
	}

	
	@Override
	public void run() {
		// 线程开启
		Thread.currentThread().setName("CJ_Tranformator Thread");
		logger.info("开始采集任务，任务执行！");
		// TODO 读取数据库连接信息
		DbDataEntity dbinfo = dataBaseInfoService.getDbInfo(rwglCjxx.getYxtId(), rwglCjxx.getYxtbId());
		// TODO 测试源系统数据库是否能够连接
		if(dataListSourceService.testConnection(dbinfo)) {
			
		} else {
			
		}
		// TODO 读取数据库
		
	}
	
}