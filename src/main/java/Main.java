import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 이름 짓는 규칙
        // 변수, 클래스 -> 명사
        // 함수 -> 동사
        // 단어 조합시 단어 의미가 바뀔 때마다 대문자 표현
        //    -> 카멜 표기법
        // 클래스 이름 지을 때는 첫글자 무조건 대문자.
        // 누가 봐도. 추측 가능한. 단어 조합으로 지으세요.
        // 파파고나 구글 번역기, 챗gpt 켜서
        // 이름 짓는 건 너무너무너무너무너무너무너무 중요함

        // 코드 정렬 (심심할 때마다 눌러주세요_
        // Alt + Ctrl + L

        Scanner scan = new Scanner(System.in);

        while (true) {
            String command = scan.nextLine();
            if(command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }
}
