package com.learn.curatorapi_op;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ExistsBuilder;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorOperator {

    private CuratorFramework client = null;
    private static final String zkServerPath="10.202.0.211:2181";

    public CuratorOperator() {


        RetryPolicy retryPolicy01 = new ExponentialBackoffRetry(1000,5,2000);

        RetryPolicy retryPolicy02 = new RetryNTimes(5,5000);

        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy01)
                .namespace("mynamespace").build();//这里指定了一个namespace之后，会在根节点下创建一个namespace的节点，且其他操作都在该目录下
        client.start();

    }


    /**
     * @Description: 关闭客户端连接
     *
     */
    public void closeClient(){
        if (client!=null){
            this.client.close();
        }
    }

    public static void main(String[] args) throws Exception {

        //实例化
        CuratorOperator curatorOperator = new CuratorOperator();
        //查看连接状态
        System.out.println(curatorOperator.client.isStarted());
        System.out.println("-----------------");

        //创建节点
        String nodePath = "/test2013/test2013_child";
//        byte[] nodeData = "test2013".getBytes();
//        curatorOperator.client.create().creatingParentContainersIfNeeded() //creatingParentsIfNeeded会递归的创建目录
//                .withMode(CreateMode.PERSISTENT) //创建模式，是临时节点还是永久的
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE) //指定权限
//                .forPath(nodePath,nodeData); //具体的节点和节点数据

//        //修改节点
//        byte[] newNodeData = "newe_data".getBytes();
//        curatorOperator.client.setData()
//                .withVersion(0)
//                .forPath(nodePath,newNodeData);


        //删除节点
//        curatorOperator.client.delete()
//                .guaranteed() //如果删除失败，继续执行删除直到成功
//                .deletingChildrenIfNeeded() //如果有子节点也删除
//                .withVersion(0) //版本号正确才可删除
//                .forPath("/test2013");


        //读取节点信息
//        Stat stat = new Stat(); //这个默认是0，下面如果不进行store的话，读取版本号时就是0
//        byte[] data = curatorOperator.client.getData().storingStatIn(stat).forPath(nodePath);
//        System.out.println("节点 "+nodePath+"的数据为"+new String(data));
//        System.out.println("节点的版本号为" + stat.getAversion());
//        System.out.println("-----------------");
//
//        //读取子节点信息
//        String parentPath = "/";
//        List<String> childNodes = curatorOperator.client.getChildren().forPath(parentPath);
//        System.out.println(parentPath+"的子节点为"+childNodes);
//        System.out.println("-----------------");
//
//        //查看节点是否存在,如果存在有值，如果不存在则是null
//        Stat statExist = curatorOperator.client.checkExists().forPath(nodePath+"/lll");
//
//        System.out.println(statExist);
//        System.out.println("-----------------");


        //使用一次性的watcher
        curatorOperator.client.getData().usingWatcher(new MyWatcher()).forPath(nodePath);


        //使用cache的watcher
//        NodeCache

//        PathChildrenCache
        Thread.sleep(30000);

        //关闭会话
        curatorOperator.closeClient();
        System.out.println(curatorOperator.client.isStarted());


    }
}
