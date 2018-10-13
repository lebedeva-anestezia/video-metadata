package org.interview.domain.model;

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
}
