import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        ArrayList<String> titles = new ArrayList<>(); // 여기 하나 밖에 저장을 못하는게 문제
//        ArrayList<String> contents = new ArrayList<>();
        ArrayList<Article> articles = new ArrayList<>();
        int lastArticleId = 1;

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

                Article article = new Article(lastArticleId, title, content);
                articles.add(article);

                System.out.println("게시물이 등록되었습니다.");
                lastArticleId++;
            } else if (command.equals("list")) {
                System.out.println("==================");
                for (int i = 0; i < articles.size(); i++) {

                    Article article = articles.get(i);

                    System.out.printf("번호 : %d\n", article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("==================");
                }
            } else if (command.equals("update")) {
                System.out.print("수정할 게시물 번호 : ");
                int targetId = scan.nextInt();

                scan.nextLine();

                boolean isExist = false;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);

                    if (targetId == article.getId()) {
                        System.out.print("제목 : ");
                        String newTitle = scan.nextLine();
                        System.out.print("내용 : ");
                        String newContent = scan.nextLine();

                        Article newArticle = new Article(targetId, newTitle, newContent);
                        articles.set(i, newArticle);

                        System.out.println("수정이 완료되었습니다.");
                        isExist = true;
                    }
                }

                if(isExist == false) {
                    System.out.println("없는 게시물입니다.");
                }



            } else if (command.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int targetId = scan.nextInt();

                scan.nextLine();

                boolean isExist = false;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);

                    if (targetId == article.getId()) {
                        articles.remove(i);
                        System.out.printf("%d번 게시물이 삭제되었습니다.\n", targetId);
                        isExist = true;
                    }
                }

                if(isExist == false) {
                    System.out.println("없는 게시물입니다.");
                }
            }
        }
    }
}

