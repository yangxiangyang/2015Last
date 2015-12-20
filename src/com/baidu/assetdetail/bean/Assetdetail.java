package com.baidu.assetdetail.bean;


public class Assetdetail {
	
//	select id, name, model, num, content, assetid from t_assetdetail
	      private Integer id;
          private String name;
          private String model;
          private String num;
          private String content;
          private Integer assetid;
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
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Integer getAssetid() {
			return assetid;
		}
		public void setAssetid(Integer assetid) {
			this.assetid = assetid;
		}
          
}
