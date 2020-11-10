package com.project.thein.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationVO {
   private int reser_id,reser_shop_hour,reser_shop_person,reser_shop_price,reser_shop_pass;
   private String reser_shop_id,reser_user_id,reser_shop_date,reser_shop_regi;
   
   SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
         
   
      
   @Override
public String toString() {
	return "ReservationVO [reser_id=" + reser_id + ", reser_shop_hour=" + reser_shop_hour + ", reser_shop_person="
			+ reser_shop_person + ", reser_shop_price=" + reser_shop_price + ", reser_shop_pass=" + reser_shop_pass
			+ ", reser_shop_id=" + reser_shop_id + ", reser_user_id=" + reser_user_id + ", reser_shop_date="
			+ reser_shop_date + ", reser_shop_regi=" + reser_shop_regi + ", format1=" + format1 + ", shop_name="
			+ shop_name + "]";
}
private String shop_name;
   public String getShop_name() {
         return shop_name;
      }
      public void setShop_name(String shop_name) {
         this.shop_name = shop_name;
      }

   
   public ReservationVO() {
      super();
      Date time = new Date();
      
      reser_shop_regi = format1.format(time);
   }
   public ReservationVO(String shop_id, String user_id) {
      super();
      this.reser_shop_id = shop_id;
      this.reser_user_id = user_id;
   }
   public void setReser_id(int reser_id) {
      this.reser_id = reser_id;
   }
   public int getReser_shop_hour() {
      return reser_shop_hour;
   }
   
   public void setReser_shop_hour(int reser_shop_hour) {
      this.reser_shop_hour = reser_shop_hour;
   }
   public int getReser_shop_person() {
      return reser_shop_person;
   }
   public void setReser_shop_person(int reser_shop_person) {
      this.reser_shop_person = reser_shop_person;
   }
   public int getReser_shop_price() {
      return reser_shop_price;
   }
   public void setReser_shop_price(int reser_shop_price) {
      this.reser_shop_price = reser_shop_price;
   }
   public int getReser_shop_pass() {
      return reser_shop_pass;
   }
   public void setReser_shop_pass(int reser_shop_pass) {
      this.reser_shop_pass = reser_shop_pass;
   }
   public String getReser_shop_id() {
      return reser_shop_id;
   }
   public void setReser_shop_id(String reser_shop_id) {
      this.reser_shop_id = reser_shop_id;
   }
   public String getReser_user_id() {
      return reser_user_id;
   }
   public void setReser_user_id(String reser_user_id) {
      this.reser_user_id = reser_user_id;
   }
   public String getReser_shop_date() {
      return reser_shop_date;
   }
   public void setReser_shop_date(String reser_shop_date) {
      this.reser_shop_date = reser_shop_date;
   }
   public String getReser_shop_regi() {
      return reser_shop_regi;
   }
   public void setReser_shop_regi(String reser_shop_regi) {
      this.reser_shop_regi = reser_shop_regi;
   }
   
}