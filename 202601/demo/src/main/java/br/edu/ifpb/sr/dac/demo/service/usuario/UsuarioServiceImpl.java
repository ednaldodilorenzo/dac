package br.edu.ifpb.sr.dac.demo.service.usuario;

import br.edu.ifpb.sr.dac.demo.dao.UsuarioDao;
import br.edu.ifpb.sr.dac.demo.dto.GetUsuariosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;
import br.edu.ifpb.sr.dac.demo.dto.UsuarioMapper;
import br.edu.ifpb.sr.dac.demo.model.PapelUsuario;
import br.edu.ifpb.sr.dac.demo.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioDao usuarioDao, UsuarioMapper usuarioMapper) {
        this.usuarioDao = usuarioDao;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public void saveAdm(PostUsuarioDTO dto) {
        Usuario autor = this.usuarioDao.findById(dto.idUsuario()).orElseThrow(() -> new RuntimeException("usuário não encontrado"));
        if (autor.getPapel() != PapelUsuario.ADMIN) {
            throw new RuntimeException("Apenas adminstradores podem inserir administradores");
        }
        Usuario usuario = this.usuarioMapper.toUsuarioEntity(dto);
        usuario.setPapel(PapelUsuario.ADMIN);
        this.usuarioDao.save(usuario);
    }

    @Override
    public List<GetUsuariosRespDTO> listAllAdm() {
        return this.usuarioDao.findAllByPapel(PapelUsuario.ADMIN).stream().map(usuario -> new GetUsuariosRespDTO(usuario.getId(), usuario.getNome(), usuario.getUsername())).toList();
    }
}
