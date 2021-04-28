package com.learn.javaapi_op;

import org.apache.zookeeper.AsyncCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCreateCallBack implements AsyncCallback.StringCallback {
    final Logger log = LoggerFactory.getLogger(MyCreateCallBack.class);
    public void processResult(int i, String path, Object ctx, String name) {
        log.warn(("创建节点"+path));
        log.warn(String.valueOf(ctx));
    }
}
