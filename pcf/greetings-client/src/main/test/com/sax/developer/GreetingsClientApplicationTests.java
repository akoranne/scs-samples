package com.sax.developer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreetingsClientApplication.class)
@DirtiesContext
public class GreetingsClientApplicationTests {

	@Test
	public void contextLoads() {
	}

}
