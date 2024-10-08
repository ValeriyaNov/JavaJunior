package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    private final Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();


    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            ClientManagerSingleton.getInstance().add(this);
            System.out.println(name + " подключился к чату");
            broadcastMessage("Server: " + name + " подключился к чату");

        } catch (IOException e) {
            closeEverything(socket, bufferedReader,bufferedWriter);
        }
    }

    @Override
    public void run(){
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if(messageFromClient == null){
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                broadcastMessage(messageFromClient);
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMessage(String message){
        String firstName = checkName(message);
        for (ClientManager client : ClientManagerSingleton.getInstance()) {
            try {
                // если отсылка к приватному сообщению есть, то направляем сообщение ТОЛЬКО одному адресату
                if (firstName != null) {
                    if (client.name.equals(firstName)) {
                        String name = message.split(": ", 2)[0];
                        String mess = message.split(": ", 2)[1].substring(firstName.length() + 1);

                        client.bufferedWriter.write(name + ":" + mess);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                    // Если отсылки на приватное сообщение нет
                } else
                    // Если клиент не равен по наименованию клиенту-отправителю, отправим сообщение
                    if (!client.name.equals(name)) {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClient();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void removeClient(){
        ClientManagerSingleton.getInstance().remove(this);
        System.out.println(name + " покинул чат");
        broadcastMessage("Server: " + name + " покинул чат");
    }
    // Метод, определяющий наличие отсылки к приватному сообщению
    private String checkName(String message) {
        String nameAfter$ = null;
        String firstWord = message.split(": ", 2)[1];
        if (firstWord.startsWith("$")) {
            String textAfter$ = firstWord.split(" ", 2)[0].substring(1);
            for (ClientManager client : ClientManagerSingleton.getInstance()) {
                if (client.name.equals(textAfter$)) nameAfter$ = textAfter$;
            }
        }
        return nameAfter$;
    }
}
