package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTable_3 {

	public static void main(String[] args) {
		Connection con = null;
		Statement statement = null;

		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://database-1.cqyitwc1ulzf.ap-northeast-1.rds.amazonaws.com/challenge_java",
					"admin",
					"NB7aeqoyJ4xwdDLZFEsq");

			System.out.print("データベース接続成功 : ");
			System.out.println(con);

			// SQLクエリを準備
			statement = con.createStatement();
			String sql = "SELECT user_id, posted_at,post_content,likes FROM posts WHERE user_id = 1002;";

			//　SQLクエリを実行（DBMSに送信）
			ResultSet result = statement.executeQuery(sql);

			// SQLクエリの実行結果を抽出
			result.next();
			{
				int user_id = result.getInt("user_id");
				System.out.println("ユーザーIDが" + user_id + "のレコードを検索しました");
			}

			do {
				Date posted_at = result.getDate("posted_at");
				String post_content = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(
						result.getRow() + "件目" + "／投稿日時=" + posted_at + "／投稿内容=" + post_content + "／いいね数=" + likes);
			} while (result.next());

		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
	}
}
