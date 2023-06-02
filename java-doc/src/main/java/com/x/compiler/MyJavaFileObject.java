package com.x.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author xgh 2023/5/29
 */
public class MyJavaFileObject extends SimpleJavaFileObject {
    private final String filePath;

    public MyJavaFileObject(String filePath) {
        super(new File(filePath).toURI(), Kind.SOURCE);
        this.filePath = filePath;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return Files.lines(Paths.get(filePath)).collect(Collectors.joining("\n"));

    }
}
