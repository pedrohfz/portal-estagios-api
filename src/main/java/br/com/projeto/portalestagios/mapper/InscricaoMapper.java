package br.com.projeto.portalestagios.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.projeto.portalestagios.dto.InscricaoResponseDTO;
import br.com.projeto.portalestagios.entity.Inscricao;

@Mapper(componentModel = "spring")
public interface InscricaoMapper {

    @Mapping(source = "vaga.titulo", target = "tituloVaga")
    @Mapping(source = "empresa.nome", target = "nomeEmpresa")
    @Mapping(source = "estudante.nome", target = "nomeEstudante")
	InscricaoResponseDTO toDto(Inscricao inscricao);

}
