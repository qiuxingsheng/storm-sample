package bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author qiuxs
 **/

public class FilterBolt extends BaseRichBolt  {
    OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        System.out.println("filter bolt start");
        Integer o = (Integer) input.getValues().get(0);
        if (o>10){
            collector.emit("spoutId", new Values(o));
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        //定义下个bolt接收streamId
        declarer.declareStream("spoutId", new Fields("spoutId"));

    }
}
