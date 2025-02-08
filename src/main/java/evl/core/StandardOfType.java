package evl.core;

import evl.common.EConstant;
import evl.rlt.Result;
import evl.rlt.collect.ResultCollect;
import evl.rlt.collect.SimpleResultCollect;

import java.util.HashMap;
import java.util.Map;

public class StandardOfType extends ContainedBase implements OfType {

    private static final String DEFAULT_PIPELINE_NAME = "default_pipeline";
    private Map<String, Pipeline> pipelineMap = null;
    private Map<String, Integer> weightMap = null;

    public StandardOfType() {
        this("standardRoofType");
    }

    public StandardOfType(String containedName) {
        setContainName(containedName);
        //过滤器链
        pipelineMap = new HashMap<String, Pipeline>();
        weightMap = new HashMap<>();
    }

    @Override
    public Result invoke() {
        ResultCollect resultCollect = new SimpleResultCollect();
        for (Map.Entry<String, Pipeline> pipelineEntry : pipelineMap.entrySet()) {
            String type = pipelineEntry.getKey();
            Pipeline pipeline = pipelineEntry.getValue();
            Integer weight = weightMap.getOrDefault(type, EConstant.DEFAULT_WEIGHT);
            Result invoke = pipeline.invoke();
            if(invoke.valid() && invoke.weight() == 0) {
                invoke.setWeight(weight);
            }
            resultCollect.collectResult(invoke);
        }
        Result handle = resultHandle().handle(resultCollect);
        handle.setDescription(containName());
        return handle;
    }

    @Override
    public StandardPipeline.PipelineBuilder buildStandardPipeline(String pipeName, int weight) {
        return StandardPipeline.create(pipeName, weight, this);
    }

    @Override
    public StandardPipeline.PipelineBuilder defaultPipeline() {
        return StandardPipeline.create(DEFAULT_PIPELINE_NAME, EConstant.DEFAULT_WEIGHT, this);
    }

    @Override
    public void addPipeline(String pipeName, int weight, Pipeline pipeline) {
        pipeline.setParent(this);
        this.pipelineMap.put(pipeName, pipeline);
        weightMap.put(pipeName, weight);
    }
}
