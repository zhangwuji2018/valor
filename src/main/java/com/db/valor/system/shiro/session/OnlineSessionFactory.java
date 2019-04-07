package com.db.valor.system.shiro.session;

import com.db.valor.common.utils.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *    自定义shiro会话工厂，根据subject对应的上下文来创建会话
 * </p>
 *
 * @author daibing
 * @since 2019/4/4
 */
public class OnlineSessionFactory implements SessionFactory {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Session createSession(SessionContext initData) {
        OnlineSession onlineSession = new OnlineSession();
        if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (request != null) {
                UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
                // 获取客户端操作系统
                String systemName = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browserName = userAgent.getBrowser().getName();
                // 获取IP地址
                String ipAddr = IpUtils.getIpAddr(request);
                onlineSession.setOs(systemName);
                onlineSession.setBrowser(browserName);
                onlineSession.setHost(ipAddr);
            }
        }
        return onlineSession;
    }
}
