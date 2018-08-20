package cn.fves24.id.util.record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: fves
 * @Date: 2018-8-20 20:21
 **/
public class QueryRecord {

    public static void writeToFile(String str) {
        String filename = "record.txt";
        File file = new File(filename);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException ignore) {}
        }
        try (FileWriter writer = new FileWriter(file,true)) {
            writer.write(str + "\n");
        } catch (IOException ignore) {
        }
    }
}
