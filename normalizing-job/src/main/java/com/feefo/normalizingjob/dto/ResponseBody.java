package com.feefo.normalizingjob.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseBody<T>
{

    @ApiModelProperty(
        value = "Se a operação foi realizada com sucesso, os dados serão informados por este atributo"
    )
    private T data;

    @ApiModelProperty(
        value = "Caso a requisição não retornar como esperado, retornaremos o motivo por este campo"
    )
    private String message;

}
