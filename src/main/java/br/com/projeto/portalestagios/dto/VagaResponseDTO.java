package br.com.projeto.portalestagios.dto;

import br.com.projeto.portalestagios.entity.Modalidade;
import br.com.projeto.portalestagios.entity.SituacaoVaga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private Modalidade modalidade;
    private String cargaHoraria;
    private String requisitos;
    private Long empresaId;
    private String empresaNome;
    private Long areaId;
    private String areaNome;
    private SituacaoVaga situacao;
}
