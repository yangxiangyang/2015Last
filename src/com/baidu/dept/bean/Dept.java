package com.baidu.dept.bean;



public class Dept {
	//id, name, pid, firstuser, seconduser, secretary
	      private Integer id;
	      private Integer pid;
          private String name;
//          private Dept pdept;
          private String firstuser;
          private String seconduser;
          private String secretary;
          private String firstuserName;
          private String seconduserName;
          private String secretaryName;
		public String getFirstuserName() {
			return firstuserName;
		}
		public void setFirstuserName(String firstuserName) {
			this.firstuserName = firstuserName;
		}
		public String getSeconduserName() {
			return seconduserName;
		}
		public void setSeconduserName(String seconduserName) {
			this.seconduserName = seconduserName;
		}
		public String getSecretaryName() {
			return secretaryName;
		}
		public void setSecretaryName(String secretaryName) {
			this.secretaryName = secretaryName;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getPid() {
			return pid;
		}
		public void setPid(Integer pid) {
			this.pid = pid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getFirstuser() {
			return firstuser;
		}
		public void setFirstuser(String firstuser) {
			this.firstuser = firstuser;
		}
		public String getSeconduser() {
			return seconduser;
		}
		public void setSeconduser(String seconduser) {
			this.seconduser = seconduser;
		}
		public String getSecretary() {
			return secretary;
		}
		public void setSecretary(String secretary) {
			this.secretary = secretary;
		}
		@Override
		public String toString() {
			return "Dept [id=" + id + ", pid=" + pid + ", name=" + name
					+ ", firstuser=" + firstuser + ", seconduser=" + seconduser
					+ ", secretary=" + secretary + ", firstuserName="
					+ firstuserName + ", seconduserName=" + seconduserName
					+ ", secretaryName=" + secretaryName + "]";
		}
          
          
}
