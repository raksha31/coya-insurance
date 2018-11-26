/**
 *
 */
package coya.model;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raksha
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
	private int id;
	private Address address;
	private int risk;
}
