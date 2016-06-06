package net.sokontokoro_factory.jaken.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class JSONParseUtil {
    /**
     *
     * @param dto
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T toObject(Class<T> dto, String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return (T) mapper.readValue(json, dto);
        }catch(IOException e){
            return null;
        }
    }

    /**
     *
     * @param dto
     * @return
     */
    public static String toJSON(Object dto){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
            return json;
        }catch(JsonProcessingException e){
            return null;
        }
    }
}
