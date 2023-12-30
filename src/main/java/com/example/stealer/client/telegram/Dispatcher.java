package com.example.stealer.client.telegram;

import com.example.stealer.client.telegram.handler.UserRequestHandler;
import com.example.stealer.client.telegram.model.UserRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dispatcher {

    final List<UserRequestHandler> handlers;

    public Dispatcher(List<UserRequestHandler> handlers) {
        this.handlers = handlers
                .stream()
                .sorted(Comparator
                        .comparing(UserRequestHandler::isGlobal)
                        .reversed())
                .collect(Collectors.toList());
    }

    public boolean dispatch(UserRequest userRequest) {
        for (UserRequestHandler userRequestHandler : handlers) {
            if(userRequestHandler.isApplicable(userRequest)){
                userRequestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }
}
