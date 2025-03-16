package no.hvl.dat110.messages;

public class PublishMsg extends Message {
	
    // Object variables to store the topic name and the message content
    private String topic;
    private String message;

    // Constructor that initializes the user, topic, and message
    public PublishMsg(String user, String topic, String message) {
        super(MessageType.PUBLISH, user); // Call superclass constructor with message type and user
        this.topic = topic;
        this.message = message;
    }

    // Getter method for topic
    public String getTopic() {
        return topic;
    }

    // Setter method for topic
    public void setTopic(String topic) {
        this.topic = topic;
    }

    // Getter method for message content
    public String getMessage() {
        return message;
    }

    // Setter method for message content
    public void setMessage(String message) {
        this.message = message;
    }

    // toString method for logging purposes
    @Override
    public String toString() {
        return "PublishMsg [user=" + getUser() + ", topic=" + topic + ", message=" + message + "]";
    }
}

