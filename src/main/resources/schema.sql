CREATE TABLE VIDEO_METADATA (
    id number not null,
    title varchar2 not null,
    album varchar2,
    artist varchar2 not null,
    duration number not null,
    genre number not null,
    release_year number
);

CREATE TABLE GENRE (
    id number not null,
    name varchar2 not null
);

CREATE TABLE SUBGENRE (
    id number not null,
    name varchar2 not null
);

CREATE TABLE SUBGENRE_TO_VIDEO_METADATA (
    id number not null,
    video_metadata_id number not null,
    subgenre_id number not null
);