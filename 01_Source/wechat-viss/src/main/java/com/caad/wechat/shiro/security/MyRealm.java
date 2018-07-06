package com.caad.wechat.shiro.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;


public class MyRealm extends AuthorizingRealm {

    /**
     * 授权,提供用户信息返回权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Set<String> roleNames = new HashSet<String>();
//        roleNames.add("admin");
//        roleNames.add("guest");
//        Set<String> permissionNames = new HashSet<String>();
//        permissionNames.add("resource:*");
//        permissionNames.add("user:*");
//        // 将角色名称提供给info
//        authorizationInfo.setRoles(roleNames);
//        // 将权限名称提供给info
//        authorizationInfo.setStringPermissions(permissionNames);
//        //userEntity = (UserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
//        // log.info(JSONObject.fromObject(userEntity).toString());
        return authorizationInfo;
    }

    /**
     * 认证,提供账户信息返回认证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String account = usernamePasswordToken.getUsername();//获取账号
        String password = new String(usernamePasswordToken.getPassword());//获取密码
        //账号认证
//        ApiMessage<UserEntity> apiMessage = userApiService.login(account,password);//用户登录
//        if(apiMessage.getData() == null){//用户登录失败
//            log.error(apiMessage.getMessage());
//            throw new AccountException(apiMessage.getMessage());
//        }
//        UserEntity userEntity = apiMessage.getData();
//        ApiMessage<MenuEntity.MenuList> apiMenuMessage = userApiService.getMenus(userEntity);
//        MenuEntity.MenuList menus = apiMenuMessage.getData();
//        if(menus == null){
//            log.error(apiMenuMessage.getMessage());
//            throw new AccountException(apiMenuMessage.getMessage());
//        }
////        if(StringUtils.isNotEmptyOrNull(userEntity.getRoleNames())){
////            log.error("无登录权限");
////            throw new AccountException("无登录权限");
////        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", null);
        session.setTimeout(3600000);
        //SecurityUtils.getSubject().getSession().setAttribute("user",userEntity);
        return new SimpleAuthenticationInfo(account, password, getName());
    }
}
