package com.endofmaster.txAi;

import com.endofmaster.txAi.nlp.NlpTextChatRequest;
import com.endofmaster.txAi.nlp.NlpTextChatResponse;
import org.junit.jupiter.api.Test;

/**
 * @author ZM.Wang
 */
public class TxAiClientTest {

    private final TxAiClient txAiClient;

    public TxAiClientTest() {
        this.txAiClient = new TxAiClient("", "");
    }

    @Test
    public void NlpTextChatTest() {
        NlpTextChatRequest request = new NlpTextChatRequest("wejuai", "asdasd");
        NlpTextChatResponse response = txAiClient.execute(request);
        if (response.successful()) {
            System.err.println(response.getAnswer());
        } else {
            System.err.println(response.getRet() + "====" + response.getMsg());
        }
    }
}
