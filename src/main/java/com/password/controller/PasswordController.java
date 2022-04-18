package com.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.password.dao.PasswordRepository;
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

	@GetMapping("/get/mail/{mailId}")
	private String getByMailId(@RequestParam String mailId, Model model) {

		model.addAttribute("passes", passwordRepository.findPasswordManagerByMailId(mailId));

		return "getall";
	}

	@GetMapping("/getsite")
	private String getBySiteDupe(Model model) {
		ViewManager viewManager = new ViewManager();
		model.addAttribute("viewManager", viewManager);
		return "getbysite";
	}

	@GetMapping("/get/siteName/{siteName}")
	private String getBySiteName(@RequestParam String siteName, Model model) {

		model.addAttribute("passes", passwordRepository.findPasswordManagerBySiteName(siteName));
		return "getall";
	}

	@DeleteMapping("/clear")
	private String deleteAll() {
		passwordRepository.deleteAll();
		return "All the records were cleared!";
	}
}
