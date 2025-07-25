package br.com.projeto.portalestagios.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.projeto.portalestagios.dto.EstudanteRequestDTO;
import br.com.projeto.portalestagios.dto.EstudanteResponseDTO;
import br.com.projeto.portalestagios.entity.Estudante;

@Mapper(componentModel = "spring", uses = AreaInteresseMapper.class)
public interface EstudanteMapper {

    @Mapping(target = "areasInteresse", ignore = true)
	Estudante toEntity(EstudanteRequestDTO dto);

	EstudanteResponseDTO toDto(Estudante estudante);

}
