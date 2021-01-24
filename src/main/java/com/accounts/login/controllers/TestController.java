package com.accounts.login.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.accounts.login.models.Skill;
import com.accounts.login.models.User;
import com.accounts.login.payload.request.SignupRequest;
import com.accounts.login.payload.response.MessageResponse;
import com.accounts.login.repository.UserRepository;
import com.accounts.login.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.accounts.login.models.Role;
import com.accounts.login.repository.RoleRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/all")
	public List<Role> allAccess() {
		return roleRepository.findAll();
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/user/{skill}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> userAccessAddingSkill(@RequestParam String string) {
		User user = this.userRepository.findByUsername("qqq").get();
		Skill newSkill = new Skill();
		newSkill.setSkillName(string);
		user.addSkill(newSkill);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User skill successfully added!"));
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
