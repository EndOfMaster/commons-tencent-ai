package com.endofmaster.txAi.nlp;

import com.endofmaster.txAi.TxAiRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.txAi.TxAiConstant.CHARSET;

/**
 * @author ZM.Wang
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
