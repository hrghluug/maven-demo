package com.x;

import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author xgh 2023/6/15
 */
public class PropertyUtils {
    private static final String GET_PREFIX = "get";
    private static final String BOOL_PREFIX = "is";

    /**
     * 只支持方法引用的形式
     *
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    @SneakyThrows
    public static <T, R> String findPropertyName(SFunction<T, R> function) {
        Method method = function.getClass().getDeclaredMethod("writeReplace");
        method.setAccessible(true);
        SerializedLambda lambda = (SerializedLambda) method.invoke(function);
        String implMethodName = lambda.getImplMethodName();
        if (implMethodName.startsWith(GET_PREFIX)) {
            return lowerFirstLetter(implMethodName.substring(3));
        }
        if (implMethodName.startsWith(BOOL_PREFIX)) {
            return lowerFirstLetter(implMethodName.substring(2));
        }
        throw new IllegalAccessException("找不到属性名");
    }

    private static String lowerFirstLetter(String str) {
        char c = str.charAt(0);
        if (c >= 'A' && c <= 'Z') {
            return ((char) (c + 32)) + str.substring(1);
        }
        return str;
    }
}
