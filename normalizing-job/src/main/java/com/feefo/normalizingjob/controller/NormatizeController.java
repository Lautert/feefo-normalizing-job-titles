package com.feefo.normalizingjob.controller;

import java.util.List;

import com.feefo.normalizingjob.dto.ResponseBody;
import com.feefo.normalizingjob.exception.MessageUserException;
import com.feefo.normalizingjob.service.NormalizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
@Api(
    value = "Feefo Normalizing Job API",
    description = "This API just return the best normalized data for the given input",
    tags =
    {
        "Normalize"
    }
)
public class NormatizeController
{
    @Autowired
    private NormalizeService normalizeService;

    @ApiOperation(
        value = "",
        httpMethod = "GET",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value =
    {
        @ApiResponse(
            code = 200,
            response = List.class,
            message = "Return the best normalized data for the given input"
        )
    })
    @GetMapping(
        value = "/normalize-job/{search}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getListChannelIdByUserName (
        @PathVariable("search")
        @ApiParam(
            value = "search",
            required = true,
            example = "engin"
        ) String search
    )
        throws MessageUserException {
        try
        {
            if (search.trim().length() == 0)
            {
                throw new MessageUserException(
                    "The search parameter is required",
                    400
                );
            }

            String response = normalizeService.normalize(search);

            ResponseBody<String> responseBody = new ResponseBody<>();
            responseBody.setData(response);

            return new ResponseEntity<Object>(responseBody, HttpStatus.OK);
        }
        catch (MessageUserException e)
        {
            ResponseBody<String> response = new ResponseBody<String>();
            response.setMessage(e.getReason());

            return new ResponseEntity<Object>(
                response,
                HttpStatus.BAD_REQUEST
            );
        }
        catch (Exception e)
        {
            ResponseBody<String> response = new ResponseBody<String>();
            response
                .setMessage("Internal Server Error, please try again later");

            return new ResponseEntity<Object>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
