package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    public static void saveToFile(String filePath, String body) {

        try {

            if (ifNotExists(filePath)) {
                mkdir(filePath);
            }

            Path path = Paths.get(filePath);
            Files.write(path, body.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ifNotExists(String filePath) {
        return !Files.exists(Paths.get(filePath));
    }

    public static String loadFromFileOrNull(String filePath) {
        StringBuilder sb = new StringBuilder();

        try {

            if (ifNotExists(filePath)) {
                return null;
            }

            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                sb.append(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }


    public static void mkdir(String filePath) {
        try {
            System.out.println(filePath);
            Files.createDirectories(Paths.get(filePath).getParent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int deleteFile(String filePath) {
        try {

            String result = loadFromFileOrNull(filePath);
            if (result == null || result.length() == 0) {
                return -1;
            }

            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    public static List<String> getFilesInRange(String path, int n, int m) {
        try {
            Stream<Path> files = Files.list(Paths.get(path));
            return files
                    .skip(n)
                    .limit(m - n + 1)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
