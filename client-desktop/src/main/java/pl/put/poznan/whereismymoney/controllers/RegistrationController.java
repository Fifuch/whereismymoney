package pl.put.poznan.whereismymoney.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.put.poznan.whereismymoney.gui.ViewManager;
import pl.put.poznan.whereismymoney.gui.ViewName;
import pl.put.poznan.whereismymoney.gui.utils.DialogFactory;
import pl.put.poznan.whereismymoney.gui.utils.builder.WarningBuilder;
import pl.put.poznan.whereismymoney.service.RegistrationService;

import javax.inject.Inject;

public class RegistrationController implements Controller {
    private ViewManager viewManager;
    private RegistrationService registrationService;

    @FXML
    private TextField login, eMail;

    @FXML
    private PasswordField password, retypedPassword;

    @Inject
    public RegistrationController(RegistrationService registrationService) {
        this.viewManager = viewManager;
        this.registrationService = registrationService;
    }

    @FXML
    private void performRegistration() {
        String loginText = login.getText();
        String email = eMail.getText();
        String passwordText = password.getText();
        String passwordRetypedText = retypedPassword.getText();
        boolean registrationSuccessful = registrationService.performRegistration(loginText, email, passwordText, passwordRetypedText);
        if(registrationSuccessful) {
            viewManager.switchView(ViewName.VIEW_SIGN_IN);
        } else {
            DialogFactory.get(new WarningBuilder("Registration failure",
                    "Some fields are empty or login/email is already in use.")).showAndWait();
        }
    }

    @FXML
    private void cancelRegistration() {
        viewManager.switchView(ViewName.VIEW_SIGN_IN);
    }

    @Override
    public void clear() {
        login.clear();
        eMail.clear();
        password.clear();
        retypedPassword.clear();
    }

    @Override
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

}