package com.interpark.tour;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
class TourApplicationTests {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TourApplication.class);

	@Test
	@DisplayName("어플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			//Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
			//Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name=" + beanDefinitionName + " object=" + bean);
			}
		}
	}
}
