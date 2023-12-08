package robot;

import java.util.Scanner;

public class Robot {

	public static Scanner scanner = new Scanner(System.in);
	public static Point robot = new Point(); //Pointクラスをインスタンス化

	public static void main(String[] args) {
		//案内文
		System.out.println("ロボット歩行プログラムへようこそ。");
		System.out.println("以下の中からロボットに指示を出してください。");

		//選択肢表示
		System.out.println("0：現在位置を表示。");
		System.out.println("1：北へ進む。");
		System.out.println("2：東へ進む。");
		System.out.println("3：南へ進む。");
		System.out.println("4：西へ進む。");
		System.out.println("9：プログラムを終了");

		boolean isLoop = true; //直接trueを書き込みで9で抜けない限り無限ループ

		//9が入力されるまで無限ループで出力される
		while (isLoop) {
			System.out.print("コードを入力してください＞");

			switch (scanner.nextInt()) { //スキャナーで入力数値を呼び出してロボットの歩行座標を呼び出す
			case 0 -> robot.printCoordinates();
			case 1 -> robot.walkNorth();
			case 2 -> robot.walkEast();
			case 3 -> robot.walkSouth();
			case 4 -> robot.walkWest();
			case 9 -> isLoop = false;

			}

		}
	}
}
