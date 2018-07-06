package com.caad.wechat.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caad.wechat.dao.NewIdDao;
import com.caad.wechat.entity.NewId;
import com.caadt.cln.common.util.DateUtil;

/**
 * 生成新的编号
 */
@Service("genNewId")
public class GenNewId {

    @Autowired
    private NewIdDao newIdDao;

    private static Log log = LogFactory.getLog(GenNewId.class);

    private NewId newId = null;

    private static final int MIN = 100;
    private static final int MAX = 9990;
    private static final int ERR = 9999; // 异常情况下的编号返回。

    /**
     * 为单据生成一个新的编号
     */
    public synchronized int genNewId() {
        try {
            if (this.newId == null) {
                this.newId = this.newIdDao.findOne(1L);
                log.info("[d03,收到]从数据库读取到的小号" + this.newId.getMiniid());
                this.newId.setMiniid(this.newId.getMiniid() + MIN);
                log.info("[d04,收到]从数据库读取到的小号" + this.newId.getMiniid() + " " + this.newId.getStarttime());
            }
            // 加一
            String today = DateUtil.getDate();
            this.newId.setMiniid(this.newId.getMiniid() + 1);
            this.newId.setUpdatetime(today);
            // 是否要重置到0
            if (this.newId.getMiniid() > MAX && this.newId.getStarttime().compareTo(today) < 0) {
                this.newId.setMiniid(MIN);
                this.newId.setStarttime(today);
                this.newId.setUpdatetime(today);
            }
            // 存到数据库
            this.newIdDao.save(this.newId);
            return this.newId.getMiniid();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return ERR; // 运行到此处，应该是出现异常的情况
    }
}
