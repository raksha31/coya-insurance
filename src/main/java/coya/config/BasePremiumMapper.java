/**
 *
 */
package coya.config;

import java.util.HashMap;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author Raksha
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "coya.constants")
@NoArgsConstructor
public class BasePremiumMapper {
    public HashMap<String, Double> BasePremium = new HashMap<>();
}
