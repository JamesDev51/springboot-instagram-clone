package com.jamesdev.springbootinstagramclone.handler.ex;

import java.util.Map;

public class CustomApiException extends  RuntimeException{

      private Map<String,String> errorMap;

      public CustomApiException(String message){
            super(message);
      }

      public CustomApiException(String message, Map<String, String> errorMap) {
            super(message);
            this.errorMap = errorMap;
      }

      public Map<String, String> getErrorMap() {
            return errorMap;
      }
}
