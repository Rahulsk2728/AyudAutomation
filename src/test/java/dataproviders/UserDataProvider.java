package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class UserDataProvider {

    @DataProvider(name = "userData")
    public Object[][] userData() {

        String filePath =
                "src/test/resources/testdata/APIUserData.xlsx";

        ExcelUtils excel =
                new ExcelUtils(filePath, "UserData");

        int rows = excel.getRowCount();
        int columns = excel.getColumnCount();

        Object[][] data =
                new Object[rows - 1][columns];

        for (int i = 1; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                data[i - 1][j] =
                        excel.getCellData(i, j);
            }
        }

        return data;
    }
}