package com.mycompany.vgtu.pages.lectures;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.domain.lecture.CategoryService;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import com.mycompany.vgtu.domain.lecture.LectureService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import com.mycompany.vgtu.utils.MyWicketMessages;
import com.mycompany.vgtu.utils.UrlUtils;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class CreateLecturePanel extends Panel {

    private static final long serialVersionUID = 1L;
    private MyWicketMessages messages = MyWicketMessages.from(this);
    private FeedbackPanel lectureFeedbackPanel;
    private final LectureJpa videoLectureJpa;
    private final IModel<CategoryJpa> dropDownSelection;
    @Inject
    private CategoryService videoLectureCategoryService;
    @Inject
    private LectureService videoLectureService;
    @Inject
    private UserService userService;
    private TextField<String> name;
    private TextArea<String> description;
    private TextField<String> url;
    private DropDownChoice<CategoryJpa> category;

    public CreateLecturePanel(String id) {
        super(id);
        this.videoLectureJpa = new LectureJpa();
        setCurrentUserAsUploader();
        this.dropDownSelection = new Model<CategoryJpa>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(CategoryJpa object) {
                super.setObject(object);
                videoLectureJpa.setCategory(object);
            }
        };
    }

    private void setCurrentUserAsUploader() {
        Optional<UserJpa> currentUser = userService.loadCurrentUser();
        if (currentUser.isPresent()) {
            videoLectureJpa.setUploader(currentUser.get());
        } else {
            throw new IllegalArgumentException("No current user. Lecture can be uploaded by registered and logged user");
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("form");
        form.add(initLoginFeedbackPanel("feedback"));
        form.add(initNameField("name"));
        form.add(intDescriptionField("description"));
        form.add(initUrlField("url"));
        form.add(initLectureCategoryDropDown("category"));
        form.add(getSubmitButton("submitButton"));
        form.add(new CreateLecturePanelValidator(name, description, url, category));
        add(form);
    }

    private FeedbackPanel initLoginFeedbackPanel(String wicketId) {
        lectureFeedbackPanel = new FeedbackPanel(wicketId);
        lectureFeedbackPanel.setOutputMarkupId(true);
        return lectureFeedbackPanel;
    }

    private Component initNameField(String wicketId) {
        name = new TextField<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                videoLectureJpa.setName(object);
            }
        });
        return name;
    }

    private Component intDescriptionField(String wicketId) {
        description = new TextArea<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                videoLectureJpa.setDescription(object);
            }
        });
        return description;
    }

    private Component initUrlField(String wicketId) {
        url = new TextField<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                String urlForIframe = UrlUtils.getYoutubeEmbedUrlFrom(object);
                videoLectureJpa.setUrl(urlForIframe);
            }
        });
        return url;
    }

    private Component initLectureCategoryDropDown(String wicketId) {
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

        category = new DropDownChoice<CategoryJpa>(wicketId,
                dropDownSelection,
                new LoadableDetachableModel<List<CategoryJpa>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<CategoryJpa> load() {
                return videoLectureCategoryService.loaddAllVideoLectureCategories();
            }
        }, renderer);
        return category;
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
