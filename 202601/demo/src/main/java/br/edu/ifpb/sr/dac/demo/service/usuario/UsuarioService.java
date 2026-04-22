package br.edu.ifpb.sr.dac.demo.service.usuario;

import br.edu.ifpb.sr.dac.demo.dto.GetUsuariosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;
import br.edu.ifpb.sr.dac.demo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    void saveAdm(PostUsuarioDTO dto);

    void saveUsuario(PostUsuarioDTO dto);

    Page<GetUsuariosRespDTO> listAllAdm(Pageable pageable);
}
