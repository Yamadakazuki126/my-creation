package BlackJack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	
	public static Card card;
	static Drow drow;
	static int player;
	static int enemy;
	static int playerSuit;
	static int enemySuit;
	
	static Button drowButton;
	static Button standButton;
	//static Button resumeButton;
	
	static Canvas canvas;
	static Label playerCardlbl;
	static Label enemyCardlbl;
	static Label labelA;
	static Label labelB;
	static Label labelC;
	static Label labelD;
	
	public GameWindow(String title, int width, int height){
		super(title);
		
		setSize(width,height);
		
		setResizable(false);
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		canvas = new Canvas();
		
		init();
		
		canvas.add(enemyCardlbl);
		
		add(canvas);
		
		setVisible(true);
	}
	
	void init(){
		drowButton = new Button("ドロー",50,500);
		standButton = new Button("スタンド",300, 500);
		//resumeButton = new Button("もう一度", 550, 500);
		drowButton.addActionListener(e -> Drow.drowCard("player"));
		standButton.addActionListener(e -> Drow.finDrow(21));
		//resumeButton.addActionListener(e -> resume());
		
		canvas.add(drowButton);
		canvas.add(standButton);
		//canvas.add(resumeButton);
		
		//resumeButton.setVisible(false);
		
		playerCardlbl = new Label();
		enemyCardlbl = new Label();
		labelA = new Label();
		labelB = new Label();
		labelC = new Label();
		labelD = new Label();
		
		card = new Card();
		
		reset();
	}
	
	void reset() {
		//Drow.reset();
		setPlayerCard();
		Drow.flag = false;
		setEnemyCard();
		
		labelA.getLabelFont("ブラックジャック", 50, -200);
		
		labelB.setBounds(250,230,500,100);
		labelB.setText("合計: " + Drow.playerNumList.get(0));
		
		labelC.setBounds(240,320, 700,150);
		labelC.setFont(new Font("Arial",Font.BOLD,20));
		
		labelD.setBounds(820,230,500,100);
		labelD.setText("合計: " + Drow.enemyNumList.get(0));
		
		canvas.add(labelA);
		canvas.add(labelB);
		canvas.add(labelC);
		canvas.add(labelD);
	}
	
	/*void resume() {
		reset();
	}*/
	
	static boolean first = false;
	static void setPlayerCard() {
		//カード情報をゲット
		player = card.getCard();
		ImageIcon playerSuit = card.getMark(player);
		String playerNum = card.getNum(player) + "";
		//カード情報をもとにラベル作成
		playerCardlbl.getLabel(playerSuit,250, 300);
		playerCardlbl.setText(playerNum);
		//カードラベルを表示
		canvas.add(playerCardlbl);
		Drow.getHandCard();
		if(first) {
			Drow.handCardView();
		}
	}
	
	static void setEnemyCard() {
		enemy = card.getCard();
		ImageIcon enemySuit = card.getMark(enemy);
		String enemyNum = card.getNum(enemy) + "";
		enemyCardlbl.getLabel(enemySuit, 820,300);
		enemyCardlbl.setText(enemyNum);
		canvas.add(enemyCardlbl);
		Drow.getHandCard();
		if(first) {
			Drow.handCardView();
		}
		first = true;
	}
}


class Canvas extends JPanel{
	Canvas(){
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		setBackground(Color.CYAN);
	}
}

class Label extends JLabel{
	Label(){
		super();
	}
	
	public void getLabel(ImageIcon icon, int posX, int posY) {
		setIcon(icon);
		setBounds(posX, posY,60,80);
		setHorizontalAlignment(Label.CENTER);
		setOpaque(true);
		setBackground(Color.WHITE);
	}
	
	public void getLabelFont(String string, int posX, int posY) {
		setText(string);
		setBounds(posX, posY,1200,500);
		setHorizontalAlignment(Label.CENTER);
		
		setFont(new Font("MSゴシック",getFont().BOLD,100));
	}
	
	static ImageIcon getSuitIcon(int suit){
		ImageIcon icon;
		
		switch(suit) {
			case 0://スペード
				icon = new ImageIcon("./src/highandlow/spade.jpeg");
				return icon;
				
			case 1://ハート
				icon = new ImageIcon("./src/highandlow/heart.jpeg");
				return icon;
				
			case 2://ダイヤ
				icon = new ImageIcon("./src/highandlow/diamond.jpeg");
				return icon;
				
			case 3://クラブ
				icon = new ImageIcon("./src/highandlow/clover.jpeg");
				return icon;
				
			default://マークが不正の場合
				icon = null;
				return icon;
		}
	}
}

class Button extends JButton{
	public Button(String string, int posX, int posY) {
		super(string);
		
		setFont(new Font("メイオリ",80,30));
		
		setBounds(posX,posY,200,50);
	}
}
