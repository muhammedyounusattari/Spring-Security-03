package younus.attari.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainClassController {

	Logger LOG = LoggerFactory.getLogger(MainClassController.class);

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public void landingPage(Model model){
		System.out.println("this s ");
		LOG.info("this is from landingPage..",this.getClass());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		model.addAttribute("name",name);
		
		//return "home"; //not required, it will call by default
		//return "welcome"; //if want to call some other view,then add this
	}
	
	
}
