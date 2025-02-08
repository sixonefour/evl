package evl.core;


import evl.common.EConstant;
import evl.data.DataSource;
import evl.data.LoadDataSource;
import evl.fiter.Filter;
import evl.fiter.FilterContext;
import evl.fiter.FilterContextImpl;
import evl.ops.Operator;
import evl.ops.OpsContext;
import evl.ops.OpsContextImpl;
import evl.rlt.Result;
import evl.rlt.SimpleResult;
import evl.rlt.collect.ResultCollect;
import evl.rlt.collect.SimpleResultCollect;
import evl.rlt.handle.ResultHandle;

/**
 * 简单实现通道
 * @author  Liuyisi
 * @since   0.1
 */
public class StandardPipeline extends ContainedBase implements Pipeline {
    private LoadDataSource loadDataSource;
    private FilterContext filterContext;
    private OpsContext opsContext;

    public StandardPipeline(String pName, Contained contained) {
        setContainName(pName);
        setParent(contained);
        this.filterContext = new FilterContextImpl(this);
        this.opsContext = new OpsContextImpl(this);
    }

    public StandardPipeline(String pName, Contained contained, FilterContext filterContext, OpsContext opsContext) {
        setContainName(pName);
        setParent(contained);
        this.filterContext = filterContext;
        this.opsContext = opsContext;
    }

    @Override
    public Result invoke() {
        //无数据源
        if(loadDataSource == null) {
            throw new RuntimeException(containName() +  " no load data source!");
        }
        //收集器
        ResultCollect resultCollect = new SimpleResultCollect();
        //数据查询
        for (DataSource dataSource : loadDataSource) {
            //数据的过滤,存在不合理数据，可以直接跳过计算
            if (filterContext.doFilter(dataSource)) {
                //计算
                Result invoke = opsContext.invoke(dataSource);
                //收集
                resultCollect.collectResult(invoke);
            } else {
                SimpleResult srt = new SimpleResult();
                srt.setValid(false);
                srt.setDescription(dataSource.sourceId() + " have been filtered");
                resultCollect.collectResult(srt);
            }
        }
        //处理
        Result handle = resultHandle().handle(resultCollect);
        handle.setDescription(containName());
        return handle;
    }

    @Override
    public LoadDataSource getLoadDataSource() {
        return loadDataSource;
    }

    @Override
    public void setLoadDataSource(LoadDataSource loadDataSource) {
        this.loadDataSource = loadDataSource;
    }

    @Override
    public FilterContext getFilterContext() {
        return filterContext;
    }

    @Override
    public void setFilterContext(FilterContext filterContext) {
        this.filterContext = filterContext;
    }

    @Override
    public void addFilter(Filter filter) {
        this.filterContext.addFilter(filter);
    }

    @Override
    public OpsContext getOpsContext() {
        return opsContext;
    }

    @Override
    public void setOpsContext(OpsContext opsContext) {
        this.opsContext = opsContext;
    }

    @Override
    public void addOps(Operator ops) {
        addOps(EConstant.DEFAULT_WEIGHT, ops);
    }

    @Override
    public void addOps(int weight, Operator ops) {
        addOps(ops.ops(), weight, ops);
    }

    @Override
    public void addOps(String name, int weight, Operator ops) {
        opsContext.addOps(name, weight, ops);
    }

    public static PipelineBuilder create(String pName, int weight, OfType roofType){
        return new PipelineBuilder(pName, weight, roofType);
    }

    public static class PipelineBuilder {
            private OfType roofType = null;
            private Pipeline pipeline = null;
            private int weight = 0;
        private PipelineBuilder(String pName, int weight, OfType roofType) {
            this.roofType = roofType;
            this.weight = weight;
            this.pipeline = new StandardPipeline(pName, roofType);
        }

        public PipelineBuilder setLoadDataSource(LoadDataSource loadDataSource) {
            this.pipeline.setLoadDataSource(loadDataSource);
            return this;
        }

        public PipelineBuilder setResultHandle(ResultHandle resultHandle) {
            this.pipeline.setResultHandle(resultHandle);
            return this;
        }

        public PipelineBuilder addFilter(Filter filter) {
            pipeline.addFilter(filter);
            return this;
        }

        public PipelineBuilder addOps(Operator ops) {
            pipeline.addOps(ops);
            return this;
        }

        public PipelineBuilder addOps(int weight, Operator ops) {
            pipeline.addOps(weight, ops);
            return this;
        }

        public PipelineBuilder addOps(String name, int weight, Operator ops) {
            pipeline.addOps(name, weight, ops);
            return this;
        }

        public OfType build(){
            roofType.addPipeline(pipeline.containName(), weight, pipeline);
            return roofType;
        }
    }

}
