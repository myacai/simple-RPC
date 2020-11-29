package github.myacai;

import github.myacai.server.HelloServiceImpl;
import github.myacai.server.IHelloService;

/**
 * @Author cwb
 * @Date 2020/11/29 14:31
 */
public class ServerMain {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9999);
    }
}
