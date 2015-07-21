package com.zwx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class CardController {
	
	public List<Card> create() throws ServiceException{
		List<Card> cards = new ArrayList<Card>();
		for(int i=1;i<=13;i++){
			String num=changeToString(i);
			if(num==null){
				throw new ServiceException("param is empty");
			}
			for(ColorEnum color:EnumSet.range(ColorEnum.红桃, ColorEnum.梅花)){
				String color1=color.name()+num;
				Card card = new Card(color1,i,color.ordinal());
				cards.add(card);
			}
		}
		System.out.println("card create success");
		return cards;
	}
	
	public String changeToString(int num){
		if(num >=1 && num<=8){
			return String.valueOf(num+2);
		}
		if(num >8 && num <=13){
			int poor =num-8;
			switch (poor) {
			case 1:return "J";
			case 2:return "Q";
			case 3:return "K";
			case 4:return "A";
			case 5:return "2";
			default:
				break;
			}
		}
		return null;
	}
	
	/**
	 * 洗牌
	 * @param cards
	 * @return
	 */
	public List<Card> washCards(List<Card> cards){
		System.out.println("----------------------开始洗牌------------------------------");
		Collections.shuffle(cards);
		System.out.println("----------------------洗牌结束------------------------------");
		return cards;
	}
	
	/**
	 * 展示所有牌
	 * @param cards
	 */
	public void showCards(List<Card> cards){
		System.out.print("所有的牌为：");
		for(Card card:cards){
			System.out.print(card.color+" ");
		}
		System.out.println("\r\n"+"==========================");
	}
	
	/**
	 * 发牌
	 * @param pNum
	 * @param cNum
	 * @param players
	 * @param cards
	 */
	public void dealCards(int pNum,int cNum,List<Player> players,List<Card> cards){
		for(int i=0;i < cNum; i++){
			for(int j=0;j < pNum;j++){
				players.get(j).cards.add(cards.get(cNum*i+j));
//				System.out.println("玩家"+players.get(j).name+ "发到："+ cards.get(cNum*i+j).color);
				System.out.println("玩家"+players.get(j).name+ " 拿牌");
			}
		}
		for(int i=0;i<pNum;i++){
			System.out.println("玩家"+players.get(i).name);
		}
	}
	
	public void judgeWinner(int pNum,List<Player> players){
		List<Card> cards = new ArrayList<Card>();
		for(int i=0;i<pNum;i++){
			Card card= new Card();
			card = findBig(players.get(i).cards);
			System.out.println("玩家"+players.get(i).name+" 的最大手牌为:" +card.color);
			cards.add(card);
		}
		System.out.println("==========================");
		int sign=0;
		Card c1= cards.get(0);
		for(int i=1;i<cards.size();i++){
			if(!judgeBig(c1, cards.get(i))){
				sign=i;
				c1=cards.get(i);
			}
		}
		System.out.println("congratulations, "+players.get(sign).name +" win!");
		System.out.println("=================");
		for(int i=0;i<pNum;i++){
			List<Card> cards2= players.get(i).cards;
			System.out.print("玩家"+players.get(i).name+" 的手牌为:");
			for(Card c:cards2){
				System.out.print(c.color+" ");
			}
			System.out.println();
		}
	}
	
	public Boolean judgeBig(Card c1,Card c2){
		if(c1.id >c2.id){
			return true;
		}else if(c1.id == c2.id){
			return (c1.ordinal >c2.ordinal?false:true);
		}else{
			return false;
		}
	}
	
	public Card findBig(List<Card> cards){
		Card c1=cards.get(0);
		for(int i=1;i<cards.size();i++){
			if(c1.id >cards.get(i).id)continue;
			if(c1.id ==cards.get(i).id){
				if(c1.ordinal >cards.get(i).ordinal){
					c1=cards.get(i);
				}
			}else{
				c1=cards.get(i);
			}
		}
		return c1;
	}
	
		public static void main(String[] args) {
			CardController cardController = new CardController();
			try {
				@SuppressWarnings("resource")
				Scanner console = new Scanner(System.in);
				List<Card> cards = cardController.create();
				cardController.showCards(cards);
				Integer pNum=0;
				System.out.println("请输入玩家数量");
				while(true){
					pNum=console.nextInt();
					if(pNum!=null){
						break;
					}else{
						System.out.println("类型有误，请重新输入");
					}
				}
				
				List<Player> list = new ArrayList<Player>();
				for(int i=0;i<pNum;i++){
					
					Integer id=0;
					System.out.println("请输入玩家id：");
					while(true){
						id=console.nextInt();
						if(id!=null){
							break;
						}else{
							System.out.println("类型有误，请重新输入");
						}
					}
					System.out.println("请输入玩家姓名：");
					String name=console.next();
					Player player= new Player();
					player.id=id;
					player.name=name;
					player.cards=new ArrayList<Card>();
					list.add(player);
				}	
				
				cardController.washCards(cards);
				cardController.showCards(cards);
				cardController.dealCards(pNum, 2, list, cards);
				cardController.judgeWinner(pNum, list);
				
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
		}

}
