package com.mycompany.vgtu.page.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class MyDataTablePanel<T extends Serializable, S extends Object> extends Panel {

    private static final long serialVersionUID = 1L;

    public MyDataTablePanel(String wicketId, List<T> dataList, S defaultSortColumn, SortOrder defaultSortOrder) {
        super(wicketId);
        List<IColumn<T, S>> columns = new ArrayList<IColumn<T, S>>();

        columns.add(new AbstractColumn<T, S>(new Model<String>("Actions")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> model) {
                setupActions(cellItem, componentId, model);
//                cellItem.add(new ActionPanel(componentId, model));
            }
        });
        setupTableColumns(columns);
        add(new AjaxFallbackDefaultDataTable<T, S>("table", columns, new MySortableDataProvider(dataList, defaultSortColumn, defaultSortOrder), 15));
    }

    protected abstract void setupTableColumns(List<IColumn<T, S>> columns);

    protected abstract void setupActions(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> model);
//            columns.add(new PropertyColumn<T, S>(new Model<String>("ID"), "id"));
//        columns.add(new PropertyColumn<T, S>(new Model<String>("First Name"), "firstName",
//                "firstName"));
//        columns.add(new PropertyColumn<T, S>(new Model<String>("Last Name"), "lastName",
//                "lastName"));
//        columns.add(new PropertyColumn<VideoLectureJpa, String>(new Model<String>("Home Phone"), "homePhone"));
//        columns.add(new PropertyColumn<VideoLectureJpa, String>(new Model<String>("Cell Phone"), "cellPhone"));
}
