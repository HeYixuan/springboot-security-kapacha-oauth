package org.springframe.system.service;

import org.junit.Test;
import org.springframe.common.util.ResponseEntity;
import org.springframe.core.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口测试类
 *
 * @author HeYixuan
 * @create 2017-04-26 21:38
 */
public class SystemUserServiceTest extends BaseTest {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 登陆失败,修改登陆次数+1
     * 登陆成功,重置登陆次数 0
     */
    @Test
    public void updateAttempts(){
        systemUserService.updateAttempts("HeYixuan");
    }


    @Test
    public void testTrue(){
        boolean bool = true;
        if (bool){
            System.err.println("结果1:" + bool);
        } else {
            System.err.println("结果2:" + bool);
        }

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("username","abc");
        data.put("age",22);
        ResponseEntity json = ResponseEntity.ok(data);
        System.err.println(json);
        //ResponseEntity<String> entity = new ResponseEntity<String>("查询错误", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
