/**
 *
 */
package coya.model;


import javax.annotation.Resource;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raksha
 *
 */

@NoArgsConstructor
@Resource
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Bicycle.class, name = "Bicycle"),
    @JsonSubTypes.Type(value = House.class, name = "House"),
    @JsonSubTypes.Type(value = Banana.class, name = "Banana") }
)
public  abstract class Product {

	private int id;
	private Double value;
	private String type;

	public Product(int id, Double value, String type) {
		this.id = id;
		this.value = value;
		this.type = type;
	}

}
