package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main(String[] args) {
		
		System.out.println("Display starting ...");

		// Create a client object and connect to the broker using "display" as the username
		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();

		// Create the "temperature" topic on the broker
		client.createTopic("temperature");

		// Subscribe to the "temperature" topic
		client.subscribe("temperature");

		// Receive COUNT messages on the topic and display them
		for (int i = 0; i < COUNT; i++) {
			Message message = client.receive();
			System.out.println("DISPLAY: " + message);
		}

		// Unsubscribe from the topic
		client.unsubscribe("temperature");

		// Disconnect from the broker
		client.disconnect();

		System.out.println("Display stopping ...");
	}
}