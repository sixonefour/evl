package evl.core;

/**
 * 伸展的具体类型
 * @author  Liuyisi
 * @since   0.1
 */
public interface OfType extends Contained {
    //添加通道
    StandardPipeline.PipelineBuilder buildStandardPipeline(String pipeName, int weight);
    //默认的通道
    StandardPipeline.PipelineBuilder defaultPipeline();
    //
    void addPipeline(String pipeName,  int weight, Pipeline pipeline);
}
