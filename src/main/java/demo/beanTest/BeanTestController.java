package demo.beanTest;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BeanTestController {

	@RequestMapping(value="beans/test", method=RequestMethod.GET)
	public String getTest(Model model, HttpServletRequest request) {
		
		Coyote coyote = new Coyote();
		coyote.setId(1L);
		coyote.setName("Wild E. Coyote");
		
		List<String> fruitsAndVegetables = Arrays.asList("potato", "banana", "apple", "grapes");
		
		model.addAttribute("coyote", coyote);
		model.addAttribute("fruitsAndVegetables", fruitsAndVegetables);
		
		return "beans/test";
	}
}
