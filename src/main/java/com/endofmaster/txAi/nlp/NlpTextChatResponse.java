package com.endofmaster.txAi.nlp;

import com.endofmaster.txAi.TxAiResponse;

/**
 * @author ZM.Wang
 * 智能闲聊
 */
public class NlpTextChatResponse extends TxAiResponse {

    private Data data;

    private static class Data {
        String session;
        String answer;

        public String getSession() {
            return session;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public String getAnswer() {
        return data.answer;
    }

    public Data getData() {
        return data;
    }
}
