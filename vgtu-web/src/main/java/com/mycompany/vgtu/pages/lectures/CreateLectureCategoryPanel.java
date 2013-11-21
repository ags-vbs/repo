package com.mycompany.vgtu.pages.lectures;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryJpa;
import com.mycompany.vgtu.domain.lecture.VideoLectureCategoryService;
import com.mycompany.vgtu.page.table.ActionPanel;
import com.mycompany.vgtu.page.table.MyDataTablePanel;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CreateLectureCategoryPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private final VideoLectureCategoryJpa videoLectureCategoryJpa;
    @Inject
    private VideoLectureCategoryService videoLectureCategoryService;

    public CreateLectureCategoryPanel(String id) {
        super(id);
        this.videoLectureCategoryJpa = new VideoLectureCategoryJpa();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getCategoriesTable("allCategories"));
        Form form = new Form("form");
        form.add(getNameField("name"));
        form.add(getSubmitButton("submitButton"));
        add(form);
    }

    private Component getNameField(String wicketId) {
        return new TextField<String>(wicketId, new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(String object) {
                super.setObject(object);
                videoLectureCategoryJpa.setName(object);
            }
        });
    }

    private Component getSubmitButton(String wicketId) {
        return new Button(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                super.onSubmit();
                videoLectureCategoryService.saveNewVideoLectureCategory(videoLectureCategoryJpa);
            }
        };
    }

    private Component getCategoriesTable(String wicketId) {
        return new MyDataTablePanel<VideoLectureCategoryJpa, String>(
                wicketId,
                videoLectureCategoryService.loaddAllVideoLectureCategories(),
                "name",
                SortOrder.ASCENDING) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void setupTableColumns(List<IColumn<VideoLectureCategoryJpa, String>> columns) {
                columns.add(new PropertyColumn<VideoLectureCategoryJpa, String>(new Model<String>("ID"), "id"));
                columns.add(new PropertyColumn<VideoLectureCategoryJpa, String>(new Model<String>("CATEGORY"), "name", "name"));
            }

            @Override
            protected void setupActions(Item<ICellPopulator<VideoLectureCategoryJpa>> cellItem, String componentId, IModel<VideoLectureCategoryJpa> model) {
                cellItem.add(new ActionPanel<VideoLectureCategoryJpa>(componentId, model, "deleteAct") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onActionClick(VideoLectureCategoryJpa object) {
// do nothin now;
                    }
                });
            }
        };
    }
}
