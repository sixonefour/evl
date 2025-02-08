package evl.data;

import evl.tran.Transform;

import java.util.List;

/**
 * 数据源实现
 * @author  Liuyisi
 * @since   0.1
 */
public class SimpleDataSourceImpl extends DefaultDataSource implements SegmentDataSource{

    private List<double[]> segmentValues;

    private List<String[]> segmentTimes;

    private final Transform transform;

    public SimpleDataSourceImpl(double[] values, String[] times, Transform transform) {
        super(values, times);
        this.transform = transform;
    }

    @Override
    public List<double[]> segmentValues() {
        if(segmentValues == null) {
            transform.toSegments(this, values(), times());
        }
        return segmentValues;
    }

    @Override
    public void setSegmentValues(List<double[]> segmentValues) {
        this.segmentValues = segmentValues;
    }

    @Override
    public List<String[]> segmentTimes() {
        if(segmentTimes == null) {
            transform.toSegments(this, values(), times());
        }
        return segmentTimes;
    }

    @Override
    public void setSegmentTimes(List<String[]> segmentTimes) {
        this.segmentTimes = segmentTimes;
    }
}
