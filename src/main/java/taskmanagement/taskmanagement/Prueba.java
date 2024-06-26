package taskmanagement.taskmanagement;

import java.util.UUID;

import javax.swing.JOptionPane;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "Prueba", "Titulo", JOptionPane.INFORMATION_MESSAGE);
	
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.encode("as");
		
	
		System.out.println(UUID.randomUUID().toString());
		
		
	
	}

}
