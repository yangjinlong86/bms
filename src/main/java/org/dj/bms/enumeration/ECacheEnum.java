package org.dj.bms.enumeration;

/**
 * @ClassName: ECacheEnum
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午9:48:37
 */

public enum ECacheEnum {
	/**
	 * 数据字典缓存
	 */
	DICTCACHE("cache.system.dict");
	private String value;

	public String getValue() {
		return value;
	}

	/**
	 * ECacheEnum
	 * 
	 * @param value
	 */

	private ECacheEnum(String value) {
		this.value = value;
	}

}