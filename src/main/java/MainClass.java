import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("데이터 베이스");
        System.out.println("오징어 게임에 오신것을 환영");
        System.out.println("아이디가 있습니까?(y/n)");
        // 만약에 y이면 아이디 입력으로

        String userId = null;
        String userPw = null;
        String name = null;
        int gusl = 0;
        UserDto udto = null;
        String yn = sc.next();
        if (yn.equalsIgnoreCase("y")) {
            System.out.print("아이디 입력: ");
            userId = sc.next();
            System.out.print("패스워드 입력: ");
            userPw = sc.next();
            udto = DBConn.login(userId, userPw);
            if (udto == null) {
                System.out.println("로그인 실패");
            } else {
                gusl = udto.getGusl();
                System.out.println(udto.getName() + "님 이 구슬 "+ gusl +"개로 게임 진행");
            }
        } else {
            System.out.print("아이디 입력: ");
            userId = sc.next();
            System.out.print("패스워드 입력: ");
            userPw = sc.next();
            System.out.print("이름 입력: ");
            name = sc.next();
            System.out.println("당신의 아이디: " + userId);
            System.out.println("당신의 패스워드: " + userPw);
            System.out.println("당신의 이름: " + name);
//        System.out.println("당신의 이름이 무엇입니까?");
//        System.out.println(name + "님이 게임에 참가하셨습니다.");
            DBConn.save(userId, userPw, name);
        }

        gusl += 5;
        System.out.println("한판 이겼습니다. 계속(n) 또는 저장하고 종료(y)");
        yn = sc.next();
        if (yn.equalsIgnoreCase("y")) {
            int result = DBConn.update(udto.getId(), gusl);
            if (result != 0) {
                System.out.println("저장 성공");
            }
            System.out.println("게임 종료");
        }
        // 아니면 신규가입
        // 아이디 입력 받고, 패스워드 입력받고, 이름 입력받고 디비 저장하고 난뒤
        // 아래 프로세스로
        // 아이디 입력이 받고 패스워드도 입력을 받고
        // 디비에 id, pw 가 있는지 확인해서 있으면
        // 게임 시작
        // 없음면 다시 입력
        // 한번 게임을 하고 게임 데이터를 저장을 할건지 물어보고
        // y/n y 이면 저장하고 게임 종료
        // 다시 게임을 실행하면 자신의 아이디에 따라 구슬 갯수 정보가 나와야 함
//        System.out.println("");


//        DBConn.test();
    }
}
