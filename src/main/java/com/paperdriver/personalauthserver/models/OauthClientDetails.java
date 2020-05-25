package com.paperdriver.personalauthserver.models;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;

@Entity
@Table(name="oauth_client_details")
public class OauthClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private Integer id;

    @Column(name="client_id")
    private String clientId;

    @Column(name="client_name")
    private String clientName;

    @Column(name="resource_ids")
    private String resourceIds;

    @Column(name="client_secret")
    private String clientSecret;

    @Column(name="client_secret_string")
    private String clientSecretString;

    @Column(name="scope")
    private String scope;

    @Column(name="authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name="web_server_redirect_uri")
    private String registeredRedirectUri;

    @Column(name="authorities")
    private String authorities;

    @Column(name="access_token_validity", length=11)
    private Integer accessTokenValiditySeconds;

    @Column(name="refresh_token_validity", length=11)
    private Integer refreshTokenValiditySeconds;

    @Column(name="additional_information", length=4096)
    private String additionalInformation;

    @Column(name="autoapprove", columnDefinition = "varchar(255)")
    private String autoapprove;

    @Column(name="uuid")
    private String uuid;

    @Column
    private Date created;

    @Column(columnDefinition ="boolean default true")
    private Boolean enabled;

    @Transient
    private String[] scopes;

    @Transient
    private String[] grantTypes;

    @Transient
    private String ownerEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecretString() { return clientSecretString; }

    public void setClientSecretString(String clientSecretString) { this.clientSecretString = clientSecretString; }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String[] getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String[] grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public BaseClientDetails castToBaseClientDetails(OauthClientDetails details) {
        BaseClientDetails baseDetails = new BaseClientDetails();
        baseDetails.setAccessTokenValiditySeconds(details.getAccessTokenValiditySeconds());
        baseDetails.setRefreshTokenValiditySeconds(details.getRefreshTokenValiditySeconds());
        baseDetails.setAuthorities(new HashSet(Arrays.asList(splitString(details.getAuthorities()))));
        baseDetails.setAuthorizedGrantTypes(new HashSet(Arrays.asList(splitString(details.getAuthorizedGrantTypes()))));
        baseDetails.setClientId(details.getClientId());
        if(details.getClientSecretString() != null){
            baseDetails.setClientSecret(details.getClientSecretString());
        }
        baseDetails.setRegisteredRedirectUri(new HashSet(Arrays.asList(splitString(details.getRegisteredRedirectUri()))));
        baseDetails.setResourceIds(new HashSet(Arrays.asList(splitString(details.getResourceIds()))));
        baseDetails.setScope(new HashSet(Arrays.asList(splitString(details.getScope()))));
        if (details.getAutoapprove() != null) {
            baseDetails.setAutoApproveScopes(new HashSet(Arrays.asList(splitString(details.getAutoapprove()))));
        }

        return baseDetails;
    }

    public String[] splitString(String str) {
        if (str == null){
            return null;
        }
        return str.split(",");
    }
}
