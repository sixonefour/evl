package evl.tran;

import evl.data.SegmentDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class StandardTransform implements Transform {

    private static final int DEFAULT_COUNT = 2;

    private final int segCount;

    public StandardTransform() {
        this(DEFAULT_COUNT);
    }

    public StandardTransform(int segCount) {
        super();
        this.segCount = segCount;
    }

    @Override
    public void toSegments(SegmentDataSource segmentDataSource, double[] values, String[] times) {
        splitOfSegment(segmentDataSource, values, times, segCount);
    }

    @Override
    public void toValues(SegmentDataSource segmentDataSource, List<double[]> segmentValues, List<String[]> segmentTimes) {

    }

    private void splitOfSegment(SegmentDataSource segmentDataSource, double[] values, String[] times, int segment) {
        int idx = values.length % segment;
        int length = (values.length - idx) / segment;

        List<double[]> segmentValues = new ArrayList<>();
        List<String[]> segmentTimes = new ArrayList<>();
        for(int i = idx; i < values.length; i+= length) {
            segmentValues.add(Arrays.copyOfRange(values, i, i + length));
            segmentTimes.add(Arrays.copyOfRange(times, i, i + length));
        }
        segmentDataSource.setSegmentValues(segmentValues);
        segmentDataSource.setSegmentTimes(segmentTimes);
    }


}
