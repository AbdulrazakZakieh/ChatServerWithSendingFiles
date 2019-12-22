/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import clientInterfaces.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import serverInterfaces.ChatInterface;

/**
 *
 * @author USER
 */
public class ChatService extends UnicastRemoteObject implements ChatInterface {

    ArrayList<ClientInterface> clients;

    public ChatService() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void signIn(ClientInterface c) throws RemoteException {
        clients.add(c);
    }

    @Override
    public void sendToAll(ClientInterface sender, String message) throws RemoteException {
        for (int i = 0; i < clients.size(); i++) {
            try {
                clients.get(i).recieveMessage(sender.getClientName() + " >> " + message);
            } catch (RemoteException ex) {
            }
        }
    }

    @Override
    public void sendFile(ClientInterface sender, int recieverID, String fileName, byte[] data) throws RemoteException {
        for (ClientInterface c : clients) {
            try {
                if (c.getId() == recieverID) {
                    c.recieveFile(fileName, data);
                    c.recieveMessage(sender.getClientName() + " sent you << " + fileName + " >>");
                    sender.recieveMessage("You sent << " + fileName  + " >> to " + c.getClientName());
                    break;
                }
            } catch (RemoteException ex) {
               ex.printStackTrace();
            }
        }
    }

}
