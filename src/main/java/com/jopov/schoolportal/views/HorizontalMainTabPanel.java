package com.jopov.schoolportal.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

@CssImport("./styles/tab-panel-layout-styles.css")
public class HorizontalMainTabPanel extends Div {

    public HorizontalMainTabPanel() {
        getElement().setAttribute("tab-theme","tan-appLayout");
    }

    public static Tabs tabPanelLayout(){
        Tab details = new Tab("Общая информация");
        Tab rating = new Tab("Рейтинг");
        Tab learning = new Tab("Хочу учиться");
        Tab contacts = new Tab("Контакты");

        Tabs tabs = new Tabs(details, rating, learning, contacts);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        tabs.addClassName("tab-panel");
        return tabs;
    }
}

