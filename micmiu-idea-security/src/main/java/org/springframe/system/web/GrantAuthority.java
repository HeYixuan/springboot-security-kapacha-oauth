package org.springframe.system.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframe.common.util.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: HeYixuan
 * @create: 2017-05-27 15:57
 */
@RestController
public class GrantAuthority {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @GetMapping("/test")
    public ResponseEntity test(){
        try {
            int i=1;
            int sum = 1/0;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("username", "何壹轩");
            map.put("email", "15517551511@126.com");
            return ResponseEntity.ok(map);
        }catch (Exception e){
            logger.error("查询数据失败.原因:{}", e.getMessage());
            return ResponseEntity.serverError();
        }

    }
}
