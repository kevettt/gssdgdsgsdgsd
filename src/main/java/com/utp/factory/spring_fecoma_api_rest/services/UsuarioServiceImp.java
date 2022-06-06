package com.utp.factory.spring_fecoma_api_rest.services;

import com.utp.factory.spring_fecoma_api_rest.repositories.UsuarioRepository;
import com.utp.factory.spring_fecoma_api_rest.security.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> fineAll() {

        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> paginacion(Pageable pageable) {

        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Usuario find(Long id) {

        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);

    }

    @Override
    public Usuario save(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario edit(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}
