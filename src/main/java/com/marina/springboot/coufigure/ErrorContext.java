package com.marina.springboot.coufigure;

public class ErrorContext {
	
	private String nameError = "ユーザー名が違います。メールアドレスを入力して下さい。";
	private String passError = "パスワードが違います。英数文字のみ、８文字以上、１２文字以下で入力して下さい。";
	
	public String getNameError() {
		return nameError;
	}
	
	public void setNameError(String nameError) {
		nameError = this.nameError;
	}
	
	public String getPassError() {
		return passError;
	}
	
	public void setPassError(String passError) {
		passError = this.passError;
	}
}
