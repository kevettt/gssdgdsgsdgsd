package com.utp.factory.spring_fecoma_api_rest.security.services;

import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import com.utp.factory.spring_fecoma_api_rest.security.repository.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService,UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null){
            logger.error("Error en el login: no existe usuario '"+username+"' en el sistema");
            throw new UsernameNotFoundException("Error en el login: no existe usuario '"+username+"' en el sistema");
        }

        List<GrantedAuthority> authorities =
                usuario
                        .getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRol().toString()))
                        .peek(authority -> logger.info("Role: "+authority.getAuthority()))
                        .collect(Collectors.toList());

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEnabled(),
                true,
                true,
                true,
                authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}