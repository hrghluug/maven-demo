package com.x.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xgh 2023/5/29
 */
public class DocDemoUseJavaParser {
    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\java-doc\\src\\main\\java\\com\\x\\User.java";
        FileInputStream in = new FileInputStream(filePath);
        CompilationUnit cu = new JavaParser().parse(in).getResult().get();
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) types.get(0);
        System.out.println("===========class doc===========");
        type.getComment()
                .map(Comment::getContent)
                .map(DocDemoUseJavaParser::trim)
                .ifPresent(System.out::println);
        System.out.println("===========field doc===========");
        type.getFields()
                .stream()
                .map(FieldDeclaration::getComment)
                .map(Optional::get)
                .map(Comment::getContent)
                .map(DocDemoUseJavaParser::trim)
                .forEach(System.out::println);
        type.getMethods()
                .forEach(method -> {

                            System.out.println("===========method:" + method.getSignature().asString() + " doc===========");
                            method.getComment()
                                    .map(Comment::getContent)
                                    .map(DocDemoUseJavaParser::trim)
                                    .ifPresent(System.out::println);
                        }
                );

    }

    public static String trim(String string) {
        return Arrays.stream(
                        string.trim().split("\n")
                )
                .map(String::trim)
                .map(str -> str.startsWith("*") ? str.substring(1) : str)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("\n"));

    }
}
