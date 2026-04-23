package br.edu.ifpb.sr.dac.demo.service.usuario;

import br.edu.ifpb.sr.dac.demo.dao.UsuarioDao;
import br.edu.ifpb.sr.dac.demo.dto.GetUsuariosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;
import br.edu.ifpb.sr.dac.demo.dto.UsuarioMapper;
import br.edu.ifpb.sr.dac.demo.model.PapelUsuario;
import br.edu.ifpb.sr.dac.demo.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioDao usuarioDao, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioDao = usuarioDao;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Long saveAdm(PostUsuarioDTO dto, Long idUsuario) {
        Usuario autor = this.usuarioDao.findById(idUsuario).orElseThrow(() -> new RuntimeException("usuário não encontrado"));
        if (autor.getPapel() != PapelUsuario.ADMIN) {
            throw new RuntimeException("Apenas adminstradores podem inserir administradores");
        }
        Usuario usuario = this.usuarioMapper.toUsuarioEntity(dto);
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
        usuario.setPapel(PapelUsuario.ADMIN);
        Usuario newUsuario = this.usuarioDao.save(usuario);

        return newUsuario.getId();
    }

    @Override
    @Transactional
    public void saveUsuario(PostUsuarioDTO dto) {
        if (this.usuarioDao.existsByEmail(dto.email()))
            throw new RuntimeException("Email já cadastrado");

        if (this.usuarioDao.existsByUsername(dto.username()))
            throw new RuntimeException("Username já cadastrado");

        if (this.usuarioDao.existsByCpf(dto.cpf()))
            throw new RuntimeException("CPF já cadastrado");

        Usuario usuario = this.usuarioMapper.toUsuarioEntity(dto);
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
        usuario.setPapel(PapelUsuario.USER);
        this.usuarioDao.save(usuario);
    }

    @Override
    public Page<GetUsuariosRespDTO> listAllAdm(Pageable pageable) {
        Page<Usuario> usuarioPageEntity = this.usuarioDao.findAllByPapel(PapelUsuario.ADMIN, pageable);
        return usuarioPageEntity.map(this.usuarioMapper::toDto);
    }
}
