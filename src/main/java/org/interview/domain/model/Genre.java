package org.interview.domain.model;

import java.util.Arrays;

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
        return Arrays.stream(Genre.values()).filter(g -> g.name.equals(name)).findAny().get();
    }
}
