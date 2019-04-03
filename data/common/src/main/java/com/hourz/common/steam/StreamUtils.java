package com.hourz.common.steam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StreamUtils {

	
	
	public static void main(String[] args) {
		List<Plan> planList = new ArrayList<>();
		Plan plan1 = new Plan();
		plan1.setCreateTime(Calendar.getInstance());
		plan1.setId(1);
		plan1.setPlanNo(UUID.randomUUID().toString());
		plan1.setPrice(BigDecimal.valueOf(99L));
		plan1.setTotal(10);
		planList.add(plan1);
		Plan plan2 = new Plan();
		plan2.setCreateTime(Calendar.getInstance());
		plan2.setId(2);
		plan2.setPlanNo(UUID.randomUUID().toString());
		plan2.setPrice(BigDecimal.valueOf(999L));
		plan2.setTotal(20);
		planList.add(plan2);
		Plan plan3 = new Plan();
		plan3.setCreateTime(Calendar.getInstance());
		plan3.setId(3);
		plan3.setPlanNo(UUID.randomUUID().toString());
		plan3.setPrice(BigDecimal.valueOf(9999L));
		plan3.setTotal(30);
		planList.add(plan3);
		Plan plan4 = new Plan();
		plan4.setCreateTime(Calendar.getInstance());
		plan4.setId(4);
		plan4.setPlanNo(UUID.randomUUID().toString());
		plan4.setPrice(BigDecimal.valueOf(99999L));
		plan4.setTotal(40);
		planList.add(plan4);
		System.out.println(planList.stream().collect(Collectors.groupingBy(Plan::getState, Collectors.summarizingLong(Plan::getTotal))));
	}
}
