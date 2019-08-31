

package net.expectx.pay.client.common;




public class EXPayClientResult {
    private boolean success;
    private int code;
    private String message;
    private Object data;





    public EXPayClientResult(boolean success, EXPayClientResultConstants constants, Object data) {
        this.success=success;
        this.code = constants.getCode();
        this.message = constants.getMessage();
        this.data = data;
    }

    public EXPayClientResult(boolean success, String message, Object data) {
        this.success=success;
        this.message = message;
        this.data = data;
    }

    public EXPayClientResult(boolean success, int code, String message, Object data) {
        this.success=success;
        this.code = code;
        this.message =message;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "EXPayClientResult{success=" + this.success + ", code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }
}
