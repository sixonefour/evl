package evl.rlt.collect;

import evl.rlt.Result;
import java.util.List;

/**
 * 结果收集器
 * @author  Liuyisi
 * @since   0.1
 */
public interface ResultCollect {

    void collectResult(Result result);

    List<Result> collect();
}
