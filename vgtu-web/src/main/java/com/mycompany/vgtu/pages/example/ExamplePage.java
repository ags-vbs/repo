package com.mycompany.vgtu.pages.example;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import static com.mycompany.vgtu.page.PageParametersBuilder.buildPageParameters;
import com.mycompany.vgtu.page.layout.MasterLayoutPage;

public class ExamplePage extends MasterLayoutPage {

    public static PageParameters parametersWith(long buildingId) {
        return buildPageParameters().withSegment(buildingId).instance();
    }

    public static ExamplePage with(long buildingId) {
        return new ExamplePage(parametersWith(buildingId));
    }

    public static ExamplePage with(long buildingId, String infoMsg) {
        ExamplePage result = with(buildingId);
        result.info(infoMsg);
        return result;
    }

    public ExamplePage(PageParameters pageParameters) {
        super(pageParameters);
//        long buildingId = pageParametersReader().nextSegment("buildingId").asLong();
//        this.buildingModel = new Model<Building>(buildingService.loadById(buildingId));
//        getContentContainer().setComponent(buildingTabbedPanel);
    }
}
