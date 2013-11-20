package com.mycompany.vgtu.pages.lectures;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryService;
import com.mycompany.vgtu.domain.lecture.VideoLectureJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureService;
import java.util.Collections;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
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
    private VideoLectureService videoLectureService;
    @Inject
    private VideoLectureCategoryService videoLectureCategoryService;
    private IModel<VideoLectureCategoryJpa> dropDownSelection;
    private WebMarkupContainer lecturesContainer;
    private List<VideoLectureJpa> listOfLectures = Collections.EMPTY_LIST;

    public LecturesListPanel(String wicketId) {
        super(wicketId);
        this.dropDownSelection = new Model<VideoLectureCategoryJpa>();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getLectureCategoryDropDown("category"));
        add(getAjaxLinkToLoadLectures("linkToLoad"));
        add(initListViewContainer("lecturesContainer", "lecturesRepeater"));
    }

    private Component getLectureCategoryDropDown(String wicketId) {
        return new DropDownChoice<VideoLectureCategoryJpa>(wicketId,
                dropDownSelection,
                new LoadableDetachableModel<List<VideoLectureCategoryJpa>>() {
                    @Override
                    protected List<VideoLectureCategoryJpa> load() {
                        return videoLectureCategoryService.loaddAllVideoLectureCategories();
                    }
                }
        );
    }

    private Component getAjaxLinkToLoadLectures(String wicketId) {
        return new AjaxLink(wicketId) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                listOfLectures = videoLectureService.loaddAllVideoLectures();
                target.add(lecturesContainer);
            }
        };
    }

    private Component initListViewContainer(String wicketId, String repeaterId) {
        lecturesContainer = new WebMarkupContainer(wicketId);
        ListView<VideoLectureJpa> listView = new ListView<VideoLectureJpa>(repeaterId, listOfLectures) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<VideoLectureJpa> item) {
                VideoLectureJpa lecture = item.getModelObject();
                item.add(getVideoTitle("lectureTitle", lecture.getName()));
                item.add(getVideoDescription("lectureDescription", lecture.getDescription()));
                item.add(getVideoFrame("lectureVideoFrame", lecture.getUrl()));
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
}
