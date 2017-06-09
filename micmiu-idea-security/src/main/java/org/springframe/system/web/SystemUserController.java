package org.springframe.system.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframe.common.util.Page;
import org.springframe.system.domain.SystemUser;
import org.springframe.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: HeYixuan
 * @create: 2017-05-16 10:11
 */
@Controller
@RequestMapping("/systemUser")
public class SystemUserController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage(){
        return "/systemUser/userList";
    }

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public JSON getList(HttpServletRequest request, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int length){
        try{
            Page<SystemUser> pageList = systemUserService.getList(null,start,length);
            JSON json = (JSON) JSONObject.toJSON(pageList);
            return json;
        } catch (Exception e){
            logger.error("查询所有用户列表失败{}", e.getMessage());
            return null;
        }

    }
}
