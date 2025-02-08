package evl.data;


import java.util.List;

/**
 * 带有分段的数据
 * @author  Liuyisi
 * @since   0.1
 */
public interface SegmentDataSource extends DataSource {

    List<double[]> segmentValues();

    void setSegmentValues(List<double[]> segmentValues);

    List<String[]> segmentTimes();

    void setSegmentTimes(List<String[]> segmentTimes);
}
