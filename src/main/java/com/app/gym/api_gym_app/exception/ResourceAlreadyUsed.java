package com.app.gym.api_gym_app.exception;

public class ResourceAlreadyUsed extends RuntimeException{

    public ResourceAlreadyUsed(String message) {
        super(message);
    }
}
