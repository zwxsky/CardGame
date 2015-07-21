package com.zwx;

public enum ColorEnum {
	红桃(4), 方块(3), 黑桃(2), 梅花(1) ;
	 private int index; 
	 ColorEnum(int idx) { 
	        this.index = idx; 
	    } 
	    public int getIndex() { 
	        return index; 
	    } 
}
