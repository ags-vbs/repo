package com.mycompany.vgtu.pages.lectures;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class LecturesListPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LecturesListPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

    }
    
    private Component getListView(String wicketId) {
        ListView listView = new ListView(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem item) {
item.add(getVi)
            }
        };
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
