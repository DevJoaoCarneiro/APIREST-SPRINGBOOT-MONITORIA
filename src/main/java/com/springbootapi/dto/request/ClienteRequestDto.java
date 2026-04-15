package com.springbootapi.dto.request;


public record ClienteRequestDto (
     String nome,
     String telefone,
     String email,
     String endereco
){
}
