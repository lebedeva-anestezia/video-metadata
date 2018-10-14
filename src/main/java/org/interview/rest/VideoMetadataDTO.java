package org.interview.rest;

import java.util.List;
import java.util.Optional;

public class VideoMetadataDTO {
    private Long id;
    private String title;
    private Optional<String> album;
    private String artist;
    private Integer duration;
    private String genre;
    private List<String> subgenres;
    private Integer releaseYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = Optional.ofNullable(album);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<String> subgenres) {
        this.subgenres = subgenres;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
