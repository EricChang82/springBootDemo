package cn.zookeeper.apiType;
import java.util.List;
 
public class ZookeeperCliTest {
    public static void main(String[] args){
        //定义父子类节点路径
        String rootPath = "/ZookeeperRoot01";
        String childPath1 = rootPath+ "/child101";
        String childPath2 = rootPath+ "/child201";
 
        //ZNodeOperation操作API
        ZNodeOperation ZNodeOperation = new ZNodeOperation();
 
        //连接Zookeeper服务器
        ZookeeperDemo zookeeperDemo =new ZookeeperDemo();
        zookeeperDemo.connect("127.0.0.1:2181");
 
        //创建节点
        if(ZNodeOperation.createZNode(rootPath,"<父>父节点数据")){
            System.out.println("节点 [ " +rootPath + " ],数据 [ " + ZNodeOperation.readData(rootPath)+" ]");
        }
 
        // 创建子节点, 读取 + 删除
        if ( ZNodeOperation.createZNode( childPath1, "<父-子(1)>节点数据" ) ) {
            System.out.println( "节点[" + childPath1 + "]数据内容[" + ZNodeOperation.readData( childPath1 ) + "]" );
            ZNodeOperation.deteleZKNode(childPath1);
            System.out.println( "节点[" + childPath1 + "]删除值后[" + ZNodeOperation.readData( childPath1 ) + "]" );
        }
 
        // 创建子节点, 读取 + 修改
        if ( ZNodeOperation.createZNode(childPath2, "<父-子(2)>节点数据" ) ) {
            System.out.println( "节点[" + childPath2 + "]数据内容[" + ZNodeOperation.readData( childPath2 ) + "]" );
            ZNodeOperation.updateZKNodeData(childPath2, "<父-子(2)>节点数据,更新后的数据" );
            System.out.println( "节点[" + childPath2+ "]数据内容更新后[" + ZNodeOperation.readData( childPath2 ) + "]" );
        }
 
        // 获取子节点
        List<String> childPaths = ZNodeOperation.getChild(rootPath);
        if(null != childPaths){
            System.out.println( "节点[" + rootPath + "]下的子节点数[" + childPaths.size() + "]" );
            for(String childPath : childPaths){
                System.out.println(" |--节点名[" +  childPath +  "]");
            }
        }
        // 判断节点是否存在
        System.out.println( "检测节点[" + rootPath + "]是否存在:" + ZNodeOperation.isExists(rootPath)  );
        System.out.println( "检测节点[" + childPath1 + "]是否存在:" + ZNodeOperation.isExists(childPath1)  );
        System.out.println( "检测节点[" + childPath2 + "]是否存在:" + ZNodeOperation.isExists(childPath2)  );
 
 
        zookeeperDemo.close();
    }
 
}