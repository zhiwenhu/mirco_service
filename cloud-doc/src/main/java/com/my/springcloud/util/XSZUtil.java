package com.my.springcloud.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.springcloud.entity.User;
import com.my.springcloud.vo.ResultVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

public class XSZUtil {

    @Resource
    private RestTemplate restTemplate;

    /**
     *  java 任何对象转成map
     *   <dependency>
     *             <groupId>com.fasterxml.jackson.datatype</groupId>
     *             <artifactId>jackson-datatype-jsr310</artifactId>
     *         </dependency>
     * @param o
     * @return
     */
    public static Map<String,Object> objectToMap(Object o){

        ObjectMapper m = new ObjectMapper();

        return m.convertValue(o,Map.class);
    }

    public static User MapToUser(Map<String,Object> map){

        ObjectMapper m = new ObjectMapper();

        return m.convertValue(map,User.class);
    }

    public ResponseEntity<ResultVo> callService(String url, Map<String, Object>  map){

        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type= MediaType.parseMediaType("application/json;charset=UTF-8");
        httpHeaders.setContentType(type);
        HttpEntity<Map<String, Object>> objectHttpEntity = new HttpEntity<>(map,httpHeaders);
        return  restTemplate.postForEntity(url, objectHttpEntity, ResultVo.class);

    }

}
