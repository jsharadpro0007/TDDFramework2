package utils.datareader;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import utils.extentreports.ConfigurationManager;

import java.util.Map;
import java.util.TreeMap;

public class ExcelTestData {
    public static String fileName = "./src/test/resources/TestData.xlsx";
    public static String sheetName = "Sheet1";

    public Map<String, String> getTestDataRow(String testId) throws Exception {
        Map<String, String> testDataMap = new TreeMap<>();
        String query = null;
        query = String.format("SELECT * from %s WHERE TestCaseId = '%s'", sheetName, testId);
        Fillo fillo = new Fillo();
        Connection conn = null;
        Recordset rs = null;

        try {
            conn = fillo.getConnection(fileName);
            rs = conn.executeQuery(query);
            while (rs.next()) {
                for (String field : rs.getFieldNames()) {
                    testDataMap.put(field, rs.getField(field));
                }
            }
        } catch (FilloException e) {
            e.printStackTrace();
            throw new Exception("Test data cannot be found ...");
        }
        conn.close();
        return testDataMap;

    }

    public String getTestData(String columnName) throws Exception{

        Map<String, String> mDataRow = null;
        mDataRow= ConfigurationManager.getTestDataMap();
        return mDataRow.get(columnName);
    }

}
