package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {


        FileManager.mkdir("aaa/bbb/ccc/ddd/eee/a.txt");
//        Person p1 = new Person(22, 182, "홍길동");
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonStr = null;
//        try {
//            jsonStr = mapper.writeValueAsString(p1);
//            System.out.println(jsonStr);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        Path path1 = Paths.get("sample.txt");
//
//        try {
//            Files.write(path1, jsonStr.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Path path2 = Paths.get("sample.txt");
//        try {
//            List<String> lines2 = Files.readAllLines(path2);
//            for (String line : lines2) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}