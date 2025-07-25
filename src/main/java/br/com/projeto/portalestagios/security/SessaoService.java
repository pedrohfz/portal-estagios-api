package br.com.projeto.portalestagios.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SessaoService {

    public String getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
        	User user = (User) authentication.getPrincipal();
            return user.getUsername();
        }

        throw new RuntimeException("Usuário não autenticado");
    }
}
