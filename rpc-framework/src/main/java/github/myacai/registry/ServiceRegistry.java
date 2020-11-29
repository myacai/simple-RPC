package github.myacai.registry;

/**
 * @Author cwb
 * @Date 2020/11/29 16:24
 */
public interface ServiceRegistry {
    <T> void register(T service);

    Object getService(String serviceName);
}
