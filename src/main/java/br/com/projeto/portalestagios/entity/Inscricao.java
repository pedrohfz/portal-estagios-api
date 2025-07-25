package br.com.projeto.portalestagios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscricao", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "estudante_id", "vaga_id" }) // evita inscrições duplicadas
})
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(nullable = false)
    private LocalDateTime dataInscricao;

}
