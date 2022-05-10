package uz.alex.climateappapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ApiResponse implements Serializable {
    private boolean success = true;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String message; // msg of result

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Object body; //  object of result

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private ApiError error; //  object of result

    public  static ApiResponse empty() {
        return builder()
                .build();
    }

    public  static ApiResponse none() {
        return builder()
                .success(false)
                .build();
    }
    public  static ApiResponse ok() {
        return builder().success(true)
                .build();
    }

    public static ApiResponse error(ApiError error){
        return  builder()
                .error(error)
                .message(error.getStatus().name())
                .build();
    }

    public static ApiResponse success(boolean success, Object body) {
        return builder().success(success)
                .body(body)
                .build();
    }

    public ApiResponse message(String message) {
        this.setMessage(message);
        return this;
    }

    public ApiResponse body(Object body) {
        this.setBody(body);
        return this;
    }


}

