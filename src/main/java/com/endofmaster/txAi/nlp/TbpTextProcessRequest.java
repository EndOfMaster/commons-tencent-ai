package com.endofmaster.txAi.nlp;

import com.endofmaster.txAi.TxAiRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class TbpTextProcessRequest extends TxAiRequest<TbpTextProcessResponse> {

    private final String botId;
    private final String session;
    private final String question;

    public TbpTextProcessRequest(String botId, String session, String question) {
        this.botId = botId;
        this.session = session;
        this.question = question;
    }

    @Override
    protected Map<String, String> buildParams() {
        Map<String, String> params = new HashMap<>();
        params.put("Action", "TextProcess");
        params.put("Nonce", "754763840");
        params.put("Timestamp", System.currentTimeMillis() / 1000 + "");
        params.put("Version", "2019-06-27");
        params.put("endpoint", "tbp.tencentcloudapi.com");
        params.put("RequestClient", "SDK_JAVA_3.1.315");
        params.put("SignatureMethod", "HmacSHA256");
        params.put("BotId", this.botId);
        params.put("BotEnv", "release");
        params.put("TerminalId", "xxx");
        params.put("InputText", "xxx");
        return params;
    }

    @Override
    protected String getUrl() {
        return "https://tbp.tencentcloudapi.com/";
    }

    @Override
    protected Class<TbpTextProcessResponse> responseClass() {
        return TbpTextProcessResponse.class;
    }
}
