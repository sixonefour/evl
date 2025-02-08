package evl.rlt.handle;


import evl.common.EConstant;
import evl.rlt.Result;
import evl.rlt.SimpleResult;
import evl.rlt.collect.ResultCollect;

import java.util.List;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class SimpleResultHandle implements ResultHandle {

    @Override
    public Result handle(Result result) {
        return result;
    }

    @Override
    public Result handle(ResultCollect resultCollect) {
        List<Result> collect = resultCollect.collect();
        SimpleResult simpleResult = new SimpleResult();
        if(collect.isEmpty()) {
            simpleResult.setValid(false);
            return simpleResult;
        }
        int sumWeight = 0;
        int cnt = 0;
        for (Result result : collect) {
            simpleResult.setAttribute(result.description(), result);
            if(result.valid()) {
                sumWeight += result.weight();
                cnt++;
            }
        }

        if(cnt == 0) {
            simpleResult.setValid(false);
            return simpleResult;
        }

        double sum = 0;
        for(Result result : collect) {
            if(result.valid()) {
                sum += (result.weight() / (double) sumWeight) * result.score();
            }
        }
        simpleResult.setWeight(EConstant.DEFAULT_WEIGHT);
        simpleResult.setScore(sum);
        return simpleResult;
    }
}
