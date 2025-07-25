package br.com.projeto.portalestagios.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.projeto.portalestagios.dto.EmpresaRequestDTO;
import br.com.projeto.portalestagios.dto.EmpresaResponseDTO;
import br.com.projeto.portalestagios.entity.Empresa;

@Mapper(componentModel = "spring", uses = AreaInteresseMapper.class)
public interface EmpresaMapper {

    @Mapping(target = "areasAtuacao", ignore = true)
	Empresa toEntity(EmpresaRequestDTO dto);

	EmpresaResponseDTO toDto(Empresa empresa);

}
