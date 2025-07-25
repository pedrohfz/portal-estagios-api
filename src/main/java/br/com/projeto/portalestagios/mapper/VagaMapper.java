package br.com.projeto.portalestagios.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.projeto.portalestagios.dto.VagaRequestDTO;
import br.com.projeto.portalestagios.dto.VagaResponseDTO;
import br.com.projeto.portalestagios.entity.AreaInteresse;
import br.com.projeto.portalestagios.entity.Empresa;
import br.com.projeto.portalestagios.entity.Vaga;

@Mapper(componentModel = "spring")
public interface VagaMapper {

    @Mapping(source = "empresa.id", target = "empresaId")
    @Mapping(source = "empresa.nome", target = "empresaNome")
    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "area.nome", target = "areaNome")
    VagaResponseDTO toDto(Vaga vaga);

    @Mapping(source = "areaId", target = "area", qualifiedByName = "mapAreaById")
    Vaga toEntity(VagaRequestDTO dto);

    @Named("mapEmpresaById")
    default Empresa mapEmpresaById(Long id) {
        if (id == null) {
            return null;
        }
        Empresa empresa = new Empresa();
        empresa.setId(id);
        return empresa;
    }

    @Named("mapAreaById")
    default AreaInteresse mapAreaById(Long id) {
        if (id == null) {
            return null;
        }
        AreaInteresse area = new AreaInteresse();
        area.setId(id);
        return area;
    }
}
