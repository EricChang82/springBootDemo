package cn.zookeeper.apiType.watcher;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author changle 
 * Create Time: 2019年4月29日 
 * Purpose:
 */

public class Watcher1 implements Watcher{
    public static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    
    public static ZooKeeper zk = null;
    
    private static Stat stat = new Stat();
    @Override
    public void process(WatchedEvent event) {

        if (KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }        
    }

}
