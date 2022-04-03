package com.jopov.schoolportal.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("school-project/teacher")
public class DirectorView extends Div {
    HorizontalMainHeader horizontalMainHeader = new HorizontalMainHeader();

    public DirectorView() {
        add(horizontalMainHeader.headerLayout());
        add(HorizontalMainTabPanel.tabPanelLayout());
    }
}
