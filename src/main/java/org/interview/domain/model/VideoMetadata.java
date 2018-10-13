package org.interview.domain.model;

import java.util.List;

public class VideoMetadata {
    private final Long id;
    private final String title;
    private final String album;
    private final String artist;
    private final Genre genre;
    private final List<String> subgenres;
    private final Integer releaseYear;
    private final Integer duration;

    private VideoMetadata(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.album = builder.album;
        this.artist = builder.artist;
        this.genre = builder.genre;
        this.subgenres = builder.subgenres;
        this.releaseYear = builder.releaseYear;
        this.duration = builder.duration;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<String> getSubgenres() {
        return subgenres;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private String album;
        private String artist;
        private Genre genre;
        private List<String> subgenres;
        private Integer releaseYear;
        private Integer duration;

        private Builder(){}

        public Builder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAlbum(String album) {
            this.album = album;
            return this;
        }

        public Builder setArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder setGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder setSubgenres(List<String> subgenres) {
            this.subgenres = subgenres;
            return this;
        }

        public Builder setReleaseYear(Integer releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public VideoMetadata build() {
            return new VideoMetadata(this);
        }
    }
}
