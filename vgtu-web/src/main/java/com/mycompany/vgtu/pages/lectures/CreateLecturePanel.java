package com.mycompany.vgtu.pages.lectures;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryService;
import com.mycompany.vgtu.domain.lecture.VideoLectureJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureService;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class CreateLecturePanel extends Panel {
    private static final long serialVersionUID = 1L;

    private final VideoLectureJpa videoLectureJpa;
    private final IModel<VideoLectureCategoryJpa> dropDownSelection;
    @Inject
    private VideoLectureCategoryService videoLectureCategoryService;
    @Inject
    private VideoLectureService videoLectureService;

    public CreateLecturePanel(String id) {
        super(id);
        this.videoLectureJpa = new VideoLectureJpa();
        this.dropDownSelection = new Model<VideoLectureCategoryJpa>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(VideoLectureCategoryJpa object) {
                super.setObject(object);
                videoLectureJpa.setCategory(object);
            }

        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("form");
        form.add(getNameField("name"));
        form.add(getDescriptionField("description"));
        form.add(getUrlField("url"));
        form.add(getLectureCategoryDropDown("category"));
        form.add(getSubmitButton("submitButton"));
        add(form);
    }

    private Component getNameField(String wicketId) {
        return new TextField<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                videoLectureJpa.setName(object);
            }

        });
    }

    private Component getDescriptionField(String wicketId) {
        return new TextArea<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                videoLectureJpa.setDescription(object);
            }

        });
    }

    private Component getUrlField(String wicketId) {
        return new TextField<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                videoLectureJpa.setUrl(object);
            }

        });
    }

    private Component getLectureCategoryDropDown(String wicketId) {
        return new DropDownChoice<VideoLectureCategoryJpa>(wicketId,
                dropDownSelection,
                new LoadableDetachableModel<List<VideoLectureCategoryJpa>>() {
            private static final long serialVersionUID = 1L;
                    @Override
                    protected List<VideoLectureCategoryJpa> load() {
                        return videoLectureCategoryService.loaddAllVideoLectureCategories();
                    }
                }
        );
    }

    private Component getSubmitButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                super.onSubmit();
                videoLectureService.saveNewVideoLecture(videoLectureJpa);
            }

        };
    }

}
