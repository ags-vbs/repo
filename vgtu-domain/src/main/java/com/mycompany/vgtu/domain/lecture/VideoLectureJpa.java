package com.mycompany.vgtu.domain.lecture;

import com.mycompany.vgtu.domain.BasicEntity;
import com.mycompany.vgtu.domain.user.UserJpa;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lectures")
public class VideoLectureJpa extends BasicEntity {

    private static final long serialVersionUID = 1L;
    private String url;
    private String description;
    private String name;
    @OneToOne
    private VideoLectureCategoryJpa category;
    @OneToOne
    private UserJpa uploader;

    public VideoLectureJpa() {
    }

    public VideoLectureJpa(String url, String description, String name, UserJpa uploader) {
        this.url = url;
        this.description = description;
        this.name = name;
        this.uploader = uploader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserJpa getUploader() {
        return uploader;
    }

    public void setUploader(UserJpa uploader) {
        this.uploader = uploader;
    }

    public VideoLectureCategoryJpa getCategory() {
        return category;
    }

    public void setCategory(VideoLectureCategoryJpa category) {
        this.category = category;
    }
}
