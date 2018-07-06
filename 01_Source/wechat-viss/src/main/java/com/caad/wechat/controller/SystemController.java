package com.caad.wechat.controller;


import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.viss.common.PagedList;
import com.caad.wechat.model.viss.systemAssessment.AssessmentPriceExt;
import com.caad.wechat.model.viss.systemAssessment.AssessmentResult;
import com.caad.wechat.model.viss.systemAssessment.DataItem;
import com.caad.wechat.model.viss.systemAssessment.DataItemExt;
import com.caad.wechat.model.viss.systemAssessment.GetInquryParam;
import com.caad.wechat.model.viss.systemAssessment.InquiryHistoryModel;
import com.caad.wechat.model.viss.systemAssessment.InquiryHistoryResultModel;
import com.caad.wechat.model.viss.systemAssessment.InquiryHistorySearchModel;
import com.caad.wechat.model.viss.systemAssessment.InquiryInfoParam;
import com.caad.wechat.model.viss.systemAssessment.MInquiryModel;
import com.caad.wechat.model.viss.systemAssessment.ProjectInfoResult;
import com.caad.wechat.model.viss.systemAssessment.ResidentProjectGroupDto;
import com.caad.wechat.model.viss.systemAssessment.ResidentProjectGroupInfoResult;
import com.caad.wechat.model.viss.systemAssessment.RoomInfo;
import com.caad.wechat.model.viss.systemAssessment.Rooms;
import com.caad.wechat.model.viss.systemAssessment.SearchInquiryModel;
import com.caad.wechat.model.viss.systemAssessment.SearchInquiryParam;
import com.caad.wechat.model.viss.systemAssessment.SystemAssessmentResult;
import com.caad.wechat.model.viss.systemAssessment.UiTemplate;
import com.caad.wechat.service.wechat.ISystemAssessmentService;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.viss.PhonePatternUtil;
import com.caad.wechat.utils.viss.StringUtils;
import com.caad.wechat.utils.wechat.WechatUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统询价
 */
@RequestMapping("/system")
@Controller
public class SystemController {

    private static Log log = LogFactory.getLog(SystemController.class);

    @Autowired
    @Resource(name = "systemAssessmentService")
    private ISystemAssessmentService systemAssessmentService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private WechatUtil wechatUtil;


    /**
     * 搜索小区、楼栋、单套
     *
     * @param name             查询的字符
     * @param propertyTypeCode 物业类型 从登录的物业类型的code中获取
     * @param regionCode       市 从当前城市中选择
     * @return JSONArray
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<InquiryInfoParam> getSystemAppraisalForSearch(@RequestParam(value = "name") String name,
                                                              @RequestParam(value = "propertyTypeCode") String propertyTypeCode,
                                                              @RequestParam(value = "regionCode") String regionCode) {
        SearchInquiryModel searchInquiryModel = new SearchInquiryModel();
        searchInquiryModel.setName(name);//查询的字符
        searchInquiryModel.setPropertyTypeCode(propertyTypeCode);//物业类型 从登录的物业类型的code中获取
        searchInquiryModel.setRegionCode(regionCode);//市 从当前城市中选择
        searchInquiryModel.setPageSize(20);
        List<InquiryInfoParam> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(this.systemAssessmentService.searcharAddress(searchInquiryModel));
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), InquiryInfoParam.class));
        }
        log.debug("[system]：搜索小区、楼栋、单套数据结果：" + JSONArray.fromObject(list));
        return list;
    }


    /**
     * 系统询价初始化
     */
    @RequestMapping(value = "/inquiry", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject initAppraisalInfo(GetInquryParam params) {

        Map<String, Object> resultMap = new HashMap<>();//返回结果

        List<UiTemplate> upList = new ArrayList<>(); // 普通选项的数据

        List<UiTemplate> downList = new ArrayList<>();// 高级选项的数据

        List<DataItem> pdList = new ArrayList<>();// 高级选项提交需要用到的数据

        List<UiTemplate> mList = new ArrayList<>(); // 所有codens的内容

        String householdsName = "";

        String floorUnit = "";

        String roomUnit = "";
        log.debug("[system]：获取初始化询价请求参数：" + JSONObject.fromObject(params));
        MInquiryModel inquiry = systemAssessmentService.inquiry(params);
        log.debug("[system]：获取初始化询价数据结果：" + JSONObject.fromObject(inquiry));
        List<DataItemExt> unitList = inquiry.getUnitId();//楼栋列表
        if (unitList != null && unitList.size() != 0) {
            for (DataItemExt dataItemExt : unitList) {
                if (StringUtils.isNotEmptyOrNull(dataItemExt.getName()) && dataItemExt.getName().equals(inquiry.getUnitName())) {
                    floorUnit = StringUtils.isNotEmptyOrNull(dataItemExt.getFloorUnit()) ? dataItemExt.getFloorUnit() : "";// 单元楼层
                    roomUnit = StringUtils.isNotEmpty(dataItemExt.getRoomUnit()) ? dataItemExt.getRoomUnit() : "";
                }
            }
        }
        Rooms roomNo = inquiry.getRoomNo();//室号
        if (roomNo != null) {
            List<RoomInfo> roomInfos = roomNo.getRoomInfos();
            for (RoomInfo roomInfo : roomInfos) {
                String unitNumber = StringUtils.isNotEmptyOrNull(roomInfo.getUnitNumber()) ? roomInfo.getUnitNumber() : "";
                String roomNumber = StringUtils.isNotEmptyOrNull(roomInfo.getRoomNumber()) ? roomInfo.getRoomNumber() : "";
                String propertyType4 = StringUtils.isNotEmptyOrNull(roomInfo.getPropertyType4()) ? roomInfo.getPropertyType4() : "";
                if (roomNumber.equals(inquiry.getRoomName()) && roomInfo.getRoomId().equals(inquiry.getRoomId()) || (propertyType4.equals(inquiry.getPropertyTypeCode()) && roomInfo.getRoomId().equals(inquiry.getRoomId()))) {
                    if (StringUtils.isNotEmptyOrNull(unitNumber)) {
                        householdsName = unitNumber;// 室号
                        householdsName += StringUtils.isNotEmptyOrNull(floorUnit) ? floorUnit : "-";
                    }
                    break;
                }
            }
        }
        List<DataItem> dataItems = inquiry.getPropertyDataSource();
        if (!StringUtils.isEmptyOrNull(dataItems)) {
            pdList.addAll(dataItems);// 物业类型
        }
        mList.addAll(inquiry.getCodens());
        for (UiTemplate uiTemplate : mList) {
            if (uiTemplate.getDisplayName().equalsIgnoreCase("up")) {
                upList.add(uiTemplate);
            } else if (uiTemplate.getDisplayName().equalsIgnoreCase("down")) {
                downList.add(uiTemplate);
            }
        }
        //基础数据
        Map<String, Object> baseData = this.initPostList(upList);
        // 高级选项
        Map<String, Object> advancedData = this.initAdvancedList(downList);
        String roomName = getUnitName(inquiry, householdsName, roomUnit);
        String unitName = StringUtils.isNotEmptyOrNull(inquiry.getUnitName()) ? inquiry.getUnitName() : "";
        resultMap.put("towardList", baseData.get("towardList"));
        resultMap.put("completionYear", baseData.get("CompletionYear"));// 建成年份
        resultMap.put("floor", baseData.get("FloorId"));// 楼层
        resultMap.put("structureArea", baseData.get("StructureArea"));// 面积
        resultMap.put("toward", baseData.get("Toward"));// 朝向
        resultMap.put("floorList", inquiry.getTotalFloor());// 楼层list
        resultMap.put("upFloor", inquiry.getTotalFloor().size() > 0 ? inquiry.getTotalFloor().get(1) : "");// 地上层数
        resultMap.put("provinceCode", inquiry.getProvinceCode());//省
        resultMap.put("cityCode", inquiry.getRegionCode());// 市
        resultMap.put("countyCode", inquiry.getCountyCode());// 区县编码
        resultMap.put("communityCode", inquiry.getCommunityId());// 小区ID
        resultMap.put("unitCode", inquiry.getUnitNo());// 楼栋ID
        resultMap.put("roomCode", inquiry.getRoomId());// 单套ID
        resultMap.put("communityName", inquiry.getProjectName());// 小区名称
        resultMap.put("propertyId", inquiry.getPropertyId());// 二级物业
        resultMap.put("floorName", inquiry.getUnitName());// 楼栋名址
        resultMap.put("sessionGuid", inquiry.getSessionGuid());
        resultMap.put("propertyType", getProperType(inquiry, pdList));// 物业类型
        resultMap.put("unitName", unitName);// 楼栋
        resultMap.put("roomName", roomName);// 室号
        resultMap.put("address", StringUtils.isEmptyOrNull(unitName) ? inquiry.getAddress() : unitName + roomName);// 详细地址
        resultMap.put("advanced", advancedData);// 高级选项
        resultMap.put("floorUnit", floorUnit);//
        resultMap.put("roomUnit", roomUnit);
        log.debug("[system]：获取初始化询价数据返回：" + JSONObject.fromObject(resultMap));
        return commonUtil.convertFieldNameForJSONObject(JSONObject.fromObject(resultMap));
    }


    /**
     * 获取楼栋信息
     */
    @RequestMapping(value = "/floors", method = RequestMethod.POST)
    @ResponseBody
    public List<DataItemExt> getResidentFloors(SearchInquiryParam param) {
        log.debug("[system]：获取楼栋信息请求参数：" + JSONObject.fromObject(param));
        List<DataItemExt> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(this.systemAssessmentService.searchFloors(param));
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), DataItemExt.class));
        }
        log.debug("[system]：获取楼栋信息数据返回：" + JSONArray.fromObject(list));
        return list;
    }


    /**
     * 获取单套信息
     */
    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    @ResponseBody
    public List<RoomInfo> getResidentRooms(SearchInquiryParam param) {
        log.debug("[system]：获取单套信息请求参数：" + JSONObject.fromObject(param));
        List<RoomInfo> list = new ArrayList<>();
        Rooms room = this.systemAssessmentService.getResidentRoom(param);
        if (!StringUtils.isEmptyOrNull(room)) {
            JSONArray jsonArray = JSONArray.fromObject(room.getRoomInfos());
            for (Object object : jsonArray) {
                JSONObject jsonObject = JSONObject.fromObject(object);
                String roomName = "";
                String unitNumber = jsonObject.getString("unitNumber");
                String roomNumber = jsonObject.getString("roomNumber");
                if (StringUtils.isNotEmptyOrNull(unitNumber)) {
                    roomName = unitNumber + (StringUtils.isNotEmptyOrNull(param.getFloorUnit()) ? param.getFloorUnit() : "-");
                    if (StringUtils.isNotEmptyOrNull(roomNumber)) {
                        roomName += roomNumber + (StringUtils.isNotEmptyOrNull(param.getRoomUnit()) ? param.getRoomUnit() : "");
                    }
                } else {
                    roomName += roomNumber + (StringUtils.isNotEmptyOrNull(param.getRoomUnit()) ? param.getRoomUnit() : "");
                }
                RoomInfo roomInfo = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), RoomInfo.class);
                roomInfo.setRoomName(roomName);
                list.add(roomInfo);
            }
        }
        log.debug("[system]：获取单套信息数据返回：" + JSONArray.fromObject(list));
        return list;
    }


    /**
     * 系统询价
     */
    @RequestMapping(value = "/makeAssessmentPrice", method = RequestMethod.POST)
    @ResponseBody
    public AssessmentPriceExt makeAssessmentPrice(@RequestParam(value = "model") String model, HttpServletRequest request) {
        log.debug("[system]：系统询价请求参数：" + model);
        AssessmentResult assessmentResult = com.alibaba.fastjson.JSONObject.parseObject(model, AssessmentResult.class);
        String appId = (String) request.getSession().getAttribute("appId");
        String openId = (String) request.getSession().getAttribute("openId");
        assessmentResult.setAppId(appId);
        assessmentResult.setUserOpenId(openId);
        OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
        String accessToken = this.wechatUtil.getAccessToken(appId, organizationInfo.getSecret());
        //String accessToken = "7_E2DJS-PqIkDSjhaW0kmSRulEcFb303KoeXc56bMLaSFEwmnj-WiNR8MQ5k1wQviNZja896jonJFI42XmvV5tKuq2TSZamkxTWqGK2-8Cm1mUZ8HM6CIV0SMe7prIewAH4QSbA6LwgT6xBAL_MUUgADAIQQ";
        JSONObject userInfo = this.wechatUtil.getWechatUserInfo(accessToken, openId);
        assessmentResult.setWeChatNickName(userInfo.getString("nickname"));
        assessmentResult.setWeChatAppName(organizationInfo.getAppName());
        AssessmentPriceExt assessmentPriceExt = this.systemAssessmentService.makeAssessmentPrice(assessmentResult);
        String userCollocation = assessmentPriceExt.getUserCollocation();
        if (StringUtils.isNotEmptyOrNull(userCollocation)) {
            assessmentPriceExt.setUserCollocation(PhonePatternUtil.phoneNumberPattern(userCollocation));
        } else {
            assessmentPriceExt.setUserCollocation("");
        }
        log.debug("[system]：系统询价返回结果：" + JSONObject.fromObject(assessmentPriceExt));
        return assessmentPriceExt;
    }


    /**
     * 获取小区信息、图片
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ProjectInfoResult getProjectInfo(@RequestParam(value = "propertyId") int propertyId,
                                            @RequestParam(value = "communityId") String communityId) {
        ProjectInfoResult projectInfoResult = this.systemAssessmentService.getProjectInfo(propertyId, communityId);
        ResidentProjectGroupInfoResult projectData = projectInfoResult.getProjectData();
        List<ResidentProjectGroupDto> list = new ArrayList<>();
        List<ResidentProjectGroupDto> groupList = projectData.getGroupFeildList();
        //对小区信息值为空不作显示
        for (ResidentProjectGroupDto projectGroupDto : groupList) {
            if (StringUtils.isNotEmptyOrNull(projectGroupDto.getFieldValue())) {
                list.add(projectGroupDto);
            }
        }
        projectData.setGroupFeildList(list);
        log.debug("[system]：获取小区信息、图片返回结果：" + JSONObject.fromObject(projectInfoResult));
        return projectInfoResult;
    }


    /**
     * 获取系统询价列表
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public PagedList<InquiryHistoryResultModel> historyList(HttpServletRequest request,
                                                            @RequestParam(value = "pageIndex") int pageIndex,
                                                            @RequestParam(value = "pageSize") int pageSize,
                                                            @RequestParam(value = "search", defaultValue = "") String search) {

        InquiryHistorySearchModel inquiryHistorySearchModel = new InquiryHistorySearchModel();
        inquiryHistorySearchModel.setWeChatComCondition(search);//搜索条件
        inquiryHistorySearchModel.setUserOpenId((String) request.getSession().getAttribute("openId"));
        InquiryHistoryModel params = new InquiryHistoryModel();
        params.setPageIndex(pageIndex);
        params.setPageSize(pageSize);
        params.setSearch(inquiryHistorySearchModel);
        PagedList<InquiryHistoryResultModel> modelPagedList = this.systemAssessmentService.historyList(params);
        List<InquiryHistoryResultModel> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(modelPagedList.getItems());
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), InquiryHistoryResultModel.class));
        }
        modelPagedList.setTemplateTypes(modelPagedList.getTemplateTypes());
        modelPagedList.setItems(list);
        log.debug("[system]：获取系统询价列表结果：" + JSONArray.fromObject(list));
        return modelPagedList;
    }


    /**
     * 系统询价详情
     */
    @RequestMapping(value = "/particulars", method = RequestMethod.GET)
    @ResponseBody
    public SystemAssessmentResult getParticulars(HttpServletRequest request,
                                                 @RequestParam("id") String id,
                                                 @RequestParam("propertyId") String propertyId) {
        GetInquryParam param = new GetInquryParam();
        param.setHistoryId(id);
        param.setPropertyId(propertyId);
        param.setOpenId((String) request.getSession().getAttribute("appId"));
        param.setUserOpenId((String) request.getSession().getAttribute("openId"));
        log.debug("[system]：获取系统询价详情请求参数：" + JSONObject.fromObject(param));
        JSONObject jsonObject = JSONObject.fromObject(systemAssessmentService.getSystemAssessmentDetail(param));
        SystemAssessmentResult inquiry = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), SystemAssessmentResult.class);
        String userCollocation = inquiry.getUserCollocation();
        if (StringUtils.isNotEmptyOrNull(userCollocation)) {
            inquiry.setUserCollocation(PhonePatternUtil.phoneNumberPattern(inquiry.getUserCollocation()));
        } else {
            inquiry.setUserCollocation("");
        }
        log.debug("[system]：获取系统询价详情结果：" + JSONObject.fromObject(inquiry));
        return inquiry;
    }


//---------------------------------------------------------------处理初始化系统询价数据----------------------------------------------------------------------------

    /**
     * 处理高级选项
     */
    private Map<String, Object> initAdvancedList(List<UiTemplate> downList) {

        Map<String, Object> map = new HashMap<>();
        for (UiTemplate uiTemplate : downList) {
            List<DataItem> dataSource = uiTemplate.getDataSource();
            if (dataSource == null || dataSource.size() == 0)
                continue;
            Object codesValue = uiTemplate.getValue();
            if (!StringUtils.isEmptyOrNull(codesValue)) {
                if (codesValue instanceof String) {
                    String dataSourceValue = genDataSource(dataSource, codesValue);
                    if (StringUtils.isEmptyOrNull(dataSourceValue)) {
                        continue;
                    }
                    map.put(uiTemplate.getFieldName(), dataSourceValue);
                } else {
                    List<String> list1 = new ArrayList<>();
                    for (Object object : (List) codesValue) {
                        String value = genDataSource(dataSource, object);
                        if (StringUtils.isEmptyOrNull(value))
                            continue;
                        list1.add(value);
                    }
                    if (list1.size() <= 0)
                        continue;
                    map.put(uiTemplate.getFieldName(), list1);
                }
            }
        }
        return map;
    }

    private String genDataSource(List<DataItem> dataSource, Object codesValue) {

        for (DataItem source : dataSource) {
            if (source.getValue() != null && !source.getValue().toString().equals(codesValue.toString()))
                continue;
            return source.getName();
        }
        return null;
    }


    /**
     * 处理基础数据
     */
    private Map<String, Object> initPostList(List<UiTemplate> upList) {
        Map<String, Object> map = new HashMap<>();
        Object postValue = null;
        String sValue = "";
        for (UiTemplate uiTemplate : upList) {
            String fieldName = uiTemplate.getFieldName();
            Object value = uiTemplate.getValue();
            List<DataItem> dataSource = new ArrayList<>();
            dataSource.addAll(uiTemplate.getDataSource());
            if (value == null) {
                postValue = "";
            } else {
                if (value instanceof String) {// value是字符串
                    sValue = value.toString();
                    if (dataSource.size() > 0 && !uiTemplate.getDisplayType().equals("TextBox")) {
                        map.put("towardList", JSONArray.fromObject(dataSource));
                    }
                    for (DataItem aDataSource : dataSource) {
                        if (aDataSource.getValue() != null) {
                            if (sValue.equals(aDataSource.getValue().toString())) {
                                postValue = aDataSource.getName();
                                break;
                            } else {
                                if (!sValue.equals(""))
                                    postValue = aDataSource.getName();
                            }
                        } else {
                            postValue = sValue;
                        }
                    }
                    if (dataSource.size() == 0) {
                        postValue = sValue;
                    }
                    map.put(fieldName, postValue);
                } else {// value数组
                    String tvalue = value.toString().substring(1, value.toString().length() - 1);
                    String[] str;
                    str = tvalue.split(",");
                    List<String> values = Arrays.asList(str);
                    String[] postArray = new String[values.size()];
                    for (int j = 0; j < values.size(); j++) {
                        for (DataItem aDataSource : dataSource) {
                            if (values.get(j) != null && aDataSource.getValue() != null) {
                                String value1 = values.get(j);
                                if (value1.startsWith("\"") && value1.endsWith("\"")) {
                                    value1 = value1.substring(1, value1.length() - 1);
                                }
                                if (value1.equals(aDataSource.getValue().toString())) {
                                    sValue += aDataSource.getName() + ",";
                                    postArray[j] = aDataSource.getName();
                                }
                            }
                        }
                    }
                    if (sValue != null && !sValue.equals("")) {
                        if (sValue.trim().endsWith(",")) {
                            sValue = sValue.substring(0, sValue.length() - 1);
                        }
                        // 所在楼层不能只显示一层，现在就算选择一层也会显示1,1 要只显示1
                        if (postArray.length == 2) {
                            if (postArray[0].equals(postArray[1])) {
                                sValue = postArray[0];
                            }
                        }
                    } else {
                        sValue = values.get(0);
                        map.put(fieldName, sValue);
                        postArray = (String[]) values.toArray();
                    }
                    postValue = postArray;
                    map.put("floorList", postValue);
                }
            }
        }
        return map;
    }


    private String getUnitName(MInquiryModel inquiry, String householdsName, String roomUnit) {
        if (StringUtils.isEmptyOrNull(roomUnit)) {
            roomUnit = "";
        }
        if (StringUtils.isNotEmptyOrNull(inquiry.getRoomName())) {
            if (StringUtils.isNotEmptyOrNull(inquiry.getRoomName()) && inquiry.getRoomName().contains(roomUnit)) {
                return StringUtils.isNotEmptyOrNull(householdsName) ? householdsName + inquiry.getRoomName() : inquiry.getRoomName();
            } else {
                return StringUtils.isNotEmptyOrNull(householdsName) ? householdsName + inquiry.getRoomName() + roomUnit : inquiry.getRoomName() + roomUnit;
            }
        }
        return "";
    }

    /**
     * 根据不同信息匹配物业类型
     */
    private JSONArray getProperType(MInquiryModel inquiry, List<DataItem> pdList) {
        List<Map<String, Object>> propertyList = new ArrayList<>();
        if (StringUtils.isNotEmptyOrNull(inquiry.getPropertyTypeCode()) && StringUtils.isNotEmptyOrNull(inquiry.getPropertyTypeName())) {
            Map<String, Object> property = new HashMap<>();
            property.put("value", inquiry.getPropertyTypeCode());
            property.put("name", inquiry.getPropertyTypeName());
            propertyList.add(property);
            return JSONArray.fromObject(propertyList);
        }
        return JSONArray.fromObject(pdList);
    }
}
