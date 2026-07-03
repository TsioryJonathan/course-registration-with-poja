package org.onlydevs.registration.endpoint.event.consumer.model;

import org.onlydevs.registration.PojaGenerated;
import org.onlydevs.registration.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
