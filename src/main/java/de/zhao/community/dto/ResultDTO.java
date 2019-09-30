package de.zhao.community.dto;

import de.zhao.community.exception.CustomizeErrorCode;
import de.zhao.community.exception.CustomizeException;
import lombok.Data;

import java.util.List;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO erroOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO erroOf(CustomizeErrorCode errorCode) {
        return erroOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO erroOf(CustomizeException e) {
        return erroOf(e.getCode(), e.getMessage());
    }

    public static ResultDTO successOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("Request success.");
        return resultDTO;
    }

    public static <T> ResultDTO successOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("Request success.");
        resultDTO.setData(t);
        return resultDTO;
    }

}
