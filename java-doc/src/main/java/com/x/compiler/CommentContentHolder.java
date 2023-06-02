package com.x.compiler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xgh 2023/5/31
 */
public class CommentContentHolder {

    private static final Map<String, String> COMMENTS = new HashMap<>();

    public static final String FIELD_SEPARATOR = ".";

    public static final String METHOD_SEPARATOR = "#";

    public static void putComment(Field field, String comment) {
        COMMENTS.put(makeKey(field), comment);
    }

    public static void putComment(Class<?> clazz, String comment) {
        COMMENTS.put(makeKey(clazz), comment);
    }

    public static void putComment(String key, String comment) {
        COMMENTS.put(key, comment);
    }

    public static void putComment(Method method, String comment) {
        COMMENTS.put(makeKey(method), comment);
    }

    public static String getComment(Field field) {
        return COMMENTS.getOrDefault(makeKey(field), "");
    }

    public static String getComment(Class<?> clazz) {
        return COMMENTS.getOrDefault(makeKey(clazz), "");
    }

    public static String getComment(Method method) {
        return COMMENTS.getOrDefault(makeKey(method), "");
    }

    public static Map<String, String> getAll() {
        return COMMENTS;
    }


    private static String makeKey(Field field) {
        return field.getDeclaringClass().getName() + FIELD_SEPARATOR + field.getName();
    }

    private static String makeKey(Method method) {
        String args = Arrays.stream(method.getParameterTypes())
                .map(Class::getName)
                .collect(Collectors.joining(",", "(", ")"));
        return method.getDeclaringClass().getName() + METHOD_SEPARATOR + method.getName() + args;
    }

    private static String makeKey(Class<?> clazz) {
        return clazz.getName();
    }
}
