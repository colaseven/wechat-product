package com.caad.wechat.model.viss.product;

import com.caad.wechat.model.viss.common.PageInfo;

public class EntrustQueryModel extends PageInfo {
    // <summary>
    /// 查询条件
    /// </summary>
    private EntrustSearchModel search;

    public EntrustSearchModel getSearch() {
        return search;
    }

    public void setSearch(EntrustSearchModel search) {
        this.search = search;
    }
}
