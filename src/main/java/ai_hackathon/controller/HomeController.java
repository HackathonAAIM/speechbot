package ai_hackathon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/home")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/audio")
	public ModelAndView test1(HttpServletResponse response) throws IOException{
		return new ModelAndView("audio");
	}
	
	@RequestMapping(value="/media")
	public ModelAndView test2(HttpServletResponse response) throws IOException{
		return new ModelAndView("mediaRecorder");
	}
	
	@RequestMapping(value="/dicta")
	public String test3(HttpServletResponse response) throws IOException{
		//return new ModelAndView("dictaphone");
		return "redirect:/static/dictaphone.html";
	}
}
