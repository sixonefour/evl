package evl.data;


import evl.common.Attributes;

/**
 * 数据源
 * @author  Liuyisi
 * @since   0.1
 */
public interface DataSource extends Attributes {
    //标识符
    String sourceId();
    void setSourceId(String sourceId);
    //当前的值
    double currentValue();
    void setCurrentValue(double value);
    //当前时间
    String currentTime();
    void setCurrentTime(String time);
    //当前状态
    int currentStatus();
    void setCurrentStatus(int status);
    //告警值
    double alarmValue();
    void setAlarmValue(double value);
    //列表
    double[] values();
    void setValues(double[] values);
    //时间
    String[] times();
    void setTimes(String[] times);
}
