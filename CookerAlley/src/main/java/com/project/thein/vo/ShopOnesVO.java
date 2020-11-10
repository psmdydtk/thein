package com.project.thein.vo;

public class ShopOnesVO {
   private int ones_id, ones_shop_description;
   private String ones_user_id,ones_shop_id,ones_shop_name,ones_shop_img;
   private double ones_shop_point;
   
   
   @Override
   public String toString() {
      return "ShopOnesVO [ones_id=" + ones_id + ", ones_shop_description=" + ones_shop_description + ", ones_user_id="
            + ones_user_id + ", ones_shop_id=" + ones_shop_id + ", ones_shop_name=" + ones_shop_name
            + ", ones_shop_img=" + ones_shop_img + ", ones_shop_point=" + ones_shop_point + "]";
   }


   public ShopOnesVO() {
      super();
   }
   
   
   public ShopOnesVO(int ones_id, String ones_user_id, String ones_shop_id) {
      super();
      this.ones_id = ones_id;
      this.ones_user_id = ones_user_id;
      this.ones_shop_id = ones_shop_id;
   }


   public int getOnes_id() {
      return ones_id;
   }
   public void setOnes_id(int ones_id) {
      this.ones_id = ones_id;
   }
   public int getOnes_shop_description() {
      return ones_shop_description;
   }
   public void setOnes_shop_description(int ones_shop_description) {
      this.ones_shop_description = ones_shop_description;
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
   public String getOnes_shop_name() {
      return ones_shop_name;
   }
   public void setOnes_shop_name(String ones_shop_name) {
      this.ones_shop_name = ones_shop_name;
   }
   public String getOnes_shop_img() {
      return ones_shop_img;
   }
   public void setOnes_shop_img(String ones_shop_img) {
      this.ones_shop_img = ones_shop_img;
   }
   public double getOnes_shop_point() {
      return ones_shop_point;
   }
   public void setOnes_shop_point(double ones_shop_point) {
      this.ones_shop_point = ones_shop_point;
   }
}