package com.mycompany.vgtu.pages.navbar;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.panel.EmptyPanel;

public class TwitterBootstrapNavBarPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private TwitterBootstrapNavBarPanel(final Builder builder) {
        super(builder.id);

        BookmarkablePageLink<Void> homePageLink = new BookmarkablePageLink<Void>("homePageLink", builder.homePage);
        homePageLink.add(new Label("label", builder.applicationName));
        add(homePageLink);

        RepeatingView repeatingView = new RepeatingView("menuItems");

        for (MenuItemEnum item : builder.linksMap.keySet()) {
            boolean shouldBeActive = item.equals(builder.activeMenuItem);

            Collection<BookmarkablePageLink<?>> linksInThisMenuItem = builder.linksMap.get(item);

            if (linksInThisMenuItem.size() == 1) {
                BookmarkablePageLink<?> pageLink = Iterables.get(linksInThisMenuItem, 0);

                MenuLinkItem menuLinkItem = new MenuLinkItem(repeatingView.newChildId(), pageLink, shouldBeActive);
                repeatingView.add(menuLinkItem);
            } else {
                repeatingView.add(new MenuDropdownItem(repeatingView.newChildId(), item, linksInThisMenuItem,
                        shouldBeActive));
            }
        }
        add(repeatingView);
        RepeatingView extraSpecialPanelsRepeatingView = new RepeatingView("extraRepeatingView");
        for (Panel panel : builder.extraSpecialPanelItems) {
            extraSpecialPanelsRepeatingView.add(panel);
        }
        add(extraSpecialPanelsRepeatingView);
    }

    public static class Builder implements Serializable {

        private static final long serialVersionUID = 1L;
        private String id;
        private Class<? extends Page> homePage;
        private String applicationName;
        private MenuItemEnum activeMenuItem;
        private Multimap<MenuItemEnum, BookmarkablePageLink<?>> linksMap = LinkedHashMultimap.create();
        private List<Panel> extraSpecialPanelItems = new ArrayList<Panel>();

        public Builder(String id,
                Class<? extends Page> homePage,
                String applicationName,
                MenuItemEnum activeMenuItem) {
            this.id = id;
            this.homePage = homePage;
            this.applicationName = applicationName;
            this.activeMenuItem = activeMenuItem;
        }

        public Builder withMenuItem(MenuItemEnum menuItem, Class<? extends Page> pageToLink, boolean visible) {
            if (visible) {
                Preconditions.checkState(linksMap.containsKey(menuItem) == false, "Builder already contains " + menuItem
                        + ". Please use withMenuItemInDropdown if you need many links in one menu item");
                BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("link", pageToLink);
                link.setBody(new Model<String>(menuItem.getLabel()));
                linksMap.put(menuItem, link);
            }
            return this;
        }

        public Builder withMenuItemAsDropdown(MenuItemEnum menuItem, Class<? extends Page> pageToLink, String label, boolean visible) {
            if (visible) {
                BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("link", pageToLink);
                link.setBody(new Model<String>(label));
                linksMap.put(menuItem, link);
            }
            return this;
        }

        public Builder withExtraTextItemRight(ExtraTextItem item, boolean visible, int paddingRightPx) {
            if (visible) {
                item.add(new AttributeAppender("style", "padding-right:" + paddingRightPx + "px"));
                extraSpecialPanelItems.add(item);
            }
            return this;
        }

        public Builder withExtraLinkItemRight(ExtraLinkItem item, boolean visible, int paddingRightPx) {
            if (visible) {
                item.add(new AttributeAppender("style", "padding-right:" + paddingRightPx + "px"));
                extraSpecialPanelItems.add(item);
            }
            return this;
        }

        public Builder witExtraBootStrapImageRight(String wicketId, String bootstrapImgId, boolean visible, int paddingRightPx) {
            if (visible) {
                EmptyPanel empty = new EmptyPanel(wicketId);
                empty.add(new AttributeAppender("class", bootstrapImgId));
                empty.add(new AttributeAppender("style", "padding-right:" + paddingRightPx + "px"));
                extraSpecialPanelItems.add(empty);
            }
            return this;
        }

        public TwitterBootstrapNavBarPanel build() {
            return new TwitterBootstrapNavBarPanel(this);
        }
    }
}
