package com.x;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author xgh 2023/6/15
 */

public class FunctionTest {
    @Test
    public void test() {
        // not found
        System.out.println(PropertyUtils.findPropertyName(user -> ((User) user).getUserName()));
        SFunction<User, String> function = new SFunction<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUserName();
            }
        };
        // throws exception
        System.out.println(PropertyUtils.findPropertyName(function));
        System.out.println(PropertyUtils.findPropertyName(User::getAge));
        System.out.println(PropertyUtils.findPropertyName(User::getAge));
        System.out.println(PropertyUtils.findPropertyName(User::isSingle));
    }

    @Data
    public static class User {
        private String userName;
        private int age;
        private boolean single;
    }
}
