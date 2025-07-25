package br.com.projeto.portalestagios.dto;

import br.com.projeto.portalestagios.entity.Modalidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VagaRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A localização é obrigatória")
    private String localizacao;

    @NotNull(message = "A modalidade é obrigatória")
    private Modalidade modalidade;

    @NotBlank(message = "A carga horária é obrigatória")
    private String cargaHoraria;

    private String requisitos;

    @NotNull(message = "A área de interesse é obrigatória")
    private Long areaId;

}
