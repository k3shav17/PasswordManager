package com.password.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.password.dao.PasswordRepository;
import com.password.entity.PasswordManager;
import com.password.entity.ViewManager;
import com.password.service.PasswordGenerationService;
import com.password.service.PasswordManagerService;

@Controller
public class PasswordController {

	@Autowired
	PasswordRepository passwordRepository;

	@Autowired
	PasswordGenerationService passwordGenerationService;

	@Autowired
	PasswordManagerService passwordManagerService;

	static String toastMessage = "";

	@PostMapping("/add")
	private String postPass(@ModelAttribute("viewManager") ViewManager viewManager) {
		passwordManagerService.savePassword(viewManager);
		return "redirect:/";
	}

	@GetMapping("/addDupe")
	private String postPassDupe(Model model) {
		ViewManager viewManager = new ViewManager();
		model.addAttribute("viewManager", viewManager);
		return "newpass";
	}

	@GetMapping("/get")
	private String getPass(Model model) {

		model.addAttribute("passes", passwordRepository.findAll());
		return "getall";
	}

	@GetMapping("/getByDupe")
	private String getByMailDupe(Model model) {
		ViewManager viewManager = new ViewManager();
		model.addAttribute("viewManager", viewManager);
		return "ByMail";
	}

	@GetMapping("/get/mailId/{mailId}")
	private String getByMailId(@PathVariable String mailId, Model model) {

		model.addAttribute("passes", passwordRepository.findPasswordManagerByMailId(mailId));

		return "getall";
	}

	@GetMapping("/get/siteName/{siteName}")
	private @ResponseBody Optional<PasswordManager> getBySiteName(@PathVariable String siteName) {

		return passwordRepository.findPasswordManagerBySiteName(siteName);
	}

	@DeleteMapping("/clear")
	private String deleteAll() {
		passwordRepository.deleteAll();
		return "All the records were cleared!";
	}
}
