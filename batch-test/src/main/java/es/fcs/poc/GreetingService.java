package es.fcs.poc;

public class GreetingService {

	private String greeting;

	public void sayGreeting() {
		System.out.println(greeting);
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
