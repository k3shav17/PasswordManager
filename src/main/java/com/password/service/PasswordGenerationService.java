package com.password.service;

import org.springframework.stereotype.Service;

@Service
public interface PasswordGenerationService {

	String generatedPassword();
}
