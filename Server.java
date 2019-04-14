import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame{

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    // Creating a constructor 
    public Server(){
        super("GoChat");
        userText = new JTextField();
        userText.setEditable(false);

        userText.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    sendMessage(event.getActionCommand());
                }
            }
        );

        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300,150);
        setVisible(true);

    }

    // setup and start the server

    public void startRunning(){
        try{
            server = new ServerSocket(6780, 100);
            while(true){
                try{
                    //connect and have the conversation
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                }
                catch(EOFException eofException)
                {
                    showMessage("\n Server ended the connection!");
                }
                finally{
                    closeCrap();
                }
            }

        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //wait for connection and then display connection information

    private void waitForConnection() throws IOException{
        showMessage("Waiting for someone to connect... \n");

        connection = server.accept();

        showMessage(" Now connected to " + connection.getInetAddress().getHostName());

    }

    // get stream to send and recieve data

    private void setupStreams() throws IOException{

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        
        // recieving messages from another computer
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now setup! \n");

    
    }

    // during the chat conversation

    private void whileChatting() throws IOException{
        String message = "You are now connected to GoChat!";
        sendMessage(message);
        ableToType(true);

        do{
            try{
                message = (String) input.readObject();
                showMessage("\n" + message);
            }
            catch(ClassNotFoundException classNotFoundException){
                showMessage("\n idk what are you saying!");
            }
        }
        while(!message.equals("CLIENT - END"));

    }

    // close streams and sockets after you're done with chatting
    private void closeCrap(){
        showMessage("\n Closing connections... \n");
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();
            
        } catch (IOException ioException {
            ioException.printStackTrace();
        }
    }

    

}