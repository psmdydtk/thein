package com.project.thein.vo;

public class ShopVO {
   private String shop_id,shop_name,shop_street,shop_menu1,shop_menu2,shop_menu3;
   private String shop_location,shop_description,shop_img,shop_hash1,shop_hash2,shop_hash3;
   private int shop_menu1_price,shop_menu2_price,shop_menu3_price;
   private double shop_point;
   
   public ShopVO() {
	super();
}

   public String getShop_id() {
      return shop_id;
   }

public void setShop_id(String shop_id) {
      this.shop_id = shop_id;
   }
   public String getShop_name() {
      return shop_name;
   }
   public void setShop_name(String shop_name) {
      this.shop_name = shop_name;
   }
   public String getShop_street() {
      return shop_street;
   }
   public void setShop_street(String shop_street) {
      this.shop_street = shop_street;
   }
   public String getShop_menu1() {
      return shop_menu1;
   }
   public void setShop_menu1(String shop_menu1) {
      this.shop_menu1 = shop_menu1;
   }
   public String getShop_menu2() {
      return shop_menu2;
   }
   public void setShop_menu2(String shop_menu2) {
      this.shop_menu2 = shop_menu2;
   }
   public String getShop_menu3() {
      return shop_menu3;
   }
   public void setShop_menu3(String shop_menu3) {
      this.shop_menu3 = shop_menu3;
   }
   public String getShop_location() {
      return shop_location;
   }
   public void setShop_location(String shop_location) {
      this.shop_location = shop_location;
   }
   public String getShop_description() {
      return shop_description;
   }
   public void setShop_description(String shop_description) {
      this.shop_description = shop_description;
   }
   public String getShop_img() {
      return shop_img;
   }
   public void setShop_img(String shop_img) {
      this.shop_img = shop_img;
   }
   public String getShop_hash1() {
      return shop_hash1;
   }
   public void setShop_hash1(String shop_hash1) {
      this.shop_hash1 = shop_hash1;
   }
   public String getShop_hash2() {
      return shop_hash2;
   }
   public void setShop_hash2(String shop_hash2) {
      this.shop_hash2 = shop_hash2;
   }
   public String getShop_hash3() {
      return shop_hash3;
   }
   public void setShop_hash3(String shop_hash3) {
      this.shop_hash3 = shop_hash3;
   }
   public int getShop_menu1_price() {
      return shop_menu1_price;
   }
   public void setShop_menu1_price(int shop_menu1_price) {
      this.shop_menu1_price = shop_menu1_price;
   }
   public int getShop_menu2_price() {
      return shop_menu2_price;
   }
   public void setShop_menu2_price(int shop_menu2_price) {
      this.shop_menu2_price = shop_menu2_price;
   }
   public int getShop_menu3_price() {
      return shop_menu3_price;
   }
   public void setShop_menu3_price(int shop_menu3_price) {
      this.shop_menu3_price = shop_menu3_price;
   }
   public double getShop_point() {
      return shop_point;
   }
   public void setShop_point(double shop_point) {
      this.shop_point = shop_point;
   }
   @Override
   public String toString() {
      return "ShopVO [shop_id=" + shop_id + ", shop_name=" + shop_name + ", shop_street=" + shop_street
            + ", shop_menu1=" + shop_menu1 + ", shop_menu2=" + shop_menu2 + ", shop_menu3=" + shop_menu3
            + ", shop_location=" + shop_location + ", shop_description=" + shop_description + ", shop_img="
            + shop_img + ", shop_hash1=" + shop_hash1 + ", shop_hash2=" + shop_hash2 + ", shop_hash3=" + shop_hash3
            + ", shop_menu1_price=" + shop_menu1_price + ", shop_menu2_price=" + shop_menu2_price
            + ", shop_menu3_price3=" + shop_menu3_price + ", shop_point=" + shop_point + "]";
   }
}