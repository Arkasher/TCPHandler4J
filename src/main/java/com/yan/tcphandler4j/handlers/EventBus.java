package com.yan.tcphandler4j.handlers;

import com.yan.tcphandler4j.annotations.EventHandler;
import com.yan.tcphandler4j.api.events.Event;
import com.yan.tcphandler4j.api.events.Listener;
import com.yan.tcphandler4j.utils.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class EventBus {

    private List<Listener> events = new LinkedList<>();

    public void registerEvents(Listener c) {
        events.add(c);
    }

    public <T extends Event> void callEvent(T event) {
        for (Listener listener : events) {
            for (Method method : listener.getClass().getMethods()) {
                boolean isAnnotationPresent = AnnotationUtils.isEventHandler(method);
                if (isAnnotationPresent) {
                    if (method.getParameterTypes().length == 0) continue;
                    if (!method.getParameterTypes()[0].getName().equals(event.getClass().getName())) continue;
                    try {
                        method.invoke(listener, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
