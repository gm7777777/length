package com.gm.converter;

public enum Unit {
	//单,复数都做枚举,就不做字符处理了
	m,mile,miles,yard,yards,inch,inches,foot,feet,fath,faths,furlong;
	
	
	//有点繁琐
	public Unit EnumExchanger(String unit){
		
		switch (Unit.valueOf(unit)){
		case miles : return mile;
		case yards :return yard;
		case inches :return inch;
		case feet :return foot;
		case faths :return fath;
		}
		return Unit.valueOf(unit);
		
	}

}
