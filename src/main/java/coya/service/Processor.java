/**
 *
 */
package coya.service;

import java.util.List;

import coya.dto.InsuranceRequestDto;
import coya.dto.InsuranceResponseDto;

/**
 * @author Raksha
 *
 */
public interface Processor {
	public InsuranceResponseDto priceFor(InsuranceRequestDto insuranceRequestDto);
}
