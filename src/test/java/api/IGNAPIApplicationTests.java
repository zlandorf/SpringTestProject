package api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.IGNAPIApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IGNAPIApplication.class)
@WebAppConfiguration
public class IGNAPIApplicationTests {

	@Test
	public void contextLoads() {
	}

}
