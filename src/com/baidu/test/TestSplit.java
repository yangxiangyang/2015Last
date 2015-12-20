package com.baidu.test;

import com.baidu.util.StringUtil;
import com.baidu.util.WebUtils;

public class TestSplit {
	
	public static void main(String[] args) {
		
		/*String str=",1,32,4";
		String[] split = str.split(",");
		for (String string : split) {
			System.out.println(string);
		}
		*/
		
		//测试StringUtil.java
		String randomInt = StringUtil.randomInt();
		System.out.println("randomInt="+randomInt);
		//测试WebUtils.java
		String randomId = WebUtils.getRandomId();
		System.out.println("randomId="+randomId);
		
		
		//测试""和null
		String name="";
		System.out.println("name.length="+name.length());
	}

}
