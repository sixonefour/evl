package evl.data;


import evl.common.AttributesImpl;

/**
 * 默认数据源实现
 * @author  Liuyisi
 * @since   0.1
 */
public class DefaultDataSource extends AttributesImpl implements DataSource {

    public final static double INVALID_VALUE = Double.POSITIVE_INFINITY;

    private String sourceId;

    private double currentValue = INVALID_VALUE;

    private String currentTime;

    private int currentStatus;

    private double alarmValue = INVALID_VALUE;

    private double[] values;

    private String[] times;

    public DefaultDataSource(double[] values, String[] times) {
        this.values = values;
        this.times = times;
    }

    @Override
    public String sourceId() {
        return sourceId;
    }

    @Override
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public double currentValue() {
        return currentValue != INVALID_VALUE ? currentValue : (values != null && values.length != 0 ? values[values.length - 1] : INVALID_VALUE );
    }

    @Override
    public void setCurrentValue(double value) {
        this.currentValue = value;
    }

    @Override
    public String currentTime() {
        return currentValue != INVALID_VALUE ? currentTime : (values != null && values.length != 0 ? times[values.length - 1] : "" );
    }

    @Override
    public void setCurrentTime(String time) {
        this.currentTime = time;
    }

    @Override
    public int currentStatus() {
        return currentStatus;
    }

    @Override
    public void setCurrentStatus(int status) {
        this.currentStatus = status;
    }

    @Override
    public double alarmValue() {
        return alarmValue;
    }

    @Override
    public void setAlarmValue(double value) {
        this.alarmValue = value;
    }

    @Override
    public double[] values() {
        return values;
    }

    @Override
    public void setValues(double[] values) {
        this.values = values;
    }

    @Override
    public String[] times() {
        return times;
    }

    @Override
    public void setTimes(String[] times) {
        this.times = times;
    }
}
