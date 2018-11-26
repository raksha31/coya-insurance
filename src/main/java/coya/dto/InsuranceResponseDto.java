/**
 *
 */
package coya.dto;

import java.time.OffsetDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Raksha
 *
 */
@Data
@AllArgsConstructor
public class InsuranceResponseDto {
	Map<String, Double> productInsurance;

}
