package com.learn.curatorapi_op;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MyWatcher implements Watcher {
    public void process(WatchedEvent watchedEvent) {
        System.out.println("此watcher实现了Watcher,触发watcher的节点路径为"+watchedEvent.getPath());

    }
}
