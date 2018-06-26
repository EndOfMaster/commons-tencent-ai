package com.endofmaster.txAi;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.endofmaster.txAi.TxAiConstant.CHARSET;

/**
 * @author ZM.Wang
 */
public class TxAiClient {

    private final static Logger logger = LoggerFactory.getLogger(TxAiClient.class);
    private final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final String appId;
    private final String appKey;
    private final HttpClient httpClient;

    public TxAiClient(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public <T extends TxAiResponse> T execute(TxAiRequest<T> request) {
        try {
            Map<String, String> params = request.buildParams();
            params.put("app_id", appId);
            params.put("time_stamp", System.currentTimeMillis() / 1000 + "");
            params.put("nonce_str", RandomStringUtils.randomAlphanumeric(32));
            params.put("sign", sign(params).toUpperCase());

            RequestBuilder requestBuilder = RequestBuilder.create("POST").setUri(request.getUrl());
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (String key: params.keySet()) {
                String value = params.get(key);
                nameValuePairs.add(new BasicNameValuePair(key, value));
            }
            HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs);
            requestBuilder.setEntity(httpEntity);
            HttpResponse response = httpClient.execute(requestBuilder.build());
            String json = StreamUtils.copyToString(response.getEntity().getContent(), Charset.forName(CHARSET));
            logger.info("腾讯开放平台请求结果json：" + json);
            return MAPPER.readValue(json, request.responseClass());
        } catch (SignatureException | IOException e) {
            throw new TxAiException(e);
        }
    }

    private String sign(Map<String, String> params) throws SignatureException {
        String preSignStr = PresignUtils.createLinkString(params, true);
        logger.info("预签名字符串：" + preSignStr);
        return Md5SignUtils.sign(preSignStr, "&key=" + appKey, CHARSET);
    }

}
