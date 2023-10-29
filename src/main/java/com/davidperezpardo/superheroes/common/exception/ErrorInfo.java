/**
 * 
 */
package com.davidperezpardo.superheroes.common.exception;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to manage the custom errors for the ServiceException.
 * @author David
 *
 */
@Getter
@Setter
public class ErrorInfo {
   
   @JsonProperty("status_code")
   private int statusCode;
   
   private String message;

   public ErrorInfo( ServiceException ex) {
       this.message = ex.getMessage();
       this.statusCode = ex.getCode();
   }
}
