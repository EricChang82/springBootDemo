package cn.zookeeper.apiType;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.Util;
import cn.zookeeper.apiType.watcher.Watcher1;

@RestController
public class ZookeeperOriController {
    //定义父子类节点路径
    String rootPath = "/ZookeeperRoot01";
    String childPath1 = rootPath + "/child101";
    String childPath2 = rootPath + "/child201";
    private ZooKeeper zooKeeper;

    @GetMapping("/getConnection")
    public String getConnection() {
        //连接Zookeeper服务器
        Watcher1 watcher1 = new Watcher1();
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, watcher1);
            Watcher1.connectedSemaphore.await(); //使用countDownLatch的await
            Watcher1.zk = zooKeeper;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "连接Zookeeper服务器";
    }

    @GetMapping("/getConnection2")
    public String getConnection2() {

        return "连接Zookeeper服务器";
    }

    /**
     *@author changle
     *Create Time: 2019年4月29日 
     *Purpose:
     * @throws Exception 
     * @throws KeeperException 
     */
    @GetMapping("/createNode/{path}/{data}")
    private String createNode(@PathVariable("path") String path, @PathVariable("path") String data) throws KeeperException, Exception {
        if (zooKeeper == null) {
            getConnection();
        }
        String zkPath = zooKeeper.create(getPath(path), data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        return "zkPath cteated:" + zkPath;
    }

    @GetMapping("/isExistsNode/{path}")
    public String isExistsNode(@PathVariable("path") String path) {
        try {
            if (zooKeeper == null) {
                getConnection();
            }
            Stat stat = zooKeeper.exists(getPath(path), false);
            if (stat != null) {
                return "节点：" + path + "存在";
            } else {
                return "节点：" + path + "不存在";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "校验失败";
    }

    @GetMapping("/deleteNode/{path}")
    public String deleteNode(@PathVariable("path") String path) {
        try {
            if (zooKeeper == null) {
                getConnection();
            }
            zooKeeper.delete(getPath(path), -1);
            return "节点：" + path + "已删除";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "删除失败";
    }

    /**
     *@author changle
     *Create Time: 2019年4月29日 
     *Purpose:
     */
    private String getPath(String path) {
        return "/" + path;
    }
    @GetMapping("/readNode/{path}")
    public String readNode(@PathVariable("path") String path) {
        try {
            if (zooKeeper == null) {
                getConnection();
            }
            String data = new String(zooKeeper.getData(getPath(path),false,null));
            return "读取节点：" + path + ",数据为:"+data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "读取失败";
    }
    @GetMapping("/updateNodeData/{path}/{data}")
    public String updateNodeData(@PathVariable("path") String path,@PathVariable("data") String data){
        try {
            if (zooKeeper == null) {
                getConnection();
            }
            Stat stat = zooKeeper.setData(getPath(path),data.getBytes(),-1);
            Util.print("更新节点数据成功，path:" + path+", stat:" + stat);
            return  "更新成功";
        } catch (Exception e) {
           e.printStackTrace();
        } 
        return "更新失败";
    }
    @GetMapping("/getChild/{path}")
    public String getChild(String path){
        try {
            List<String> list = zooKeeper.getChildren(getPath(path),false);
            if(list.isEmpty()){
                Util.print(path + "的路径下没有节点");
            }
            return "子节点为："+list.toString();
        }  catch (Exception e) {
            e.printStackTrace();
            return "读取失败";
         } 
    }
}
