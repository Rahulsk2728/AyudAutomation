package dataproviders;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider(name = "userData")
    public Object[][] userData() {

        return new Object[][]{
                {"Rahul", "SDET"},
                {"Amit", "QA Engineer"},
                {"John", "Automation Engineer"},
                {"Priya", "Senior SDET"}
        };
    }
}