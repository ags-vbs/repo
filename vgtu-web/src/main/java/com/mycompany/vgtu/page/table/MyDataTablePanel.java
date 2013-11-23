package com.mycompany.vgtu.page.table;

import com.mycompany.vgtu.utils.MyWicketMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public abstract class MyDataTablePanel<T extends Serializable, S extends Object> extends Panel {

    private static final long serialVersionUID = 1L;
    private final MyWicketMessages msg = MyWicketMessages.from(this);

    public MyDataTablePanel(String wicketId, S defaultSortColumn, SortOrder defaultSortOrder) {
        super(wicketId);
        List<IColumn<T, S>> columns = new ArrayList<IColumn<T, S>>();

        columns.add(new AbstractColumn<T, S>(msg.txtModel("actions")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> model) {
                setupActions(cellItem, componentId, model);
            }
        });
        setupTableColumns(columns);
        add(new AjaxFallbackDefaultDataTable<T, S>("table", columns, new MySortableDataProvider<T, S>(defaultSortColumn, defaultSortOrder) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<T> getSublistOfTotalItemsToView(long first, long count, SortParam<S> sortParam) {
                return MyDataTablePanel.this.getSublistOfTotalItemsToView(first, count, sortParam);
            }

            @Override
            protected long getTotalItemsCount() {
                return MyDataTablePanel.this.getTotalItemsCount();
            }
        }, 15));
    }

    protected abstract void setupTableColumns(List<IColumn<T, S>> columns);

    protected abstract void setupActions(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> model);

    protected abstract List<T> getSublistOfTotalItemsToView(long first, long count, SortParam<S> sortParam);

    protected abstract long getTotalItemsCount();
}
