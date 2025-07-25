package br.com.projeto.portalestagios.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.portalestagios.dto.UsuarioRequestDTO;
import br.com.projeto.portalestagios.dto.UsuarioResponseDTO;
import br.com.projeto.portalestagios.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toDto(Usuario usuario);
}
