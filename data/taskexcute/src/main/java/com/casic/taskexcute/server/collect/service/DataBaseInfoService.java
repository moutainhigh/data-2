package com.casic.taskexcute.server.collect.service;

import com.casic.taskexcute.server.collect.common.DbDataEntity;

public interface DataBaseInfoService {

	DbDataEntity getDbInfo(Integer yxtId, Integer idYxtb);
}
