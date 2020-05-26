package lk.ijse.gdse41.publicChatClient.connector;

import lk.ijse.gdse41.publicChatCommon.control.ChatController;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Created by oshan on 19-Nov-17.
 */
public class ServerConnector {
    private static ServerConnector serverConnector;
    private ChatController controller;

    private ServerConnector() throws IOException, NotBoundException {
        Properties properties=new Properties();
       // InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("settings.properties");        properties.load(input);
        InputStream input = new FileInputStream(new File("C:\\Users\\acer\\Desktop\\ChatRoomFX\\PublicChatServer\\out\\artifacts\\PublicChatServer_jar\\dbSettings\\settings.properties"));
        properties.load(input);
        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));
        //properties.load(new FileInputStream("settings.properties"));
//        System.setProperty("java.rmi.server.hostname",properties.getProperty("ip"));
        System.out.println(properties.getProperty("ip"));
        controller= (ChatController) Naming.lookup(String.format("rmi://%s:1099/ChatServer", properties.getProperty("ip")));
    }

    public static ServerConnector getServerConnector() throws IOException, NotBoundException {
       if(serverConnector==null){
           serverConnector=new ServerConnector();
       }
       return serverConnector;
    }

    public ChatController getController(){
        return controller;
    }
}
