package com.mycompany.vgtu.page.table;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

abstract class MySortableDataProvider<T extends Serializable, S extends Object> extends SortableDataProvider<T, S> {

    private static final long serialVersionUID = 1L;

    public MySortableDataProvider(S sortColumn, SortOrder sortOrder) {
        setSort(sortColumn, sortOrder);
    }

    @Override
    public Iterator<? extends T> iterator(long first, long count) {
        return getSublistOfTotalItemsToView(first, count, getSort()).iterator();
    }

    @Override
    public long size() {
        return getTotalItemsCount();
    }

    @Override
    public IModel<T> model(T object) {
        return Model.of(object);
    }

    protected abstract List<T> getSublistOfTotalItemsToView(long first, long count, SortParam<S> sortParam);

    protected abstract long getTotalItemsCount();
}
