package com.gm.calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gm.converter.Unit;
import com.gm.converter.UnitConverterHandler;

/**
 * 计算器适配器 配置成各种计算机器
 * 
 * @author time_gao
 * @date 2013-08-02
 */
public abstract class CalculatorAdapter {

	// 单位转化器
	UnitConverterHandler unitHandler;

	String tempFile;
	// 源文件,目标文件
	File sourceFile, targetFile;
	BufferedReader source;
	BufferedWriter target;

	// 文件内标记点
	long filePoint;

	// 算式stack
	List  formulas = new ArrayList();
	// 单位换算
	Map unitMap = new HashMap();

	public CalculatorAdapter() {
		// TODO Auto-generated constructor stub
	}

	// 填入不同单位的转换 tim_gao
	public CalculatorAdapter(UnitConverterHandler unitHandler) {
		// TODO Auto-generated constructor stub
		this.setUnitHandler(unitHandler);
	}

	public abstract void initOutPut() throws IOException;
	
	public void getInPutInfo() throws IOException {

		// 输入文件
		sourceFile = (File) getHandler().getInputFile();

		// 出数文件
		targetFile = (File) getHandler().getOutputFile();

		// 读取文件
		source = new BufferedReader(new FileReader(sourceFile));

		// 写文件
		target = new BufferedWriter(new FileWriter(targetFile));
		initOutPut();
		// 读文件
		while (true) {
			String temp = source.readLine();
			// 当读行不为空时
			if (temp != null) {
				String[] str = temp.split("=");
				if (str != null && str.length > 1) {// 存在"="号时,是赋值
					// 转化str最后一个字符 到单位 enum 做为key
					// 去掉最后的单位 做为 value
					String unit = null;
					String key = str[0];
					for(int i = 0 ; i<key.length();i++){
						
						if(!(key.substring(i,i+1).matches("([-\\+]?[1-9]([0-9]*)(\\.[0-9]+)?)|(^0$)"))){
							if(unit == null){
								unit = key.substring(i,i+1);
							}else{
								unit +=key.substring(i,i+1); 
							}
							
						}
					}
					
					this.unitMap.put(unitHandler.getUnit(unit.trim()),
							 new BigDecimal(
										str[1].trim().substring(0, str[1].trim().length() - 1).trim()));

				} else {// 不存在"="号时是公式
					 formulas.add(str[0]);
				}

			} else {// 读完时
				break;
			}

		}

	}

	/**
	 * 运算
	 * @throws IOException 
	 */
	public void compute() throws IOException {
		this.getHandler().loadComputing(unitMap, formulas);
	}

	public UnitConverterHandler getUnitHandler() {
		return unitHandler;
	}

	public void setUnitHandler(UnitConverterHandler unitHandler) {
		this.unitHandler = unitHandler;
	}

	// 子类用来提供处理的逻辑,这样就可以复用已经写好的处理逻辑
	public abstract CalculatorHandler getHandler();

}
