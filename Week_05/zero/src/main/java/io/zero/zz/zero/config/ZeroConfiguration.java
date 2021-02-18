package io.zero.zz.zero.config;

import io.zero.zz.zero.Zero;
import io.zero.zz.zero.properties.ZeroProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhurui
 * @Date 2021/2/8 3:13 下午
 * @Version 1.0
 */
@Configuration
@ConditionalOnClass(Zero.class)
@EnableConfigurationProperties(ZeroProperties.class)
public class ZeroConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Zero zero(ZeroProperties zeroProperties) {
        Zero zero = new Zero();
        zero.setPort(zeroProperties.getPort());
        return zero;
    }

}
