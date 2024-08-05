package com.yedam.app.aontation;

import org.springframework.stereotype.Component;

@Component //빈으로 등록하겠다는 선언
public class Chef {
	public void cooking() {
		System.out.println("String 어노테이션 방식");
	}
}
