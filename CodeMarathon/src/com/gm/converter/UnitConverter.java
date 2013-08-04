package com.gm.converter;

import java.math.BigDecimal;

/**
 * 单位转换器
 * @author tim_gao
 * @date 2013-8-2
 */
public class UnitConverter implements UnitConverterHandler{

	public UnitConverter() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public Unit getUnit(String unit) {
		// TODO Auto-generated method stub
		if(unit!=null&&unit.length()>0){
			//繁琐
			return Unit.valueOf(unit).EnumExchanger(unit);
		}return null;
	}
	

}
