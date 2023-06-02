package com.x.compiler;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xgh 2023/5/29
 */
public class CommentProcessor extends AbstractProcessor {
    final Class<?> clazz;

    public CommentProcessor(Class<?> clazz) {
        this.clazz = clazz;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        String clazzName = clazz.getName();
        Elements elementUtils = processingEnv.getElementUtils();
        TypeElement typeElement = elementUtils.getTypeElement(clazz.getCanonicalName());
        CommentContentHolder.putComment(clazz, elementUtils.getDocComment(typeElement));
        typeElement.getEnclosedElements().forEach(
                el -> {
                    if (el instanceof ExecutableElement) {
                        ExecutableElement method = (ExecutableElement) el;
                        String args = method.getParameters().stream()
                                .map(variableElement -> variableElement.asType().toString())
                                .collect(Collectors.joining(",", "(", ")"));
                        String methodName = method.getSimpleName().toString();
                        String key = clazzName + CommentContentHolder.METHOD_SEPARATOR + methodName + args;
                        CommentContentHolder.putComment(key, elementUtils.getDocComment(method));
                    } else if (el instanceof VariableElement) {
                        VariableElement variant = (VariableElement) el;
                        String key = clazzName + CommentContentHolder.FIELD_SEPARATOR + variant.getSimpleName().toString();
                        CommentContentHolder.putComment(key, elementUtils.getDocComment(variant));
                    }
                }
        );
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        return true;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}
