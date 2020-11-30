package github.myacai;

import github.myacai.registry.DefaultServiceRegistry;
import github.myacai.server.HelloServiceImpl;

/**
 * @Author cwb
 * @Date 2020/11/29 14:31
 */
public class ServerMain {
    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        DefaultServiceRegistry defaultServiceRegistry = new DefaultServiceRegistry();
        // 手动注册
        defaultServiceRegistry.register(helloService);
        SocketRpcServer rpcServer = new SocketRpcServer(defaultServiceRegistry);
        rpcServer.start(9999);
    }
}
