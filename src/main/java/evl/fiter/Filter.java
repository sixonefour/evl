package evl.fiter;


import evl.data.DataSource;

/**
 * 过滤器
 * @author  Liuyisi
 * @since   0.1
 */
public interface Filter {

    /**
     * @param dataSource 数据源
     * @return 返回false时，程序将会直接返回，不会再调用算法评分
     */
    default boolean doFilter(DataSource dataSource) {
        filter(dataSource);
        return true;
    }

    default void filter(DataSource dataSource) {
        throw new RuntimeException("not implemented");
    }
}
