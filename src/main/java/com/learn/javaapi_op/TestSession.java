package com.learn.javaapi_op;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestSession implements Watcher {

    private static final  String zkServerPath = "10.202.0.209:2181,10.202.0.210:2181,10.202.0.211:2181";
    private static final int timeout = 900000;
    final static Logger log = LoggerFactory.getLogger(TestSession.class);


    public static void main(String[] args) throws IOException, InterruptedException {


        //会话连接
        ZooKeeper zk = new ZooKeeper(zkServerPath,timeout,new TestSession());
        log.warn("客户端开始连接zookeeper服务器...");
        log.warn("连接状态{}",zk.getState());

        //加个阻塞为了使时间充分建立起连接，然后再获取连接状态
        new Thread().sleep(10000);
        log.warn("连接状态{}",zk.getState());




        //执行会话重连操作
        new Thread().sleep(2000);
        long sessionId = zk.getSessionId();
        String transfered_ssid = "0x" + Long.toHexString(sessionId);
//        transfered_ssid = transfered_ssid;
        byte[] sessionPasswd = zk.getSessionPasswd();
        log.warn("开始会话重连，sessionId为{}，sessionPasswd是{}",transfered_ssid,sessionPasswd);

        ZooKeeper zk02 = new ZooKeeper(zkServerPath,timeout,new TestSession(),sessionId,sessionPasswd);
        log.warn("客户端开始重连zookeeper服务器...");
        log.warn("重新连接状态{}",zk02.getState());

        //加个阻塞为了使时间充分建立起连接，然后再获取连接状态
        new Thread().sleep(10000);
        log.warn("重新连接状态{}",zk02.getState());

    }

    public void process(WatchedEvent watchedEvent) {
        log.warn("接收到watch通知{}",watchedEvent);

    }
}
