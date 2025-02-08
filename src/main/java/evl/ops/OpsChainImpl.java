package evl.ops;


import evl.data.DataSource;
import evl.rlt.Result;
import evl.rlt.collect.ResultCollect;
import evl.rlt.collect.SimpleResultCollect;
import evl.rlt.handle.ResultHandle;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class OpsChainImpl implements OpsChain {

    private final Map<String, Integer> weightMap;

    private final Iterator<Map.Entry<String, Operator>> iterator;

    private final ResultHandle resultHandle;

    private final ResultCollect resultCollect;

    public OpsChainImpl(Map<String, Integer> weightMap, Iterator<Map.Entry<String, Operator>> iterator, ResultHandle resultHandle) {
        this.weightMap = weightMap;
        this.iterator = iterator;
        this.resultHandle = resultHandle;
        resultCollect = new SimpleResultCollect();
    }

    @Override
    public Result invoke(DataSource dataSource) {
        processNext(dataSource);
        return resultHandle.handle(resultCollect);
    }

    @Override
    public void processNext(DataSource dataSource) {
        if(iterator.hasNext()) {
            Map.Entry<String, Operator> operatorEntry = iterator.next();
            String opsName = operatorEntry.getKey();
            Operator operator = operatorEntry.getValue();
            Result evaluated = operator.evaluate(dataSource, this);
            if(evaluated != null) {
                if(StringUtils.isBlank(evaluated.description())) {
                    evaluated.setDescription(opsName);
                }
                if(evaluated.valid() && evaluated.weight() == 0) {
                    Integer weight = weightMap.get(opsName);
                    evaluated.setWeight(weight);
                }
                resultCollect.collectResult(evaluated);
            }
        }
    }
}
