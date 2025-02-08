package evl.fiter;

import evl.core.Contained;
import evl.core.ContainedBase;
import evl.data.DataSource;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class FilterContextImpl extends ContainedBase implements FilterContext{

    private final FilterChain filterChain;

    public FilterContextImpl(Contained contained) {
        filterChain = new FilterChainImpl();
        setParent(contained);
    }


    @Override
    public void addFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    @Override
    public boolean doFilter(DataSource dataSource) {
        return filterChain.invoke(dataSource);
    }

}
