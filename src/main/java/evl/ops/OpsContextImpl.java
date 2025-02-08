package evl.ops;


import evl.common.EConstant;
import evl.core.Contained;
import evl.core.ContainedBase;
import evl.data.DataSource;
import evl.priority.Ordered;
import evl.rlt.Result;

import java.util.*;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class OpsContextImpl extends ContainedBase implements OpsContext {

    private Map<String, Operator> operatorMap;

    private final Map<String, Integer> weightMap;

    public OpsContextImpl(Contained contained) {
        this.weightMap = new HashMap<>();
        this.operatorMap = new LinkedHashMap<>();
        setParent(contained);
        setContainName("默认执行器");
    }


    @Override
    public Result invoke(DataSource dataSource) {
        if(operatorMap.isEmpty()) {
            throw new RuntimeException("OpsContextImpl: operatorMap is empty");
        }
        Result result = new OpsChainImpl(Collections.unmodifiableMap(weightMap), operatorMap.entrySet().iterator(), resultHandle()).invoke(dataSource);
        if(result.valid() && result.weight() == 0) {
            result.setWeight(EConstant.WEIGHT_OF_100);
        }
        if(result.description() == null) {
            result.setDescription("算法评估明细");
        }
        return result;
    }

    @Override
    public void addOps(String opsName, int weight, Operator ops) {
        operatorMap.put(opsName, ops);
        weightMap.put(opsName, weight);
        sort();
    }

    private void sort() {
        Set<Map.Entry<String, Operator>> entrySet = operatorMap.entrySet();
        List<Map.Entry<String, Operator>> needSorted = new ArrayList<>();
        List<Map.Entry<String, Operator>> notNeedSorted = new ArrayList<>();
        for (Map.Entry<String, Operator> entry : entrySet) {
            if(entry.getValue() instanceof Ordered) {
                needSorted.add(entry);
            }else {
                notNeedSorted.add(entry);
            }
        }
        needSorted.sort((o1, o2) -> (((Ordered) o1.getValue()).order() - ((Ordered) o2.getValue()).order()));
        Map<String, Operator> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Operator> entry : needSorted) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Operator> entry : notNeedSorted) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        this.operatorMap = sortedMap;
    }

    @Override
    public Result invoke() {
        throw new RuntimeException("Not implemented");
    }
}
