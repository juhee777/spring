package com.yedam.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
										//classpath 안쓰면 -> file:src/main/resources/applicationContext.xml
		TV tv = (TV)ctx.getBean("tv"); //"tv"라는 이름의 id속성 getBean은 obj
		tv.turnOn();
		
		TV subTv = (TV)ctx.getBean(TV.class); //직접적으로 class를 불러옴(id속성이 없는 경우)
		subTv.turnOn();
		
		if(tv == subTv) {//같은 대상을 가르키고 있는지 확인
			System.out.println("같은 빈입니다.");
		}else {
			System.out.println("다른 빈입니다.");
		}
		
	}

}
