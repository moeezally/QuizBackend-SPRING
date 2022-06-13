package org.moeez.configuration;


public class ErrorMessage {

    private String message;
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

    private String field;
    public void setField(String field){
        this.field = field;
    }
    public String getField(){
        return this.field;
    }

    public ErrorMessage(String field,String message){
        this.field = field;
        this.message = message;
    }


}
