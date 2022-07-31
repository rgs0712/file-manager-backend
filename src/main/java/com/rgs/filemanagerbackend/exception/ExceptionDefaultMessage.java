package com.rgs.filemanagerbackend.exception;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionDefaultMessage implements Serializable {
    private Integer httpStatusCode;
    private String messageError;

}
