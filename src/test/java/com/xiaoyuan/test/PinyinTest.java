package com.xiaoyuan.test;


public class PinyinTest {
   
   public static void main(String[] args) {
		System.out.println(PinYinUtil.getPingYin("中国石油大学（北京）"));
		System.out.println(PinYinUtil.getFirstSpell("中国石油大学（北京）"));
		//System.out.println(PinYinUtil.getFullSpell("中国石油大学（北京）"));
} 
}
