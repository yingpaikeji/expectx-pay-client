package net.expectx.pay.client.util;


import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.*;

public class EXPayClientUtil {
    private static final String[] HEX_DIGITS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 生成签名
     * @param data      参数
     * @param key       密钥
     * @return
     * @throws Exception
     */
    public static String generateSignature(Map<String, String> data, String key) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        String[] var6 = keyArray;
        int var7 = keyArray.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String k = var6[var8];
            if (!k.equals("sign") && (data.get(k)).trim().length() > 0) {
                sb.append(k).append("=").append((data.get(k)).trim()).append("&");
            }
        }

        sb.append("key=").append(key);
        return md5(sb.toString(),"UTF-8").toUpperCase();

    }

    public static String encrypt(String encryptionCode,HttpServletRequest request) {
        Byte encryptionTime = 1;
        /**
         * 判断是否有参数传入
         */
        Enumeration<String> enumeration = request.getParameterNames();
        /**
         * 获得参数并字典排序
         */
        List<String> parameterValueList = new LinkedList<>();
        while (enumeration.hasMoreElements()) {
            String parameterName = enumeration.nextElement();
            if (!"Encrypt".equals(parameterName)){
                parameterValueList.add(request.getParameter(parameterName));
            }
        }
        parameterValueList.add(encryptionCode);
        String[] parameterValues = new String[parameterValueList.size()];
        for (int i = 0, j = parameterValueList.size(); i < j; i++) {
            parameterValues[i] = parameterValueList.get(i);
        }
        Arrays.sort(parameterValues);
        StringBuffer parameterValue = new StringBuffer();
        for (int i = 0; i < parameterValues.length; i++) {
            parameterValue.append(parameterValues[i]);
        }
        String tempEncrypt = md5(parameterValue.toString(), "UTF-8").toLowerCase();

        for (int i = 0; i < encryptionTime - 1; i++) {
            tempEncrypt = md5(tempEncrypt,"UTF-8").toLowerCase();
        }
        System.out.println("Encrypt String:" + tempEncrypt.toLowerCase());
        return tempEncrypt;
    }




    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            int n = b[i];
            if ( b[i] < 0) {
                n =  b[i] + 256;
            }

            int d1 = n / 16;
            int d2 = n % 16;
            resultSb.append( HEX_DIGITS[d1] + HEX_DIGITS[d2]);
        }

        return resultSb.toString();
    }


    public static String md5(String origin, String charsetName) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String resultString;
            if (charsetName != null && !"".equals(charsetName)) {
                resultString = byteArrayToHexString(md.digest(origin.getBytes(charsetName)));
            } else {
                resultString = byteArrayToHexString(md.digest(origin.getBytes()));
            }

            return resultString;
        } catch (Exception var4) {
            return null;
        }
    }
    public static void main(String [] args){
        System.out.println(md5("111","UTF-8"));
    }

}
