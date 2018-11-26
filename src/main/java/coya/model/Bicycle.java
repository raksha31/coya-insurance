/**
 *
 */
package coya.model;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raksha
 *
 */
@Data
@NoArgsConstructor
@Resource
@JsonTypeName("Bicycle")
public class Bicycle extends Product {
	public Bicycle(int id, Double value, String type, int gears) {
		super(id, value,type);
		this.gears = gears;
	}

	int gears;

}
