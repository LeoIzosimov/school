package com.jopov.schoolportal.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./styles/header-layout-styles.css")
@HtmlImport("./src/header-layout-src.html")
public class HorizontalMainHeader extends Div {

    SignUpModal signUpModal = new SignUpModal();

    public HorizontalMainHeader() {
    }

    public HorizontalLayout headerLayout() {

        Dialog registrationModal = new Dialog();
        registrationModal.getElement().setAttribute("aria-label", "Регистрация нового пользователя");
        VerticalLayout registrationModalLayout = signUpModal.createDialogLayout(registrationModal);
        registrationModal.add(registrationModalLayout);

        Dialog loginModal = new Dialog();
        loginModal.getElement().setAttribute("aria-label", "Войти");
        VerticalLayout loginModalLayout = SignInModal.createDialogLayout(loginModal);
        loginModal.add(loginModalLayout);

        HorizontalLayout headerLayout = new HorizontalLayout();

        Button homeButton = new Button(new Icon(VaadinIcon.OPEN_BOOK));

        Button loginButton = new Button("Войти", e -> loginModal.open());
        Button registrationButton = new Button("Регистрация", e -> registrationModal.open());

        HorizontalLayout buttonLayout = new HorizontalLayout(loginButton, registrationButton, homeButton);
        homeButton.addClickListener(e -> UI.getCurrent().navigate(HomePage.class));
        homeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        homeButton.addClassName("homeButton");
        homeButton.getStyle().set("margin-inline-end", "auto");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //loginButton.addClickListener(e -> UI.getCurrent().navigate(LoginView.class));
        buttonLayout.getStyle().set("flex-wrap", "wrap");
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        registrationButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        setSizeFull();

        headerLayout.addClassName("header");
        headerLayout.setWidth("100%");
        headerLayout.setPadding(true);
        headerLayout.add(homeButton);
        headerLayout.add(loginModal, loginButton);
        headerLayout.add(registrationModal, registrationButton);
        return headerLayout;
    }
}
