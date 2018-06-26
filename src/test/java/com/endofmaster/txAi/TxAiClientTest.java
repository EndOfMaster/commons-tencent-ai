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
        this.txAiClient = new TxAiClient("", "");
    }

    @Test
    public void NlpTextChatTest() {
        NlpTextChatRequest request = new NlpTextChatRequest("wejuai", "你是谁");
        NlpTextChatResponse response = txAiClient.execute(request);

    }
}
