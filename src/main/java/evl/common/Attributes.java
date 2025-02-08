package evl.common;


/**
 * 属性
 * @author  Liuyisi
 * @since   0.1
 */
public interface Attributes {

    Object getAttribute(String k);

    void setAttribute(String k, Object val);

    String[] getParameterTypeNames();
}
