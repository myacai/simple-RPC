package github.myacai;

import github.myacai.dto.RpcRequest;

/**
 * @Author cwb
 * @Date 2020/11/30 22:01
 */
public interface RpcClient {
    Object sendRpcRequest(RpcRequest rpcRequest);
}
