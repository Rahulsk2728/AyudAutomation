package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class LoginDataProvider{

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        ExcelUtils excel = new ExcelUtils(
                "TestData/LoginData.xlsx",
                "Login");

        int rowCount = excel.getRowCount();

        Object[][] data = new Object[rowCount][2];

        for (int i = 1; i <= rowCount; i++) {

            data[i - 1][0] = excel.getCellData(i, 0);
            data[i - 1][1] = excel.getCellData(i, 1);

        }

        excel.closeWorkbook();

        return data;
    }
}