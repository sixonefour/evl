package evl.data;

import java.util.List;

/**
 * 加载数据源
 * @author  Liuyisi
 * @since   0.1
 */
public interface LoadDataSource extends Iterable<DataSource> {
    List<DataSource> load();
}
