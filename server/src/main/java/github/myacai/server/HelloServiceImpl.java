package github.myacai.server;

import github.myacai.dto.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author cwb
 * @Date 2020/11/29 14:35
 */
public class HelloServiceImpl implements IHelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(Hello hello) {
        logger.info("HelloServiceImpl收到: {}.", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        logger.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}

