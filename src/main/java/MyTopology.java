
import bolt.FilterBolt;
import bolt.InsertBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import spout.DataSpout;

/**
 * @author qiuxs
 **/


public class MyTopology {


    public  static void main(String[] args) {
        System.out.println("MyTopology main start");
        // 定义一个拓扑

        TopologyBuilder builder = new TopologyBuilder();
        // 设置1个Executeor(线程)，默认一个
        DataSpout dataSpout = new DataSpout();
        builder.setSpout("spoutId", dataSpout);
        // shuffleGrouping:表示是随机分组
        // 设置1个Executeor(线程)，和两个task
        FilterBolt filterBolt = new FilterBolt();
        InsertBolt insertBolt = new InsertBolt();

        builder.setBolt("filterBolt", filterBolt).setNumTasks(1).allGrouping("spoutId", "spoutId");
        builder.setBolt("insertBolt", insertBolt).setNumTasks(1).allGrouping("filterBolt", "spoutId");
        Config conf = new Config();

        try {
            // 有参数时，表示向集群提交作业，并把第一个参数当做topology名称
            // 没有参数时，本地提交
            if (args != null && args.length > 0) {
                System.out.println("运行远程模式");
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } else {
                // 启动本地模式
                System.out.println("运行本地模式");
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("TopologyApp", conf, builder.createTopology());
            }
        } catch (Exception e) {
            System.out.println("storm启动失败!程序退出!");
            System.exit(1);
            e.printStackTrace();
        }
//        System.out.println("storm启动成功...");
    }
}
