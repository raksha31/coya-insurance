/**
 *
 */
package coya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coya.dto.InsuranceRequestDto;
import coya.dto.InsuranceResponseDto;
import coya.service.Processor;

/**
 * @author Raksha
 *
 */
@RestController
@RequestMapping("/coya")
public class InsuranceController {
	@Autowired
	Processor processor;
	@PostMapping("/premium")
	public InsuranceResponseDto getQuotes(@RequestBody InsuranceRequestDto insuranceRequestDto) {
		return processor.priceFor(insuranceRequestDto);
	}

}
