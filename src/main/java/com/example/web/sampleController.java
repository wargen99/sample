package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
/*import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;*/
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.repository.user.UserRepository;

@RestController
public class sampleController {

	@RequestMapping(value = "/user/{userid}")
	public @ResponseBody String home(@PathVariable String userid) {
		String str = userid;
		return str;
	}

}
