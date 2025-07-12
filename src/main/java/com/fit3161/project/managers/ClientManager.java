package com.fit3161.project.managers;

import com.fit3161.project.managers.helpers.RequestObjectTransformer;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ClientManager implements RequestObjectTransformer {
    private static ClientManager reference;
    private Object request;

    public static ClientManager client(){
        return reference;
    }

    @PostConstruct
    private void init(){
        reference = this;
    }
}
