package evl.ops;


import evl.core.Contained;
import evl.data.DataSource;
import evl.rlt.Result;

/**
 * 管理算子的容器
 * @author  Liuyisi
 * @since   0.1
 */
public interface OpsContext extends Contained {

    Result invoke(DataSource dataSource);

    void addOps(String name, int weight, Operator ops);
}
