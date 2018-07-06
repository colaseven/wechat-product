package com.caad.wechat.vissapi_sdkdemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.caadt.vissapi.sdk.ApiClient;
import com.caadt.vissapi.sdk.ApiException;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

public class WechatApiDemo1 extends TestCase {

    private static Log log = LogFactory.getLog(WechatApiDemo1.class);

    ApiClient apiclient;

    @Override
    protected void setUp() throws ApiException {
        this.apiclient = new ApiClient(Constant.HTTP_URL, Constant.USERNAME, Constant.PASSWORD, "ApidemoJava-Caad");
    }


    /**
     * 获取省列表
     */
    @Test
    public void testGetProvinces() throws IOException {
        Map<String, Object> params = new HashMap<>();
        JSONObject jsonobject = this.apiclient.get("/api/Entrust/Entrust/GetProvinces", params);
        log.info("返回数据文本：" + jsonobject.toString());
    }


    /**
     * 获取市列表
     */
    @Test
    public void testGetCity() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("provinceId", "10516");//76为上海
        JSONObject jsonobject = this.apiclient.get("/api/Entrust/Entrust/GetCitys", params);
        log.info("返回数据文本：" + jsonobject.toString());
    }


    /**
     * 查询小区列表(可查询出小区、楼栋、单套)
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        Map<String, Object> params = new HashMap<>();
        //PropertyTypeCode={0}&ProvinceCode={1}&RegionCode={2}&CountyCode={3}&Name={4}&PageSize={5}&PageIndex={6}
        params.put("PropertyTypeCode", "resident");//写死、住宅版
        params.put("ProvinceCode", "");//省
        params.put("RegionCode", "10716");//市
        params.put("CountyCode", "");//区
        params.put("Name", "蓝堡湾");//用户录入需要查询的字段
        params.put("PageSize", 20);
        params.put("PageIndex", 0);
        JSONObject jsonobject = this.apiclient.get("/api/Inquiry/Inquiry/GetListByAddress", params);
        log.info("返回数据文本：" + jsonobject.toString());
    }

    /**
     * 获取楼栋
     */
    @Test
    public void testGetFloors() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("PageSize", 20);
        params.put("RegionCode", 10864);//区
        params.put("PropertyId", 92);
        params.put("ProjectId", "1814876660590000109");//小区id
        params.put("PageIndex",1);


//        params.put("Name", "测试");
        String postcontent = JSONObject.fromObject(params).toString();
        JSONObject jsonobject = this.apiclient.post("/api/Inquiry/Inquiry/SearchFloors", postcontent);

        log.info("返回数据文本：" + jsonobject.toString());
    }


    /**
     * 获取单套
     */
    @Test
    public void testRooms() throws IOException {
        //http://172.16.2.3:8004/api/Inquiry/Inquiry/GetResidentRoom?propertyType=92&floorId=714651986930001109
        Map<String, Object> params = new HashMap<>();
        params.put("propertyType", 92);
        params.put("floorId", "714651986930001109");
        JSONObject jsonobject = this.apiclient.get("/api/Inquiry/Inquiry/GetResidentRoom", params);
        log.info("返回数据文本：" + jsonobject.toString());
    }


    /**
     * 获取详情小区信息（用于小区信息、小区图片界面）
     *
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @Test
    public void test2() throws UnsupportedEncodingException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("communityId", "714659882280429226");// 小区ID，金龙花园
        params.put("propertyTypeId", "92");// 二级物业类型 住宅-92，写字楼-25
        JSONObject jsonobject = this.apiclient.get("/api/Project/Project/GetProjectInfo", params);
        log.info("返回数据文本：" + jsonobject.toString());
    }


    /**
     * 获取小区信息（用于价格评估界面）
     *
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @Test
    public void test3() throws UnsupportedEncodingException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("propertyId", "92");// 二级物业类型 住宅-92，写字楼-25
        params.put("projectId", "714659882320000226");// 小区ID，仁和花苑
        JSONObject jsonobject = this.apiclient.get("/api/Inquiry/Inquiry/Inquiry", params);
        log.info("返回数据文本：" + jsonobject.toString());
    }


    /**
     * 获得估价结果（用于点击估价按钮）
     *
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @Test
    public void test5() throws UnsupportedEncodingException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("TotalFloor", "6");// 楼房总层
        data.put("CommunityId", "714659882320000226");// 小区ID,仁和花苑
        data.put("CompletionYear", "1995");// 建筑年份
        data.put("PropertyID", "92");// 二级物业类型 住宅-92，写字楼-25
        data.put("StructureArea", "55");// 建筑面积
        data.put("FloorId", "2");// 所在层数
        data.put("PropertyTypeId", "GY_001");// 物业类型编码
        data.put("Toward", "南");// 朝向
        data.put("RegionCode", "76");// 城市编码 76对应上海
        String postcontent = JSONObject.fromObject(data).toString();
        JSONObject jsonobject = this.apiclient.post("/api/Inquiry/Inquiry/MakeAssessmentPrice", postcontent);
        log.info("返回数据文本：" + jsonobject.toString());
    }

    /**
     * 评估结果
     * 必须先调用test3获取数据后才能进行评估test5
     *
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @Test
    public void test7() throws UnsupportedEncodingException, IOException {
        test3();
        test5();
    }


    /**
     * 试用申请
     *
     * @throws Exception
     */
    @Test
    public void testApply() throws Exception {

        URI uri = new URIBuilder().setScheme("http").setHost("172.16.2.6:8089").setPath("/Home/Save") //
                .setParameter("Name", "张三") //姓名
                .setParameter("Company", "上海中估联信息技术有限公司") //公司名称
                .setParameter("Position", "开发工程师") //职位
                .setParameter("Province", "10516") //省
                .setParameter("Region", "") //市区
                .setParameter("Contact", "15221312712").setParameter("SoureType", "1").build();//soureType 为通过微信提交过去的试用申请
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(new HttpPost(uri));
        String message = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject json = JSONObject.fromObject(message);
    }

}
