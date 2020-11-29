package github.myacai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author cwb
 * @Date 2020/11/29 14:02
 */
public class RpcServer {
    private ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    public RpcServer() {
        // 线程池参数
        int corePoolSize = 10;
        int maximumPoolSizeSize = 100;
        long keepAliveTime = 1;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        this.threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSizeSize, keepAliveTime, TimeUnit.MINUTES, workQueue, threadFactory);
    }

    /**
     * 服务端主动注册服务
     * TODO 修改为注解然后扫描
     */
    public void register(Object service, int port) {
        try (ServerSocket server = new ServerSocket(port);) {
            logger.info("server starts...");
            Socket socket;
            while ((socket = server.accept()) != null) {
                logger.info("client connected");
                threadPool.execute(new WorkerThread(socket, service));
            }
        } catch (IOException e) {
            logger.error("occur IOException:", e);
        }
    }
}
