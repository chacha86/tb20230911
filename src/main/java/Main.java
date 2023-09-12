import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        ArrayList<String> titles = new ArrayList<>(); // 여기 하나 밖에 저장을 못하는게 문제
//        ArrayList<String> contents = new ArrayList<>();
        ArrayList<Article> articles = new ArrayList<>();

        while (true) {
            System.out.print("명령어 : ");
            String command = scan.nextLine();
            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (command.equals("add")) {
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.print("게시물 내용을 입력해주세요 : ");
                String content = scan.nextLine();

                Article article = new Article(title, content);
                articles.add(article);

                System.out.println("게시물이 등록되었습니다.");
            } else if (command.equals("list")) {
                System.out.println("==================");
                for (int i = 0; i < articles.size(); i++) {

                    Article article = articles.get(i);

                    System.out.printf("번호 : %d\n", i + 1);
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("==================");
                }
            } else if (command.equals("update")) {
                System.out.print("수정할 게시물 번호 : ");
                int target = scan.nextInt();

                scan.nextLine();

                if(0 <= target && target < articles.size()) {
                    System.out.print("제목 : ");
                    String newTitle = scan.nextLine();
                    System.out.print("내용 : ");
                    String newContent = scan.nextLine();

                    Article newArticle = new Article(newTitle, newContent);
                    articles.set(target - 1, newArticle);

                    System.out.println("수정이 완료되었습니다.");
                } else {
                    System.out.println("없는 게시물입니다.");
                }

            } else if (command.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int target = scan.nextInt();

                scan.nextLine();

                // index -> 0부터 시작하기 때문에 0보다 작으면 안됨
                // 내가 가지고 있는 데이터의 개수 - 1 보다 크면 안됨
                if(0 <= target && target < articles.size()) {
                    articles.remove(target - 1);
                    System.out.printf("%d번 게시물이 삭제되었습니다.\n", target);
                } else {
                    System.out.println("없는 게시물입니다.");
                }


            }
        }
    }
}

