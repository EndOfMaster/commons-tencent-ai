package com.endofmaster.txAi.nlp;

import com.endofmaster.txAi.TxAiResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public class TbpTextProcessResponse extends TxAiResponse {

    @JsonProperty("Response")
    private Response response;

    static class Response {
        @JsonProperty("ResponseText")
        String answer;

        @JsonProperty("Error")
        Error error;

        public String getAnswer() {
            return answer;
        }

        public Error getError() {
            return error;
        }
    }

    public static class Error {
        @JsonProperty("Message")
        String message;

        public String getMessage() {
            return message;
        }
    }

    public boolean successful() {
        return response.error == null;
    }

    public Error getError() {
        return response.error;
    }

    public String getAnswer() {
        return response.answer;
    }

    public Response getResponse() {
        return response;
    }
}
