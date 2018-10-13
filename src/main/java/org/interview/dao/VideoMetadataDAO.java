package org.interview.dao;

import org.interview.domain.model.Genre;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video_metadata")
public class VideoMetadataDAO {

    @Id
    @PrimaryKeyJoinColumn
    private Long id;
    private String title;
    private String album;
    private String artist;

    @ManyToOne
    private GenreDAO genre;

    @ManyToMany(cascade = CascadeType.PERSIST)
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

    public GenreDAO getGenre() {
        return genre;
    }

    public void setGenre(GenreDAO genre) {
        this.genre = genre;
    }

    public List<SubgenreDAO> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<SubgenreDAO> subgenres) {
        this.subgenres = subgenres;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
