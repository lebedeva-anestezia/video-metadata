package org.interview.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBGENRE_TO_VIDEO_METADATA")
public class SubgenreToVideoMetadataDAO {

    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_metadata_id")
    private VideoMetadataDAO videoMetadata;

    @ManyToOne
    @JoinColumn(name = "subgenre_id")
    private SubgenreDAO subgenre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VideoMetadataDAO getVideoMetadata() {
        return videoMetadata;
    }

    public void setVideoMetadata(VideoMetadataDAO videoMetadata) {
        this.videoMetadata = videoMetadata;
    }

    public SubgenreDAO getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(SubgenreDAO subgenre) {
        this.subgenre = subgenre;
    }
}
