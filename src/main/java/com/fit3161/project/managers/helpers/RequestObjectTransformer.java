package com.fit3161.project.managers.helpers;

public interface RequestObjectTransformer {
    Object getRequest();
    default <T> T getRequestAs(Class<T> cls) {
        return cls.cast(getRequest());
    }
}
