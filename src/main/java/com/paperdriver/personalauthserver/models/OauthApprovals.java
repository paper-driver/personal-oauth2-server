package com.paperdriver.personalauthserver.models;

import javafx.util.converter.BigIntegerStringConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
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
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userid", referencedColumnName = "ID" , columnDefinition = "varchar(255)")
//    private Credentials credentials;
    @Column(name = "userid", columnDefinition = "varchar(255)")
    private String userId;

    @Getter
    @Setter
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clientid", referencedColumnName = "client_id", columnDefinition = "varchar(255)")
//    private OauthClientDetails oauthClientDetails;
    @Column(name = "clientid", columnDefinition = "varchar(255)")
    private String clientId;

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
    @Column(name = "expiresat")
    private Timestamp expiresAt;

    @Getter
    @Setter
    @Column(name = "lastmodifiedat")
    private Timestamp lastModifiedAt;



}
