package com.v1.qikserve.domain;




import java.util.UUID;


public class IdentifierProducer {

    private final UUID identifier;

    private IdentifierProducer(UUID identifier) {
        this.identifier = identifier;
    }

    public static IdentifierProducer createInstance() {
        UUID generatedIdentifier = generateIdentifier();
        return new IdentifierProducer(generatedIdentifier);
    }

    private static UUID generateIdentifier() {
        return UUID.randomUUID();
    }

    public UUID getIdentifier() {
        return identifier;
    }
}
