package github.myacai.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @Author cwb
 * @Date 2020/11/29 14:11
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
