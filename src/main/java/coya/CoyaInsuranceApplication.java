package coya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import coya.config.BasePremiumMapper;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(BasePremiumMapper.class)
@EnableAutoConfiguration
public class CoyaInsuranceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CoyaInsuranceApplication.class, args);
    }
}
