package org.interview.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SUBGENRE_TO_VIDEO_METADATA")
public class SubgenreToVideoMetadataDAO implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "video_metadata_id", referencedColumnName = "id")
    private VideoMetadataDAO videoMetadata;

    @Id
    @ManyToOne
    @JoinColumn(name = "subgenre_id", referencedColumnName = "id")
    private SubgenreDAO subgenre;
}
