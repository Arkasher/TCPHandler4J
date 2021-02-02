package com.yan.tcphandler4j.handlers;

import java.util.HashMap;

public class Instance {

    private static HashMap<String, Object> instances = new HashMap<>();

    public static <T> T get(String instanceName, Class<T> type) {
        return type.cast(instances.get(instanceName));
    }

    public static <T> T put(String instanceName, Object instance) {
        if(get(instanceName, instance.getClass()) == null) {
            instances.put(instanceName, instance);
            return (T) instance.getClass().cast(instance);
        }
        return (T) instance.getClass().cast(instance);
    }

}
