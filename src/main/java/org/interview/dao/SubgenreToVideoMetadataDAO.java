package org.interview.dao;

import javax.persistence.*;

@Entity
@Table(name = "SUBGENRE_TO_VIDEO_METADATA")
public class SubgenreToVideoMetadataDAO {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "video_metadata_id", referencedColumnName = "id")
    private VideoMetadataDAO videoMetadata;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "subgenre_id", referencedColumnName = "id")
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
