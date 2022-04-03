package com.jopov.schoolportal.views;

import com.jopov.schoolportal.secutity.controllers.SignInController;
import com.jopov.schoolportal.secutity.request.LoginRequest;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginView extends Div {


    private SignInController signInController = new SignInController();

    public LoginView() {

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Школа,");
        i18n.getHeader().setDescription("блин");

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Авторизируйтесь");
        i18nForm.setUsername("Введите имя пользователя");
        i18nForm.setPassword("Введите пароль");
        i18nForm.setSubmit("Войти");
        i18nForm.setForgotPassword("Забыли пароль?");
        i18n.setForm(i18nForm);

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Некорректные логин/пароль");
        i18nErrorMessage.setMessage("Проверьте правильность введенных данных");
        i18n.setErrorMessage(i18nErrorMessage);

        i18n.setAdditionalInformation("");

        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18n);

        add(loginOverlay);
        loginOverlay.addLoginListener((e) -> {
            LoginRequest login = new LoginRequest(e.getUsername(), e.getPassword());
            signInController.authenticateUser(login);
            UI.getCurrent().navigate("school-project/home");
        });
        loginOverlay.setOpened(true);
        loginOverlay.getElement().setAttribute("no-autofocus", "");
    }
}
