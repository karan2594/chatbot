import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class ClientChat 
{

	public static void main(String[] args) 
	{

		try
		{
			Socket s=new Socket("localhost",2222);
			
			DataInputStream dis=new DataInputStream(s.getInputStream());
			DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			
			Scanner scan=new Scanner(System.in);
			String fna="";
			
			while(!fna.equalsIgnoreCase("bye"))
			{
			System.out.print("You: ");
			fna=scan.nextLine();
			
			dos.writeUTF(fna);
			
			fna=dis.readUTF();
			System.out.println("chatbot:"+fna);
			}
			scan.close();
			dis.close();
			dos.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

}
