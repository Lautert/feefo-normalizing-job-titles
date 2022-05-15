package com.feefo.normalizingjob.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseBody<T>
{

    @ApiModelProperty(
        value = "If the operation was successful, the data will be informed by this attribute"
    )
    private T data;

    @ApiModelProperty(
        value = "If the operation was not successful, the reason will be informed by this attribute"
    )
    private String message;

}
