package evl.tran;

import evl.data.SegmentDataSource;

import java.util.List;

/**
 * 转换器
 * @author  Liuyisi
 * @since   0.1
 */
public interface Transform {

    //将数据分段
    void toSegments(SegmentDataSource segmentDataSource, double[] values, String[] times);

    //将数组组合
    void toValues(SegmentDataSource segmentDataSource, List<double[]> segmentValues, List<String[]> segmentTimes);
}
