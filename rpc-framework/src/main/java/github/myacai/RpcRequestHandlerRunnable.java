package github.myacai;
import github.myacai.dto.RpcRequest;
import github.myacai.dto.RpcResponse;
import github.myacai.registry.DefaultServiceRegistry;
import github.myacai.registry.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * @Author cwb
 * @Date 2020/11/29 16:25
 */
public class RpcRequestHandlerRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RpcRequestHandlerRunnable.class);
    private Socket socket;
    private static RpcRequestHandler rpcRequestHandler;
    private static ServiceRegistry serviceRegistry;
    static {
        rpcRequestHandler=new RpcRequestHandler();
        serviceRegistry = new DefaultServiceRegistry();
    }

    public RpcRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = rpcRequestHandler.handle(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("occur exception:", e);
        }
    }

}
