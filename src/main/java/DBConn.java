import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {

    public static void test() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        System.out.println(DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            String sql = "SELECT * FROM `game`";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            String userId = null;
            String password = null;
            String name = null;
            int i = 1;
            while (rs.next()) {
//                System.out.println(i++);
                userId = rs.getString("userid");
                password = rs.getString("userpw");
                name = rs.getString("name");
                System.out.println(userId + " " + password + " " + name);
//                System.out.println(password);
//                System.out.println(name);
            }



        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static UserDto login(String userId, String userPw) {
        String name = null;   // 메소드 외부로 이름 전달용
        UserDto udto = null;   // 외부 전달용
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        System.out.println(DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            String sql = "SELECT * FROM `game` WHERE userid = ? AND userpw = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);

            rs = pstmt.executeQuery();

            if (rs.next()) {   // 만약에 rs.next()에 값이 있으면
                name = rs.getString("name");
                int gusl = rs.getInt("gusl");
                int id = rs.getInt("id");
                udto = new UserDto();
                udto.setId(id);
                udto.setName(name);
                udto.setGusl(gusl);
//                System.out.println(userId + " " + password + " " + name);
//                System.out.println(password);
//                System.out.println(name);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return udto;
    }

    public static void save(String userId, String userPw, String name) {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        System.out.println(DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            String sql = "INSERT INTO `game` (`userid`, `userpw`, `name`) VALUES (?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            pstmt.setString(3, name);

            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("데이터 넣기 실패");
            } else {
                System.out.println("데이터 넣기 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static int update(int id, int gusl) {
        int result = 0;
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        System.out.println(DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            String sql = "UPDATE `game` SET `gusl`=?, update_at = NOW() WHERE  `id`= ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gusl);
            pstmt.setInt(2, id);

            result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("수정 실패");
            } else {
                System.out.println("수정 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}