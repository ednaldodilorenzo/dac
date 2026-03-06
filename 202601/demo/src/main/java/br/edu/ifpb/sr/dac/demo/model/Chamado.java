package br.edu.ifpb.sr.dac.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PrioridadeChamado prioridade;
    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false)
    private Usuario dono;
    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Usuario atendente;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private StatusChamado status;
}
