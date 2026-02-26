package br.edu.ifpb.sr.dac.demo.dto;

import br.edu.ifpb.sr.dac.demo.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    GetUsuariosRespDTO toDto(Usuario usuario);

    Usuario toEntity(GetUsuariosRespDTO userDTO);

    PostUsuarioDTO toPostUsuarioDto(Usuario usuario);

    Usuario toUsuarioEntity(PostUsuarioDTO dto);

}