package pa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ProfileApp extends JFrame
{
	Container c;
	JLabel labName,labPhone,labEmail,labGender,labLanguage;
	JTextField txtName,txtPhone,txtEmail;
	JRadioButton rbMale,rbFemale;
	JCheckBox cbPython,cbJava,cbJs;
	JButton btnSubmit;
		
	ProfileApp()
	{
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.decode("#00FFFF"));

		labName = new JLabel("Enter Name : ");
		txtName = new JTextField(15);
		labPhone = new JLabel("Enter Phone : ");
		txtPhone = new JTextField(15);
		labEmail = new JLabel("Enter Email : ");
        	txtEmail = new JTextField(20);
		labGender = new JLabel("Select Gender : ");
		rbMale = new JRadioButton("Male",true);
		rbFemale = new JRadioButton("Female");
		labLanguage = new JLabel("Select Languages : ");
		cbPython = new JCheckBox("Python");
		cbJava = new JCheckBox("Java");
		cbJs = new JCheckBox("JavaScript");
		btnSubmit = new JButton("Submit");

		Font f = new Font("Tahoma",Font.BOLD,30);
		labName.setFont(f);
		txtName.setFont(f);
		labPhone.setFont(f);
		txtPhone.setFont(f); 
		labEmail.setFont(f);
      		txtEmail.setFont(f);
		labGender.setFont(f);
		rbMale.setFont(f);
		rbFemale.setFont(f);
		labLanguage.setFont(f);
		cbPython.setFont(f);
		cbJava.setFont(f);
		cbJs.setFont(f); 
		btnSubmit.setFont(f);

		// Set background color to yellow for components
        Color bgColor = Color.YELLOW;
	Color bColor = new Color(0, 255, 0); // Lime Green
        labName.setOpaque(true);
        labName.setBackground(bgColor);
        labPhone.setOpaque(true);
        labPhone.setBackground(bgColor);
        labEmail.setOpaque(true);
        labEmail.setBackground(bgColor);
        labGender.setOpaque(true);
        labGender.setBackground(bgColor);
        labLanguage.setOpaque(true);
        labLanguage.setBackground(bgColor);
        rbMale.setOpaque(true);
        rbMale.setBackground(bgColor);
        rbFemale.setOpaque(true);
        rbFemale.setBackground(bgColor);
        cbPython.setOpaque(true);
        cbPython.setBackground(bgColor);
        cbJava.setOpaque(true);
        cbJava.setBackground(bgColor);
        cbJs.setOpaque(true);
        cbJs.setBackground(bgColor);
        btnSubmit.setOpaque(true);
        btnSubmit.setBackground(bColor);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rbMale);
		bg.add(rbFemale);

		labName.setBounds(80,20,300,40);
		txtName.setBounds(400,20,300,40);
		labPhone.setBounds(80,100,300,40);
		txtPhone.setBounds(400,100,300,40);
		labEmail.setBounds(80, 180, 300, 40);
        	txtEmail.setBounds(400, 180, 300, 40);
        	labGender.setBounds(80, 260, 300, 40);
        	rbMale.setBounds(400, 260, 200, 40);
        	rbFemale.setBounds(620, 260, 200, 40);
        	labLanguage.setBounds(80, 340, 300, 40);
        	cbPython.setBounds(400, 340, 300, 40);
        	cbJava.setBounds(400, 410, 300, 40);
        	cbJs.setBounds(400, 480, 300, 40);
        	btnSubmit.setBounds(400, 550, 300, 40);

		c.add(labName);
		c.add(txtName);
		c.add(labPhone);
		c.add(txtPhone);
		c.add(labEmail);
        	c.add(txtEmail);
		c.add(labGender);
		c.add(rbMale);
		c.add(rbFemale);
		c.add(labLanguage);
		c.add(cbPython);
		c.add(cbJava);
		c.add(cbJs);
		c.add(btnSubmit);

		ActionListener a = (ae)->{
			try
			{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/spapp";
			Connection con = DriverManager.getConnection(url,"root","abc456");
			
			String sql = "insert into student values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			String name = txtName.getText();
			name = name.trim();
			if(name.equals(""))
			{
				JOptionPane.showMessageDialog(c,"You did not enter name");
				txtName.setText("");
				txtName.requestFocus();
				return;
			}

			if(!name.trim().matches("[A-Za-z]+"))
			{
				JOptionPane.showMessageDialog(c,"Name should contain only alphabets");
				txtName.requestFocus();
				txtName.setText("");
				return;
			}

			String phone = txtPhone.getText();
			phone = phone.trim();
			if(phone.equals("")) 
			{
    				JOptionPane.showMessageDialog(c,"You did not enter phone number");
   				txtPhone.requestFocus();
				txtPhone.setText("");
    				return;
			}

			if(!phone.matches("\\d{10}")) 
			{ // Adjust the regex pattern based on your phone number requirements
    				JOptionPane.showMessageDialog(c,"Phone number should be 10 digits");
       				txtPhone.requestFocus();
    				txtPhone.setText("");
    				return;
			}

			String email = txtEmail.getText().trim();
	                if (email.equals(""))
        	        {
                	   	JOptionPane.showMessageDialog(c, "You did not enter email");
                    		txtEmail.requestFocus();
                    		txtEmail.setText("");
                    		return;
                	}

                	if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
                	{
                    		JOptionPane.showMessageDialog(c, "Enter a valid email address");
                    		txtEmail.requestFocus();
                    		txtEmail.setText("");
                    		return;
                	}

			String gender = "";
			if (rbMale.isSelected())		gender = "Male";
			else						gender = "Female";

			String language ="";
			if (cbPython.isSelected())		language += " Python ";
			if (cbJava.isSelected())		language += " Java ";
			if (cbJs.isSelected())			language += " JavaScript ";
			
			language = language.trim();
			if(language.equals("")) 
			{
    				JOptionPane.showMessageDialog(c,"You must select at least one language");
       				return;
			}

			pst.setString(1,name);
			pst.setString(2,phone);
			pst.setString(3,email);
			pst.setString(4,gender);
			pst.setString(5,language);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(c,"Thank you");
			txtName.setText("");
			txtPhone.setText("");
			txtEmail.setText("");
			rbMale.setSelected(true);
			cbPython.setSelected(false);
    			cbJava.setSelected(false);
    			cbJs.setSelected(false);
			txtName.requestFocus();
			con.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(c,"issue " + e);
		}
	};
	btnSubmit.addActionListener(a);

		setTitle("Student Profile App By RSB");
		setSize(1000,650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setVisible(true);
	}
}

class SPA
{
	public static void main(String args[])
	{
		ProfileApp s = new ProfileApp();
	}
}