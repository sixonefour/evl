package evl.core;


import evl.common.Config;
import evl.naming.AbstractNaming;
import evl.rlt.Result;
import evl.rlt.handle.ResultHandle;

/**
 * 容器
 * @author  Liuyisi
 * @since   0.1
 */
public interface Contained {

    //执行器
    Result invoke();

    //容器名称
    String containName();
    void setContainName(String containName);

    //全局参数设置
    void setConfig(Config config);
    Config getConfig();

    //父容器
    Contained parent();
    void setParent(Contained context);

    //结果处理器
    void setResultHandle(ResultHandle resultHandle);
    ResultHandle resultHandle();

    //命名器
    void setNaming(AbstractNaming abstractNaming);
    AbstractNaming naming();

}
