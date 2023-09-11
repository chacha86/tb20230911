import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<String> titles = new ArrayList<>(); // 여기 하나 밖에 저장을 못하는게 문제
        ArrayList<String> contents = new ArrayList<>();

        while (true) {
            System.out.print("명령어 : ");
            String command = scan.nextLine();
            if(command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if(command.equals("add")) {
                System.out.print("게시물 제목을 입력해주세요 : ");
                titles.add(scan.nextLine());
                System.out.print("게시물 내용을 입력해주세요 : ");
                contents.add(scan.nextLine());

                System.out.println("게시물이 등록되었습니다.");
            } else if(command.equals("list")) {
                System.out.println("==================");
                for(int i = 0; i < titles.size(); i++) {
                    System.out.printf("제목 : %s\n", titles.get(i));
                    System.out.printf("내용 : %s\n", contents.get(i));
                    System.out.println("==================");
                }
            }
        }
    }
}
