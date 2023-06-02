package com.x;

import lombok.Data;

/**
 * user实体
 *
 * @author xgh 2023/5/29
 */
@Data
public class User {
    /**
     * 用户名
     */
    String userName;
    /**
     * 年龄
     */
    int age;

    /**
     * 获取信息
     *
     * @return
     */
    public String getInfo() {
        return "User {" + userName + "}";
    }

    /**
     * 私有方法
     *
     * @return
     */
    private String getPrivate() {
        return "User {" + userName + "}";
    }

    /**
     * 获取信息
     *
     * @return
     */
    public String getInfo(String newUserName) {
        return "User {" + newUserName + "}";
    }


}
