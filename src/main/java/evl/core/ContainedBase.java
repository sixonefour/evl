package evl.core;


import evl.common.Config;
import evl.naming.AbstractNaming;
import evl.naming.NamingImpl;
import evl.rlt.Result;
import evl.rlt.handle.ResultHandle;

/**
 * 容器基础实现
 * @author  Liuyisi
 * @since   0.1
 */
public abstract class ContainedBase implements Contained{

    private Config config;

    private String containedName;

    private ResultHandle resultHandle;

    private Contained parent;

    private AbstractNaming abstractNaming;

    public ContainedBase() {
        abstractNaming = new NamingImpl();
    }

    @Override
    public Result invoke() {
        throw new RuntimeException(getClass().getSimpleName() + " Not implemented invoke!");
    }

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public Config getConfig() {
        if(config == null && parent != null) {
            return parent.getConfig();
        }
        return config;
    }

    @Override
    public void setResultHandle(ResultHandle resultHandle) {
        this.resultHandle = resultHandle;
    }

    @Override
    public ResultHandle resultHandle() {
        if(resultHandle == null && parent != null) {
            return parent.resultHandle();
        }
        return resultHandle;
    }

    @Override
    public void setParent(Contained context) {
        this.parent = context;
    }

    @Override
    public Contained parent() {
        return this.parent;
    }

    @Override
    public void setContainName(String containName) {
        this.containedName = containName;
    }

    @Override
    public String containName() {
        if(containedName == null) {
            return naming().naming(this);
        }
        return containedName;
    }

    @Override
    public void setNaming(AbstractNaming abstractNaming) {
        this.abstractNaming = abstractNaming;
    }

    @Override
    public AbstractNaming naming() {
        if(abstractNaming == null && parent != null) {
            return parent.naming();
        }
        return this.abstractNaming;
    }
}
