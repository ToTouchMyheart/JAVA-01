package io.zero.zz.zero.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author zhurui
 * @Date 2021/2/8 3:12 下午
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "zero")
@Component
@Data
public class ZeroProperties {
    private String port;
}
