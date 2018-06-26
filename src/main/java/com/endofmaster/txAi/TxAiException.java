package com.endofmaster.txAi;

/**
 * @author ZM.Wang
 */
public class TxAiException extends RuntimeException {

    public TxAiException(String errorMsg) {
        super(errorMsg);
    }

    public TxAiException(Throwable e) {
        super(e);
    }
}
