package ckc.android.example.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 创建线程池的一个参数
 * */
class ThreadFactoryExample implements ThreadFactory {

    //线程组
    private final ThreadGroup mThreadGroup;
    //原子数
    private final AtomicInteger mThreadNum = new AtomicInteger(0);

    public ThreadFactoryExample() {
        SecurityManager manager = System.getSecurityManager();
        mThreadGroup = (manager != null) ? manager.getThreadGroup()
                : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        //设置新线程的归属组、任务、名称等
        Thread thread = new Thread(mThreadGroup, r, "name"+mThreadNum.getAndIncrement());

        //设置守护进程
        thread.setDaemon(false);

        //设置优先级
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}
