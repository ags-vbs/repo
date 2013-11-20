package com.mycompany.vgtu.pages.navbar;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
class MenuLinkItem extends Panel {

    MenuLinkItem(String id, BookmarkablePageLink<?> pageLink, boolean shouldBeActive) {
        super(id);
        add(pageLink);
        if (shouldBeActive) {
            add(new AttributeAppender("class", " active "));
        }
    }
}
