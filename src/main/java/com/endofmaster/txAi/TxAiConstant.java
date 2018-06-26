package com.endofmaster.txAi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author ZM.Wang
 */
public interface TxAiConstant {
    String CHARSET = "UTF-8";
    DateFormat REQ_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    DateFormat PAID_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
