package com.mycompany.vgtu.pages.lectures;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.domain.lecture.CategoryService;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import com.mycompany.vgtu.domain.lecture.LectureService;
import com.mycompany.vgtu.utils.MyWicketMessages;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class CreateLecturePanel extends Panel {

    private static final long serialVersionUID = 1L;    
    private MyWicketMessages messages = MyWicketMessages.from(this);
    private final LectureJpa videoLectureJpa;
    private final IModel<CategoryJpa> dropDownSelection;
    @Inject
    private CategoryService videoLectureCategoryService;
    @Inject
    private LectureService videoLectureService;

    public CreateLecturePanel(String id) {
        super(id);
        this.videoLectureJpa = new LectureJpa();
        this.dropDownSelection = new Model<CategoryJpa>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(CategoryJpa object) {
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
        IChoiceRenderer<CategoryJpa> renderer = new IChoiceRenderer<CategoryJpa>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object getDisplayValue(CategoryJpa object) {
                return object.getName();
            }

            @Override
            public String getIdValue(CategoryJpa object, int index) {
                return object.getId().toString();
            }
        };

        return new DropDownChoice<CategoryJpa>(wicketId,
                dropDownSelection,
                new LoadableDetachableModel<List<CategoryJpa>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<CategoryJpa> load() {
                return videoLectureCategoryService.loaddAllVideoLectureCategories();
            }
        }, renderer);
    }

    private Component getSubmitButton(String wicketId) {
        return new Button(wicketId, messages.txtModel("add")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                super.onSubmit();
                videoLectureService.saveNewVideoLecture(videoLectureJpa);
                setResponsePage(CreateLecturePage.class);
            }
        };
    }
}
