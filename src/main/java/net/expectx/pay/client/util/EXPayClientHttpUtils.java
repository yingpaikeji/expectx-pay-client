//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.expectx.pay.client.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EXPayClientHttpUtils {

    public EXPayClientHttpUtils() {
    }



    public static HttpResponse doPost(String host, Map<String, String> headers, Map<String, String> queries, Map<String, String> bodies) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(buildUrl(host, queries));
        Iterator var8 = headers.entrySet().iterator();

        while(var8.hasNext()) {
            Entry<String, String> e = (Entry)var8.next();
            request.addHeader(e.getKey(),e.getValue());
        }

        if (bodies != null) {
            List<NameValuePair> nameValuePairList = new ArrayList();
            Iterator var12 = bodies.keySet().iterator();

            while(var12.hasNext()) {
                String key = (String) var12.next();
                nameValuePairList.add(new BasicNameValuePair(key,bodies.get(key)));
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }



    private static String buildUrl(String host, Map<String, String> queries) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);

        if (null != queries) {
            StringBuilder sbQuery = new StringBuilder();
            Iterator var5 = queries.entrySet().iterator();

            while(var5.hasNext()) {
                Entry<String, String> query = (Entry)var5.next();
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }

                if (StringUtils.isBlank( query.getKey()) && !StringUtils.isBlank( query.getValue())) {
                    sbQuery.append( query.getValue());
                }

                if (!StringUtils.isBlank( query.getKey())) {
                    sbQuery.append( query.getKey());
                    if (!StringUtils.isBlank( query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode( query.getValue(), "utf-8"));
                    }
                }
            }

            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }
}
