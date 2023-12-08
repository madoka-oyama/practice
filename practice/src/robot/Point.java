package robot;


public class Point {
	// フィールド（内部データ）
	public int[] coordinates = { 0, 0 }; //最初の位置（原点）座標

	public void walkNorth() { //北に１歩進むメソッド
		move(0, 1);
		System.out.println("北に１歩進む");
	}

	public void walkSouth() { //南に１歩進むメソッド
		move(0, -1);
		System.out.println("南に１歩進む");
	}

	public void walkEast() { //東に１歩進むメソッド
		move(1, 0);
		System.out.println("東に１歩進む");
	}

	public void walkWest() { //西に１歩進むメソッド
		move(-1, 0);
		System.out.println("西に１歩進む");
	}

	private void move(int x, int y) { //歩く方向を読み込むメソッド
		coordinates[0] += x;
		coordinates[1] += y;
	}

	public void printCoordinates() { //現在の座標を表示するメソッド
		System.out.println("(" + coordinates[0] + ", " + coordinates[1] + ")");
	}
}
