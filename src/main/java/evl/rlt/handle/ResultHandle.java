package evl.rlt.handle;


import evl.rlt.Result;
import evl.rlt.collect.ResultCollect;

/**
 * 结果处理器
 * @author  Liuyisi
 * @since   0.1
 */
public interface ResultHandle {

    Result handle(Result result);

    Result handle(ResultCollect resultCollect);

}
