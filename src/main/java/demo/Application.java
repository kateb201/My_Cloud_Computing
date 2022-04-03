package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mysql> create database db_example;
 * -- Creates the new database
 * mysql> create user 'springuser'@'%' identified by 'ThePassword';
 * -- Creates the user
 * mysql> grant all on db_example.* to 'springuser'@'%';
 * -- Gives all privileges to the new user on the newly created database
 */
// helo
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
