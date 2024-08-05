package com.yedam.app.xml;

public class Restaurant {
	private Chef chef;
	//생성자 인젝션
	Restaurant(Chef chef){
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}
	//세터 인젝션
	Restaurant(){
		System.out.println("세터 인젝션");
		}
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	void run() {
		chef.cooking();
	}
}
