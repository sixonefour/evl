package evl.ops;


import evl.data.DataSource;
import evl.rlt.Result;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public interface OpsChain {

    Result invoke(DataSource dataSource);

    void processNext(DataSource dataSource);
}
