package org.springframe.system.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframe.common.util.Page;
import org.springframe.core.BaseTest;
import org.springframe.system.domain.SystemRole;
import org.springframe.system.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Created by HeYixuan on 2017/4/20.
 */
public class SystemRoleTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SystemRoleDao systemRoleDao;

    @Autowired
    private SystemRoleService systemRoleService;


    @Test
    public void getList(){
        Collection<SystemRole> roles = systemRoleDao.getList();
        roles.forEach(role->{
            System.err.println(role.toString());
        });
    }

   @Test
   public void getRoles(){
       List<SystemRole> roles = systemRoleService.getRoles("HeYixuan");
       try{
           roles.forEach(role -> {
               System.err.println("角色:" + role.getName());
           });
       }catch (Exception e){
           logger.error("数据查询失败...", e.getMessage());

       }

   }

    @Test
    public void getList2(){
        String sql ="SELECT * FROM SYSTEM_ROLE";
        String name = "ROLE_ADMIN";
        if (name!=null){
            sql += " WHERE name = ?";
        }
        Page<SystemRole> pageList = systemRoleDao.getList(sql,new Object[]{},1,10);
        System.err.println("结果:"+pageList.getData());
    }

    @Test
    public void getList3(){
        List<SystemRole> roles = systemRoleDao.getList("SELECT * FROM SYSTEM_ROLE WHERE ID = ?", new Object[]{6});
        System.err.println(roles);
        //TODO:空就不会进入,使用的是迭代
        roles.forEach( role -> {
            System.err.println("角色集合:"+role.getName());
        });

    }
}
