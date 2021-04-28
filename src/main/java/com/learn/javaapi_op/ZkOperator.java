package com.learn.javaapi_op;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

import java.util.List;

public class ZkOperator implements Watcher {

    //    private static final  String zkServerPath = "10.202.0.209:2181,10.202.0.210:2181,10.202.0.211:2181";
    private static final int timeout = 900000;
    final static Logger log = LoggerFactory.getLogger(com.learn.javaapi_op.ZkOperator.class);

    private ZooKeeper zk = null;

    public ZkOperator() {
    }

    public ZkOperator(String zkServerPath) {

        try {
            //会话连接
            zk = new ZooKeeper(zkServerPath, timeout, new com.learn.javaapi_op.ZkOperator());
            log.warn("客户端开始连接zookeeper服务器...");
            log.warn("连接状态{}", zk.getState());

        } catch (IOException e) {
            log.error(String.valueOf(e));
            if (zk != null) {
                try {
                    zk.close();
                } catch (InterruptedException e1) {
                    log.error(String.valueOf(e1));
                }
            }

        }

    }

    public void process(WatchedEvent watchedEvent) {
        log.warn("接收到watch通知{}", watchedEvent);

    }

    public void createNode(int type, String path, byte[] data, List<ACL> acls, CreateMode createMode, String ctx, AsyncCallback.StringCallback callback) throws InterruptedException, KeeperException {
        //type为0表示同步
        if (type == 0) {

            log.warn("开始执行同步创建节点....");
            String result = zk.create(path, data, acls, createMode);
            log.warn("这是同步创建的节点{}", result);
        }

        //type为1表示异步

        else {
            log.warn("开始执行异步创建节点....");
            zk.create(path, data, acls, createMode, callback, ctx);
        }
    }

    public void modifyNode(int type, String path, byte[] data, int version,AsyncCallback.StatCallback cb, Object ctx) throws KeeperException, InterruptedException {

        if (type == 0) {
            log.warn(String.valueOf(zk.setData(path,data,version)));
        }


        //异步方式修改Node数据
        else {
            zk.setData(path, data, version, cb, ctx);
        }
    }


    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkOperator zkOperator = new ZkOperator("10.202.0.209:2181");

//        //同步的方式创建
//        zkOperator.createNode(0, "/test_create", new byte[]{67, 89}, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, null, null);
//
//        //异步的方式创建
        String ctx = "{'create':'success'}";
//        zkOperator.createNode(1, "/test_create_pers01", new byte[]{67, 77}, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, ctx, new MyCreateCallBack());


        //同步的方式修改
        zkOperator.modifyNode(0,"/test_create_pers",new byte[]{77,67},0,null,null);
        //异步的方式修改
        zkOperator.modifyNode(1,"/test_create_pers01",new byte[]{97,88,67},0,new MyModifyCallBack(),ctx);

    }


}
