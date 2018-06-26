package com.endofmaster.txAi;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public abstract class TxAiRequest<T extends TxAiResponse> {

    protected abstract Map<String, String> buildParams() throws UnsupportedEncodingException;

    protected abstract String getUrl();

    protected abstract Class<T> responseClass();

}
