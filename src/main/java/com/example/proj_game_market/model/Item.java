package com.example.proj_game_market.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 5, message = "O nome do item deve ter mais de 5 caracteres")
    private String nome;

    @NotNull(message = "Campo obrigatório!")
    @Enumerated(EnumType.STRING) 
    private TipoItem tipo;

    @NotNull(message = "Campo obrigatório!")
    @Enumerated(EnumType.STRING) 
    private RaridadeItem raridade;

    @Positive(message = "O preço deve ser positivo e maior que 0")
    private int preco;

}
