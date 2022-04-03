package com.jopov.schoolportal.views;

import com.jopov.schoolportal.secutity.controllers.SignUpController;
import com.jopov.schoolportal.secutity.request.SignupRequest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class SignUpModal {


    @Autowired
    private SignUpController signUpController;

    private SignupRequest signupRequest;

    public SignUpModal() {
    }

    public VerticalLayout createDialogLayout(Dialog dialog) {
        H2 headline = new H2("Регистрация нового пользователя");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField username = new TextField("Логин");
        TextField password = new TextField("Пароль");
        //TextField confirmPasswordField = new TextField("Подтвердите пароль");
        VerticalLayout fieldLayout = new VerticalLayout(username,
                password/*, confirmPasswordField*/);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Назад", e -> dialog.close());
        Button registrationButton = new Button("Регистрация", e -> {
            signUpController.registerUser(
                    new SignupRequest(username.getValue(), password.getValue())
            );
        });

        registrationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                registrationButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }
}
