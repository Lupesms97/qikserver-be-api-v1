package com.v1.qikserve.domain;



import java.util.UUID;


public class IdentifierProducer {

    private final String identifier;

    private IdentifierProducer() {
        this.identifier = generateIdentifier();
    }

    public static IdentifierProducer createInstance() {
        return new IdentifierProducer();
    }

    private String generateIdentifier() {
        return UUID.randomUUID().toString();
    }

}
