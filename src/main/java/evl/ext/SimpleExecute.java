package evl.ext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecute {


    private static ExecutorService exs = null;


    public static ExecutorService getExecutorService() {
        if(exs == null) {
            exs = Executors.newFixedThreadPool(10);
        }
        return exs;
    }


}
