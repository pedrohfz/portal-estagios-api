package br.com.projeto.portalestagios.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.portalestagios.dto.AreaInteresseRequestDTO;
import br.com.projeto.portalestagios.dto.AreaInteresseResponseDTO;
import br.com.projeto.portalestagios.entity.AreaInteresse;

@Mapper(componentModel = "spring")
public interface AreaInteresseMapper {

	AreaInteresse toEntity(AreaInteresseRequestDTO dto);

	AreaInteresseResponseDTO toDto(AreaInteresse areaInteresse);

}
