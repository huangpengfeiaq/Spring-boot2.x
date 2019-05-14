package com.springboot.framework.constant;


public class Const {

	public static final String CURRENT_USER="currentUser";
	public static final String USERNAME = "account";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String VERIFY_CODE="verifyCode";
	
	/**
	 * 默认的访问Token的HTTP请求头的名字
	 */
	public static final String ACCESS_TOKEN_HEADER_NAME = "X-Access-Auth-Token";

	/**
	 * 支付宝用户USERID的HTTP请求头的名字
	 */
	public static final String ALIPAY_USER_ID_HEADER_NAME = "Yd_Alipay_User_Id";

	/**
	 * 缓存用户的key,USER_; USER_1
	 */
	public static final String SERVER_USER_KEY = "USER_";

	/**
	 * 用户过期时间(单位:秒，1天)
	 */
	public static final int SERVER_USER_EXP_KEY = 60 * 60 * 24 * 1;

	/**
	 * 端来源-请求头key
	 */
	public static final String REQUEST_SIDE_HEAD_NAME = "X-REQUEST-SIDE";

	/**
	 * 系统参数缓存前缀
	 */
	public static final String CACHE_GLOBAL_CONFIG_PREFIX = "CACHE_GLOBAL_CONFIG_";
	/**
	 * 系统参数缓存数据 7天
	 */
	public static final int CACHE_GLOBAL_CONFIG_SECOND = 86400 * 7;

	/**
	 * 周榜缓存7天
	 */
	public static final int WEEK_RANK_EXPIRE_TIME = 86400 * 7;
	
}
