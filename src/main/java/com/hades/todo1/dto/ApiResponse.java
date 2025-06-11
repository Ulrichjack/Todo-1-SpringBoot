package com.hades.todo1.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse <T>(T data, String message, HttpStatus status) {

    // succees 200 OK
    public  static <T> ApiResponse <T> ok (T data,String message){
        return new ApiResponse<>(data,message,HttpStatus.OK) ;
    }

   //sucee sans donnee 204
    public static ApiResponse <Void> noContent (String message) {
        return new ApiResponse<>(null,null,HttpStatus.NO_CONTENT);
    }

    //creation reussi 201
    public static <T> ApiResponse <T> create (T data ,String message){
        return  new ApiResponse<>(data,message,HttpStatus.CREATED);
    }

    // Errreur 400 ,404 , 500
    public static <T> ApiResponse <T> error (String message, HttpStatus status){
        return  new ApiResponse<>(null,message,HttpStatus.CREATED);
    }

}
