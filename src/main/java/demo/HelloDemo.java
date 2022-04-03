package demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloDemo implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Hello Spring Demo");

	}

}
