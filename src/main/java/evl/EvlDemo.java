package evl;


import evl.core.Engine;
import evl.core.OfType;
import evl.core.StandardEngine;
import evl.core.StandardOfType;
import evl.rlt.Result;
import evl.rlt.handle.SimpleResultHandle;

public class EvlDemo {

    public static void main(String[] args) {

        //创建一个计算引擎
        Engine engine = new StandardEngine();
        //设置全局的结果处理器,子类如果没有设置自己的结果处理器，则会调用父类的
        engine.setResultHandle(new SimpleResultHandle());

        StandardOfType zjzlRT = new StandardOfType("zjzl");
        //可以设置自己的结果处理器
        zjzlRT.setResultHandle(new SimpleResultHandle());

        //可以设置多个数据源，设置多数据源时，需要
        //zjzlRT.addSource();
        //engine.addChild("zjzl", 1, zjzlRT);
        //顶板离层
        OfType dblcRT = new StandardOfType("dblc");

        dblcRT.buildStandardPipeline("1", 1).build()
                .buildStandardPipeline("2", 1).build();

        engine.addChild("dblc", 1, dblcRT);


        //设置数据源
        //dblcRT.addSource();
        //engine.addChild("dblc", 1, dblcRT);


        //依次类推
        //其它类型



        //执行计算
        Result invoke = engine.invoke();
        System.out.println(invoke);
    }
}
