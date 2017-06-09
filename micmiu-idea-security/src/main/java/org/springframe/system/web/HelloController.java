package org.springframe.system.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframe.common.util.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: HeYixuan
 * @create: 2017-05-28 9:23
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity listAllUsers() {
        try {
            int i = 1+1;
            return ResponseEntity.ok();
        }
        catch (Exception e){
            logger.error("查询异常." , e.getMessage());
            return ResponseEntity.serverError();
        }
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUser(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(id);
        } catch (Exception e){
            logger.error("查询用户{}异常." , id, e.getMessage());
            return ResponseEntity.serverError();
        }
    }
}
