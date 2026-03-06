package br.edu.ifpb.sr.dac.demo.dto;

import br.edu.ifpb.sr.dac.demo.model.Chamado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {
    PostChamadoReqDTO toPostChamadoReqDTO(Chamado chamado);

    GetChamadosRespDTO toGetChamadosRespDTO(Chamado chamado);

    Chamado toEntity(PostChamadoReqDTO dto);
}
