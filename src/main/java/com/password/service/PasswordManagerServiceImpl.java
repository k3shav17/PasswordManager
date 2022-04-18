package com.password.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.password.dao.PasswordRepository;
import com.password.entity.PasswordManager;
import com.password.entity.ViewManager;

@Service
public class PasswordManagerServiceImpl implements PasswordManagerService {

	@Autowired
	PasswordRepository passwordRepository;

	@Autowired
	PasswordGenerationService passwordGenerationService;

	static String toastMessage = "";

	@Override
	public Object savePassword(ViewManager viewManager) {
		String isSiteName = viewManager.getSiteName();

//		passwordRepository.findPasswordManagerBySiteName(isSiteName).ifPresentOrElse(s -> {
//			toastMessage = isSiteName + " already present in the records";
//		}, () -> {
//
//			String password = passwordGenerationService.generatedPassword();
//
//			PasswordManager passwordManager = new PasswordManager(viewManager.getSiteName(), password,
//					viewManager.getMailId());
//			passwordRepository.save(passwordManager);
//			toastMessage = isSiteName + " has been added to records";
//		});

		if (passwordRepository.findPasswordManagerBySiteName(isSiteName).isEmpty()) {
			String password = passwordGenerationService.generatedPassword();

			PasswordManager passwordManager = new PasswordManager(viewManager.getSiteName(), password,
					viewManager.getMailId());
			passwordRepository.save(passwordManager);
			toastMessage = isSiteName + " has been added to records";

		} else {
			toastMessage = isSiteName + " already present in the records";

		}

		return toastMessage;
	}

}
