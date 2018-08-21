package cn.fves24.id.util.record;

import cn.fves24.id.entity.model.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fves
 * @Date: 2018-8-20 20:21
 **/
public class QueryRecord {
    private static String filename = "record.txt";

    public static void writeToFile(String str) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException ignore) {
            }
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(str + "\n");
        } catch (IOException ignore) {
        }
    }

    public static List<Record> readToList() {
        File file = new File(filename);
        List<Record> list = new ArrayList<>();
        if (file.exists()) {
            FileReader reader = null;
            BufferedReader bufferedReader = null;
            String line;
            try {
                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);
                while ((line = bufferedReader.readLine()) != null) {
                    String[] split = line.split("\\|");
                    Record record = new Record();
                    record.setDate(split[0]);
                    record.setName(split[1]);
                    record.setArea(split[2]);
                    record.setCode(split[3]);
                    record.setQq(split[4]);
                    list.add(record);
                }
            } catch (IOException ignore) {
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
