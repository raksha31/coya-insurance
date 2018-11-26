/**
 *
 */
package coya.model;


import javax.annotation.Resource;

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
@Resource
public class Address {
	private int id;
	private int locationRisk;
}
