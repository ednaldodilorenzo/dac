package br.edu.ifpb.sr.dac.demo.service.chamado;

import br.edu.ifpb.sr.dac.demo.dto.GetChamadosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostChamadoReqDTO;

import java.util.List;

public interface ChamadoService {
    void salvar(PostChamadoReqDTO dto);

    List<GetChamadosRespDTO> buscarTodosPorUsuario(Long idUsuario);
}
