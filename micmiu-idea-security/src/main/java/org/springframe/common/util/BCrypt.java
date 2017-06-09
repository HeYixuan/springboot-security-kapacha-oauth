package org.springframe.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by HeYixuan on 2017/4/18.
 * 根据BCrypt算法对明文进行加密
 */

public class BCrypt {

    public static void main(String [] args){
        PasswordEncoder pe = new BCryptPasswordEncoder();
        //验证明文和算法后的串是否匹配
        System.out.println(pe.matches("123456", "$2a$10$vrRXrBN7.5fAkaHXN7HFY..4GuYh2zAnPrp/.f0qVHmhl6v6GGa5C"));
        //获取BCrypt算法加密后的串
        System.out.println(pe.encode("123456"));
    }
}
