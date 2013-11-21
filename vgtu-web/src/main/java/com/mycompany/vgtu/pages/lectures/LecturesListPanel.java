package com.mycompany.vgtu.pages.lectures;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.domain.lecture.CategoryService;
import com.mycompany.vgtu.domain.lecture.LectureJpa;
import com.mycompany.vgtu.domain.lecture.LectureService;
import com.mycompany.vgtu.domain.lecture.VoteJpa;
import com.mycompany.vgtu.domain.lecture.VoteService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class LecturesListPanel extends Panel {
    
    private static final long serialVersionUID = 1L;
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
        
        return new AjaxButton(wicketId) {
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
                LectureJpa lecture = item.getModelObject();
                item.add(getVideoTitle("lectureTitle", lecture.getName()));
                item.add(getVideoDescription("lectureDescription", lecture.getDescription()));
                item.add(getVideoFrame("lectureVideoFrame", lecture.getUrl()));
                item.add(getVideoRating("lectureRating", lecture.getId()));
                item.add(getVideoVoteForm("ratingForm", lecture));
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
        LoadableDetachableModel<Double> model = new LoadableDetachableModel<Double>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected Double load() {
                return voteService.getAverageVoteForLecture(lectureId);
            }
        };
        return new Label(wicketId, model);
    }
    
    private Component getVideoVoteForm(String formId, final LectureJpa lecture) {
        final UserJpa currentUser = userService.loadCurrentUser();
        Form form = new Form(formId) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isVisible() {
                if (currentUser != null) {
                    if (!voteService.hasUserAlreadyVotedForLecture(currentUser.getId(), lecture.getId())) {
                        return true;
                    }
                }
                return false;
            }
        };
        Integer[] ratingValues = {1, 2, 3, 4, 5};
        final RadioChoice<Integer> rating = new RadioChoice<Integer>("ratingRadio", new Model(), Arrays.asList(ratingValues));
        AjaxButton button = new AjaxButton("ratingSubmit") {
            private static final long serialVersionUID = 1L;
            
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                VoteJpa vote = new VoteJpa((Integer) rating.getDefaultModelObject(), currentUser, lecture);
                voteService.saveNewVote(vote);
                target.add(form);
            }
        };
        form.add(rating);
        form.add(button);
        return form;
    }
}
