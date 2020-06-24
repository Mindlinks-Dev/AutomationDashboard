package com.cohbaines.testpages;

public class test1 {
public void tech()
{
	System.out.println("Tech");
}

}
class Atech{
	test1 a=new test1();
	public void tech(){
		System.out.println("an tech");
	}
	
		


public void dothis(){
	a.tech();
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Atech atech = new Atech();
		 atech.dothis();

	}

}

