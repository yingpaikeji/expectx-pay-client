package net.expectx.pay.client.common;

/**
 *  restful接口结果常量枚举类
 *
 * @author lijian
 * @date 2017/12/20
 */
public enum EXPayClientResultConstants {
    FAILED                                  (0, "操作失败"),
    SUCCESS                                 (200, "操作成功"),
    ERROR_SYSTEM                            (500, "系统异常"),
    PARAM_CHECK_FAILED                      (501, "参数检查失败"),
    ERROR_UNAUTHORIZED                      (502, "令牌失效，请重新登录"),
    PARAMETER_MANIPULATION                  (503, "参数被篡改"),
    HEADER_NO_MAC                           (504, "缺少消息头MAC地址或为空"),
    HEADER_NO_USER_AGENT                    (505, "缺少消息头User-Agent地址或为空"),
    HEADER_NO_APP_KEY_AND_SECRET_PARAM      (506, "缺少消息头App-Key和App-Secret参数或为空"),
    HEADER_NO_ENCRYPT_PARAM                 (507, "缺少消息头Encrypt参数或为空"),
    ERROR_APP_KEY                           (506001, "无效的App-Key"),
    ERROR_APP_SECRET                        (506002, "无效的App-Secret"),
    ERROR_USERNAME_OR_PASSWORD              (200001, "用户名或密码错误"),
    USER_IS_EXIST_YES                       (200002, "用户已存在"),
    USER_IS_EXIST_NO                        (200003, "用户不存在"),
    USER_NEW_AND_OLD_PASSWORD_INCONSISTENT  (200004, "新旧密码不能一致"),
    ERROR_USER_PASSWORD                     (200005, "用户密码错误"),
    LOGIN_LOCKED                            (200006, "登陆方式被锁定"),
    PHONE_IS_EXIST_YES                      (200007, "手机号已存在"),
    EMAIL_IS_EXIST_YES                      (200008, "邮箱已存在"),
    ACCOUNT_LOCKED                          (200009, "账号被锁定"),
    EXCEPTION_DATA_BASE_NO                  (50020,  "暂无数据"),
    EXCEPTION_DATA_BASE_INSERT              (50021,  "数据保存异常"),
    EXCEPTION_DATA_BASE_UPDATE              (50022,  "数据更新异常"),
    EXCEPTION_DATA_BASE_DELETE              (50023,  "数据删除异常"),
    EXCEPTION_DATA_BASE_SELECT              (50024,  "数据查询异常"),
    EXCEPTION_DATA_BASE_RESTORE             (50025, "数据还原异常"),
    EXCEPTION_DATA_BASE_REPEAT              (50026, "数据已存在");
    /**
     * 自定义状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String message;

    EXPayClientResultConstants(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
