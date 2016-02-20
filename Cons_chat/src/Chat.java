import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Chat 
{

	public static void main(String[] args)
	{
	  
		try
		{
			ServerSocket ss=new ServerSocket(2222);
			Socket client=ss.accept();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chatbot","root","");
			
			DataInputStream dis=new DataInputStream(client.getInputStream());
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			String fna=" ";
			
			while(!fna.equalsIgnoreCase("bye"))
			{
			fna=dis.readUTF();
			PreparedStatement pst=con.prepareStatement("select ANS from mytab where QUE=?");
			pst.setString(1,fna);
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				
				fna=rs.getString(1);
				
			}
			else
			{
				fna="Sorry,i can't understand you... :(";
			}
			dos.writeUTF(fna);
			}
		    dis.close();
		    dos.close();
		    con.close();
            ss.close();
  
		}
		catch(Exception e)
		{
			System.out.print(e);
		}

	}

}

/* aaa */