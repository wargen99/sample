package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.dto.projectInfo.Project;
import com.example.dto.userInfo.User;
import com.example.repository.project.projectRepository;
import com.example.repository.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private projectRepository projectRepo;

	@Test
	public void repoTest() {

		User user = new User();
		user.setEmail("test@test.com");
		user.setName("john");
		user.setAddress("london");

		userRepo.save(user);

		List<User> userList = userRepo.findByEmail("test@test.com");

		for (User subUser : userList) {
			System.out.println(subUser.getName());
			System.out.println(subUser.getEmail());
			System.out.println(subUser.getIdx());
			System.out.println(subUser.getAddress());
		}

		Project project = new Project();
		project.setProjectName("testProject");
		project.setProjectDescription("test");
		
		projectRepo.save(project);

		List<Project> projectList = projectRepo.findByProjectName("testProject");

		for (Project subPro : projectList) {
			System.out.println(subPro.getProjectName());
			System.out.println(subPro.getProjectDescription());
			System.out.println(subPro.getIdx());
			
		}

	}

}
