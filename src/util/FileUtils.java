package util;

import java.io.*;
import java.util.*;

public class FileUtils {
    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    public static void overwriteFile(String path, List<String> lines) {
        writeFile(path, lines, false);
    }
}
