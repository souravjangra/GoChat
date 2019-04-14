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

}
