package com.example.proj_game_market.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull(message = "Classe é obrigatória")
    @Enumerated(EnumType.STRING)
    private ClasseType type;

    @Min(value = 1, message = "Nível não pode ser abaixo de 1")
    @Max(value = 99, message = "Nível não pode ser acima de 99")
    private int nivel;

    @PositiveOrZero(message = "Deve ser positivo")
    private int moedas;
}
