package com.x.compiler;


import com.x.User;

import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * @author xgh 2023/5/29
 */
public class DocDemoUseProcessor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\java-doc\\src\\main\\java\\com\\x\\User.java";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        SimpleJavaFileObject fileObject = new MyJavaFileObject(filePath);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, null, null, null, Collections.singleton(fileObject));
        CommentProcessor processor = new CommentProcessor(User.class);
        task.setProcessors(Collections.singleton(processor));
        task.call();
        Map<String, String> map = CommentContentHolder.getAll();
        map.forEach(
                (key, value) -> {
                    System.out.println("==========" + key + "==========");
                    System.out.println(value);
                }
        );


    }
}
