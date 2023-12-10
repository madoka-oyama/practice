package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Posts_Chapter07 {

	public static void main(String[] args) {

		try {
			// データベースに接続
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://database-1.cqyitwc1ulzf.ap-northeast-1.rds.amazonaws.com/java_db",
					"admin",
					"NB7aeqoyJ4xwdDLZFEsq");

			System.out.println("データベース接続成功");
			System.out.println(con);

			// データベース接続を解除
			con.close();

		} catch (SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
		}
	}

}