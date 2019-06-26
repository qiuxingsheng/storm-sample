package bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * @author qiuxs
 **/

public class InsertBolt extends BaseRichBolt  {

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    @Override
    public void execute(Tuple input) {
        System.out.println("insertBolt start");

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
