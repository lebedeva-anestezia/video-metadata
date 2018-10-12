package org.interview.dao;

import org.interview.domain.model.Genre;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "video_metadata")
public class VideoMetadataDAO {

    private Long id;
    private String title;
    private String album;
    private String artist;

    @ManyToOne
    @JoinColumn(name = "id")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "SUBGENRE_TO_VIDEO_METADATA",
            joinColumns = @JoinColumn(name = "video_metadata_id"),
            inverseJoinColumns = @JoinColumn(name = "subgenre_id")
    )
    private List<SubgenreDAO> subgenres;

    @Column(name = "release_year")
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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
