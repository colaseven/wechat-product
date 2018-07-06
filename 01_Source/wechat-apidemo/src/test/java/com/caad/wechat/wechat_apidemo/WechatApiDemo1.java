package com.caad.wechat.wechat_apidemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.caadt.cln.common.util.FileUtil;
import com.caadt.cln.common.util.HttpClientUtil;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

public class WechatApiDemo1 extends TestCase {

	private static Log log = LogFactory.getLog(WechatApiDemo1.class);

	WechatApiAuth0 wechatApiAuth0 = new WechatApiAuth0(); // weixin系统的每个登录用户的对象（包括：username,
	// password, token）

	@Override
	public void setUp() throws IOException {
		this.wechatApiAuth0.genUserToken();// 预先认证用户信息，获取token以供后续操作时多次使用，在header中使用到token等信息。
	}

	/**
	 * 查询小区列表
	 *
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {
		String url = Constant.HTTP_URL + "/api/Project/Project/ListBySorl";
		Map<String, String> headerparams = this.wechatApiAuth0.getHeaderParams();
		Map<String, String> params = new HashMap<>();
		params.put("PageSize", "10");
		params.put("Search.RegionCode", "76");// 城市编码 76代表上海
		params.put("Search.Text", "金龙花园");// search.text:查询条件
		String result = HttpClientUtil.callMethodResponseBodyAsString(null, null, 0, url, params, "GET", headerparams, null);
		FileUtil.setContentString("D:/data1.json", result, "UTF-8");
		log.info("返回数据文本：" + result);
	}

	/**
	 * 获取小区信息（用于小区信息界面）
	 *
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Test
	public void test2() throws UnsupportedEncodingException, IOException {
		String url = Constant.HTTP_URL + "/api/Project/Project/GetProjectInfo";
		Map<String, String> headerparams = this.wechatApiAuth0.getHeaderParams();
		Map<String, String> params = new HashMap<>();
		params.put("communityId", "714659882280429226");// 小区ID，金龙花园
		params.put("propertyTypeId", "92");// 二级物业类型 住宅-92，写字楼-25
		String result = HttpClientUtil.callMethodResponseBodyAsString(null, null, 0, url, params, "GET", headerparams, null);
		FileUtil.setContentString("D:/data2.json", result, "UTF-8");
		log.info("返回数据文本：" + result);
	}

	/**
	 * 获取小区信息（用于价格评估界面）
	 *
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Test
	public void test3() throws UnsupportedEncodingException, IOException {
		String url = Constant.HTTP_URL + "/api/Inquiry/Inquiry/Inquiry";
		Map<String, String> headerparams = this.wechatApiAuth0.getHeaderParams();
		Map<String, String> params = new HashMap<>();
		params.put("propertyId", "92");// 二级物业类型 住宅-92，写字楼-25
		params.put("projectId", "714659882320000226");// 小区ID，仁和花苑
		// params.put("address", "绿苑小区");// 小区名址（搜索地址）
		// params.put("propertyType4", "{3}");// 四级物业
		String result = HttpClientUtil.callMethodResponseBodyAsString(null, null, 0, url, params, "GET", headerparams, null);
		FileUtil.setContentString("D:/data3.json", result, "UTF-8");
		log.info("返回数据文本：" + result);
	}

	/**
	 * 获取案例列表（用于相关案例界面）
	 *
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Test
	public void test4() throws UnsupportedEncodingException, IOException {
		String url = Constant.HTTP_URL + "/api/Inquiry/Inquiry/GetAuctionCaseList";
		Map<String, String> headerparams = this.wechatApiAuth0.getHeaderParams();
		Map<String, String> params = new HashMap<>();
		params.put("pageSize", "10");// 每页显示条数
		params.put("propertyType", "92");// 二级物业类型 住宅-92，写字楼-25
		params.put("Search.CommunityId", "714659882320000226");// 小区ID
		params.put("Search.RegionCode", "76");
		params.put("Sort.Id", "");// 排序字段
		params.put("Sort.listedPrice", "true");// 挂牌/单价,使用挂牌单价排序，ture：排序，null：不使用
		params.put("Sort.listedTotlePrice", "");// 挂牌总价,使用挂牌总价排序，ture：排序，null：不使用
		params.put("Sort.listedTime", "");// 挂牌时间 使用挂牌时间排序，ture：排序，null：不使用
		String result = HttpClientUtil.callMethodResponseBodyAsString(null, null, 0, url, params, "GET", headerparams, null);
		FileUtil.setContentString("D:/data4.json", result, "UTF-8");
		log.info("返回数据文本：" + result);
	}

	/**
	 * 获得估价结果（用于点击估价按钮）
	 *
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Test
	public void test5() throws UnsupportedEncodingException, IOException {
		String url = Constant.HTTP_URL + "/api/Inquiry/Inquiry/MakeAssessmentPrice";
		Map<String, String> data = new HashMap<>();
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
		log.info("请求参数：" + postcontent);
		Map<String, String> headerparams = this.wechatApiAuth0.getHeaderParams();
		Map<String, String> params = new HashMap<>();
		String result = HttpClientUtil.callMethodResponseBodyAsString(null, null, 0, url, params, "POSTCONTENT", headerparams, postcontent);
		FileUtil.setContentString("D:/data5.json", result, "UTF-8");
		log.info("返回数据文本：" + result);
	}

	@Test
	public void test7() throws UnsupportedEncodingException, IOException {
		test3();
	}

}
