package org.interview.domain.model;

import java.util.Arrays;
import java.util.Optional;

public enum Genre {
    BLUES("blues"),
    CLASSICAL("classical"),
    COUNTRY("country"),
    ELECTRONIC("electronic"),
    FOLK("fold"),
    JAZZ("jazz"),
    NEW_AGE("new age"),
    RAGGAE("raggae"),
    ROCK("rock");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Genre fromName(String name) {
        Optional<Genre> optionalGenre = Arrays.stream(Genre.values()).filter(g -> g.name.equals(name)).findAny();
        if (optionalGenre.isPresent()) {
            return optionalGenre.get();
        }
        throw new IllegalArgumentException("The passed name " + name + " doesn't correspond to any exiting genre");
    }
}
