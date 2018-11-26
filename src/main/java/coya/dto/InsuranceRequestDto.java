/**
 *
 */
package coya.dto;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import coya.model.Product;
import coya.model.User;

/**
 * @author Raksha
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class InsuranceRequestDto {
	User user;	List<Product> products;

}
