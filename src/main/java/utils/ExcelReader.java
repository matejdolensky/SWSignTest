package utils;

import api.TestEntry;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;

import java.io.InputStream;
import java.util.List;

public class ExcelReader {

    public static List<TestEntry> readTestEntries(String fileName) {
        try (InputStream is = ExcelReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) throw new RuntimeException("Excel file not found: " + fileName);
            return Poiji.fromExcel(is, PoijiExcelType.XLSX, TestEntry.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel with Poiji", e);
        }
    }
}
