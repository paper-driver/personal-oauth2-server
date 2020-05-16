package com.paperdriver.personalauthserver.repositories;

import com.paperdriver.personalauthserver.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials,Long> {
    Credentials findByName(String name);
}
