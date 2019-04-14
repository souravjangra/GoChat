import javax.swing.JFrame;

public class ServerTest{
    public static void main(String[] args){
        Server srv = new Server();
        srv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        srv.startRunning();
    }
}