package com.gm.calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gm.converter.Unit;
import com.gm.converter.UnitConverter;

/**
 * 长度计算机
 * @author Administrator
 *
 */
public class LengthCalculator extends CalculatorAdapter implements CalculatorHandler{

	
	public LengthCalculator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//生成所需要的单位
		UnitConverter ucon = new UnitConverter();
		
		LengthCalculator lc = new LengthCalculator();
		lc.setUnitHandler(ucon);
		try {
			lc.getInPutInfo();
			lc.compute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public BigDecimal getOutcome() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public File getInputFile() throws IOException {
		// TODO Auto-generated method stub
		//输入文件地址
				
		System.out.print("Please input the address of the 'input.txt':");
		return new File(getReader().readLine());
//		return new File("C:\\Users\\Administrator\\Desktop\\work\\input.txt");
	}

	//得到输入流字符
	protected BufferedReader getReader(){
		InputStreamReader din =new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(din);
		return in;
	}
	
	@Override
	public File getOutputFile() throws IOException {
		// TODO Auto-generated method stub
		//输入目标地址
				System.out.print("Please input the address of the 'output.txt':");
			
		return new File(getReader().readLine());
//		return new File("C:\\Users\\Administrator\\Desktop\\work\\output.txt");
	}

	@Override
	public void loadComputing(Map unitAmt, List formulas) throws IOException {
		// TODO Auto-generated method stub
		  DecimalFormat df = new DecimalFormat("0.00");
		//公式循环
		for(Iterator it = formulas.iterator();it.hasNext();){
		
			//公式
			String formula =  it.next().toString();
		
			if(!formula.trim().equals("")){
				
			//当前需要计算的值
			StringBuffer sbf = new StringBuffer();
			
			//当前计算
			Stack sign = new Stack();
			
			Map valueUnit = new HashMap();
			
			//当前数
			BigDecimal tempAmt = null;
			
			//结果
			BigDecimal outcome = null ;
			//计算循环
			for(int i =formula.length();i>0;i--){
				//本字符(用字符串表示的)
				String temp;
				//取得当前字符
				temp = formula.substring(i-1,i);
				//当为" "才会向下走
				if(temp.equals(" ")){
					while(!sign.isEmpty()){
				
						sbf.append(sign.pop());
				
					}
				}else{
					//字符处理
						sign.push(temp);
				
				
					continue;
				}
				
				
				
				
				//如果为数字或小数点
				if(sbf.toString().matches("([-\\+]?[1-9]([0-9]*)(\\.[0-9]+)?)|(^0$)")){
					tempAmt.multiply(new BigDecimal(sbf.toString()));
					sbf = new StringBuffer();
					//如果当前字符为单位,运算
				}else if(sbf.toString().matches("[\\*\\+\\-\\/]")){
				
					if(iscomputed(sign)){
						//因为是顺序的,没有括号,所以可以这么算
						if(outcome==null){
							outcome = new BigDecimal(0.00);
						}
						outcome = opreating(sign,outcome,tempAmt);
						
					}else{
						outcome = tempAmt;
						
					}
					sbf = new StringBuffer();
				}else if (unitAmt.containsKey(this.getUnitHandler().getUnit(sbf.toString()))){
					//得到上个数字,每得到一个数要跟上次计算
					
					tempAmt = (BigDecimal) unitAmt.get(this.getUnitHandler().getUnit(sbf.toString()));
					sbf = new StringBuffer();
				}
				
			}
			
			//处理值换算
			if(iscomputed(sign)){
				StringBuffer amt = new StringBuffer();
				while(!sign.isEmpty()){
					amt.append(sign.pop());
				}
				outcome = tempAmt.multiply(new BigDecimal(amt.toString()));
			}
			
			
		
			target.write(df.format(outcome)+" "+Unit.m);
			target.newLine();
			
		}
		}
		target.flush();
	}
	
	protected BigDecimal opreating(Stack sign,BigDecimal outcome,BigDecimal temp){
		String tmp= (String) sign.pop();
		if(OperatingSign.add.toString().equals(tmp)){
			outcome.add(temp);
		}else if (OperatingSign.minus.toString().equals(tmp)){
			outcome.subtract(temp);
		}else if (OperatingSign.multi.toString().equals(tmp)){
			outcome.multiply(temp);
		}else if(OperatingSign.exc.toString().equals(tmp)){
			outcome.divide(temp);
		}
		
		return outcome;
	}
	
	protected boolean iscomputed(Stack sign){
		
		return !sign.isEmpty();
	}

	@Override
	public CalculatorHandler getHandler() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void initOutPut() throws IOException {
		// TODO Auto-generated method stub
		//邮箱
		this.target.write("119618949@qq.com");
		this.target.newLine();
		this.target.newLine();
	}  
	
}
