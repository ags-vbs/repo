package com.mycompany.vgtu.page.table;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

class MySortableDataProvider<T extends Serializable, S extends Object> extends SortableDataProvider<T, S> {

    private static final long serialVersionUID = 1L;
    private List<T> loadedData;

    public MySortableDataProvider(List<T> loadedData, S sortColumn, SortOrder sortOrder) {
        setSort(sortColumn, sortOrder);
        this.loadedData = loadedData;
    }

    @Override
    public Iterator<? extends T> iterator(long first, long count) {
        return loadedData.iterator();
    }

    @Override
    public long size() {
        return loadedData.size();
    }

    @Override
    public IModel<T> model(T object) {
        return Model.of(object);
    }
}
