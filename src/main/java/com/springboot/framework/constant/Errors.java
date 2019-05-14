package com.springboot.framework.constant;

/**
 * 错误码（400-599 禁止使用）
 *
 * @author leihe@uworks.cc
 */
public enum Errors {
  // 0-200 系统级
  SUCCESS(0, "操作成功"),
  //
  SYSTEM_ERROR(1, "系统错误"),
  // 自定义错误，可以修改label
  SYSTEM_CUSTOM_ERROR(2, "自定义错误"),
  //
  SYSTEM_DATA_ERROR(3, "数据异常"),
  //
  SYSTEM_DATA_NOT_FOUND(4, "数据不存在"),
  //
  SYSTEM_NOT_LOGIN(5, "请登录"),
  //
  SYSTEM_UPDATE_ERROR(6, "数据更新失败"),
  //
  SYSTEM_NO_ACCESS(7, "无权限访问"),
  //
  SYSTEM_REQUEST_PARAM_ERROR(8, "请求参数错误"),

  SYSTEM_BUSINESS_ERROR(9, "系统繁忙,请您稍后再试"),
  
  SYSTEM_INSERT_ERROR(10,"已存在"),
  
  SYSTEM_INSERT_FAIL(11,"数据添加失败"),
  
  SYSTEM_DELETE_FAIL(12,"数据删除失败"),

  // 201-300，用户模块
  USER_LOGIN_ERROR(201, "用户名或密码错误"),
  //
  USER_PASSWORD_LENGTH(202, "密码长度不符合要求"),
  //
  USER_USERNAME_SAME(203, "该用户名已存在"),
  //
  USER_PASSWORD_SAME(204, "密码不能与老密码重复"),
  //
  USER_NOT_FIND(205, "用户不存在"),
  //
  USER_HAS_DELETED(206, "该用户已经失效"),
  //
  USER_ACCOUNT_ERROR(207, "帐号异常，请联系客服"),
  //
  USER_OLD_PASSWORD_ERROR(208, "原密码错误"),
  //
  USER_POINT_ERROR(209, "用户积分不足"),

  // 短信发送模块
  USER_MOBILE_EXISTS(210, "该手机号已经注册"),
  //
  USER_MOBILE_NOT_REGISTER(211, "该手机号并未注册"),
  //
  USER_SMS_SEND_FAST(212, "请30秒后再试"),
  //
  USER_SMS_SEND_ERROR(213, "短信接口调用失败"),
  //
  CAPTCHA_IS_NULL(214, "验证码不存在"),
  //
  CAPTCHA_EXPIRED(215, "验证码已过期"),
  //
  CAPTCHA_ERROR(216,"验证码输入有误"),
  //
  WEIXIN_USER_NOT_LOGIN(217,"微信用户未登陆"),
 //
  BIND_CARD_CODE(218,"该手机号不存在"),
  //
  BIND_CARD_HAS(219,"此手机号已经被绑定"),

  // 600 - 630 为收货地址
  DELIVERY_PHONE_AND_MOBILE_NULL(600, "手机号和电话号码至少填一个"),
  //
  DELIVERY_IS_NULL(601, "地址为空"),
  //
  DELIVERY_IS_NOT_IN_USER(602, "用户不存在该地址"),

  // 业务错误(订单)901-950
  //
  ORDER_NO_GOODS(901, "请选择商品"),
  //
  ORDER_NO_DELIVERY(902, "请选择收货地址"),
  //
  ORDER_PAY_TYPE_ERROR(903, "请选择支付类型"),
  //
  ORDER_PAY_DEFAULT_BANK_ERROR(904, "请选择支付银行"),
  //
  ORDER_PAY_CALLBACK_STATUS_ERROR(905, "订单支付状态错误"),
  //
  ORDER_PAY_CALLBACK_SELLID_ERROR(906, "订单支付商家信息错误"),
  //
  ORDER_PAY_CALLBACK_TOTALFEE_ERROR(907, "订单支付金额错误"),
  //
  ORDER_PAY_CALLBACK_TRADE_ERROR(908, "没有查询到流水信息"),
  //
  ORDER_PAY_STATUS_ERROR(909, "订单状态错误"),
  //
  GOODS_INVENTORY_NOT_ENOUGH(910, "商品库存不足"),
  //
  ORDER_UNDER_HAS_NO_GOODS(911, "订单不存在商品"),
  //
  ORDER_NOT_BELONG_TO_USER(912, "订单不属于该用户"),
  //
  INVOICE_INFO_ERROR(913, "发票信息填写有误"),
  //
  ORDER_MOBILE_ERROR(914, "请至少填写一个联系方式"),
  //
  ORDER_STATUS_PAY_ERROR(915, "只有待支付状态的订单可以完成支付"),
  //
  ORDER_STATUS_FINISH_ERROR(916, "只有待收货状态的订单可以完成收货"),
  //
  ORDER_STATUS_CANCEL_ERROR(917, "已支付过的商品请联系客服取消订单"),
  //
  ORDER_GOODS_NOT_FOUND(918, "商品信息已经更新，请重新加入购物车"),
  
  ORDER_ID_NOT_ERROR(919, "订单ID或订单号不能为空"),
 

  //
  CAN_NOT_REPEAT_EXPERIENCE(1001,"您已免费体验过，不能重复体验"),
  
  //
  
  PROP_NOT_FIND(1002,"道具没有找到，请重新输入"),
  
  //
  
//
  
  NOT_ADMIN(1003,"请使用管理员账号登陆"),
  
  //
  
//
  
  IS_ADMIN(1004,"请使用玩家账号登陆"),
  
  //
  ;

  public int code;
  public String label;

  private Errors(int code, String label) {
    this.code = code;
    this.label = label;
  }

  /**
   * 获取状态码描述
   *
   * @param code
   *          状态码
   * @return 状态码描述，如果没有返回空串
   */
  public static String getLabel(int code) {
    String result = "";
    for (Errors status : Errors.values()) {
      if (status.code == code) {
        result = status.label;
      }
    }
    return result;
  }

}
