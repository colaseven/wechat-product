package com.caad.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NewId")
public class NewId {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 20)
    private int miniid;

    @Column(length = 20)
    private String starttime;

    @Column(length = 20)
    private String updatetime;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMiniid() {
        return this.miniid;
    }

    public void setMiniid(int miniid) {
        this.miniid = miniid;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}
