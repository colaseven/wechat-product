package com.caad.wechat.controller;

import com.caad.wechat.api.WechatConstant;
import com.caad.wechat.model.viss.aritificial.DataSourceModel;
import com.caad.wechat.model.viss.aritificial.EntrustResultModel;
import com.caad.wechat.model.viss.aritificial.NodeModel;
import com.caad.wechat.model.viss.aritificial.WeChatEntrustInformationParam;
import com.caad.wechat.model.viss.aritificial.WeChatEntrustinfomationResult;
import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.model.viss.common.PagedList;
import com.caad.wechat.model.viss.product.EntrustQueryModel;
import com.caad.wechat.model.viss.product.EntrustSearchModel;
import com.caad.wechat.service.wechat.IAritificialService;
import com.caad.wechat.utils.viss.CommonUtil;
import com.caad.wechat.utils.viss.NetUtils;
import com.caad.wechat.utils.viss.StringUtils;
import com.caad.wechat.utils.wechat.AuthorizationUtil;
import com.caad.wechat.utils.wechat.WechatUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 快捷询价
 */
@RequestMapping("/quick")
@Controller
public class QuickController {

    private static Log log = LogFactory.getLog(QuickController.class);

    /**
     * 微信正式环境访问地址
     */
    @Value("#{settings['viss.baseurl']}")
    private String VISS_BASEURL;

    /**
     * 微信授权工具类
     */
    @Autowired
    private AuthorizationUtil authorizationUtil;

    /**
     * 接口注入
     */
    @Autowired
    @Resource(name = "aritificialService")
    private IAritificialService aritificialService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private WechatUtil wechatUtil;

    // 验证码过期时间，秒
    private final static float LIMIT_TIME = 120.0f;


    /**
     * 获取省市区
     */

    @RequestMapping(value = "/areas", method = RequestMethod.GET)
    @ResponseBody
    public List<NodeModel> getAreas(@RequestParam(value = "id", defaultValue = "0") String id,
                                    @RequestParam(value = "level") String level,
                                    @RequestParam(value = "propertyId") String propertyId) {
        List<NodeModel> areas = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(aritificialService.getAreas(propertyId, level, Long.parseLong(id)));
        for (Object object : jsonArray) {
            areas.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), NodeModel.class));
        }
        log.debug("[quick]：获取省市区返回结果" + JSONArray.fromObject(areas));
        return areas;
    }


    /**
     * 获取物业类型选择项
     */
    @RequestMapping("/getSelect")
    @ResponseBody
    public Map<String, List<NodeModel>> getSelect() {
        Map<String, List<NodeModel>> map = new HashMap<>();
        Map<String, List<NodeModel>> result = aritificialService.getSelect();
        for (Map.Entry<String, List<NodeModel>> entry : result.entrySet()) {
            List<NodeModel> list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(entry.getValue());
            for (Object object : jsonArray) {
                list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), NodeModel.class));
            }
            map.put(entry.getKey(), list);
        }
        log.debug("[quick]：获取物业类型选择项返回结果" + JSONObject.fromObject(map));
        return map;
    }


    /**
     * 获取附件类型
     */
    @RequestMapping("/getAttachmentTypes")
    @ResponseBody
    public List<DataSourceModel> getAttachmentTypes() {
        List<DataSourceModel> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(aritificialService.getAttachmentTypes());
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), DataSourceModel.class));
        }
        log.debug("[quick]：获取附件类型返回结果" + JSONArray.fromObject(list));
        return list;
    }


    /**
     * 文件上传
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject fileUpload(MultipartFile file,
                                 HttpServletRequest request,
                                 @RequestParam(value = "pictureType", defaultValue = "") String pictureType) throws Exception {
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        log.info("pathRoot===============================" + pathRoot);
        //String hostAddress = NetUtils.getLocalAddress().getHostAddress();
        //log.info("hostAddress=========================" + hostAddress);
        //String basePath = request.getScheme() + "://" + hostAddress + ":" + request.getServerPort() + request.getContextPath() + "/";
        String originalFilename = file.getOriginalFilename();
        Map<String, Object> result = new HashMap<>();
        if (!file.isEmpty()) {
            log.info("[quick]：文件上传开始：" + originalFilename);
            //生成uuid作为文件名称
            String uuid = System.currentTimeMillis() + UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名称
            String imageName = contentType.substring(contentType.indexOf("/") + 1);
            String path = "attachment/" + uuid + "." + imageName;
            file.transferTo(new File(pathRoot + path));
            result.put("name", originalFilename);
            result.put("pictureType", pictureType);
            result.put("filePath", VISS_BASEURL + path);
            JSONObject json = JSONObject.fromObject(result);
            log.info("[quick]：文件上传结束：" + json);
            return json;
        }
        return null;
    }


    /**
     * 保存单据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public EntrustResultModel saveBaseInfo(@RequestParam("model") String model, @RequestParam("type") String type, HttpServletRequest request) {
        EntrustResultModel entrustResultModel = new EntrustResultModel();
        try {
            String appId = (String) request.getSession().getAttribute("appId");
            String openId = (String) request.getSession().getAttribute("openId");
            JSONObject json = JSONObject.fromObject(model);
            if (type.equals("entrust")) {
                String codeAndTime = (String) request.getSession().getAttribute(appId + "@verifyCode");
                String sessionMobile = codeAndTime.split("\\|")[0];
                String vcode = codeAndTime.split("\\|")[1];
                String sendTime = codeAndTime.split("\\|")[2];
                long checkTime = new Date().getTime();
                // 如果超时验证不通过
                if (StringUtils.isEmptyOrNull(codeAndTime)) {
                    entrustResultModel.setMessage("验证码输入错误，请重新输入！");
                    entrustResultModel.setState(40);
                    return entrustResultModel;
                }
                if (!vcode.equals(json.getString("verificationCode").trim())) {
                    entrustResultModel.setMessage("验证码输入错误，请重新输入！");
                    entrustResultModel.setState(40);
                    return entrustResultModel;
                }
                float btTime = (checkTime - Long.parseLong(sendTime)) / (1000);
                // 检测是否频繁发送
                if (LIMIT_TIME < btTime) {
                    entrustResultModel.setMessage("验证码过期，请重新获取！");
                    entrustResultModel.setState(40);
                    return entrustResultModel;
                }
                // 判断输入手机号码和获取验证码手机号码是否一致
                if (!sessionMobile.equals(json.getString("entrustMobilePhone"))) {
                    entrustResultModel.setMessage("手机号码修改后，请重新获取短信验证码！");
                    entrustResultModel.setState(40);
                    return entrustResultModel;
                }
            }
            JSONArray array = json.getJSONArray("imgs");
            if (array.size() > 1) {
                for (int i = 0; i < array.size(); i++) {
                    array.getJSONObject(i).remove("id");
                }
            }
            WeChatEntrustInformationParam weChatEntrustInformationParam = com.alibaba.fastjson.JSONObject.parseObject(json.toString(), WeChatEntrustInformationParam.class);
            weChatEntrustInformationParam.setOpenId(appId);
            weChatEntrustInformationParam.setUserOpenId(openId);
            OrganizationWeChatSettingResult organizationInfo = this.commonUtil.getOrganizationBySession(request, appId);
            String accessToken = this.wechatUtil.getAccessToken(appId, organizationInfo.getSecret());
            //String accessToken = "7_ubHTPiYVEATZse5tvuyQcF1DVVarVvYGdwpD-EkhbT1u2537LEQUub3DAWGSHLiwP0xHlc9yoQlXYqaSvuz8eLtUNOrOARMGhdCsMwCZI1aaKRFVTuEi6Pn6KBO4LmXrwlwr4cVO3V1qV1eXWUAbAHAGEK";
            JSONObject userInfo = this.wechatUtil.getWechatUserInfo(accessToken, openId);
            weChatEntrustInformationParam.setWeChatNickName(userInfo.getString("nickname"));
            weChatEntrustInformationParam.setWeChatAppName(organizationInfo.getAppName());
            log.debug("[quick]：保存快捷回价开始：" + JSONObject.fromObject(weChatEntrustInformationParam));
            entrustResultModel = aritificialService.saveBaseInfo(weChatEntrustInformationParam);
            log.debug("[quick]：保存快捷回价结束：" + JSONObject.fromObject(entrustResultModel));
        } catch (Exception e) {
            entrustResultModel.setState(40);
            entrustResultModel.setMessage(((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
            log.error(((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
        }
        return entrustResultModel;
    }


    /**
     * 询价历史记录列表入口
     */
    @RequestMapping("/historyInit")
    public String historyInit(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        String requestURI = VISS_BASEURL + "quick/historyIndex.do";
        return "redirect:" + WechatConstant.AUTHORIZATION_URL.replace("APPID", appId).replace("REDIRECT_URI", requestURI).replace("STATE", appId);
    }


    /**
     * 询价历史记录列表入口
     */
    @RequestMapping("/historyIndex")
    public String historyIndex(HttpServletRequest request) {
        String appId = request.getParameter("state");
        String code = request.getParameter("code");
        request.getSession().setAttribute("appId", appId);
        OrganizationWeChatSettingResult organizationBySession = commonUtil.getOrganizationBySession(request, appId);
        //根据code获取微信openId
        String openId = this.authorizationUtil.getOpenId(code, appId, organizationBySession.getSecret());//用户授权的openid
        request.getSession().setAttribute("openId", openId);
        return "redirect:/view/operatingRecord.html";
    }


    /**
     * 获取人工询价历史记录
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public PagedList<WeChatEntrustinfomationResult> getHistoryList(HttpServletRequest request,
                                                                   @RequestParam(value = "pageIndex") int pageIndex,
                                                                   @RequestParam(value = "pageSize") int pageSize,
                                                                   @RequestParam(value = "search", defaultValue = "") String search,
                                                                   @RequestParam(value = "templateTypeId", defaultValue = "") String templateTypeId) {
        EntrustSearchModel entrustSearchModel = new EntrustSearchModel();
        entrustSearchModel.setEntrustType(templateTypeId);
        entrustSearchModel.setUserOpenId((String) request.getSession().getAttribute("openId"));
        entrustSearchModel.setWeChatComCondition(search);
        EntrustQueryModel params = new EntrustQueryModel();
        params.setPageIndex(pageIndex);
        params.setPageSize(pageSize);
        params.setSearch(entrustSearchModel);
        PagedList<WeChatEntrustinfomationResult> historyList = aritificialService.getHistoryList(params);
        List<WeChatEntrustinfomationResult> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(historyList.getItems());
        for (Object object : jsonArray) {
            list.add(com.alibaba.fastjson.JSONObject.parseObject(object.toString(), WeChatEntrustinfomationResult.class));
        }
        historyList.setTemplateTypes(historyList.getTemplateTypes());
        historyList.setItems(list);
        log.debug("[quick]：获取人工询价历史记录结果：" + JSONArray.fromObject(historyList));
        return historyList;
    }


    /**
     * 获取单据详情
     */
    @RequestMapping(value = "/particulars", method = RequestMethod.GET)
    @ResponseBody
    public WeChatEntrustinfomationResult getParticulars(HttpServletRequest request,
                                                        @RequestParam(value = "entrustId") long entrustId,
                                                        @RequestParam(value = "appId", defaultValue = "") String appId) {
        String telephone = "";
        if (StringUtils.isNotEmptyOrNull(appId)) {
            HttpSession session = request.getSession();
            session.invalidate();
            OrganizationWeChatSettingResult organizationBySession = commonUtil.getOrganizationBySession(request, appId);
            telephone = organizationBySession.getTelephone();
            request.getSession().setAttribute("appId", appId);
        }
        WeChatEntrustinfomationResult entrustInfo = aritificialService.getEntrustInfo(entrustId);
        if (StringUtils.isNotEmptyOrNull(telephone)) {
            entrustInfo.setTelephone(telephone);
        }
        log.debug("[quick]：获取单据详情结果：" + JSONObject.fromObject(entrustInfo));
        return entrustInfo;
    }


//    /**
//     * fastDFS文件上传
//     */
//    @ResponseBody
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public Map<String, Object> uploadFile(MultipartFile uploadFile) {
//        Map<String, Object> result = new HashMap<>();
//        String storagePath = null;
//        String originalFilename;
//        String url;
//        try {
//            //接收上传的文件
//            originalFilename = uploadFile.getOriginalFilename();
//            String extname = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//            //path : group/m...
//            storagePath = FastDFSUtils.uploadFile(uploadFile.getBytes(), extname);
//            url = FastDFSUtils.getServerPath() + storagePath;
//            if (storagePath == null) {
//                log.error("文件上传失败！");
//                result.put("success", false);
//                result.put("data", "文件上传失败！");
//                return result;
//            }
//
//        } catch (Exception e) {
//            log.error("文件上传失败", e);
//            //异常，删除已上传的文件（如果已上传）
//            FastDFSUtils.deleteFile(storagePath);
//            result.put("success", false);
//            result.put("data", "文件上传失败！");
//            return result;
//        }
//        result.put("success", true);
//        result.put("data", url);
//        result.put("fileName", originalFilename);
//        return result;
//    }
//
//
//    /**
//     * 删除文件
//     */
//    @ResponseBody
//    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
//    public void deleteFile(String fileName) {
//        try {
//            FastDFSUtils.deleteFile(fileName);
//        } catch (Exception e) {
//            log.error("删除文件失败", e);
//        }
//    }
}
