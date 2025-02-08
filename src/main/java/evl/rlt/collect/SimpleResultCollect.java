package evl.rlt.collect;


import evl.rlt.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class SimpleResultCollect implements ResultCollect {

    private final List<Result> results = new ArrayList<Result>();

    @Override
    public void collectResult(Result result) {
        results.add(result);
    }

    @Override
    public List<Result> collect() {
        return results;
    }
}
