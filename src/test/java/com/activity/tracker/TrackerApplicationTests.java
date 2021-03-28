package com.activity.tracker;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrackerApplication.class)
class TrackerApplicationTests {

	@Test
	void main() {
		TrackerApplication.main(new String[]{"test"});
	}

	@Test
	public void whenSpringContextIsBootstrapped_thenNoExceptions() {
	}

}
