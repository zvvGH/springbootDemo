package org.zvv.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 哆啦づ ON 2019/5/9
 */
@Service
public class AdPlayerService {
//    public static void main(String[] args) throws Exception {
//        final String url = "http://dev- api6.pingxiaobao.com/rtb/subscribe.shtml?appid=1234567890&sequence=1527494937&timestamp=1527494937&version=1.3&sign=c 6dd7d b38a1290af0a36ce8f767e7";
//        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        final RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("payload",
//                " {\"device-uuid\":\"1403040073\",\"slot- id\":\"00000046\",\"quantity\":1,\"type\":\"IMG\",\"ip\":\"8.8.8.8\"}");
//
//        for (Map.Entry<String, String> entry : parameters.entrySet()) {
//            requestBuilder.addParameter(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//
//        final HttpUriRequest httpRequest = requestBuilder.build();
//
//        final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
//        final CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
//
//        HttpEntity responseEntity = httpResponse.getEntity();
//        System.out.println(EntityUtils.toString(responseEntity));
//    }
//
//    private static String sign(Map<String, String> map) {
//
//        final Map<String, String> parameterMap = new TreeMap<String, String>();
//
//        for (Map.Entry<String, String> entity : map.entrySet()) {
//            parameterMap.put(entity.getKey(), entity.getValue());
//        }
//
//        parameterMap.put("appkey", getAppKey());
//        StringBuilder stb = new StringBuilder();
//        for (Map.Entry<String, String> entity : parameterMap.entrySet()) {
//            stb.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
//        }
//
//        String str = stb.toString();
//        System.out.println(str);
//
//        return DigestUtils.md5Hex(str.substring(0, str.length() - 1));
//    }

}
