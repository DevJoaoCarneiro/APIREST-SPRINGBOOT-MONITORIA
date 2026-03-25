package com.springbootapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDto {
    private String nome;

    private String email;

    private String endereco;

    private String telefone;


}
