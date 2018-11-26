/**
 *
 */
package coya.model;


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
@JsonTypeName("Banana")
public class Banana  extends Product{



	public Banana(int id, Double value, String type, int blackspots) {
		super(id, value, type);
		this.blackspots = blackspots;
	}

	int blackspots;

}
