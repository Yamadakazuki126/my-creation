package BlackJack;

import java.util.ArrayList;

import javax.swing.ImageIcon;

class Drow{
	
	static ArrayList<Label>playerHandList = new ArrayList<>();
	static ArrayList<Integer>playerSuitList = new ArrayList<>();
	static ArrayList<Integer> playerNumList = new ArrayList<>();
	
	static ArrayList<Label>enemyHandList = new ArrayList<>();
	static ArrayList<Integer>enemySuitList = new ArrayList<>();
	static ArrayList<Integer> enemyNumList = new ArrayList<>();
	
	static String tempNum;
	static ImageIcon tempSuit;
	static Label tempLabel;
	static Label temp;
	static int playerDrowCnt=0;
	static int enemyDrowCnt=0;
	static int drowCnt;
	static boolean flag = true;
	
	GameWindow card;
	GameWindow canvas;
	
	
	static int playerSum;
	static int enemySum;
	static void drowCard(String string) {
		if(string.equals("player")) {
			playerDrowCnt++;
			flag = true;
			GameWindow.setPlayerCard();
			checkSum(string);
			GameWindow.labelB.setText("合計: " + playerSum);
		}else {
			enemyDrowCnt++;
			flag = false;
			GameWindow.setEnemyCard();
			checkSum(string);
			GameWindow.labelD.setText("合計: " + enemySum);
		}
	}
	
	static String checkStr;
	static void checkSum(String string) {
		if(string.equals("player")) {
			playerSum = playerNumList.stream().mapToInt(Integer::intValue).sum();
			finDrow(playerSum);
		}else {
			enemySum = enemyNumList.stream().mapToInt(Integer::intValue).sum();
			finDrow(enemySum);
		}
	}
	
	static void finDrow(int sum) {
		if(sum >21) {
			finProcess("バースト");
			
		}else if(sum == 21){
			finProcess("スタンド");
			
		}else {
			//何もしない
		}
	}
	
	static boolean Stand = true;
	static void finProcess(String string) {
		GameWindow.labelC.setText(string);
		GameWindow.labelC.setVisible(true);
		GameWindow.drowButton.setVisible(false);
		GameWindow.standButton.setVisible(false);
		
		while(enemySum < 15) {
			drowCard("enemy");
		}
		judge(playerSum,enemySum);
	}
	
	static void judge(int player, int enemy) {
		System.out.println("player: " + player + " " +  "enemy: " + enemy);
		if(player > 21 && enemy > 21 || player == enemy) {
			GameWindow.labelA.setText("引き分けです");
		}else if(player > 21 && enemy <= 21) {
			GameWindow.labelA.setText("プレイヤ-のバースト");
		}else if(player <= 21 && enemy > 21) {
			GameWindow.labelA.setText("相手のバースト");
		}else if(player > enemy) {
			GameWindow.labelA.setText("プレイヤーの勝利です");
		}else if(player < enemy) {
			GameWindow.labelA.setText("プレイヤーの敗北です");
		}
		
		if(player == 21) {
			GameWindow.labelC.setText("💎BlackJack💎");
		}
		
		//GameWindow.resumeButton.setVisible(true);
	}
	
	
	static int posX;
	static int posY;
	static int playerX = 230;
	static int playerY = 300;
	static int enemyX  = 780;
	static int enemyY = 300;
	
	/*static void reset(){
		for(int i = 0; i < drowCnt; i++) {
			GameWindow.canvas.add(playerHandList.get(i));
			playerHandList.get(i).setVisible(false);
			GameWindow.canvas.repaint();
		}
		for(int i = 0; i < drowCnt; i++) {
			GameWindow.canvas.add(enemyHandList.get(i));
			enemyHandList.get(i).setVisible(false);
			GameWindow.canvas.repaint();
		}
		playerHandList.clear();
		playerHandList.clear();
		playerX = 230;
		playerY = 300;
		enemyX  = 780;
		enemyY = 300;
	}*/
	
	static void getHandCard() {
		if(flag) {
			posX = playerX;
			posY = playerY;
			drowCnt = playerDrowCnt;
		}else {
			posX = enemyX;
			posY = enemyY;
			drowCnt = enemyDrowCnt;
		}
		
		posX += 70;
		if(flag) {
			playerX = posX;
		}else {
			enemyX = posX;
		}
		
		for(int i = drowCnt-1; i < drowCnt; i++) {
			if(i < 0) {
				break;
			}
			tempLabel = new Label();
			if(flag) {
				tempNum = playerNumList.get(i) + "";
				tempSuit =Label.getSuitIcon(playerSuitList.get(i));
			}else {
				tempNum = enemyNumList.get(i) + "";
				tempSuit =Label.getSuitIcon(enemySuitList.get(i));
			}
			tempLabel.getLabel(tempSuit,posX, posY);
			tempLabel.setText(tempNum);
			
			if(flag) {
				playerHandList.add(tempLabel);
			}else {
				enemyHandList.add(tempLabel);
			}
		}
	}
			
	static void handCardView() {
		if(posX >= 600 && posX < 850) {
			playerY += 90;
			playerX = 300;
		}else if(posX >= 1200) {
			enemyY += 90;
			enemyX = 850;
		}
		if(flag) {
			for(int i = 0; i < drowCnt; i++) {
				GameWindow.canvas.add(playerHandList.get(i));
				playerHandList.get(i).setVisible(true);
				GameWindow.canvas.repaint();
			}
		}else {
			for(int i = 0; i < drowCnt; i++) {
				GameWindow.canvas.add(enemyHandList.get(i));
				enemyHandList.get(i).setVisible(true);
				GameWindow.canvas.repaint();
			}
		}
		if(flag) {
			flag = false;
		}else {
			flag = true;
		}
	}
}