package com.zwx;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card c1, Card c2) {
		if(c1.id >c2.id){
			return 1;
		}else if(c1.id == c2.id){
			return (c1.ordinal >c2.ordinal?0:1);
		}else{
			return 0;
		}
	}

}
