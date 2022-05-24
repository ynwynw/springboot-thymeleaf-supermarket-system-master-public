package com.yeqifu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WarehouseApplicationTests {

	@Test
	void contextLoads() {
		String a = "鼠标右键按下";
		System.out.println(a.substring(2, 4));
	}

}
