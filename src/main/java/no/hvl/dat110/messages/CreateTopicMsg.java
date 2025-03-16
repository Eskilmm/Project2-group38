package no.hvl.dat110.messages;

public class CreateTopicMsg extends Message {
	
	//Object variable to store the topic name
    private String topic;

    // Constructor that initializes the user and topic
    public CreateTopicMsg(String user, String topic) {
        super(MessageType.CREATETOPIC, user); // Call superclass constructor with message type and user
        this.topic = topic;
    }

    // Getter method for topic
    public String getTopic() {
        return topic;
    }

    // Setter method for topic
    public void setTopic(String topic) {
        this.topic = topic;
    }

    // toString method for logging purposes
    @Override
    public String toString() {
        return "CreateTopicMsg [user=" + getUser() + ", topic=" + topic + "]";
    }
}
