package evl.fiter;


import evl.data.DataSource;

/**
 * 过滤器链
 * @author  Liuyisi
 * @since   0.1
 */
public interface FilterChain {

    void addFilter(Filter filter);

    boolean invoke(DataSource dataSource);
}
