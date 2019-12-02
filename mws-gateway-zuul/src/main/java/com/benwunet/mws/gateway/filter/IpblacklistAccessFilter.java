package com.benwunet.mws.gateway.filter;


import com.benwunet.mws.gateway.feign.SysUserClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * ip黑名单拦截
 * ip黑名单变化不会太频繁，<br>
 * 考虑到性能，我们不实时掉接口从别的服务获取了，<br>
 * 而是定时把黑名单ip列表同步到网关层,
 * @author xiangkaihong
 * @date 2019/5/4 10:41
 */

@Component
public class IpblacklistAccessFilter extends ZuulFilter {

    /**ip黑名单列表 */
    private Set<String> blackIPs = new HashSet<>();

    @Override
    public boolean shouldFilter() {
        if (blackIPs.isEmpty()) {
            return false;
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String ip = getIpAddress(request);
        /**判断ip是否在黑名单列表里*/
        return blackIPs.contains(ip);
    }

    @Override
    public Object run() {
        //System.out.println("AAAaaaSS");
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        requestContext.setResponseBody("black ip");
        requestContext.setSendZuulResponse(false);

        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Autowired

    private SysUserClient userClient;


    private SysUserClient sysUserClient;


    /** 定时同步黑名单ip */
    @Scheduled(cron = "${cron.black-ip}")
    public void syncBlackIPList() {
        System.out.println("black ip");
        try {
            Set<String> list = sysUserClient.findAllIpbacklists(Collections.emptyMap());
            blackIPs = list;
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * 获取请求的真实ip
     * @author xiangkaihong
     * @date 2019/5/4 10:45
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
