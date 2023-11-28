package demoqa.tests;

import demoqa.models.CredentialsModel;

public class TestData {

    private static final String login = "Batman",
            password = "Qwerty123!";

    public static CredentialsModel credentials = new CredentialsModel(login, password);
}
