package org.springframe.system.dao;

import org.junit.Test;
import org.springframe.core.BaseTest;
import org.springframe.system.domain.SystemResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by HeYixuan on 2017/4/22.
 */
public class SystemResoucesTest extends BaseTest {

    @Autowired
    private SystemResourcesDao systemResourcesDao;

    /**
     * 根据角色查询所有资源
     * @return
     */
    @Test
    public void loadByRole(){
        Collection<SystemResources> resourcesList = systemResourcesDao.loadByRole(1);
        System.err.println("Collection is:" + resourcesList.size());
        resourcesList.forEach(resources->{
            System.err.println(resources.toString());
        });
    }


    /**
     * 根据父节点查询所有的子节点
     * @return
     */
    @Test
    public void getListByParentId(){
        Collection<SystemResources> resources = systemResourcesDao.getListByParentId(new Object[]{1});
        resources.forEach(r->{
            System.err.println(r.toString());
        });
    }

    /**
     * 查询所有资源
     * @return
     */
    @Test
    public void getList(){
        Collection<SystemResources> resources = systemResourcesDao.getList();
        resources.forEach(r->{
            System.err.println(r.toString());
        });
    }

    @Test
    public void getUnBindList(){
        Collection<SystemResources> resources = systemResourcesDao.getUnBingList();
        resources.forEach(r->{
            System.err.println(r.toString());
        });
    }


    @Test
    public void TestConcurrentMap(){

        ConfigAttribute ca1 = new SecurityConfig("ROLE_ADMIN");
        ConfigAttribute ca2 = new SecurityConfig("ROLE_USER");
        Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();

        configAttributes.add(ca1);
        configAttributes.add(ca2);

        ConfigAttribute ca3 = new SecurityConfig("ROLE_ANON");
        ConfigAttribute ca4 = new SecurityConfig("ROLE_DBA");
        Collection<ConfigAttribute> configAttributes2 = new ArrayList<ConfigAttribute>();

        configAttributes2.add(ca3);
        configAttributes2.add(ca4);

        ConcurrentMap<String, Collection<ConfigAttribute>> resourceMap = new ConcurrentHashMap<>();
        resourceMap.put("/system/admin",configAttributes);
        resourceMap.put("/user/userInfo",configAttributes2);

        resourceMap.forEach( (k,v) ->{
            System.err.println("Key："+ k);
            /*System.err.print("Key："+ k +"，Value："+ v);
            v.forEach(a->{
                s
            });*/
        });


        /*resourceMap.forEach( (k,v) ->{
            System.err.print("Key："+ k +"，Value："+ v);
            v.forEach(a->{

            });
        });*/

        /*Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            System.err.println("Key:"+resURL);
        }*/
    }

}
