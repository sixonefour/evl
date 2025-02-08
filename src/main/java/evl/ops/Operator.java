package evl.ops;


import evl.data.DataSource;
import evl.rlt.Result;

/**
 * 算法类
 * 继承子类实现任意一个evaluate就可以;
 * 第一个evaluate方法，可以提前停止操作链的执行
 *  例如:某个算法操作发现预警值非常高了，没必要执行后面的算法。
 * 第二个evaluate方法，只需要实现相关算法操作就行
 *
 * @author  Liuyisi
 * @since   0.1
 */

public interface Operator {

    String ops();

    /**
     * 评价
     * @param dataSource 资源
     * @return 计算值
     */
    default Result evaluate(DataSource dataSource, OpsChain opsChain) {
        Result evaluate = evaluate(dataSource);
        opsChain.processNext(dataSource);
        return evaluate;
    }

    /**
     * @param dataSource 资源
     * @return 计算值
     */
    default Result evaluate(DataSource dataSource) {
        throw new RuntimeException("Not implemented yet");
    }
}
