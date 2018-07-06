package com.caad.wechat;

import com.caad.wechat.model.viss.LoginModel;
import com.caad.wechat.model.viss.common.QueryAreaModel;
import com.caad.wechat.service.portal.IPassportService;
import com.caad.wechat.utils.sdk.ProxyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ProxyTest {

    @Autowired
    @Resource(name = "passportService")
    private IPassportService passportService;

    @Test
    public void test() throws Exception {
        IPassportService passportService = ProxyFactory.resolveApiProxy(IPassportService.class.getName());
        LoginModel loginModel = new LoginModel();
        loginModel.setUserName("liming123");
        loginModel.setPassWord("NDVlY2JkNTliMGZmM2QyZWUxZTE4MTlmNjJhY2NlYzM");
        QueryAreaModel result = passportService.Login(loginModel);
        System.out.print(11111);
        System.out.print(result.toString());
    }

    @Test
    public void testPostWithSpring() throws Exception {

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName("liming123");
        loginModel.setPassWord("NDVlY2JkNTliMGZmM2QyZWUxZTE4MTlmNjJhY2NlYzM");
        QueryAreaModel result = passportService.Login(loginModel);
        System.out.print(11111);
        System.out.print(result.toString());
    }

    @Test
    public void testGetWithSpring() throws Exception {

        String result = passportService.getName(1);
        System.out.print(11111);
        System.out.print(result.toString());
    }

}
