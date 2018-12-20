package org.quickMap.constant;


public class ApiServiceConstant {

    /**
     * 访问令牌
     */
    public static final String AUTH_TOKEN = "authToken";

    /**
     * 用户信息
     */
    public static final String USER_INFO = "userInfo";

    /**
     * 用户角色前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * 角色
     */
    public static class RoleList{

        /**
         * 管理员
         */
        public static final String ADMIN =  "ADMIN";

        /**
         * 普通用户
         */
        public static final String COMMON_USER = "COMMON";

        /**
         * VIP用户
         */
        public static final String VIP_USER = "VIP";
    }

    /**
     * 文件删除状态
     */
    public static class DelStatus{

        /**
         * 删除
         */
        public static final int DEL =  0;

        /**
         * 正常
         */
        public static final int COMMON = 1;
    }
}
