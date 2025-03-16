package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

    // Maps from topic to set of subscribed users
    protected ConcurrentHashMap<String, Set<String>> subscriptions;

    // Maps from user to corresponding client session object
    protected ConcurrentHashMap<String, ClientSession> clients;

    public Storage() {
        subscriptions = new ConcurrentHashMap<>();
        clients = new ConcurrentHashMap<>();
    }

    public Collection<ClientSession> getSessions() {
        return clients.values();
    }

    public Set<String> getTopics() {
        return subscriptions.keySet();
    }

    // Get the session object for a given user
    // Session object can be used to send a message to the user
    public ClientSession getSession(String user) {
        return clients.get(user);
    }

    // Get the set of subscribers for a given topic
    public Set<String> getSubscribers(String topic) {
        return subscriptions.getOrDefault(topic, ConcurrentHashMap.newKeySet());
    }

    /**
     * Adds a new client session when a user connects.
     */
    public void addClientSession(String user, Connection connection) {
        clients.put(user, new ClientSession(user, connection));
        Logger.log("Client session added: " + user);
    }

    /**
     * Removes a client session when a user disconnects.
     */
    public void removeClientSession(String user) {
        ClientSession session = clients.remove(user);
        if (session != null) {
            session.disconnect();
            Logger.log("Client session removed: " + user);
        }
    }

    /**
     * Creates a new topic with an empty subscriber set.
     */
    public void createTopic(String topic) {
        subscriptions.putIfAbsent(topic, ConcurrentHashMap.newKeySet());
        Logger.log("Topic created: " + topic);
    }

    /**
     * Deletes a topic, removing all its subscribers.
     */
    public void deleteTopic(String topic) {
        subscriptions.remove(topic);
        Logger.log("Topic deleted: " + topic);
    }

    /**
     * Adds a user as a subscriber to a topic.
     */
    public void addSubscriber(String user, String topic) {
        subscriptions.computeIfAbsent(topic, k -> ConcurrentHashMap.newKeySet()).add(user);
        Logger.log("User " + user + " subscribed to topic: " + topic);
    }

    /**
     * Removes a user from a topic's subscription list.
     */
    public void removeSubscriber(String user, String topic) {
        subscriptions.computeIfPresent(topic, (k, subscribers) -> {
            subscribers.remove(user);
            Logger.log("User " + user + " unsubscribed from topic: " + topic);
            return subscribers.isEmpty() ? null : subscribers; // Remove topic if empty
        });
    }
}

