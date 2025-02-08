package evl.common;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author  Liuyisi
 * @since   0.1
 */
@Data
public class AttributesImpl implements Attributes {

    private final Map<String, Object> params;

    public AttributesImpl() {
        params = new LinkedHashMap<>();
    }

    @Override
    public Object getAttribute(String k) {
        return params.get(k);
    }

    @Override
    public void setAttribute(String key, Object value) {
        params.put(key, value);
    }

    @Override
    public String[] getParameterTypeNames() {
        Set<String> keySet = params.keySet();
        if(!keySet.isEmpty()) {
            return keySet.toArray(new String[0]);
        }
        return new String[0];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("attributes {");

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        // 删除最后多余的逗号和空格
        if (!params.isEmpty()) {
            builder.setLength(builder.length() - 2);
        }

        builder.append("}");
        return builder.toString();
    }
}
