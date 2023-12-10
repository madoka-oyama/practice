package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTable_4 {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement statement = null;
		Statement statement1 = null;

		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://database-1.cqyitwc1ulzf.ap-northeast-1.rds.amazonaws.com/challenge_java",
					"admin",
					"NB7aeqoyJ4xwdDLZFEsq");

			System.out.print("データベース接続成功 : ");
			System.out.println(con);

			// SQLクエリを準備 テーブル内に投稿読み込みクエリ
			String sql = "INSERT INTO posts (user_id,posted_at, post_content,likes) VALUES (?, ?, ?, ?);";

			// SQLクエリを準備 テーブル内の検索クエリ
			statement1 = con.createStatement();
			String sql1 = "SELECT user_id, posted_at,post_content,likes FROM posts WHERE user_id = 1002;";

			//テーブル内に投稿データを追加
			String[][] userList = {
					{ "1003", "2023-02-08", "昨日の夜は徹夜でした・・", "13" },
					{ "1002", "2023-02-08", "お疲れ様です！", "12" },
					{ "1003", "2023-02-09", "今日も頑張ります！", "18" },
					{ "1001", "2023-02-09", "無理は禁物ですよ！", "17" },
					{ "1002", "2023-02-10", "明日から連休ですね！", "20" },
			};

			statement = con.prepareStatement(sql);
			for (int i = 0; i < userList.length; i++) {

				// SQL投稿取り込みクエリの「?」部分をリストのデータに置き換え取り込み
				statement.setString(1, userList[i][0]); // ユーザーID
				statement.setString(2, userList[i][1]); // 投稿日時
				statement.setString(3, userList[i][2]); // 投稿内容
				statement.setString(4, userList[i][3]); // いいね数

				statement.executeUpdate();

			}
			System.out.println("レコード追加を実行します");
			System.out.println(userList.length + "件のレコードが追加されました");

			//　投稿内容を検索するSQLクエリを実行
			ResultSet result = statement1.executeQuery(sql1);

			// 投稿内容を検索するSQLクエリの実行結果を抽出
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
