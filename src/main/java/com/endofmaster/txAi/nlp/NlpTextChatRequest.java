package com.endofmaster.txAi.nlp;

import com.endofmaster.txAi.TxAiRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.txAi.TxAiConstant.CHARSET;

/**
 * @author ZM.Wang
 * 智能闲聊
 */
public class NlpTextChatRequest extends TxAiRequest<NlpTextChatResponse> {

    private final String session;
    private final String question;

    public NlpTextChatRequest(String session, String question) {
        this.session = session;
        this.question = question;
    }

    @Override
    protected Map<String, String> buildParams() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        params.put("time_stamp", System.currentTimeMillis() / 1000 + "");
        params.put("nonce_str", RandomStringUtils.randomAlphanumeric(24));
        params.put("session", session);
        params.put("question", URLEncoder.encode(question, CHARSET).toUpperCase());
        return params;
    }

    @Override
    protected String getUrl() {
        return "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";
    }

    @Override
    protected Class<NlpTextChatResponse> responseClass() {
        return NlpTextChatResponse.class;
    }
}
