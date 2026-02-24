package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// A class that represents an event-driven smart contract platform
public class EDSC {
    // A list of blockchain events
    protected List<BlockchainEvent> events;
    // A socket for communicating with other nodes
    private Socket socket;

    public EDSC(List<BlockchainEvent> events, Socket socket) {
        this.events = events != null ? events : new ArrayList<>();
        this.socket = socket;
    }

    public EDSC(String localhost, int port) throws IOException {
        this.events = new ArrayList<>();
        this.socket = new Socket(localhost, port);
        new Thread(new Listener()).start();
        System.out.println("EDSC started on " + localhost + ":" + port);
    }

    // Accessor method to get the list of events
    public List<BlockchainEvent> getEvents() {
        return events;
    }

    // A method that adds a new event to the blockchain and broadcasts it to other nodes
    public void addEvent(String data) throws Exception {
        if (socket == null || socket.isClosed()) {
            throw new IOException("Socket is not connected");
        }

        // Get the hash of the last event or use "0" if there is no event
        String prevHash = events.isEmpty() ? "0" : events.get(events.size() - 1).getHash();

        // Create a new event with the given data and the previous hash
        BlockchainEvent event = new BlockchainEvent(data, prevHash);
        // Add the event to the list
        events.add(event);
        System.out.println("New event added: " + event.getData());
        // Send the event to other nodes using the socket's output stream
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(event.getData() + "," + event.getTimestamp() + "," + event.getPrevHash() + "," + event.getHash());
        }
    }

    // A method that validates an event by checking its hash and previous hash
    public boolean validateEvent(BlockchainEvent event) {
        // Check if the event's hash matches its calculated hash
        if (!event.getHash().equals(event.calculateHash())) {
            System.out.println("Invalid hash");
            return false;
        }
        // Check if the event's previous hash matches the last event's hash or "0" if there is no event
        String prevHash = events.isEmpty() ? "0" : events.get(events.size() - 1).getHash();

        if (!event.getPrevHash().equals(prevHash)) {
            System.out.println("Invalid previous hash");
            return false;
        }

        return true;
    }

    // A method that triggers actions based on the event's data
    public void triggerAction(BlockchainEvent event) {
        // For simplicity, we assume that the event's data is a string that contains a command and an argument separated by a space
        String[] parts = event.getData().split(" ");
        
        if (parts.length < 1) {
            System.out.println("Invalid command format");
            return;
        }

        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";
        
        // Switch on the command and perform the corresponding action
        switch (command) {
            case "print":
                // Print the argument to the console
                System.out.println(argument);
                break;
            case "connect":
                // Connect to another node using the argument as the IP address and port number
                if (argument.isEmpty()) {
                    System.out.println("Connect requires IP:port format");
                    break;
                }
                try {
                    String[] addressParts = argument.split(":");
                    if (addressParts.length != 2) {
                        System.out.println("Invalid address format: " + argument);
                        break;
                    }
                    synchronized (this) {
                        if (socket != null && !socket.isClosed()) {
                            socket.close();
                        }
                        socket = new Socket(addressParts[0], Integer.parseInt(addressParts[1]));
                    }
                    System.out.println("Connected to " + argument);
                } catch (IOException | NumberFormatException e) {
                    System.out.println("Connection failed: " + e.getMessage());
                }
                break;
            case "disconnect":
                // Disconnect from the current node
                try {
                    synchronized (this) {
                        if (socket != null && !socket.isClosed()) {
                            socket.close();
                        }
                    }
                    System.out.println("Disconnected");
                } catch (IOException e) {
                    System.out.println("Disconnect failed: " + e.getMessage());
                }
                break;
            default:
                // Unknown command
                System.out.println("Unknown command: " + command);
        }
    }

    // A class that implements Runnable interface to listen for incoming messages from other nodes
    class Listener implements Runnable {
        @Override
        public void run() {
            try {
                // Get the socket's input stream and wrap it in a buffered reader
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                // Loop until the socket is closed or an exception occurs
                while ((message = in.readLine()) != null) {
                    // Parse the message as an event by splitting it by commas
                    String[] parts = message.split(",");
                    
                    if (parts.length < 4) {
                        System.out.println("Invalid message format");
                        continue;
                    }

                    try {
                        String data = parts[0];
                        long timestamp = Long.parseLong(parts[1]);
                        String prevHash = parts[2];
                        String hash = parts[3];
                        // Create a new event with the parsed data
                        BlockchainEvent event = new BlockchainEvent(data, prevHash);
                        event.setTimestamp(timestamp);
                        event.setHash(hash);
                        // Validate the event and add it to the list if valid
                        if (validateEvent(event)) {
                            events.add(event);
                            System.out.println("New event received: " + event.getData());
                            // Trigger an action based on the event's data
                            triggerAction(event);
                        } else {
                            System.out.println("Invalid event received: " + event.getData());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing event data: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Listener stopped: " + e.getMessage());
            }
        }
    }
}

