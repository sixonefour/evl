package evl.core;

import evl.data.LoadDataSource;
import evl.fiter.Filter;
import evl.fiter.FilterContext;
import evl.ops.Operator;
import evl.ops.OpsContext;

/**
 * 通道
 * @author  Liuyisi
 * @since   0.1
 */
public interface Pipeline extends Contained {

    LoadDataSource getLoadDataSource();
    void setLoadDataSource(LoadDataSource loadDataSource);

    FilterContext getFilterContext();
    void setFilterContext(FilterContext filterContext);
    void addFilter(Filter filter);

    OpsContext getOpsContext();
    void setOpsContext(OpsContext opsContext);
    void addOps(Operator ops);
    void addOps(int weight, Operator ops);
    void addOps(String name, int weight, Operator ops);
}
