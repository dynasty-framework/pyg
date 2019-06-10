package com.pyg.common.utils;

public enum ResultCodeEnum {
	Success(0, "系统正常"),
	/****************************** 系统内部错误开始 **********************************/
	failure(1, "系统异常"),	
	InstantiationException(101, "InstantiationException"),
	IllegalAccessException(102, "IllegalAccessException"),
	BeansException(103, "BeansException"),
	IllegalArgumentException(104, "IllegalArgumentException"),
	IMException(105, "IMException"),
	ParseException(106, "ParseException"),
	SchedulerException(107, "SchedulerException"),
	JAXBException(108, "JAXBException"),
	NoSuchAlgorithmException(109, "NoSuchAlgorithmException"),
	KeyManagementException(110, "KeyManagementException"),
	ClientProtocolException(111, "ClientProtocolException"),
	IOException(112, "IOException"),
	UnsupportedEncodingException(113, "UnsupportedEncodingException"),
	/****************************** 系统内部错误结束 **********************************/
	
	/****************************** 依赖系统错误开始 **********************************/
	/**
	 * 积分系统失败
	 */
	POINT_SERVICE_FAILURE(501, "POINT_SERVICE_FAILURE"),
	/****************************** 依赖系统错误结束 **********************************/
	
	/*******************************系统参数代码错误***********************************/
	SYSPARAMERROR_METHOD_NULL(1001,"method参数为空"),
	SYSPARAMERROR_METHOD_ERROR(1002,"方法名称错误"),
	SYSPARAMERROR_PARAME_ERROR(1003,"缺少参数或参数为NUll"),
	SYSPARAMERROR_SIGN_NULL(1004,"sign参数为空"),
	SYSPARAMERROR_SIGN_ERROR(1005,"sign参数错误"),
	SYSPARAMERROR_TIMESTAMP_NULL(1006,"时间戳为空"),
	SYSPARAMERROR_TIMESTAMP_PATTERNERROR(1007,"时间戳格式错误"),
	SYSPARAMERROR_APIVERSION_NULL(1008,"版本号为空"),
	SYSPARAMERROR_APIVERSION_INVALID(1009,"非法的版本号"),
	SYSPARAMERROR_FORMAT_ERROR(1010,"返回值格式错误"),
	SYSPARAMERROR_CANSHU_ERROR(1011,"参数不能为空"),
	SYSPARAMERROR_QUERY_ERROR(1012,"查询发生错误"),
	SYSPARAMERROR_REQUEST_PARAMS_ERROR(1013,"请求参数错误"),
	SYSPARAMERROR_TOKEN_UNVALID(1014,"您的账号无效或已在其他设备登录，如非本人操作，请重置密码"),//
	SYSPARAMERROR_TOKEN_EXPIRED(1015,"token过期"),
	SYSPARAMERROR_CONNECT_ERROR(1016, "连接用户中心失败"),
	SYSPARAMERROR_DB_ERROR(1017, "操作数据库出错"),
	SYSPARAMERROR_TW0PWD_NOEQUAL(1018, "密码与确认密码不一致"),
	SYSPARAMERROR_DB_PARAM_ERROR(1019, "数据库参数错误"),
	SYSPARAMERROR_CREATE_APP_IM_ACCOUNT_ERROR(1020, "操作失败，请重试"),
	SYSPARAMERROR_USER_EXISTS_TIP(1021, "您的手机号已注册，请登录。"),

	ImageFormat(1121, "只能上传jpg、jpeg、png、gif格式的图片"),
	ImageSize(1122, "图片大小不能超过10兆"),
	/*******************************系统参数代码错误***********************************/
	
	
	/********************************* 用户相关接口 ************************************/	
	USER_PWD_ERROR(2001,"用户名或密码错误"),
	USER_LOGINNAME_NOT_NULL(2002,"用户登录名不能是空"),
	USER_MOBILE_NOT_NULL(2003,"用户手机号不能是空"),
	REG_MOBILE_EXISTS(2004,"该手机号已注册"),
	LOGIN_NOT_EXISTS(2005,"该手机号未注册"),
	USER_CODE_NOT_NULL(2006,"请先发送验证码"),
	USER_VERIFY_QUICK(2007,"您操作过快，请稍后重试"),
	USER_VERIFY_ERROR(2008,"您输入验证码错误"),
	USER_CODE_EXPIRE(2009,"验证码已过期"),

	/********************************* 用户相关接口 ************************************/
	
	/********************************* 项目相关开始 ************************************/
	ITEM_NOT_EXIST(3001, "项目不存在"),
	ITEM_ADOPT_ERROR(3002, "项目审核通过，不能修改"),
	/********************************* 项目相关结束 ************************************/
	
	/*********************************** 主题开始 ***********************************/
	/*********************************** 主题结束 ***********************************/
	
	/*********************************** 订单开始 Zyw edit ***********************************/
	EMPTY_ORDER_ID(5001,"订单号为空"),
	ORDER_NOT_EXIST(5002,"订单不存在"),
	PAYBACK_SUCCESS(5003,"订单支付回调成功"),
	PAYBACK_ERROR(5004,"订单支付回调失败"),
	PAY_SUCCESS(5005,"订单数据组装成功"),
	PAY_ERROR(5006,"订单支付失败"),
	/*********************************** 订单结束 ***********************************/
	
	/*********************************** 支付开始 ***********************************/
	/*********************************** 支付结束 ***********************************/
	
	/********************************* 基础类型相关开始 ************************************/
	DEPARTMENT_NOT_EXIST(6001, "科室不存在"),
	PRODUCT_TYPE_NOT_EXIST(6001, "产品类型不存在"),
	
	/********************************* 基础类型相关结束 ************************************/
	
	/********************************* 短信相关开始 ************************************/
	SMS_PHONE_MISSTAKE(7001, "手机号格式错误"),
	/********************************* 短信相关结束 ************************************/


	;
	
	private int index;

	private String message;

	private ResultCodeEnum(int index, String message) {
		this.index = index;
		this.message = message;
	}

	public int getIndex() {
		return index;
	}

	public String getMessage() {
		return message;
	}
	
	public static ResultCodeEnum getByIndex(int index) {
		for (ResultCodeEnum os: ResultCodeEnum.values()) {
			if (os.getIndex() == index) {
				return os;
			}
		}
		return null;
	}
	
	public static ResultCodeEnum getByMessage(String message) {
		for (ResultCodeEnum os: ResultCodeEnum.values()) {
			if (os.getMessage().equals(message)) {
				return os;
			}
		}
		return null;
	}

}

