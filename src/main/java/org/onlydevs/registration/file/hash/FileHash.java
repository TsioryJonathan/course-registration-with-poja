package org.onlydevs.registration.file.hash;

import org.onlydevs.registration.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
