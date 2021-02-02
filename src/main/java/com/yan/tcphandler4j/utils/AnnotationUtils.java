package com.yan.tcphandler4j.utils;

import com.yan.tcphandler4j.annotations.EventHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtils {

    public static boolean isEventHandler(Method method) {
        boolean isAnnotationPresent = false;
        for(Annotation annotation : method.getDeclaredAnnotations()) {
            if(annotation instanceof EventHandler) {
                isAnnotationPresent = true;
                break;
            }
        }
        return isAnnotationPresent;
    }

}
