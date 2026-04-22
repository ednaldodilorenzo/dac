package br.edu.ifpb.sr.dac.demo.service.auth;

import br.edu.ifpb.sr.dac.demo.dao.UsuarioDao;
import br.edu.ifpb.sr.dac.demo.model.Usuario;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioDao userDao;

    public UserDetailsServiceImpl(UsuarioDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public @NullMarked UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return User.
                builder().
                username(usuario.getUsername()).
                password(usuario.getSenha()).
                roles(String.valueOf(usuario.getPapel())).
                build();
    }
}
