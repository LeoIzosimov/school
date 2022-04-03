package com.jopov.schoolportal.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInModal extends Div {

    public SignInModal() {

    }

    public static VerticalLayout createDialogLayout(Dialog dialog) {
        H2 headline = new H2("Авторизация");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField firstNameField = new TextField("Логин");
        TextField passwordField = new TextField("Пароль");
        VerticalLayout fieldLayout = new VerticalLayout(firstNameField,
                passwordField);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Назад", e -> dialog.close());
        Button loginButton = new Button("Войти", e -> dialog.close());
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton, loginButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout, buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }
}
