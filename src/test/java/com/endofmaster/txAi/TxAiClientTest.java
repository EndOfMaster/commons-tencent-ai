package com.endofmaster.txAi;

import com.endofmaster.txAi.nlp.NlpTextChatRequest;
import com.endofmaster.txAi.nlp.NlpTextChatResponse;
import org.junit.Test;

/**
 * @author ZM.Wang
 */
public class TxAiClientTest {

    private final TxAiClient txAiClient;

    public TxAiClientTest() {
        this.txAiClient = new TxAiClient("1106921819", "OCmd4mvl7REf1cgS");
    }

    @Test
    public void NlpTextChatTest() {
        NlpTextChatRequest request = new NlpTextChatRequest("wejuai", "你是谁");
        NlpTextChatResponse response = txAiClient.execute(request);
        if (response.successful()) {
            System.err.println(response.getAnswer());
        } else {
            System.err.println(response.getRet() + "====" + response.getMsg());
        }
    }
}
