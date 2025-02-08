package evl.core;


import evl.common.EConstant;
import evl.rlt.Result;
import evl.rlt.collect.ResultCollect;
import evl.rlt.collect.SimpleResultCollect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class StandardEngine extends ContainedBase implements Engine {

    private final Map<String, Contained> containedMap;

    private final Map<String, Integer> weightMap;

    public StandardEngine() {
         this("standardEngine");
    }

    public StandardEngine(String containedName) {
        setContainName(containedName);
        this.containedMap = new HashMap<String, Contained>();
        this.weightMap = new HashMap<>();
    }

    @Override
    public Result invoke() {
        ResultCollect resultCollect = new SimpleResultCollect();
        for (Map.Entry<String, Contained> containedEntry : containedMap.entrySet()) {
            String childName = containedEntry.getKey();
            Contained child = containedEntry.getValue();
            Integer weight = weightMap.getOrDefault(childName, EConstant.DEFAULT_WEIGHT);
            Result result = child.invoke();
            result.setDescription(childName);
            if(result.valid() && result.weight() == 0) {
                result.setWeight(weight);
            }
            resultCollect.collectResult(result);
        }
        Result handle = resultHandle().handle(resultCollect);
        handle.setDescription(containName());
        return handle;
    }



    @Override
    public void addChild(String childName, int weight, Contained contained) {
        contained.setParent(this);
        containedMap.put(childName, contained);
        weightMap.put(childName, weight);
    }

    @Override
    public void removeChild(String childName) {
        containedMap.remove(childName);
        weightMap.remove(childName);
    }
}
