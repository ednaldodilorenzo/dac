package br.edu.ifpb.sr.dac.demo.service.usuario;

import br.edu.ifpb.sr.dac.demo.dto.GetUsuariosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;

import java.util.List;

public interface UsuarioService {
    void saveAdm(PostUsuarioDTO dto);
    List<GetUsuariosRespDTO> listAllAdm();
}
