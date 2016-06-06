package net.sokontokoro_factory.jaken;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by TATSUYA-PC4 on 2016/04/09.
 */
public class JakenException extends RuntimeException{

    @Getter
    @Setter
    private String message;

    public JakenException(){
        message = "システムエラー";
    }
    public JakenException(String message){
        this.message = message;
    }
}
