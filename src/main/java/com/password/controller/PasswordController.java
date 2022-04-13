package com.password.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.password.dao.PasswordRepository;
import com.password.entity.PasswordManager;
import com.password.entity.ViewManager;
import com.password.service.PasswordGenerationService;

@RestController
public class PasswordController {

	@Autowired
	PasswordRepository passwordRepository;

	@Autowired
	PasswordGenerationService passwordGenerationService;

	static String toastMessage = "";

	@PostMapping("/add")
	private @ResponseBody Object postPass(@RequestBody ViewManager viewManager) {

		String isSiteName = viewManager.getSiteName();

		passwordRepository.findPasswordManagerBySiteName(isSiteName).ifPresentOrElse(s -> {
			toastMessage = isSiteName + " already present in the records";
		}, () -> {

			String password = passwordGenerationService.generatedPassword();

			PasswordManager passwordManager = new PasswordManager(viewManager.getSiteName(), password,
					viewManager.getMailId());
			passwordRepository.save(passwordManager);
			toastMessage = isSiteName + " has been added to records";
		});

		return toastMessage;
	}

	@GetMapping("/get")
	private @ResponseBody List<PasswordManager> getPass() {

		return passwordRepository.findAll();
	}

	@GetMapping("/get/{mailId}")
	private @ResponseBody List<PasswordManager> getByMailId(@PathVariable String mailId) {

		return passwordRepository.findPasswordManagerByMailId(mailId);
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
