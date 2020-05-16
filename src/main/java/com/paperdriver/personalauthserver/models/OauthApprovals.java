package com.paperdriver.personalauthserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "oauth_approvals")
public class OauthApprovals {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private Integer id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", referencedColumnName = "credentials_id" , columnDefinition = "varchar(255)")
    private Credentials credentials;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", referencedColumnName = "oauth_client_details_id", columnDefinition = "varchar(255)")
    private OauthClientDetails oauthClientDetails;

    @Getter
    @Setter
    @Column(name = "scope")
    private String scope;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "expiresAt")
    private Timestamp expiresAt;

    @Getter
    @Setter
    @Column(name = "lastModifiedAt")
    private Timestamp lastModifiedAt;



}
