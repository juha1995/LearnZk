package com.learn.curatorapi_op;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

public class MyCuratorWatcher implements CuratorWatcher {
    public void process(WatchedEvent watchedEvent) throws Exception {
        System.out.println("此watcher实现了CuratorWatcher,触发watcher的节点路径为"+watchedEvent.getPath());
    }
}
