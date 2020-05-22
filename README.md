# personal-oauth2-server
The personal oauth2 authorization server using client credential grant type.

This server is only recommended to be used to manager secured microservice-to-microservice connections.

## feature/client-credential
This branch is a replication of https://github.com/Akourtiim/oauth2-spring-boot-2.0.2
with replacement of database to PostgreSQL on AWS.

The tutorial is [here](https://medium.com/@akourtim.ahmed/oauth-2-centralized-authorization-with-spring-boot-2-0-2-and-spring-security-5-and-jdbc-token-store-8dbc063bd5d4).

## feature/client-credential-2
This branch is a full integration of JPA/Hibernate for authorization server database instead of relying on SQL scripts.
However, the usage of columns (userid, clientid) from the table OAUTH_APPROVALS is unclear. Thus, directly converted the SQL schema
to JPA entity class.

(05/18/2020) - Updated view templates to support phone size window. Centralized CSS/JS management to common folder and resolved issues of not being able to load local css files.

This is the updated version of feature/client-credential.

_**CAUTION**: Test data still need to be manually injected to the database initially but will be permanently maintained._

## feature/client-credential-3
This branch is the modification of feature/client-credential-2. Client-secrets are encoded before being stored in the database.
However, encoded client-secrets are not decodable. Therefore, the new entity field was added to the table oauth_client_details to store original
client-secret texts. This way, clients can just copy plain-text client-secrets to go through authorization.

There is no change in Spring provided ClientDetails and ClientDetailsService, but there are some manual mapping from OauthClientDetails to BaseClientDetails when the server is updating information.
At the index.html, client details are retrieved and mapped to ClientDetails, but in form.html, client details are mapped to OAuthClientDetails before sending out ModelViews.
