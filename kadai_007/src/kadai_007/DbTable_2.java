package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbTable_2 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement statement = null;

		//テーブル内に投稿データを追加
		String[][] userList = {
				{ "1003", "2023-02-08", "昨日の夜は徹夜でした・・", "13" },
				{ "1002", "2023-02-08", "お疲れ様です！", "12" },
				{ "1003", "2023-02-09", "今日も頑張ります！", "18" },
				{ "1001", "2023-02-09", "無理は禁物ですよ！", "17" },
				{ "1002", "2023-02-10", "明日から連休ですね！", "20" },
		};
		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://database-1.cqyitwc1ulzf.ap-northeast-1.rds.amazonaws.com/challenge_java",
					"admin",
					"NB7aeqoyJ4xwdDLZFEsq");

			System.out.print("データベース接続成功 : ");
			System.out.println(con);

			// SQLクエリを準備
			String sql = "INSERT INTO posts (user_id,posted_at, post_content,likes) VALUES (?, ?, ?, ?);";
			
			statement = con.prepareStatement(sql);
			for (int i = 0; i < userList.length; i++) {

				// SQLクエリの「?」部分をリストのデータに置き換え
				statement.setString(1, userList[i][0]); // ユーザーID
				statement.setString(2, userList[i][1]); // 投稿日時
				statement.setString(3, userList[i][2]); // 投稿内容
				statement.setString(4, userList[i][3]); // いいね数

				statement.executeUpdate();

			}
			System.out.println("レコード追加を実行します");
			System.out.println(userList.length + "件のレコードが追加されました");

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
