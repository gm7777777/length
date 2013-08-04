package com.gm.converter;

import java.math.BigDecimal;


/**
 * 单位转换处理
 * @author tim_gao
 * @date 2013-08-02
 */
public interface UnitConverterHandler {
	
	/**
	 * 得到转化后单位
	 * @param unit
	 * @return Unit enum
	 */
	public abstract Unit getUnit(String unit);
	

}
