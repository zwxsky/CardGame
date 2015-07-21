package com.zwx;

import java.util.List;

public class Player {
	
	public int id;
	
	public String name;
	
	public List<Card> cards;
	
	public Player(){
		
	}

	public Player(int id,String name,List<Card> cards){
		this.id  =id;
		this.name=name;
		this.cards=cards;
	}
}
