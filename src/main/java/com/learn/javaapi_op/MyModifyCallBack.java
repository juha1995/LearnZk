package com.learn.javaapi_op;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyModifyCallBack implements AsyncCallback.StatCallback {
    final Logger log = LoggerFactory.getLogger(MyModifyCallBack.class);
    public void processResult(int i, String s, Object ctx, Stat stat) {
        log.warn("修改的状态为",stat);
        log.warn(String.valueOf(ctx));
    }
}
