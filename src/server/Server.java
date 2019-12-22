/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import serverInterfaces.ChatInterface;

/**
 *
 * @author USER
 */
public class Server {
    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1099);
        ChatService service = new ChatService();
        reg.rebind(ChatInterface.LOOKUP_NAME, service);
        System.out.println("Server is working");
    }
}
