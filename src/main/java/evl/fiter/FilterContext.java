package evl.fiter;


import evl.core.Contained;
import evl.data.DataSource;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public interface FilterContext extends Contained {
    //过滤器
    void addFilter(Filter filter);
    //
    boolean doFilter(DataSource dataSource);
}
