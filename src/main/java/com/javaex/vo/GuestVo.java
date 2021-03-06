package com.javaex.vo;

public class GuestVo {

	private String name;
	private String password;
	private String content;
	private int no;
	private String regDate;

	public GuestVo(String name, String password, String content) {
		this.name = name;
		this.password = password;
		this.content = content;
	}

	public GuestVo() {
	}

	public GuestVo(String name, String password, String content, int no, String regDate) {
		this.name = name;
		this.password = password;
		this.content = content;
		this.no = no;
		this.regDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "GuestVo [name=" + name + ", password=" + password + ", content=" + content + ", no=" + no + ", regDate="
				+ regDate + "]";
	}

}
