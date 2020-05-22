package com.paperdriver.personalauthserver.controllers;

import com.paperdriver.personalauthserver.configs.*;

import com.paperdriver.personalauthserver.models.OauthClientDetails;
import com.paperdriver.personalauthserver.repositories.OAuthClientDetailsRepository;
import com.paperdriver.personalauthserver.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("clients")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private JdbcClientDetailsService clientsDetailsService;

    @Autowired
    private OAuthClientDetailsRepository oAuthClientDetailsRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Collection.class,new SplitCollectionEditor(Set.class,","));
        binder.registerCustomEditor(GrantedAuthority.class,new AuthPropertyEditor());
    }

    @RequestMapping(value="/form",method= RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String showEditForm(@RequestParam(value="client",required=false)String clientId, Model model){

//        ClientDetails clientDetails;
        OauthClientDetails clientDetails;
        if(clientId !=null){
//            clientDetails=clientsDetailsService.loadClientByClientId(clientId);
            clientDetails = oAuthClientDetailsRepository.getByClientId(clientId);
        }
        else{
//            clientDetails =new BaseClientDetails();
            clientDetails = new OauthClientDetails();
        }
        logger.info("got client detail about with secret = {}", clientDetails.getClientSecretString());
        model.addAttribute("clientDetails",clientDetails);
        return "form";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String editClient(
            @ModelAttribute OauthClientDetails clientDetails,
            @RequestParam(value = "newClient", required = false) String newClient
    ) {
        logger.info("check before update client: {}", clientDetails.getClientSecretString());
        if (newClient == null) {
            clientsDetailsService.updateClientDetails(clientDetails.castToBaseClientDetails(clientDetails));
        } else {
            clientsDetailsService.addClientDetails(clientDetails.castToBaseClientDetails(clientDetails));
        }


        if (!clientDetails.getClientSecret().isEmpty()) {
            clientsDetailsService.updateClientSecret(clientDetails.getClientId(), clientDetails.getClientSecret());
            oAuthClientDetailsRepository.setClientSecretString(clientDetails.getClientId(), clientDetails.getClientSecretString());
            oAuthClientDetailsRepository.flush();
        }
        return "redirect:/";
    }

    @RequestMapping(value="{client.clientId}/delete",method = RequestMethod.GET)
    public String deleteClient(@ModelAttribute BaseClientDetails ClientDetails,@PathVariable("client.clientId") String id){
        logger.warn("Delete the selected client: {}", clientsDetailsService.loadClientByClientId(id).toString());
        clientsDetailsService.removeClientDetails(clientsDetailsService.loadClientByClientId(id).getClientId());
        return "redirect:/";
    }

}
