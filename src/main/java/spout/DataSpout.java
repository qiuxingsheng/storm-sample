package spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qiuxs
 **/
public class DataSpout extends BaseRichSpout {

    SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        System.out.println("spout open");
    }

    @Override
    public void nextTuple() {
        /*try {
            Thread.sleep(1000);
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("spout nextTuple start");
        int rndomn = (int)Math.random() * 1000;

        collector.emit("spoutId", new Values(rndomn));

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("spoutId", new Fields("spoutId"));
    }
}
