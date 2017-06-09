package org.springframe.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: HeYixuan
 * @create: 2017-05-17 9:13
 */
@Controller
@RequestMapping("/systemRole")
public class SystemRoleController {

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(){
        return "/systemRole/manage";
    }
}
