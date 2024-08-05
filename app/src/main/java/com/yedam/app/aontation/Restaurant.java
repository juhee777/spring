package com.yedam.app.aontation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //빈으로 등록하겠다는 선언
public class Restaurant {
	private Chef chef;
	@Autowired //생성자 인젝션
	Restaurant(Chef chef){ //id 이름이 같을 경우 에러
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}
	//세터 인젝션
	Restaurant(){
		System.out.println("세터 인젝션");
		}
	//@Autowired
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	void run() {
		chef.cooking();
	}
}
