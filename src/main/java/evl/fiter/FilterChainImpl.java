package evl.fiter;

import evl.data.DataSource;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class FilterChainImpl extends ArrayList<Filter> implements FilterChain{

    public FilterChainImpl() {
    }

    @Override
    public void addFilter(Filter filter) {
        add(filter);
    }

    @Override
    public boolean invoke(DataSource dataSource) {

        /**
         * 过滤器会判断数据是否合法
         * 存在不合法的数据时，会立即返回，将不再调用后面算法进行计算
         */
        boolean f = true;
        Iterator<Filter> iterator = iterator();
        while (iterator.hasNext() && f) {
            Filter filter = iterator.next();
            f = filter.doFilter(dataSource);
        }
        return f;
    }


}
