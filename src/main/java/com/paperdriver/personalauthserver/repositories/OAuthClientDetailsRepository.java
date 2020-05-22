package com.paperdriver.personalauthserver.repositories;

import com.paperdriver.personalauthserver.models.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OAuthClientDetailsRepository extends JpaRepository<OauthClientDetails, Long> {

//    Void updateClientSecretString(@Param("id") Long id, @Param("secret_string") String secret_string);

    @Query(value = "SELECT u FROM OauthClientDetails u WHERE u.clientId = :client_id")
    OauthClientDetails getByClientId(@Param("client_id") String client_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OauthClientDetails u SET u.clientSecretString = :client_secret_string WHERE u.clientId = :client_id")
    void setClientSecretString(@Param("client_id") String client_id, @Param("client_secret_string") String client_secret_string);
}
