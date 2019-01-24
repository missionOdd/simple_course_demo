package com.mission.course.config;

import com.mission.course.common.filter.CustomRolesAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mission
 * @date 2018/10/25 0025-9:26
 */
@Configuration
@Slf4j
public class ShiroConfig {

  /**
   * 创建ShiroFilterFactoryBean
   */
  @Bean
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    //关联安全管理器
    shiroFilterFactoryBean.setSecurityManager(securityManager);


    //过滤器拦截
    /**
     * anon: 无需认证才可以访问
     * authc:必须认证才可以访问
     * user:如果使用rememberMe 的功能可以直接访问
     * perms :该资源必须有资源权限才可以访问
     * role:该资源必须得到角色权限才可以访问
     */

    //自定义Filter
    Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
    filters.put("rolersOr",new CustomRolesAuthorizationFilter());
    shiroFilterFactoryBean.setFilters(filters);

    //设置过滤器,LinkedHashMap保证顺序
    Map<String,String> filterMap = new LinkedHashMap<>();
    filterMap.put("/admin/**","rolersOr[author,admin]");
    //修改登录页面
    shiroFilterFactoryBean.setLoginUrl("/login");


    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
    log.debug("Shiro拦截器工厂类");
    return shiroFilterFactoryBean;
  }


  /**
   * 创建DefaultWebSecurityManager
   */
  @Bean("securityManager")
  public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userShiroRealm") UserShiroRealm userShiroRealm){
  DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
  //关联realm
    securityManager.setRealm(userShiroRealm);
    return securityManager;
  }

  /**
   * 创建Realm
   */
  @Bean("userShiroRealm")
  public UserShiroRealm getUserRealm(){
    return new UserShiroRealm();
  }


  /**
   * 设置记住我cookie过期时间
   * @return
   */
  @Bean
  public SimpleCookie remeberMeCookie(){
    log.debug("记住我，设置cookie过期时间");
  //cookie名称;对应前端的checkbox的name = rememberMe
    SimpleCookie scookie=new SimpleCookie("rememberMe");
  //记住我cookie生效时间1小时 ,单位秒 [1小时]
    scookie.setMaxAge(3600);
    return scookie;
  }

  /**
   * 配置cookie记住我管理器
   */
  @Bean
  public CookieRememberMeManager rememberMeManager(){
    log.debug("配置cookie记住我管理器");
    CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(remeberMeCookie());
    //rememberMe cookie加密的密钥 默认AES算法 密钥长度(128 256 512 位)
    //cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
    return cookieRememberMeManager;
  }




}
