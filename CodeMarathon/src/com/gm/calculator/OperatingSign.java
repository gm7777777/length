package com.gm.calculator;

public enum OperatingSign {
//大写为枚举,小写为符号
 add{
	 public String toString(){
		 return "+";
	 }
 },ADD
 ,minus{
	 public String toString(){
		 return "-";
	 }
 },MINUS
 ,multi{
	 public String toString(){
		 return "+";
	 }
 },MULTI,
 exc{
	 public String toString(){
		 return "/";
	 }
 },EXC,
 equal{
	 public String toString(){
		 return "=";
	 }
 },EQUAL
}
