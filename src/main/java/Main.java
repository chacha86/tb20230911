import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Article> articles = new ArrayList<>();

    public static void main(String[] args) {

        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", getCurrentDate());
        Article a2 = new Article(2, "자바 질문좀 할게요~.", "냉무", getCurrentDate());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", getCurrentDate());

        articles.add(a1);
        articles.add(a2);
        articles.add(a3);

        Scanner scan = new Scanner(System.in);
        int lastArticleId = 4;

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

                Article article = new Article(lastArticleId, title, content, getCurrentDate());
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

                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물입니다.");
                } else {
                    System.out.print("제목 : ");
                    String newTitle = scan.nextLine();
                    System.out.print("내용 : ");
                    String newContent = scan.nextLine();

                    article.setTitle(newTitle);
                    article.setContent(newContent);

                    System.out.println("수정이 완료되었습니다.");
                }


            } else if (command.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int targetId = scan.nextInt();

                scan.nextLine();

                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물입니다.");
                } else {
                    //articles.remove(i); // 위치 기반으로 삭제
                    articles.remove(article);// 값 기반 삭제
                }

            } else if (command.equals("detail")) {
                // 중복 -> 함수

                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int targetId = scan.nextInt();
                Article article = findById(targetId);

                scan.nextLine();

                if (article == null) {
                    System.out.println("존재하지 않는 게시물입니다.");
                } else {
                    System.out.println("===================");
                    System.out.printf("번호 : %d\n", article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.printf("내용 : %s\n", article.getContent());
                    System.out.printf("등록일 : %s\n", article.getRegDate());
                    System.out.println("===================");
                }
            }
        }
    }

    public static Article findById(int id) {

        Article target = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (id == article.getId()) {
                target = article;
            }
        }

        return target;

    }


    public static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        String formatedNow = now.format(formatter);

        return formatedNow;
    }
}

