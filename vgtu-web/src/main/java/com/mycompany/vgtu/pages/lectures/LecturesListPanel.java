package com.mycompany.vgtu.pages.lectures;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.domain.lecture.CategoryService;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import com.mycompany.vgtu.domain.lecture.LectureService;
import com.mycompany.vgtu.domain.lecture.VoteJpa;
import com.mycompany.vgtu.domain.lecture.VoteService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import com.mycompany.vgtu.utils.MyWicketMessages;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class LecturesListPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private MyWicketMessages messages = MyWicketMessages.from(this);
    @Inject
    private LectureService videoLectureService;
    @Inject
    private CategoryService videoLectureCategoryService;
    @Inject
    private VoteService voteService;
    @Inject
    private UserService userService;
    private WebMarkupContainer lecturesContainer;
    private IModel<List<LectureJpa>> listOfLecturesModel;
    private CategoryJpa selectedCategory;

    public LecturesListPanel(String wicketId) {
        super(wicketId);
        this.listOfLecturesModel = new LoadableDetachableModel<List<LectureJpa>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<LectureJpa> load() {
                if (selectedCategory == null) {
                    return videoLectureService.loadAllVideoLectures();
                } else {
                    return videoLectureService.loadAllVideoLecturesByCategory(selectedCategory.getId());
                }
            }
        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("form");
        form.add(getLectureCategoryDropDown("category"));
        form.add(getAjaxSubmitButtonToLoadLectures("submitSearch"));
        add(form);
        add(initListViewContainer("lecturesContainer", "lecturesRepeater"));
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
        Model<CategoryJpa> model = new Model<CategoryJpa>() {
            private static final long serialVersionUID = 1L;

            @Override
            public CategoryJpa getObject() {
                return selectedCategory;
            }

            @Override
            public void setObject(CategoryJpa object) {
                super.setObject(object);
                selectedCategory = object;
            }
        };
        return new DropDownChoice<CategoryJpa>(wicketId, model, new LoadableDetachableModel<List<CategoryJpa>>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<CategoryJpa> load() {
                return videoLectureCategoryService.loaddAllVideoLectureCategories();
            }
        }, renderer);
    }

    private Component getAjaxSubmitButtonToLoadLectures(String wicketId) {

        return new AjaxButton(wicketId, messages.txtModel("search")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                target.add(lecturesContainer);
            }
        };
    }

    private Component initListViewContainer(String wicketId, String repeaterId) {
        lecturesContainer = new WebMarkupContainer(wicketId);
        ListView<LectureJpa> listView = new ListView<LectureJpa>(repeaterId, listOfLecturesModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<LectureJpa> item) {
                item.setOutputMarkupId(true);
                LectureJpa lecture = item.getModelObject();
                item.add(getVideoTitle("lectureTitle", lecture.getName()));
                item.add(getVideoDescription("lectureDescription", lecture.getDescription()));
                item.add(getVideoFrame("lectureVideoFrame", lecture.getUrl()));
                item.add(getVideoRating("lectureRating", lecture.getId()));
                item.add(getVideoVoteForm("ratingForm", lecture, item));
                item.add(getVideoDeleteLink("deleteLink", lecture));
            }
        };
        lecturesContainer.add(listView);
        lecturesContainer.setOutputMarkupId(true);
        return lecturesContainer;
    }

    private Component getVideoTitle(String wicketId, String description) {
        return new Label(wicketId, description);
    }

    private Component getVideoDescription(String wicketId, String description) {
        return new Label(wicketId, description);
    }

    private Component getVideoFrame(final String wicketId, final String url) {
        InlineFrame frame = new InlineFrame(wicketId, WebPage.class) {
            private static final long serialVersionUID = 1L;

            @Override
            protected CharSequence getURL() {
                return url;
            }
        };
        frame.add(new AttributeModifier("frameborder", "0"));
        frame.add(new AttributeModifier("height", "315"));
        frame.add(new AttributeModifier("width", "420"));
        return frame;
    }

    private Component getVideoRating(String wicketId, final Long lectureId) {
        LoadableDetachableModel<String> model = new LoadableDetachableModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected String load() {
                Double voteValue = voteService.getAverageVoteForLecture(lectureId);
                StringBuilder builder = new StringBuilder();
                builder.append(messages.txtModel("rating").getObject());
                if (voteValue == null) {
                    builder.append(messages.txtModel("noVotes").getObject());
                } else {
                    builder.append(voteValue);
                }
                return builder.toString();
            }
        };
        return new Label(wicketId, model);
    }

    private Component getVideoVoteForm(String formId, final LectureJpa lecture, final ListItem<LectureJpa> item) {
        final Optional<UserJpa> currentUser = userService.loadCurrentUser();
        Form form = new Form(formId) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                if (currentUser.isPresent()) {
                    if (!voteService.hasUserAlreadyVotedForLecture(currentUser.get().getId(), lecture.getId())) {
                        return true;
                    }
                }
                return false;
            }
        };
        //Radio
        Integer[] ratingValues = {1, 2, 3, 4, 5};
        IModel<List<? extends Integer>> ratingValuesModel = Model.ofList(Arrays.asList(ratingValues));
        final IModel<Integer> selected = new Model<Integer>();
        RadioGroup group = new RadioGroup("radioGroup", selected);
        group.add(new ListView<Integer>("ratingLabel", ratingValuesModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Integer> it) {
                it.add(new Radio("radio", it.getModel()));
                it.add(new Label("label", it.getModelObject().toString()));
            }
        });
        AjaxButton button = new AjaxButton("ratingSubmit", messages.txtModel("vote")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                VoteJpa vote = new VoteJpa(selected.getObject(), currentUser.get(), lecture);
                voteService.saveNewVote(vote);
                target.add(item);
            }
        };
        form.add(group);
        form.add(button);
        return form;
    }

    private Component getVideoDeleteLink(String wicketId, final LectureJpa lecture) {
        AjaxLink deleteLink = new AjaxLink(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                videoLectureService.deleteById(lecture.getId());
                target.add(lecturesContainer);
            }

            @Override
            public boolean isVisible() {
                return videoLectureService.canLectureBeDeletedByCurrentUser(lecture);
            }
        };
        deleteLink.setBody(messages.txtModel("deleteLink"));
        return deleteLink;
    }
}
