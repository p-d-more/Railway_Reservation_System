/*Railway Reservation Project*/

package RailwayReservation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.Random;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.sql.Date;  
import java.util.Calendar;  
//first page for login and registration
class railwayReservation extends JFrame implements ActionListener {
	Connection con=null;
	PreparedStatement ps= null;
	ResultSet rs=null;
	JLabel uname,pass;
	JPasswordField passtext;
	JTextField utext;
	JButton login ,reg;
	Container content;
	railwayReservation(){
		try{

		}
		catch(Exception e){
		
		}
//component insertion
		JFrame jf = new JFrame();
		content = this.getContentPane();
		setSize(700,700);
		setVisible(true);
		content.setLayout(null); 
		
		JLabel jl= new JLabel("WELCOME TO RAILWAY RESERVATION !!");
		jl.setBounds(200,70,400,40);
		content.add(jl);  
		JLabel msg1= new JLabel("MAKES YOUR JOURNEY EASY and SAFE !!");
		msg1.setBounds(200,120,400,40);
		content.add(msg1);  

		uname= new JLabel("UserName:");
		uname.setBounds(200,200,100,40);
		content.add(uname);
		utext = new JTextField(20);
		utext.setBounds(340,210,150,30);
		content.add(utext);
		pass= new JLabel("Password:");
		pass.setBounds(200,250,100,40);
		content.add(pass);
		passtext = new JPasswordField(8);
		passtext.setBounds(340,260,150,30);
		content.add(passtext);
		login = new JButton("Login/Sign In");
		login.setBounds(250,330,200,40);
		content.add(login);
		login.addActionListener(this);
		JLabel jl2= new JLabel("OR NEW USER?");
		jl2.setBounds(270,380,150,40);
		content.add(jl2);  
		reg = new JButton("Register/Sign Up");
		reg.setBounds(250,430,200,40);
		content.add(reg);
		reg.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae){	
		int flag=0;
		if(ae.getSource()==login){
			String u1=null,dp=null,du=null;
			u1=utext.getText();
			char p1[]=passtext.getPassword();
			String passwd = new String(p1);
			try{
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RAILWAY?useSSL=false","root","1504");
				if(con!=null){
				System.out.println("connection created");
				}
//checks wheather the user is legitimate user or not across database information
				ps=con.prepareStatement("select * from USERDETAILS");
				rs=ps.executeQuery();
				while(rs.next()){
				du = rs.getString(2);			
				dp = rs.getString(3);
				System.out.println("dp:"+dp);
				if(du.equals(u1) && dp.equals(passwd)){
					flag=1;
				}
				else{ }
				}
				if(flag==1){
					JOptionPane.showMessageDialog(content,"Login Successfull !");  //to show success msg
					mainPage m= new mainPage();
				}
				else{ 
					//if password or username goes wrong alert msg
					JOptionPane.showMessageDialog(content,"Incorrect Username or Password !!","Alert",JOptionPane.WARNING_MESSAGE);  					
				}

				con.close();
				ps.close();
			}

			catch(Exception ex){System.out.println(ex);}
			
		}
		else if(ae.getSource()==reg){
			Register r = new Register();//for first time user registration
		}
		else{}
		utext.setText("");
		passtext.setText("");
		
	}

//main start of the program.
	public static void main(String[] args) {
		railwayReservation r = new railwayReservation();
		

	}
 
}
//first time user have to register first 
class Register extends JFrame implements ActionListener {
	Connection con=null;
	PreparedStatement ps= null,ps1=null;
	JTextField ftext,ltext,adhartext,agetext,utext,passtext,confirmpasstext;
	JButton b;
	Container content2;
	Register(){
//to display the page contents
		content2 = this.getContentPane();
		JLabel reg= new JLabel("Welcome to Registration !! ");
		reg.setBounds(250,50,400,40);
		content2.add(reg);
		JLabel msg2= new JLabel("Register here to make Your Journey Easy and Safe!!");
		msg2.setBounds(200,100,400,40);
		content2.add(msg2);  
  
		JLabel firstname= new JLabel("First Name:");
		firstname.setBounds(200,200,100,40);
		content2.add(firstname);  
		ftext = new JTextField(20);
		ftext.setBounds(310,200,200,40);
		content2.add(ftext);

		JLabel lastname= new JLabel("Last Name:");
		lastname.setBounds(200,250,100,40);
		content2.add(lastname); 
		ltext = new JTextField(20);
		ltext.setBounds(310,250,200,40);
		content2.add(ltext);

		JLabel age= new JLabel("Age:");
		age.setBounds(200,300,100,40);
		content2.add(age);  
		agetext = new JTextField(20);
		agetext.setBounds(310,300,200,40);
		content2.add(agetext);

		JLabel adhar= new JLabel("Adhar Number:");
		adhar.setBounds(200,350,200,40);
		content2.add(adhar);  
		adhartext = new JTextField(20);
		adhartext.setBounds(310,350,200,40);
		content2.add(adhartext);

		JLabel msg3= new JLabel("SETUP YOUR USERNAME AND PASSWORD !!!");
		msg3.setBounds(200,400,400,40);
		content2.add(msg3);  
		
		JLabel uname= new JLabel("UserName:");
		uname.setBounds(200,450,200,40);
		content2.add(uname);
		utext = new JTextField(20);
		utext.setBounds(310,450,200,40);
		content2.add(utext);
		
		JLabel pass= new JLabel("Password:");
		pass.setBounds(200,500,200,40);
		content2.add(pass);
		passtext = new JTextField(20);
		passtext.setBounds(310,500,200,40);
		content2.add(passtext);
		
		JLabel confirmpass= new JLabel("Confirm Password:");
		confirmpass.setBounds(150,550,200,40);
		content2.add(confirmpass);
		confirmpasstext = new JTextField(20);
		confirmpasstext.setBounds(310,550,200,40);
		content2.add(confirmpasstext);
	

		b=new JButton("REGISTER");
		b.setBounds(250,640,200,40);
		content2.add(b);
		b.addActionListener(this);
		setSize(800,800);  
		content2.setLayout(null);  
		setVisible(true);  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}  
	public void actionPerformed(ActionEvent ae){
//user registration details are stored in database 
		String user=null,pswd=null,ln=null,fn=null,cp=null;
		int adno,age;
			fn=ftext.getText();
			ln=ltext.getText();
			age=Integer.parseInt(agetext.getText());
			adno=Integer.parseInt(adhartext.getText());
			user=utext.getText();
			pswd=passtext.getText();
			cp =confirmpasstext.getText();
	if(fn==null || ln==null ||user==null ||pswd==null ||cp==null){	
		JOptionPane.showMessageDialog(content2,"Please enter your details!!","Alert",JOptionPane.WARNING_MESSAGE); 
	}
	else{
		try{

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RAILWAY?useSSL=false","root","1504");
			if(con!=null){
			System.out.println("connection created");
			}
			if(pswd.equals(cp)){System.out.println("pass matches");
			}
			else{	JOptionPane.showMessageDialog(content2,"Password and Confirm Password does not match!!","Alert",JOptionPane.WARNING_MESSAGE); 
			}
			//to store passenger details contains information about passenger into passenger table
			ps=con.prepareStatement("insert into PASSENGER values(?,?,?,?)");
			ps.setInt(1,adno);
			ps.setString(2,fn);
			ps.setString(3,ln);
			ps.setInt(4,age);
			int i = ps.executeUpdate();
			if(i!=0){	
			ps1=con.prepareStatement("insert into USERDETAILS values(?,?,?) ");//to store userdetails like username & password in userdetails table
			ps1.setInt(1,adno);
			ps1.setString(2,user);
			ps1.setString(3,pswd);
			int j = ps1.executeUpdate();
			if(j!=0){	JOptionPane.showMessageDialog(content2,"Congrats !! Your Account has created!! You can login now !!");  
			}
			}
		}
			catch(Exception ex){System.out.println(ex);}

	}
		
	}
}
//mainpage invoked after login and used to display train timetable
class mainPage extends JFrame implements ActionListener{
	JTextField desjc ,srcjc;
	String usersrc="",userdes="";
	String[] data = new String[9];
	Connection con=null;
	PreparedStatement ps= null,ps1=null;
	ResultSet rs;
	Container content3;
	JButton book,cancel,search;
	JTable jt;
	DefaultTableModel model;
	public mainPage(){//to show the page contents
		content3 = this.getContentPane();
		setSize(900,900); 
	 
		JLabel jl= new JLabel("WELCOME TO RAILWAY RESERVATION !!");
		jl.setBounds(250,50,400,40);
		content3.add(jl);  
		JLabel msg1= new JLabel("MAKES YOUR JOURNEY EASY and SAFE !!");
		msg1.setBounds(250,100,400,40);
		content3.add(msg1);  
		JLabel msg2= new JLabel("BOOK YOUR TICKETS NOW !!");
		msg2.setBounds(270,150,400,40);
		content3.add(msg2);  
		JLabel srclbl= new JLabel("Source");
		srclbl.setBounds(150,200,200,40);
		content3.add(srclbl);  

		JLabel deslbl= new JLabel("Destination");
		deslbl.setBounds(370,200,300,40);
		content3.add(deslbl);  
		
		srcjc= new JTextField();
		srcjc.setBounds(100,230,180,40);
		content3.add(srcjc);
		desjc= new JTextField();
		desjc.setBounds(330,230,180,40);
		content3.add(desjc);
		search = new JButton("Search");
		search.setBounds(550,230,150,40);
		search.addActionListener(this);
		content3.add(search);
		String column[]={"TRAINID","TRAINNO","TRAIN TYPE","DESTINATION","DEPARTURE TIME","ARRIVAL TIME","AVAIL SEAT","SOURCE","PRICE"};
		
		model = new DefaultTableModel(column,1);
	 	jt = new JTable(model);
		JScrollPane scrol = new JScrollPane(jt);
		content3.add(scrol);
		scrol.setBounds(10,280,850,400);
		book = new JButton("Book");
		book.setBounds(300,720,100,50);
		cancel = new JButton("Cancel");
		cancel.setBounds(500,720,100,50);
		content3.add(book);
		content3.add(cancel);
		book.addActionListener(this);
		cancel.addActionListener(this);
		content3.setLayout(null);  
		setVisible(true);  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


	}

	public void actionPerformed(ActionEvent ae)
   	 {
		
		
		if(ae.getSource()==search){
			usersrc = srcjc.getText();
			userdes =desjc.getText();

		try{

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RAILWAY?useSSL=false","root","1504");
			if(con!=null){
			System.out.println("connection created");
			}
			//to fetch the source destination specified train details for ticket booking from train table
			ps=con.prepareStatement("SELECT * FROM TRAIN WHERE SOURCE=? AND DESTI=?");
			ps.setString(1,usersrc);
			ps.setString(2,userdes);
			rs=ps.executeQuery();
			while(rs.next()){
			data[0]=Integer.toString(rs.getInt(1));
			data[1]=rs.getString(2);
			data[2]=rs.getString(3);
			data[3]=rs.getString(4);
			data[4]=rs.getTime(5).toString();
			data[5]=rs.getTime(6).toString();
			data[6]=Integer.toString(rs.getInt(7));
			data[7]=rs.getString(8);
			data[8]=Double.toString(rs.getDouble(9));
			model.addRow(data);
			System.out.println(rs.getInt(1));
			}
		} catch(Exception e){System.out.println(e);}

		}
		System.out.println(usersrc + userdes);
		if(ae.getSource()==book){
			bookSeat bs= new bookSeat(data);//to book ticket
		}
		if(ae.getSource()==cancel){
			cancelSeat cs = new cancelSeat(data[0],data[6]);//to cancel ticket
		}


				
	 }
}
//booking seat
class bookSeat extends JFrame implements ActionListener{
	java.sql.Date date1;	
	DateFormat dateFormat; 
	Connection con=null;
	PreparedStatement ps= null;
	PreparedStatement ps1=null;
	Container content4;
	JTextField dtext,stext,ptext,t1text,t2text,fntext,lntext,datext,ttext;
	JButton register,showticket;
	int tid,as;
	String train,src,des,pr,time,date;
	public bookSeat(String info[]){//to show all page contents
		Random rand = new Random();
		//date1 = Calendar.getInstance().getTime();  
                dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
		date1 = new java.sql.Date(new java.util.Date().getTime());
             	date = dateFormat.format(date1);
		tid = Integer.parseInt(info[0]);
		train = info[1]; 
		src= info[7];
		des = info[3];
		pr= info[8];
		time = info[4];
		as = Integer.parseInt(info[6]);
		content4 = this.getContentPane();
		content4.setLayout(null); 
		JLabel msglbl = new JLabel("Welcome to Ticket Booking");
		msglbl.setBounds(300,50,400,40);
		JLabel t2 = new JLabel("TICKET NUMBER(ID) :");
		t2.setBounds(200,100,200,40);
		JLabel t1 = new JLabel("TRAIN NUMBER:");
		t1.setBounds(200,150,200,40);
		JLabel p = new JLabel("PRICE :");
		p.setBounds(200,200,200,40);
		JLabel fn = new JLabel("FNAME :");
		fn.setBounds(200,250,200,40);
		JLabel ln = new JLabel("LNAME :");
		ln.setBounds(200,300,200,40);
		JLabel  d = new JLabel("DESTINATION :");
		d.setBounds(200,350,200,40);
		JLabel s = new JLabel("SOURCE :");
		s.setBounds(200,400,200,40);
		JLabel da = new JLabel("DATE :");
		da.setBounds(200,450,200,40);
		JLabel t = new JLabel("TIME :");
		t.setBounds(200,500,200,40);

		t1text = new JTextField(train);
		t1text.setBounds(430,150,200,40);
		t2text = new JTextField(Integer.toString(rand.nextInt(1000)));
		t2text.setBounds(430,100,200,40);
		ptext = new JTextField(pr);
		ptext.setBounds(430,200,200,40);
		ttext = new JTextField(time);
		ttext.setBounds(430,500,200,40);
		stext = new JTextField(src);
		stext.setBounds(430,400,200,40);
		dtext = new JTextField(des);
		dtext.setBounds(430,350,200,40);
		datext = new JTextField(date);
		datext.setBounds(430,450,200,40);

		fntext = new JTextField(20);
		fntext.setBounds(430,250,200,40);
		lntext = new JTextField(20);
		lntext.setBounds(430,300,200,40);

		register = new JButton("Pay and Book");
		register.setBounds(200,580,200,40);
		content4.add(msglbl);
		content4.add(t1);
		content4.add(t1text);
		content4.add(s);
		content4.add(stext);
		content4.add(d);
		content4.add(dtext);
		content4.add(t);
		content4.add(ttext);
		content4.add(t2);
		content4.add(t2text);

		content4.add(fn);
		content4.add(fntext);
		content4.add(ln);
		content4.add(lntext);
		content4.add(da);
		content4.add(datext);
		content4.add(p);
		content4.add(ptext);



		content4.add(register);
		register.addActionListener(this);
		showticket= new JButton("Show Your Ticket");
		showticket.setBounds(450,580,200,40);
		content4.add(showticket);
		showticket.addActionListener(this);

		setSize(800,800);  
		setVisible(true);  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent ae){
		String u,p,e;
		int r,tickid;
		if(as!=0){
		if(ae.getSource()==register){
		try{
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RAILWAY?useSSL=false","root","1504");
			if(con!=null){
			System.out.println("connection created");
			}
//ticket details are stored in ticket table
			tickid=Integer.parseInt(t2text.getText());
			ps=con.prepareStatement("INSERT INTO TICKET VALUES(?,?,?,?,?,?,?,?)");
			ps.setInt(1,tickid);
			ps.setInt(2,tid);
			ps.setDouble(3,Double.parseDouble(ptext.getText()));
			ps.setDate(4,date1);
			ps.setString(5,fntext.getText());
			ps.setString(6,lntext.getText());
			ps.setString(7,dtext.getText());
			ps.setString(8,stext.getText());

			int i= ps.executeUpdate();
			System.out.println("Registration Done!! "+i+" rows inserted.");	
			if(i!=0){//after seat is booked the number of availble seat will be decreased in train table
				 JOptionPane.showMessageDialog(content4,"Ticket booked! Happy & safe Journey.."); 
				ps1=con.prepareStatement("UPDATE TRAIN SET SEAT =? WHERE TRAINID=?");
				int das = as-1;
				ps1.setInt(1,das);
				ps1.setInt(2,tickid);
				int j =ps1.executeUpdate();
				System.out.println("seat updated "+j+das+tickid);
			}
		con.close();
		ps.close();
		}
			catch(Exception ex){System.out.println(ex);
		}
		}
		}
		else{
				JOptionPane.showMessageDialog(content4,"Ticket not available!!","Alert",JOptionPane.WARNING_MESSAGE); 		
		}
		if(ae.getSource()==showticket){
			showTicket st = new showTicket(train,time);
		}
	}	
}

//for cancellation of ticket
class cancelSeat extends JFrame  implements ActionListener{
	Connection con=null;
	PreparedStatement ps= null;
	PreparedStatement ps1=null;
	JLabel regl;
	JTextField rtext;
	JButton delete;
	Container content5;
	int as=0,tnid;
	public cancelSeat(String train , String seat){//to display to page contains
		tnid=Integer.parseInt(seat);
		as=Integer.parseInt(seat);
		content5 = this.getContentPane();
		content5.setLayout(new FlowLayout()); 
		regl = new JLabel("Ticket Number(ID) :");
		rtext = new JTextField(20);
		delete = new JButton("Cancel Booking");
		content5.add(regl);
		content5.add(rtext);
		content5.add(delete);
		delete.addActionListener(this);
		setSize(300,300);  
		setVisible(true);  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent ae){
		int r;
		try{//for deleting the entry from ticket table 
			r=Integer.parseInt(rtext.getText());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RAILWAY?useSSL=false","root","1504");
			if(con!=null){
			System.out.println("connection created");
			}
			ps=con.prepareStatement("DELETE FROM TICKET WHERE TICKETID=?");
			ps.setInt(1,r);
			int i= ps.executeUpdate();
			if(i!=0){//after cancelling avaible seat have to increased as the seat is cancelled
				JOptionPane.showMessageDialog(content5,"Ticket cancelled !");
				System.out.println("Deletion Done!! "+i+" rows deleted.");
				ps1=con.prepareStatement("UPDATE TRAIN SET SEAT =? WHERE TRAINID=?");
				int ias = as+1;
				ps1.setInt(1,ias);
				ps1.setInt(2,tnid);
				int j =ps1.executeUpdate();
				System.out.println("seat updated "+j+ias+tnid);
	

			}
			else{
				JOptionPane.showMessageDialog(content5,"No match found!!","Alert",JOptionPane.WARNING_MESSAGE); 
			}
		con.close();
		ps.close();
		}
			catch(Exception ex){System.out.println(ex);
		}
		
	}	
}


//this is used to show ticket

class showTicket extends JFrame implements ActionListener{
	Connection con=null;
	PreparedStatement ps= null;
	ResultSet rs=null;
	JLabel regl;
	JTextField rtext,dtext,stext,ptext,t1text,t2text,fntext,lntext,datext,ttext;
	JButton search;
	Container content6;
	public showTicket(String tn,String tt){ //to display the page contents
		content6= this.getContentPane();
		content6.setLayout(null); 
		JLabel msglbl = new JLabel("Your Ticket Details");
		content6.add(msglbl);
		msglbl.setBounds(300,50,400,40);
		regl = new JLabel("Ticket Number :");
		regl.setBounds(200,100,200,40);
		rtext = new JTextField(20);
		rtext.setBounds(430,100,100,40);
		search = new JButton("Search");
		search.setBounds(300,150,100,40);
		content6.add(regl);
		content6.add(rtext);
		content6.add(search);
		

		JLabel t2 = new JLabel("TICKET NUMBER(ID) :");
		t2.setBounds(200,200,200,40);
		JLabel t1 = new JLabel("TRAIN NUMBER:");
		t1.setBounds(200,250,200,40);
		JLabel p = new JLabel("PRICE :");
		p.setBounds(200,300,200,40);
		JLabel fn = new JLabel("FNAME :");
		fn.setBounds(200,350,200,40);
		JLabel ln = new JLabel("LNAME :");
		ln.setBounds(200,400,200,40);
		JLabel  d = new JLabel("DESTINATION :");
		d.setBounds(200,450,200,40);
		JLabel s = new JLabel("SOURCE :");
		s.setBounds(200,500,200,40);
		JLabel da = new JLabel("DATE :");
		da.setBounds(200,550,200,40);
		JLabel t = new JLabel("TIME :");
		t.setBounds(200,600,200,40);

		
		t1text = new JTextField(tn);
		t1text.setBounds(430,250,200,40);
		t2text = new JTextField(20);
		t2text.setBounds(430,200,200,40);
		ptext = new JTextField(20);
		ptext.setBounds(430,300,200,40);
		ttext = new JTextField(tt);
		ttext.setBounds(430,600,200,40);
		stext = new JTextField(20);
		stext.setBounds(430,500,200,40);
		dtext = new JTextField(20);
		dtext.setBounds(430,450,200,40);
		datext = new JTextField(20);
		datext.setBounds(430,550,200,40);
		fntext = new JTextField(20);
		fntext.setBounds(430,350,200,40);
		lntext = new JTextField(20);
		lntext.setBounds(430,400,200,40);

		content6.add(t1);
		content6.add(t1text);
		content6.add(s);
		content6.add(stext);
		content6.add(d);
		content6.add(dtext);
		content6.add(t);
		content6.add(ttext);
		content6.add(t2);
		content6.add(t2text);

		content6.add(fn);
		content6.add(fntext);
		content6.add(ln);
		content6.add(lntext);
		content6.add(da);
		content6.add(datext);
		content6.add(p);
		content6.add(ptext);


		search.addActionListener(this);
		setSize(800,800);  
		setVisible(true);  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent ae){
		int r;
		try{//used to fetch ticket from ticket table 
			r=Integer.parseInt(rtext.getText());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RAILWAY?useSSL=false","root","1504");
			if(con!=null){
			System.out.println("connection created");
			}
			ps=con.prepareStatement("SELECT * FROM TICKET WHERE TICKETID=?");
			ps.setInt(1,r);
			rs= ps.executeQuery();
			if(rs!=null){
			while(rs.next()){

			t2text.setText(Integer.toString(rs.getInt(1)));
			ptext.setText(Double.toString(rs.getDouble(3)));
			datext.setText(rs.getDate(4).toString());
			fntext.setText(rs.getString(5));
			lntext.setText(rs.getString(6));
			stext.setText(rs.getString(7));
			dtext.setText(rs.getString(8));
			}
			}
			else{//to display error msg if ticket is not booked with specified number
				JOptionPane.showMessageDialog(content6,"No match found!!","Alert",JOptionPane.WARNING_MESSAGE); 
			}
		con.close();
		ps.close();
		}
			catch(Exception ex){System.out.println(ex);
		}
		
	}	
}


