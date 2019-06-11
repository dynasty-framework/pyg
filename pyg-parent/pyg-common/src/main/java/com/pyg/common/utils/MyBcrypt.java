package com.pyg.common.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @description: 密码加密
 * @author: HP
 * @date: 2019-06-10 21:03
 */
public class MyBcrypt {

        public static void main(String[] args) {
                String encrypt = encrypt("123456");
                System.out.println(encrypt);
                String pwd = "$2a$10$Eg4jyULMMnxxWiIEns11VO6Nm9OAX8x7XzTBVQKgI3PhJSgWWGV3S";
                boolean b = verifyPwd("123456", pwd);
                if(b) {
                        System.out.println("success");
                }
        }

        /**
         * 密码加密方法
         * @param password
         * @return
         */
        public static String encrypt(String password) {
                String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
                return hashpw;
        }

        /**
         * 密码验证
         * @param password
         * @return
         */
        public static boolean verifyPwd(String password, String hashpwd) {
                return BCrypt.checkpw(password, hashpwd);
        }
}
