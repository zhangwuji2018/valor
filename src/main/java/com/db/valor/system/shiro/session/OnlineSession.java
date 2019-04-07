package com.db.valor.system.shiro.session;

import lombok.Data;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * <p>
 *    用于保存当前登录用户的在线状态
 * </p>
 *
 * @author daibing
 * @since 2019/4/4
 */
@Data
public class OnlineSession extends SimpleSession {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String loginName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 登录IP地址
     */
    private String host;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.ONLINE;

    private static enum OnlineStatus{
        /**
         * 在线状态
         */
        ONLINE("在线"),

        /**
         * 离线状态
         */
        OFFLINE("离线");

        private String info;

        OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
}
