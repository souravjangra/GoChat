import javax.swing.JFrame;
import javax.swing.JFrame.*;

public class ClientTest{
    public static void main(String[] args){
        Client me;
        me = new Client("127.0.0.1"); //localhost
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.startRunning();


    }
}