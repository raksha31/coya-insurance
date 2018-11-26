/**
 *
 */
package coya.config;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raksha
 *
 */
public enum UserSurchargeLevel {
	LESS_THAN_20, LESS_THAN_201, LESS_THAN_501;

	private static HashMap<UserSurchargeLevel, Double> map = new HashMap<>();

	public static HashMap<UserSurchargeLevel, Double> getUserSurchargeMap() {
		map.put(LESS_THAN_20, 0.3);
		map.put(LESS_THAN_201, 1.0);
		map.put(LESS_THAN_501, 3.0);
		return map;
	}

}
