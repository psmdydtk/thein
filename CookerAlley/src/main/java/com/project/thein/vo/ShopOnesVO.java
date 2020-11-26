package com.project.thein.vo;

public class ShopOnesVO {
   private int ones_id;
   private String ones_user_id,ones_shop_id;
   

   public ShopOnesVO() {
      super();
   }


public int getOnes_id() {
	return ones_id;
}


public void setOnes_id(int ones_id) {
	this.ones_id = ones_id;
}


public String getOnes_user_id() {
	return ones_user_id;
}


public void setOnes_user_id(String ones_user_id) {
	this.ones_user_id = ones_user_id;
}


public String getOnes_shop_id() {
	return ones_shop_id;
}


public void setOnes_shop_id(String ones_shop_id) {
	this.ones_shop_id = ones_shop_id;
}


@Override
public String toString() {
	return "ShopOnesVO [ones_id=" + ones_id + ", ones_user_id=" + ones_user_id + ", ones_shop_id=" + ones_shop_id + "]";
}
   
   
}