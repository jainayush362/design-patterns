import java.lang.*;
import java.util.*;
import java.io.*;

class User{
	private String name;
	private String emailId;
	private Integer age;

	private User(Builder builder){
		this.name = builder.name;
		this.emailId = builder.emailId;
		this.age = builder.age;

	}

	public static Builder builder(){
		return new Builder();
	}

	public String getName(){
		return name;
	}

	public String getEmailId(){
		return emailId;
	}

	public Integer getAge(){
		return age;
	}

	@Override
	public String toString(){
		return this.name +"->"+ this.emailId +"->"+ this.age;
	}

	static class Builder{
		private String name;
		private String emailId;
		private Integer age;

		private Builder(){
		}

		public Builder setName(String name){
			this.name = name;
			return this;
		}

		public Builder setEmailId(String emailId){
			this.emailId = emailId;
			return this;
		}

		public Builder setAge(Integer age){
			this.age = age;
			return this;
		}

		public User build(){
			return new User(this);
		}
	}
}

class BuilderPattern{
	public static void main(String args[]){
		System.out.println("This is about Builder design pattern in Java");
		User user = User.builder()
						.setName("Ayush")
						.setEmailId("jainayush362@gmail.com")
						.setAge(23)
						.build();
		System.out.println(user.toString());

	}
}