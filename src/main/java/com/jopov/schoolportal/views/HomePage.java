package com.jopov.schoolportal.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("school-project/home")
public class HomePage extends Div {

    HorizontalMainHeader horizontalMainHeader = new HorizontalMainHeader();

    public HomePage() {
        add(horizontalMainHeader.headerLayout());
        add(HorizontalMainTabPanel.tabPanelLayout());
    }
}
