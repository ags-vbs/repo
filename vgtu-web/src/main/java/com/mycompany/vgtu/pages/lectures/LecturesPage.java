package com.mycompany.vgtu.pages.lectures;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

@RequiresAuthentication
public class LecturesPage extends VgtuLayoutPage {
    private static final long serialVersionUID = 1L;

    public LecturesPage() {
        getContentContainer().setComponent(new LecturesListPanel(getContentContainer().getId()));
    }
}
