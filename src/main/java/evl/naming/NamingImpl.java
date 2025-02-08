package evl.naming;

import evl.core.Contained;

/**
 * @author  Liuyisi
 * @since   0.1
 */
public class NamingImpl implements AbstractNaming{

    @Override
    public String naming(Contained contained) {
        return contained.toString();
    }
}
