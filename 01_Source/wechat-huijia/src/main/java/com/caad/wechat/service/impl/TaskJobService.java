package com.caad.wechat.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caad.wechat.dao.HuijiaReqDao;
import com.caadt.cln.common.util.DateUtil;

@Service("taskJobService")
public class TaskJobService {

    private static Log log = LogFactory.getLog(TaskJobService.class);

    @Autowired
    private HuijiaReqDao huijiaReqDao;

    @Transactional
    public void job() {
        String status = "1";
        Date createtime = DateUtil.dateAddDays(new Date(), -2);
        log.info("定时对数据库数据进行清理：" + new Date());
        huijiaReqDao.deleteByStatusAndCreatetime(status, createtime);
    }
}
