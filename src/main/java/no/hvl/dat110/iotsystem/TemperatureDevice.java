package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

    private static final int COUNT = 10;

    public static void main(String[] args) {

        System.out.println("Temperature device started");

        // Simulated virtual temperature sensor
        TemperatureSensor sn = new TemperatureSensor();

        // Create a client object and connect to the broker using the username "sensor"
        Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
        client.connect();

        // Publish temperature readings COUNT times
        for (int i = 0; i < COUNT; i++) {
            int temperature = sn.read();
            System.out.println("READING: " + temperature);
            client.publish("temperature", Integer.toString(temperature));

            // Sleep for 1 second to simulate periodic sensor readings
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Disconnect from the broker
        client.disconnect();

        System.out.println("Temperature device stopping ... ");
    }
}

