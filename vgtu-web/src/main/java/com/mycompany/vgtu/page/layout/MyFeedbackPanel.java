package com.mycompany.vgtu.page.layout;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;


public class MyFeedbackPanel extends FeedbackPanel {
    private static final long serialVersionUID = 1L;

    public MyFeedbackPanel(String id) {
        super(id);
    }

    public MyFeedbackPanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }

    @Override
    protected String getCSSClass(FeedbackMessage message) {
        String css;
        switch (message.getLevel()){
            case FeedbackMessage.SUCCESS:
                css = "alert alert-success";
                break;
            case FeedbackMessage.INFO:
                css = "alert alert-info";
                break;
            case FeedbackMessage.ERROR:
                css = "alert alert-danger";
                break;
            default:
                css = "alert alert-warning";
        }

        return css;
    }
}
