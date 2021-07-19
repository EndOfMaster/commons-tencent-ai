package com.endofmaster.txAi;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.txAi.nlp.NlpTextChatRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Base64;
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

    private final OkHttpClient client = new OkHttpClient();

    public TxAiClient(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    private Response doRequest(String url, String body) throws IOException {
        MediaType contentType = MediaType.parse("application/x-www-form-urlencoded");
        Request request = (new Request.Builder()).url(url).post(RequestBody.create(contentType, body)).build();
        return this.client.newCall(request).execute();
    }

    public <T extends TxAiResponse> T execute(TxAiRequest<T> request) {
        try {
            String json;
            Map<String, String> params = request.buildParams();
            if (request instanceof NlpTextChatRequest) {
                params.put("app_id", appId);
                params.put("sign", sign(params).toUpperCase());
                RequestBuilder requestBuilder = RequestBuilder.create("POST").setUri(request.getUrl());
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                for (String key : params.keySet()) {
                    String value = params.get(key);
                    nameValuePairs.add(new BasicNameValuePair(key, value));
                }
                HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs, Charset.forName(CHARSET));
                requestBuilder.setEntity(httpEntity);
                HttpResponse response2 = httpClient.execute(requestBuilder.build());
                json = StreamUtils.copyToString(response2.getEntity().getContent(), Charset.forName(CHARSET));
            } else {
                params.put("SecretId", this.appId);
                String endpoint = params.get("endpoint");
                params.remove("endpoint");
                params.put("Signature", sign2(params, endpoint));
                PresignUtils.urlencode(params);
                String linkString = PresignUtils.createLinkString(params, true);
                Response response = doRequest(request.getUrl(), linkString);
                json = response.body().string();
            }
            logger.debug("腾讯开放平台请求结果json：" + json);
            return MAPPER.readValue(json, request.responseClass());
        } catch (SignatureException | IOException e) {
            throw new TxAiException(e);
        }
    }

    private String sign(Map<String, String> params) throws SignatureException {
        String preSignStr = PresignUtils.createLinkString(params, true) + "&app_key=" + appKey;
        logger.debug("预签名字符串：" + preSignStr);
        return DigestUtils.md5Hex(preSignStr);
    }

    private String sign2(Map<String, String> params, String endpoint) {
        String preSignStr = "POST" + endpoint + "/?" + PresignUtils.createLinkString(params, false);
        logger.debug("预签名字符串：" + preSignStr);
        return Base64.getEncoder().encodeToString(new HmacUtils(HmacAlgorithms.HMAC_SHA_256, this.appKey).hmac(preSignStr));
    }

}
