package BlackJack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

class Card {
	  // カード総数
	  private final int numCards = 52;
	  // カードリスト
	  // 内部では0～51の番号で管理し、
	  // スペード「0～12」、ハート「13～25」、ダイヤ「26～38」、クラブ「39～51」となる
	  private List<Integer> cardList;
	  
	  // カードインデックス
	  // 1枚取るごとに加算し、51になったら0に戻す
	  private int cardIndex;
	  
	  public Card() {
		cardList = new ArrayList<>();

		//ひとつずつカードを追加する
		for(int i = 0; i < numCards; i++){
			cardList.add(i);
		}
		//リセットする
		reset();
	}

	public int getCard() {
		//カードを山札から一枚取り、インデックスを加算する
		int card = cardList.get(cardIndex++);
		//全てのカードを取ったらリセットする
		if(cardIndex >= numCards){
			reset();
		}
		return card;
	}

	  // カードのマークを取得する
	  public ImageIcon getMark(int card) {
	    // カード番号を13で割ると、その答えは0～3のいずれかになる
	    // それぞれの番号をカードのマークに振り分ける
		if(Drow.flag) {
			Drow.playerSuitList.add(card/ 13);
		}else {
			Drow.enemySuitList.add(card/13);
		}
		ImageIcon icon = Label.getSuitIcon(card/13);
		return icon;
	  }

	  // カードの番号を取得する
	  public int getNum(int card) {
	    // カード番号を13で割った余りがカード番号になるが、
	    // 内部では「0」から始まっているため、1足しておく必要がある
		int num = (card % 13 + 1);
		if(Drow.flag) {
			if(num < 9) {
				Drow.playerNumList.add(num);
			}else {
				Drow.playerNumList.add(10);
			}
		}else {
			if(num < 9) {
				Drow.enemyNumList.add(num);
			}else {
				Drow.enemyNumList.add(10);
			}
		}
	    return (num);
	  }

	  private void reset() {
	    // カードインデックスを初期化する
	    cardIndex = 0;

	    // 山札をシャッフルする
	    Collections.shuffle(cardList);
	  }
	}


