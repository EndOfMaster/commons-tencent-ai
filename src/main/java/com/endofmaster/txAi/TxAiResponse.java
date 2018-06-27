package com.endofmaster.txAi;

/**
 * @author ZM.Wang
 */
public abstract class TxAiResponse {

    private int ret;

    private String msg;

    public boolean successful() {
        return ret == 0;
    }

    public int getRet() {
        return ret;
    }

    public TxAiResponse setRet(int ret) {
        this.ret = ret;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public TxAiResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
