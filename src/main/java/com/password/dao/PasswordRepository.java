package com.password.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.password.entity.PasswordManager;

@Repository
public interface PasswordRepository extends MongoRepository<PasswordManager, String> {

	@Query(value = "{'siteName' : {$regex : ?0, $options: 'i'}}")
	Optional<PasswordManager> findPasswordManagerBySiteName(String siteName);

	@Query(value = "{'mailId' : {$regex : ?0, $options: 'i'}}")
	List<PasswordManager> findPasswordManagerByMailId(String mailId);
}
