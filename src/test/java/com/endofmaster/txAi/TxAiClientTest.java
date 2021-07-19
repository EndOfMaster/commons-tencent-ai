package com.endofmaster.txAi;

import com.endofmaster.txAi.nlp.NlpTextChatRequest;
import com.endofmaster.txAi.nlp.NlpTextChatResponse;
import com.endofmaster.txAi.nlp.TbpTextProcessRequest;
import com.endofmaster.txAi.nlp.TbpTextProcessResponse;
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

    @Test
    public void TbpTest() {
        TbpTextProcessRequest request = new TbpTextProcessRequest("c01e5a72-312a-4ef1-b037-c2ace3c2949c", "aaaa", "啊啊啊");
        TbpTextProcessResponse response = txAiClient.execute(request);
        if (response.successful()) {
            System.err.println(response.getAnswer());
        } else {
            System.err.println(response.getError().getMessage());
        }
    }

}
