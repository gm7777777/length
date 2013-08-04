package com.gm.calculator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 计算器业务处理
 * @author tim_gao
 * @date 2013-08-02
 */
public interface CalculatorHandler {
	
	public abstract File getInputFile() throws IOException;
	
	public abstract File getOutputFile() throws IOException;
	
	public abstract void loadComputing(Map unitAmt,List  formulas) throws IOException;

	public abstract BigDecimal getOutcome();
	

}
