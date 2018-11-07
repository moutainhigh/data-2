package com.casic.taskexcute.server.collect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.casic27.sjrh.pojo.RwglCjxx;


@Component
@RabbitListener(queues = "Cjrw")
public class CJ_Tranformator_Mgn {

	protected Logger logger = LoggerFactory.getLogger(CJ_Tranformator_Mgn.class);

	private ExecutorService locateExecutor = Executors.newFixedThreadPool(1);
	public static AtomicLong getTasks = new AtomicLong(0);// 定位线程完成次数

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	@RabbitHandler
    public void process(RwglCjxx cjxx) {
        System.out.println("采集 Receiver object : " + cjxx);
        System.out.println("采集 任务状态 : " + cjxx.getRwzt());
        System.out.println("采集 执行状态 : " + cjxx.getZxzt());
        System.out.println("采集 执行频率: " + cjxx.getZxpl());
        System.out.println("采集 任务名称 : " + cjxx.getRwmc());
        
        locateExecutor.execute(new CJ_Tranformator_Thread(df.format(new Date()), cjxx.getRwmc(), cjxx));
        
    }
}
