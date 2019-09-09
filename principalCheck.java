package Pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import MetodosSql.MetodosSql;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;

public class principalCheck {

	private JFrame frame;
	private JTextField textFieldContrato;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JButton btnConsultar = new JButton("CONSULTAR");
	private JTable table;
	String queryUpdate="update site_option\r\n" + 
			"set option_value=option_value\r\n" + 
			"where site_no=(select site_no from site \r\n" + 
			"where udf4='CONTRATO')\r\n" + 
			"and option_id='MAS_VIDEO_USERNAME'";
	
	String querySmartCredentials="SELECT * FROM [SMART]..[ACTIVEMB].[SMPR_TVIDEO_CREDENTIALS] WITH (NOLOCK) \r\n" + 
			"where des_contract_number='CONTRATO'";
	String querySiteOptions="select obligatorios.option_id as Obligatorio,sio.option_id,sio.option_value,MAX(sy.cs_no) as CS_NO from site_option sio\r\n" + 
			"inner join system sy on sio.site_no=sy.site_no\r\n" + 
			"right join (select DISTINCT option_id from site_option\r\n" + 
			"where option_id like 'MAS_VIDEO%'\r\n" + 
			"and \r\n" + 
			"option_id='MAS_VIDEO_IP'\r\n" + 
			"or\r\n" + 
			"option_id='MAS_VIDEO_PASSWORD'\r\n" + 
			"or\r\n" + 
			"option_id='MAS_VIDEO_PORT'\r\n" + 
			"or\r\n" + 
			"option_id='MAS_VIDEO_PORT2'\r\n" + 
			"or\r\n" + 
			"option_id='MAS_VIDEO_TYPE'\r\n" + 
			"or\r\n" + 
			"option_id='MAS_VIDEO_USERNAME')obligatorios\r\n" + 
			"on obligatorios.option_id=sio.option_id\r\n" + 
			"and sio.site_no=(select site_no from site where udf4='CONTRATO')\r\n" + 
			"and \r\n" + 
			"(sio.option_id='MAS_VIDEO_IP'\r\n" + 
			"or\r\n" + 
			"sio.option_id='MAS_VIDEO_PASSWORD'\r\n" + 
			"or\r\n" + 
			"sio.option_id='MAS_VIDEO_PORT'\r\n" + 
			"or\r\n" + 
			"sio.option_id='MAS_VIDEO_PORT2'\r\n" + 
			"or\r\n" + 
			"sio.option_id='MAS_VIDEO_TYPE'\r\n" + 
			"or\r\n" + 
			"sio.option_id='MAS_VIDEO_USERNAME'\r\n" + 
			")\r\n" + 
			"group by obligatorios.option_id,sio.option_id,sio.option_value";
	
	
	private JTextField textFieldIpServer;
	private JTable table_1;
	private JTable table_2;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principalCheck window = new principalCheck();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public principalCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1105, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblContrato = new JLabel("CONTRATO");
		lblContrato.setBounds(10, 11, 91, 14);
		frame.getContentPane().add(lblContrato);
		
		textFieldContrato = new JTextField();
		textFieldContrato.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConsultar.doClick();
				}
			}
		});
		textFieldContrato.setBounds(10, 33, 176, 20);
		frame.getContentPane().add(textFieldContrato);
		textFieldContrato.setColumns(10);
		JLabel lblPais = new JLabel("PAIS (IP DEL SERVER)");
		lblPais.setBounds(10, 65, 176, 14);
		frame.getContentPane().add(lblPais);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 341, 231);
		
		frame.getContentPane().add(scrollPane);
		final JTextArea txtrJtextarealog = new JTextArea();
		scrollPane.setViewportView(txtrJtextarealog);
		
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConsultar.doClick();
				}
			}
		});
		
		textFieldUsuario.setBounds(217, 33, 149, 20);
		frame.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(217, 11, 91, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(217, 65, 91, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('X');
		passwordField.setToolTipText("");
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConsultar.doClick();
				}
			}
		});
		passwordField.setBounds(217, 85, 149, 20);
		frame.getContentPane().add(passwordField);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(383, 148, 679, 231);
		frame.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 412, 1052, 67);
		frame.getContentPane().add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 507, 1052, 48);
		frame.getContentPane().add(scrollPane_3);
		
		
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		table_1 = new JTable();
		scrollPane_2.setViewportView(table_1);
		
		table_2 = new JTable();
		scrollPane_3.setViewportView(table_2);
		
		
		btnConsultar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			btnConsultar.setEnabled(false);//Deshabilitar para que no le den click seguidos
			txtrJtextarealog.setText("");//Inicializar vacío
			int hayError=0;
			String contrato=textFieldContrato.getText();
			String pais=textFieldIpServer.getText();
			
			
			
			//contrato=	
			hayError=chequearCampoVacio(textFieldContrato.getText(),"Contrato",txtrJtextarealog);
			hayError=chequearCampoVacio(textFieldUsuario.getText(),"Usuario",txtrJtextarealog);
			hayError=chequearCampoVacio(passwordField.getText(),"Password",txtrJtextarealog);
			hayError=chequearCampoVacio(textFieldIpServer.getText(),"Ip del Servidor",txtrJtextarealog);
			
			if(hayError==0) {
				//comenzar verificacion
				txtrJtextarealog.append("Consultando contrato "+contrato+" de "+pais+"\n");
				txtrJtextarealog.append("Tenga paciencia, la consulta es internacional\n");
				
				MetodosSql metodos=new MetodosSql(pais,"monitordb", textFieldUsuario.getText(), passwordField.getText());
				
				try {
					
					table_1.setModel(metodos.llenarJtable("select * from smart_tmp",txtrJtextarealog).getModel());
					
					
					if(!contrato.equalsIgnoreCase("smart_tmp")) {
						txtrJtextarealog.append("Esto va a demorar unos 2 minutos...");
					table.setModel(metodos.llenarJtable(querySiteOptions.replace("CONTRATO", contrato),txtrJtextarealog).getModel());
					table_2.setModel(metodos.llenarJtable(querySmartCredentials.replace("CONTRATO", contrato),txtrJtextarealog).getModel());
					}
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
			}else {
				txtrJtextarealog.append("Corrija los errores descriptos y vuelva a intentar");
			}
			
			btnConsultar.setEnabled(true);//volver a habilitar el botón cuándo finalice-
			
			
			}
		});
		btnConsultar.setBounds(423, 33, 185, 46);
		frame.getContentPane().add(btnConsultar);
		
		textFieldIpServer = new JTextField();
		textFieldIpServer.setBounds(10, 85, 176, 20);
		frame.getContentPane().add(textFieldIpServer);
		textFieldIpServer.setColumns(10);
		
		
		
		
		JLabel lblTablaTemporalSmarttmp = new JLabel("Tabla temporal Smart_tmp");
		lblTablaTemporalSmarttmp.setBounds(10, 390, 164, 14);
		frame.getContentPane().add(lblTablaTemporalSmarttmp);
		
		
		
		
		JLabel lblTablaDelLado = new JLabel("Tabla del lado Smart (Tarda en cargar unos 2 minutos aprox)");
		lblTablaDelLado.setBounds(10, 482, 631, 14);
		frame.getContentPane().add(lblTablaDelLado);
		
		JLabel lblLogs = new JLabel("LOGS");
		lblLogs.setBounds(10, 116, 48, 14);
		frame.getContentPane().add(lblLogs);
		
		JLabel lblSiteoption = new JLabel("SITE_OPTION (NO DEBE HABER NULL, SI HAY , COMPLETAR DESDE MASTER)");
		lblSiteoption.setBounds(395, 116, 457, 14);
		frame.getContentPane().add(lblSiteoption);
		
		JLabel lblNewLabel = new JLabel("SI HAY DIFERENCIA ENTRE MASTER Y SMART, CAMBIAR USUARIO EN MASTER, ACEPTAR Y LUEGO PONERLO COMO ESTABA");
		lblNewLabel.setBounds(184, 390, 878, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("FORZAR UPDATE HACIA SMART");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				btnConsultar.setEnabled(false);//Deshabilitar para que no le den click seguidos
				txtrJtextarealog.setText("");//Inicializar vacío
				int hayError=0;
				String contrato=textFieldContrato.getText();
				String pais=textFieldIpServer.getText();
				
				
				
				//contrato=	
				hayError=chequearCampoVacio(textFieldContrato.getText(),"Contrato",txtrJtextarealog);
				hayError=chequearCampoVacio(textFieldUsuario.getText(),"Usuario",txtrJtextarealog);
				hayError=chequearCampoVacio(passwordField.getText(),"Password",txtrJtextarealog);
				hayError=chequearCampoVacio(textFieldIpServer.getText(),"Ip del Servidor",txtrJtextarealog);
				
				if(hayError==0) {
					//comenzar verificacion
					txtrJtextarealog.append("Forzando pasaje de contrato "+contrato+" de "+pais+"\n");
					txtrJtextarealog.append(queryUpdate.replace("CONTRATO", contrato));
					
					
					MetodosSql metodos=new MetodosSql(pais,"monitordb", textFieldUsuario.getText(), passwordField.getText());
					
					metodos.insertarOmodif(queryUpdate.replace("CONTRATO", contrato), txtrJtextarealog);
					txtrJtextarealog.append("Listo, espere a que el dato viaje a Smart desde smart_tmp\n");
				}else {
					txtrJtextarealog.append("Corrija los errores descriptos y vuelva a intentar");
				}
				
				btnConsultar.setEnabled(true);//volver a habilitar el botón cuándo finalice-
				
				
				
				//
				
			}
		});
		btnNewButton.setBounds(631, 33, 265, 46);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
	
		
	
		
	
	}
		
		
	protected int chequearCampoVacio(String campo,String nombreCampo, JTextArea txtrJtextarealog) {
		if(campo.isEmpty()) {
		txtrJtextarealog.append("No se permite campo vacío -> "+nombreCampo+"\n");	
		return 1;
		}else{
		return 0;
		}		
	}
}

