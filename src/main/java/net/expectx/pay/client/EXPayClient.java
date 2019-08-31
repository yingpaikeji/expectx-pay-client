package net.expectx.pay.client;

import net.expectx.pay.client.common.EXPayClientResult;
import net.expectx.pay.client.util.EXPayClientHttpUtils;
import net.expectx.pay.client.util.EXPayClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class EXPayClient {
    private String serverUrl;
    private String merchantNo ;
    private String applicationNo;
    private String privateKey ;
    private String encryptionCode;
    public EXPayClient(String serverUrl, String merchantNo, String applicationNo, String privateKey) {
        this.serverUrl=serverUrl;
        this.merchantNo=merchantNo;
        this.applicationNo=applicationNo;
        this.privateKey=privateKey;
    }

    public EXPayClient(String serverUrl, String merchantNo, String applicationNo, String privateKey, String encryptionCode) {
        this.serverUrl = serverUrl;
        this.privateKey = privateKey;
        this.merchantNo = merchantNo;
        this.applicationNo = applicationNo;
        this.encryptionCode = encryptionCode;
    }

    /**
     * 扫码付
     * @param request
     * @return
     */
    public EXPayClientResult pageTradePrecreateExecute(HttpServletRequest request){

       try {
           if (!"POST".equals(request.getMethod())){
               throw new Exception("为了安全起见,必须是POST请求");
           }
           String merchantOrderId = request.getParameter("merchantOrderId");
           String orderName =request.getParameter("orderName");
           String orderDesc = request.getParameter("orderDesc");
           String orderDate = request.getParameter("orderDate");
           String tradeAmount = request.getParameter("tradeAmount");
           Map<String, String> headers=new HashMap<>();
           headers.put("User-Agent",request.getHeader("User-Agent"));
           headers.put("Encrypt", EXPayClientUtil.encrypt(this.encryptionCode,request));
           Map<String,String>queries=new HashMap<>();
           Map<String, String> body = new HashMap<>(2);
           body.put("merchantNo", this.merchantNo);
           body.put("applicationNo", this.applicationNo);
           body.put("merchantOrderId", merchantOrderId);
           body.put("tradeAmount", tradeAmount);
           body.put("orderName", orderName);
           body.put("orderDate", orderDate);
           body.put("orderDesc", orderDesc);

           body.put("sign", EXPayClientUtil.generateSignature(body,this.privateKey));


           HttpResponse resp = EXPayClientHttpUtils.doPost(this.serverUrl, headers, queries, body);
           String form = EntityUtils.toString(resp.getEntity());
           return new EXPayClientResult(true,200,null,form);

       }catch (Exception e){
           e.printStackTrace();
           return new EXPayClientResult(false,0,"系统错误:"+e.getLocalizedMessage(),null);
       }

    }

    String getServerUrl() {
        return serverUrl;
    }

    void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    String getPrivateKey() {
        return privateKey;
    }

    void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    String getMerchantNo() {
        return merchantNo;
    }

    void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    String getApplicationNo() {
        return applicationNo;
    }

    void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }
}
