import github.myacai.RpcClient;
import github.myacai.RpcClientProxy;
import github.myacai.SocketRpcClient;
import github.myacai.dto.Hello;
import github.myacai.server.IHelloService;

/**
 * @Author cwb
 * @Date 2020/11/29 14:29
 */
public class ClientMain {
    public static void main(String[] args) {
        RpcClient rpcClient = new SocketRpcClient("127.0.0.1", 9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcClient);
        IHelloService helloService = rpcClientProxy.getProxy(IHelloService.class);
        String hello = helloService.hello(new Hello("111", "222"));
        System.out.println(hello);
    }
}
