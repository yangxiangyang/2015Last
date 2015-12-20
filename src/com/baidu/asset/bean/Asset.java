package com.baidu.asset.bean;

import java.util.Date;


public class Asset {
	
	//id, num, name, typeid, buydate, userid, price, factory, status, content
	      private Integer id;
	      private String num;
          private String name; 
          private Integer typeid;
          private Date buydate;
          private Integer userid;
          private Integer price;
          private String factory;
          private String status;
          private String content;
          public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getTypeid() {
			return typeid;
		}
		public void setTypeid(Integer typeid) {
			this.typeid = typeid;
		}
		public Date getBuydate() {
			return buydate;
		}
		public void setBuydate(Date buydate) {
			this.buydate = buydate;
		}
		public Integer getUserid() {
			return userid;
		}
		public void setUserid(Integer userid) {
			this.userid = userid;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		public String getFactory() {
			return factory;
		}
		public void setFactory(String factory) {
			this.factory = factory;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
          
          
          
          
          
          
}
