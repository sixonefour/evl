package evl.core;

/**
 * 发动机
 * @author  Liuyisi
 * @since   0.1
 */
public interface Engine extends Contained {

    void addChild(String childName, int weight, Contained contained);

    void removeChild(String childName);

}
