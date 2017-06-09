package org.springframe.system.web;/**
 * Created by HeYixuan on 2017/4/25.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员控制器
 *
 * @author HeYixuan
 * @create 2017-04-25 15:59
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(){
        return "admin/admin";
    }
}
