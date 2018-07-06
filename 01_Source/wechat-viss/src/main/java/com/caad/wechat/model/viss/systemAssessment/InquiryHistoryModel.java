package com.caad.wechat.model.viss.systemAssessment;

import com.caad.wechat.model.viss.common.PageInfo;

public class InquiryHistoryModel extends PageInfo {
    // <summary>
    /// 查询条件
    /// </summary>
    private InquiryHistorySearchModel search;

    /// <summary>
    /// 排序
    /// </summary>
    private InquiryHistorySortModel sort;

    public InquiryHistorySearchModel getSearch() {
        return search;
    }

    public void setSearch(InquiryHistorySearchModel search) {
        this.search = search;
    }

    public InquiryHistorySortModel getSort() {
        return sort;
    }

    public void setSort(InquiryHistorySortModel sort) {
        this.sort = sort;
    }
}
