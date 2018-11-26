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
@JsonTypeName("House")
public class House extends Product{

	public House(int id, Double value,String type, int size) {
		super(id, value,type);
		this.size = size;
	}
	int size;



}
