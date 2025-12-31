import java.util.regex.Pattern;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;

/**
 * Clase principal y única que gestiona todo la lógica de la TPV para el cobro de clientes y generación de tickets
 * @author Ariel Sempertegui
 *
 */
public class App {
	
	/**
	 * Declaración de Constantes
	 */
	private static final double TOTOPOS = 5.99;
	private static final double QUESADILLAS = 4.99;
	private static final double TLAYUDA = 5.59;
	private static final double ENCHILADAS = 11.95;
	private static final double POZOLE = 11.95;
	private static final double CHILAQUILES = 11.95;
	private static final double GASEOSA = 1.75;
	private static final double AGUAS_FRESCAS = 1.85;
	private static final double MARGARITA = 7.50;
	private static final double MICHELADA = 8.00;
	private static final double MARQUESITAS = 1.50;
	private static final double POLO = 1.50;
	private static final double CAFE_NESPRESSO = 1.90;
	private static final double CAFE_MACCHIATO = 1.40;
	private static final double CAFE_LATTE = 1.50;
	private static final double LONG_NUMERO_TARJETA = 16;
	private static final char MONEDA = '€';
	private static final String EXP_REG_IMPORTE = "^(\\d+|\\d+\\.|\\d+\\.\\d{1,2})$";
	private static final String EXP_REG_NUMTARJETA = "^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$";
	private static final String ASTERISCOS = "**************************************************\n";
	
	/**
	 * Declaración de Atributos
	 */
	private JFrame ventana;
	private JLabel lblTituloMenu;
	private JLabel lblTituloPago;
	private JTextField txtFImporte;
	private JPanel panelCarta;
	private JMenu menuCamarero;
	private JMenu menuCambiarFondo;
	private final ButtonGroup grupoEntrantes = new ButtonGroup();
	private final ButtonGroup grupoPlatoPrincipal = new ButtonGroup();
	private final ButtonGroup grupoBebidas = new ButtonGroup();
	private final ButtonGroup grupoPostre = new ButtonGroup();
	private final ButtonGroup grupoFormaDePago = new ButtonGroup();
	private final ButtonGroup grupoCamareros = new ButtonGroup();
	private JRadioButtonMenuItem rdbtnCamarero1;
	private JRadioButtonMenuItem rdbtnCamarero2;
	private JRadioButtonMenuItem rdbtnCamarero3;
	private JRadioButton rdbtnTotopos;
	private JRadioButton rdbtnQuesadillas;
	private JRadioButton rdbtnTlayuda;
	private JRadioButton rdbtnEnchiladas;
	private JRadioButton rdbtnPozole;
	private JRadioButton rdbtnChilaquiles;
	private JRadioButton rdbtnGaseosa;
	private JRadioButton rdbtnAguasFrescas;
	private JRadioButton rdbtnMargarita;
	private JRadioButton rdbtnMichelada;
	private JRadioButton rdbtnMarquesitas;
	private JRadioButton rdbtnPolo;
	private JRadioButton rdbtnEfectivo;
	private JRadioButton rdbtnTarjeta;
	private JCheckBoxMenuItem chBoxFondoBasico;
	private JCheckBox chBoxCafe;
	private JComboBox<?> cBoxCafe;
	private JLabel lblPrecioTotalEfectivo;
	private JLabel lblPrecioTotalTarjeta;
	private JButton btnPagarEfectivo;
	private JButton btnPagarTarjeta;
	private double precioTotal = 20.19;
	private JFormattedTextField formattedTxtFNumTarjeta;
	private JTextArea txtATicket;
	private JButton btnNuevoPedido;
	private JButton btnBorrarPedido;
	private JButton btnSalir;
	

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public App() throws ParseException {
		initialize();
		generarTicket();
	}
	
	/**
	 * Este método devuelve el camarero seleccionado
	 * @return
	 */
	private String mostrarCamarero() {
		
		if(rdbtnCamarero1.isSelected()) {
			return rdbtnCamarero1.getText();
		}
		else if(rdbtnCamarero2.isSelected()) {
			return rdbtnCamarero2.getText();
		}
		else {
			return rdbtnCamarero3.getText();
		}
	
	}
	
	/**
	 * Este metodo devuelve un string que es la fecha y hora
	 * @return
	 */
	private static String actualizarFechaYHora() {
		
		LocalDateTime fechaYhora = LocalDateTime.now();
		DateTimeFormatter formato_fechaYhora = DateTimeFormatter.ofPattern("HH:mm  dd-MM-yyyy");
		String formatted_fechaYhora = fechaYhora.format(formato_fechaYhora);

		return formatted_fechaYhora;
	}
	
	/**
	 * Este método genera automaticamente el ticket para poder mostrarlo en el Ticket
	 */
	private void generarTicket() {
		
		txtATicket.setText("");
		String fechaYhora = actualizarFechaYHora();
		String camarero = mostrarCamarero();
		String caratulaTicket = "                   RESTAURANTE EL HUEY\n"
							  + ASTERISCOS
							  + "Av/ dels Tamarindes, 39\n"
							  + "46035 València\n"
							  + "NIF: A-4610384\n"
							  + "TEL.: 936753441                    "+fechaYhora+"\n"
							  + ASTERISCOS
							  + "Camarero: "+camarero+"\n"
							  + ASTERISCOS
							  + "_Descripcion________________________\n";

		txtATicket.append(caratulaTicket);
		
		if(rdbtnTotopos.isSelected()) {
			txtATicket.append("1 "+rdbtnTotopos.getText()+"\n");
		}
		else if(rdbtnQuesadillas.isSelected()) {
			txtATicket.append("1 "+rdbtnQuesadillas.getText()+"\n");
		}
		else if(rdbtnTlayuda.isSelected()) {
			txtATicket.append("1 "+rdbtnTlayuda.getText()+"\n");
		}
		
		
		if(rdbtnEnchiladas.isSelected()) {
			txtATicket.append("1 "+rdbtnEnchiladas.getText()+"\n");

		}
		else if(rdbtnPozole.isSelected()) {
			txtATicket.append("1 "+rdbtnPozole.getText()+"\n");

		}
		else if(rdbtnChilaquiles.isSelected()) {
			txtATicket.append("1 "+rdbtnChilaquiles.getText()+"\n");

		}
		
		
		if(rdbtnGaseosa.isSelected()) {
			txtATicket.append("1 "+rdbtnGaseosa.getText()+"\n");

		}
		else if(rdbtnAguasFrescas.isSelected()) {
			txtATicket.append("1 "+rdbtnAguasFrescas.getText()+"\n");

		}
		else if(rdbtnMargarita.isSelected()) {
			txtATicket.append("1 "+rdbtnMargarita.getText()+"\n");

		}
		else if(rdbtnMichelada.isSelected()) {
			txtATicket.append("1 "+rdbtnMichelada.getText()+"\n");

		}
		
		
		if(rdbtnMarquesitas.isSelected()) {
			txtATicket.append("1 "+rdbtnMarquesitas.getText()+"\n");

		}
		else if(rdbtnPolo.isSelected()) {
			txtATicket.append("1 "+rdbtnPolo.getText()+"\n");

		}
		
		if(chBoxCafe.isSelected()) {
			switch(cBoxCafe.getSelectedIndex()) {
				case 0, 1, 2 -> txtATicket.append("1 Café "+cBoxCafe.getSelectedItem().toString()+"\n");
			}
		}
		
		
	}
	
	/**
	 * Este método muestra el precio total en el apartado "PAGO"
	 * @param precioTotal
	 */
	private void mostrarPrecio(double precioTotal) {
		
		lblPrecioTotalEfectivo.setText(String.format("%.2f €", precioTotal));
		lblPrecioTotalTarjeta.setText(String.format("%.2f €", precioTotal));
	}
	
	/**
	 * Este método calcula el precio total
	 */
	private void calcularPrecioTotal() {
		
		precioTotal = 0;
		
		//grupoEntrantes
		if(rdbtnTotopos.isSelected()) {
			precioTotal += TOTOPOS;
		}
		else if(rdbtnQuesadillas.isSelected()) {
				precioTotal += QUESADILLAS;
		}
		else if(rdbtnTlayuda.isSelected()) {
			precioTotal += TLAYUDA;
		}
		
		//grupoPlatoPrincipal
		if(rdbtnEnchiladas.isSelected()) {
			precioTotal += ENCHILADAS;
		}
		else if(rdbtnPozole.isSelected()) {
				precioTotal += POZOLE;
		}
		else if(rdbtnChilaquiles.isSelected()) {
			precioTotal += CHILAQUILES;
		}
		
		//grupoBebidas
		if(rdbtnGaseosa.isSelected()) {
			precioTotal += GASEOSA;
		}
		else if(rdbtnAguasFrescas.isSelected()) {
				precioTotal += AGUAS_FRESCAS;
		}
		else if(rdbtnMargarita.isSelected()) {
			precioTotal += MARGARITA;
		}
		else if(rdbtnMichelada.isSelected()) {
			precioTotal += MICHELADA;
		}
		
		//grupoPostre
		if(rdbtnMarquesitas.isSelected()) {
			precioTotal += MARQUESITAS;
		}
		else if(rdbtnPolo.isSelected()) {
				precioTotal += POLO;
		}
		
		//cafe
		if(chBoxCafe.isSelected()) {
			switch(cBoxCafe.getSelectedIndex()) {
				case 0: precioTotal += CAFE_NESPRESSO; break;
				case 1: precioTotal += CAFE_MACCHIATO; break;
				case 2: precioTotal += CAFE_LATTE;
			}
		}
			
	}
	
	/**
	 * Este método sirve para cambiar el color
	 */
	private void cambiarColorFondo() {
		
		if(chBoxFondoBasico.isSelected()) {
			
			panelCarta.setBackground(Color.WHITE);
			rdbtnTotopos.setBackground(Color.WHITE);
			rdbtnQuesadillas.setBackground(Color.WHITE);
			rdbtnTlayuda.setBackground(Color.WHITE);
			rdbtnEnchiladas.setBackground(Color.WHITE);
			rdbtnPozole.setBackground(Color.WHITE);
			rdbtnChilaquiles.setBackground(Color.WHITE);
			rdbtnGaseosa.setBackground(Color.WHITE);
			rdbtnAguasFrescas.setBackground(Color.WHITE);
			rdbtnMargarita.setBackground(Color.WHITE);
			rdbtnMichelada.setBackground(Color.WHITE);
			rdbtnMarquesitas.setBackground(Color.WHITE);
			rdbtnPolo.setBackground(Color.WHITE);
			chBoxCafe.setBackground(Color.WHITE);
			rdbtnEfectivo.setBackground(Color.WHITE);
			rdbtnTarjeta.setBackground(Color.WHITE);
			
		}
		else {
			
			panelCarta.setBackground(new Color(236,106,83));
			panelCarta.setBackground(new Color(236,106,83));
			rdbtnTotopos.setBackground(new Color(236,106,83));
			rdbtnQuesadillas.setBackground(new Color(236,106,83));
			rdbtnTlayuda.setBackground(new Color(236,106,83));
			rdbtnEnchiladas.setBackground(new Color(236,106,83));
			rdbtnPozole.setBackground(new Color(236,106,83));
			rdbtnChilaquiles.setBackground(new Color(236,106,83));
			rdbtnGaseosa.setBackground(new Color(236,106,83));
			rdbtnAguasFrescas.setBackground(new Color(236,106,83));
			rdbtnMargarita.setBackground(new Color(236,106,83));
			rdbtnMichelada.setBackground(new Color(236,106,83));
			rdbtnMarquesitas.setBackground(new Color(236,106,83));
			rdbtnPolo.setBackground(new Color(236,106,83));
			chBoxCafe.setBackground(new Color(236,106,83));
			rdbtnEfectivo.setBackground(new Color(236,106,83));
			rdbtnTarjeta.setBackground(new Color(236,106,83));
			
		}
	}
	/**
	 * Este método habilita y deshabilita los radio buttons de Efectivo y Tarjeta en función de la opción elegida 
	 */
	private void cambiarFormaDePago(){
		
		if(rdbtnEfectivo.isSelected()) {
			
			lblPrecioTotalEfectivo.setVisible(true);
			txtFImporte.setEnabled(true);
			txtFImporte.setEditable(true);
			
			lblPrecioTotalTarjeta.setVisible(false);
			formattedTxtFNumTarjeta.setEnabled(false);
			formattedTxtFNumTarjeta.setEditable(false);
			formattedTxtFNumTarjeta.setText("");
			btnPagarTarjeta.setEnabled(false);
		}
		
		if(rdbtnTarjeta.isSelected()) {
			
			lblPrecioTotalTarjeta.setVisible(true);
			formattedTxtFNumTarjeta.setEnabled(true);
			formattedTxtFNumTarjeta.setEditable(true);
			
			lblPrecioTotalEfectivo.setVisible(false);
			txtFImporte.setEnabled(false);
			txtFImporte.setEditable(false);
			txtFImporte.setText("");
			btnPagarEfectivo.setEnabled(false);
		
		}
	}
	
	/**
	 * Este método deshabilita el Panel Principal
	 */
	private void deshabilitarPanelCarta() {
		
		menuCamarero.setEnabled(false);
		menuCambiarFondo.setEnabled(false);
		rdbtnTotopos.setEnabled(false);
		rdbtnQuesadillas.setEnabled(false);
		rdbtnTlayuda.setEnabled(false);
		rdbtnEnchiladas.setEnabled(false);
		rdbtnPozole.setEnabled(false);
		rdbtnChilaquiles.setEnabled(false);
		rdbtnGaseosa.setEnabled(false);
		rdbtnAguasFrescas.setEnabled(false);
		rdbtnMargarita.setEnabled(false);
		rdbtnMichelada.setEnabled(false);
		rdbtnMarquesitas.setEnabled(false);
		rdbtnPolo.setEnabled(false);
		chBoxCafe.setEnabled(false);
		cBoxCafe.setEnabled(false);
		rdbtnEfectivo.setEnabled(false);
		txtFImporte.setEnabled(false);
		txtFImporte.setText("");
		btnPagarEfectivo.setEnabled(false);
		rdbtnTarjeta.setEnabled(false);
		formattedTxtFNumTarjeta.setEnabled(false);
		formattedTxtFNumTarjeta.setText("");
		btnPagarTarjeta.setEnabled(false);
		
	}
	
	/**
	 * Este método habilita el Panel Principal
	 */
	private void habilitarPanelCarta() {
		
		menuCamarero.setEnabled(true);
		menuCambiarFondo.setEnabled(true);
		rdbtnTotopos.setEnabled(true);
		rdbtnQuesadillas.setEnabled(true);
		rdbtnTlayuda.setEnabled(true);
		rdbtnEnchiladas.setEnabled(true);
		rdbtnPozole.setEnabled(true);
		rdbtnChilaquiles.setEnabled(true);
		rdbtnGaseosa.setEnabled(true);
		rdbtnAguasFrescas.setEnabled(true);
		rdbtnMargarita.setEnabled(true);
		rdbtnMichelada.setEnabled(true);
		rdbtnMarquesitas.setEnabled(true);
		rdbtnPolo.setEnabled(true);
		chBoxCafe.setEnabled(true);
		cBoxCafe.setEnabled(false);
		rdbtnEfectivo.setEnabled(true);
		txtFImporte.setEnabled(true);
		txtFImporte.setText("");
		btnPagarEfectivo.setEnabled(false);
		rdbtnTarjeta.setEnabled(true);
		formattedTxtFNumTarjeta.setEnabled(false);
		formattedTxtFNumTarjeta.setText("");
		btnPagarTarjeta.setEnabled(false);
		
		rdbtnQuesadillas.setSelected(true);
		rdbtnEnchiladas.setSelected(true);
		rdbtnGaseosa.setSelected(true);
		rdbtnPolo.setSelected(true);
		chBoxCafe.setSelected(false);
		rdbtnEfectivo.setSelected(true);
		
	}
	
	/**
	 * Este método cuando pulsa el boton borrar(volver al estado inicial en este caso) hace que toda la aplicación se reinicie
	 */
	private void volverAlEstadoInicial() {
		
		habilitarPanelCarta();
		rdbtnQuesadillas.setSelected(true);
		rdbtnEnchiladas.setSelected(true);
		rdbtnGaseosa.setSelected(true);
		rdbtnPolo.setSelected(true);
		chBoxCafe.setSelected(false);
		rdbtnEfectivo.setSelected(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() throws ParseException {
		
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.setTitle("Restaurante El Huey");
		ventana.setBounds(100, 100, 800, 900);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon imgCheck = new ImageIcon("img/check.png"); //Imagen para el JOpionPane de confirmacion
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(4);
		splitPane.setEnabled(false);
		ventana.getContentPane().add(splitPane, BorderLayout.CENTER);

		panelCarta = new JPanel();
		splitPane.setLeftComponent(panelCarta);
		panelCarta.setBackground(new Color(236, 106, 83));
		panelCarta.setLayout(null);
		splitPane.setDividerLocation(500);
		
		JMenuBar barraDeMenu = new JMenuBar();
		barraDeMenu.setBounds(0, 0, 499, 26);
		panelCarta.add(barraDeMenu);
		
		menuCamarero = new JMenu("Camarero");
		barraDeMenu.add(menuCamarero);
		
		rdbtnCamarero1 = new JRadioButtonMenuItem("Alex");
		rdbtnCamarero1.setSelected(true);
		menuCamarero.add(rdbtnCamarero1);
		grupoCamareros.add(rdbtnCamarero1);
		
		rdbtnCamarero2 = new JRadioButtonMenuItem("Sergio");
		menuCamarero.add(rdbtnCamarero2);
		grupoCamareros.add(rdbtnCamarero2);
		
		rdbtnCamarero3 = new JRadioButtonMenuItem("Leticia");
		menuCamarero.add(rdbtnCamarero3);
		grupoCamareros.add(rdbtnCamarero3);
		
		menuCambiarFondo = new JMenu("Cambiar fondo");
		barraDeMenu.add(menuCambiarFondo);
		
		chBoxFondoBasico = new JCheckBoxMenuItem("Fondo Básico");
		menuCambiarFondo.add(chBoxFondoBasico);
		
		lblTituloMenu = new JLabel();
		lblTituloMenu.setOpaque(true);
		lblTituloMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloMenu.setText("MENÚ");
		lblTituloMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloMenu.setForeground(Color.WHITE);
		lblTituloMenu.setBackground(Color.BLACK);
		lblTituloMenu.setBounds(0, 26, 499, 47);
		panelCarta.add(lblTituloMenu);
		
		JLabel lblEntrantes = new JLabel("Entrantes");
		lblEntrantes.setForeground(Color.BLACK);
		lblEntrantes.setFont(new Font("DejaVu Serif", Font.ITALIC, 17));
		lblEntrantes.setHorizontalAlignment(SwingConstants.LEFT);
		lblEntrantes.setBounds(23, 98, 144, 26);
		panelCarta.add(lblEntrantes);
		
		JLabel lblPlatoPrincipal = new JLabel("Plato Principal");
		lblPlatoPrincipal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlatoPrincipal.setForeground(Color.BLACK);
		lblPlatoPrincipal.setFont(new Font("DejaVu Serif", Font.ITALIC, 17));
		lblPlatoPrincipal.setBounds(298, 98, 144, 26);
		panelCarta.add(lblPlatoPrincipal);
		
		JLabel lblBebidas = new JLabel("Bebidas");
		lblBebidas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBebidas.setForeground(Color.BLACK);
		lblBebidas.setFont(new Font("DejaVu Serif", Font.ITALIC, 17));
		lblBebidas.setBounds(23, 273, 144, 26);
		panelCarta.add(lblBebidas);
		
		JLabel lblPostre = new JLabel("Postre");
		lblPostre.setHorizontalAlignment(SwingConstants.LEFT);
		lblPostre.setForeground(Color.BLACK);
		lblPostre.setFont(new Font("DejaVu Serif", Font.ITALIC, 17));
		lblPostre.setBounds(298, 273, 144, 26);
		panelCarta.add(lblPostre);
		
		chBoxCafe = new JCheckBox("Café");
		chBoxCafe.setForeground(Color.BLACK);
		chBoxCafe.setBackground(new Color(236, 106, 83));
		chBoxCafe.setFont(new Font("DejaVu Serif", Font.ITALIC, 17));
		chBoxCafe.setBounds(23, 492, 71, 26);
		panelCarta.add(chBoxCafe);
		
		cBoxCafe = new JComboBox();
		cBoxCafe.setEnabled(false);
		cBoxCafe.setForeground(Color.BLACK);
		cBoxCafe.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cBoxCafe.setModel(new DefaultComboBoxModel(new String[] {
				String.format("Nespresso %.2f%c", CAFE_NESPRESSO, MONEDA), 
				String.format("Macchiato %.2f%c", CAFE_MACCHIATO, MONEDA), 
				String.format("Latte %.2f%c", CAFE_LATTE, MONEDA)}));
		cBoxCafe.setSelectedIndex(0);
		cBoxCafe.setBounds(100, 492, 127, 26);
		panelCarta.add(cBoxCafe);
		
		lblPrecioTotalEfectivo = new JLabel(precioTotal+" €");
		lblPrecioTotalEfectivo.setForeground(new Color(0, 255, 0));
		lblPrecioTotalEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioTotalEfectivo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPrecioTotalEfectivo.setBounds(127, 652, 173, 23);
		panelCarta.add(lblPrecioTotalEfectivo);
		
		rdbtnTotopos = new JRadioButton(String.format("Totopos %.2f%c", TOTOPOS, MONEDA));
		rdbtnTotopos.setForeground(Color.BLACK);
		rdbtnTotopos.setBackground(new Color(236, 106, 83));
		rdbtnTotopos.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnTotopos.setBounds(46, 142, 161, 23);
		panelCarta.add(rdbtnTotopos);
		grupoEntrantes.add(rdbtnTotopos);
		
		rdbtnQuesadillas = new JRadioButton(String.format("Quesadillas %.2f%c", QUESADILLAS, MONEDA));
		rdbtnQuesadillas.setSelected(true);
		rdbtnQuesadillas.setForeground(Color.BLACK);
		rdbtnQuesadillas.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnQuesadillas.setBackground(new Color(236, 106, 83));
		rdbtnQuesadillas.setBounds(46, 179, 161, 23);
		panelCarta.add(rdbtnQuesadillas);
		grupoEntrantes.add(rdbtnQuesadillas);
		
		rdbtnTlayuda = new JRadioButton(String.format("Tlayuda %.2f%c", TLAYUDA, MONEDA));
		rdbtnTlayuda.setForeground(Color.BLACK);
		rdbtnTlayuda.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnTlayuda.setBackground(new Color(236, 106, 83));
		rdbtnTlayuda.setBounds(46, 218, 161, 23);
		panelCarta.add(rdbtnTlayuda);
		grupoEntrantes.add(rdbtnTlayuda);
		
		rdbtnEnchiladas = new JRadioButton(String.format("Enchiladas %.2f%c", ENCHILADAS, MONEDA));
		rdbtnEnchiladas.setSelected(true);
		rdbtnEnchiladas.setForeground(Color.BLACK);
		rdbtnEnchiladas.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnEnchiladas.setBackground(new Color(236, 106, 83));
		rdbtnEnchiladas.setBounds(319, 142, 161, 23);
		panelCarta.add(rdbtnEnchiladas);
		grupoPlatoPrincipal.add(rdbtnEnchiladas);
		
		rdbtnPozole = new JRadioButton(String.format("Pozole %.2f%c", POZOLE, MONEDA));
		rdbtnPozole.setForeground(Color.BLACK);
		rdbtnPozole.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnPozole.setBackground(new Color(236, 106, 83));
		rdbtnPozole.setBounds(319, 179, 161, 23);
		panelCarta.add(rdbtnPozole);
		grupoPlatoPrincipal.add(rdbtnPozole);
		
		rdbtnChilaquiles = new JRadioButton(String.format("Chilaquiles %.2f%c", CHILAQUILES, MONEDA));
		rdbtnChilaquiles.setForeground(Color.BLACK);
		rdbtnChilaquiles.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnChilaquiles.setBackground(new Color(236, 106, 83));
		rdbtnChilaquiles.setBounds(319, 218, 161, 23);
		panelCarta.add(rdbtnChilaquiles);
		grupoPlatoPrincipal.add(rdbtnChilaquiles);
		
		rdbtnGaseosa = new JRadioButton(String.format("Gaseosa %.2f%c", GASEOSA, MONEDA));
		rdbtnGaseosa.setSelected(true);
		rdbtnGaseosa.setForeground(Color.BLACK);
		rdbtnGaseosa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnGaseosa.setBackground(new Color(236, 106, 83));
		rdbtnGaseosa.setBounds(46, 318, 161, 23);
		panelCarta.add(rdbtnGaseosa);
		grupoBebidas.add(rdbtnGaseosa);
		
		rdbtnAguasFrescas = new JRadioButton(String.format("Aguas Frescas %.2f%c", AGUAS_FRESCAS, MONEDA));
		rdbtnAguasFrescas.setForeground(Color.BLACK);
		rdbtnAguasFrescas.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnAguasFrescas.setBackground(new Color(236, 106, 83));
		rdbtnAguasFrescas.setBounds(46, 355, 161, 23);
		panelCarta.add(rdbtnAguasFrescas);
		grupoBebidas.add(rdbtnAguasFrescas);
		
		rdbtnMargarita = new JRadioButton(String.format("Margarita %.2f%c", MARGARITA, MONEDA));
		rdbtnMargarita.setForeground(Color.BLACK);
		rdbtnMargarita.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnMargarita.setBackground(new Color(236, 106, 83));
		rdbtnMargarita.setBounds(46, 391, 161, 23);
		panelCarta.add(rdbtnMargarita);
		grupoBebidas.add(rdbtnMargarita);
		
		rdbtnMichelada = new JRadioButton(String.format("Michelada %.2f%c", MICHELADA, MONEDA));
		rdbtnMichelada.setForeground(Color.BLACK);
		rdbtnMichelada.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnMichelada.setBackground(new Color(236, 106, 83));
		rdbtnMichelada.setBounds(46, 426, 161, 23);
		panelCarta.add(rdbtnMichelada);
		grupoBebidas.add(rdbtnMichelada);
		
		rdbtnMarquesitas = new JRadioButton(String.format("Marquesitas %.2f%c", MARQUESITAS, MONEDA));
		rdbtnMarquesitas.setForeground(Color.BLACK);
		rdbtnMarquesitas.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnMarquesitas.setBackground(new Color(236, 106, 83));
		rdbtnMarquesitas.setBounds(319, 318, 161, 23);
		panelCarta.add(rdbtnMarquesitas);
		grupoPostre.add(rdbtnMarquesitas);
		
		rdbtnPolo = new JRadioButton(String.format("Polo %.2f%c", POLO, MONEDA));
		rdbtnPolo.setSelected(true);
		rdbtnPolo.setForeground(Color.BLACK);
		rdbtnPolo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnPolo.setBackground(new Color(236, 106, 83));
		rdbtnPolo.setBounds(319, 355, 161, 23);
		panelCarta.add(rdbtnPolo);
		grupoPostre.add(rdbtnPolo);
		
		lblTituloPago = new JLabel();
		lblTituloPago.setOpaque(true);
		lblTituloPago.setText("PAGO");
		lblTituloPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPago.setForeground(Color.WHITE);
		lblTituloPago.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloPago.setBackground(Color.BLACK);
		lblTituloPago.setBounds(0, 557, 499, 47);
		panelCarta.add(lblTituloPago);
		
		rdbtnEfectivo = new JRadioButton("Efectivo");
		rdbtnEfectivo.setSelected(true);
		rdbtnEfectivo.setForeground(Color.BLACK);
		rdbtnEfectivo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rdbtnEfectivo.setBackground(new Color(236, 106, 83));
		rdbtnEfectivo.setBounds(48, 622, 84, 23);
		panelCarta.add(rdbtnEfectivo);
		grupoFormaDePago.add(rdbtnEfectivo);
		
		rdbtnTarjeta = new JRadioButton("Tarjeta");
		rdbtnTarjeta.setForeground(Color.BLACK);
		rdbtnTarjeta.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rdbtnTarjeta.setBackground(new Color(236, 106, 83));
		rdbtnTarjeta.setBounds(48, 732, 84, 23);
		panelCarta.add(rdbtnTarjeta);
		grupoFormaDePago.add(rdbtnTarjeta);
		
		JLabel lblPrecioEfectivo = new JLabel("Precio:");
		lblPrecioEfectivo.setForeground(Color.BLACK);
		lblPrecioEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioEfectivo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPrecioEfectivo.setBounds(58, 652, 62, 23);
		panelCarta.add(lblPrecioEfectivo);
		
		JLabel lblImporte = new JLabel("Importe:");
		lblImporte.setForeground(Color.BLACK);
		lblImporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImporte.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblImporte.setBounds(58, 686, 62, 23);
		panelCarta.add(lblImporte);
		
		txtFImporte = new JTextField();
		txtFImporte.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtFImporte.setForeground(new Color(0, 0, 255));
		txtFImporte.setBounds(127, 686, 62, 23);
		panelCarta.add(txtFImporte);
		txtFImporte.setColumns(10);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setForeground(Color.BLACK);
		lblEuro.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblEuro.setBounds(199, 686, 25, 23);
		panelCarta.add(lblEuro);
		
		JLabel lblPrecioTarjeta = new JLabel("Precio:");
		lblPrecioTarjeta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioTarjeta.setForeground(Color.BLACK);
		lblPrecioTarjeta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPrecioTarjeta.setBounds(58, 762, 62, 23);
		panelCarta.add(lblPrecioTarjeta);
		
		lblPrecioTotalTarjeta = new JLabel(precioTotal+" €");
		lblPrecioTotalTarjeta.setVisible(false);
		lblPrecioTotalTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioTotalTarjeta.setForeground(new Color(0, 255, 0));
		lblPrecioTotalTarjeta.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPrecioTotalTarjeta.setBounds(127, 762, 173, 23);
		panelCarta.add(lblPrecioTotalTarjeta);
		
		JLabel lblNumTarjeta = new JLabel("N.º de la Tarjeta:");
		lblNumTarjeta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumTarjeta.setForeground(Color.BLACK);
		lblNumTarjeta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNumTarjeta.setBounds(10, 796, 110, 23);
		panelCarta.add(lblNumTarjeta);
		
		formattedTxtFNumTarjeta = new JFormattedTextField(new MaskFormatter("#### #### #### ####"));
		formattedTxtFNumTarjeta.setText("");
		formattedTxtFNumTarjeta.setEditable(false);
		formattedTxtFNumTarjeta.setEnabled(false);
		formattedTxtFNumTarjeta.setForeground(Color.BLACK);
		formattedTxtFNumTarjeta.setFont(new Font("Cambria", Font.PLAIN, 15));
		formattedTxtFNumTarjeta.setBounds(127, 796, 196, 23);
		panelCarta.add(formattedTxtFNumTarjeta);
		
		btnPagarEfectivo = new JButton("Pagar");
		btnPagarEfectivo.setEnabled(false);
		btnPagarEfectivo.setForeground(Color.BLACK);
		btnPagarEfectivo.setBounds(223, 687, 77, 23);
		panelCarta.add(btnPagarEfectivo);
		
		btnPagarTarjeta = new JButton("Pagar");
		btnPagarTarjeta.setEnabled(false);
		btnPagarTarjeta.setBounds(338, 797, 77, 23);
		panelCarta.add(btnPagarTarjeta);
		
		JPanel panelTicket = new JPanel();
		panelTicket.setBackground(new Color(117, 236, 230));
		splitPane.setRightComponent(panelTicket);
		panelTicket.setLayout(null);
		
		JScrollPane scrollPaneTxtAreaTicket = new JScrollPane();
		scrollPaneTxtAreaTicket.setBounds(10, 150, 259, 343);
		panelTicket.add(scrollPaneTxtAreaTicket);
		
		txtATicket = new JTextArea();
		txtATicket.setEditable(false);
		scrollPaneTxtAreaTicket.setViewportView(txtATicket);
		txtATicket.setLineWrap(true);
		
		JLabel lblTicket = new JLabel("T i c k e t");
		lblTicket.setFont(new Font("Carlito", Font.BOLD, 26));
		lblTicket.setHorizontalAlignment(SwingConstants.CENTER);
		lblTicket.setBounds(81, 99, 116, 33);
		panelTicket.add(lblTicket);
		
		JLabel lblGorro = new JLabel("");
		lblGorro.setBounds(25, 11, 229, 164);
		ImageIcon imgGorro = new ImageIcon("img/gorro.png");
		Icon icoGorro = new ImageIcon(imgGorro.getImage().getScaledInstance(lblGorro.getWidth(), lblGorro.getHeight(), Image.SCALE_SMOOTH));
		lblGorro.setIcon(icoGorro);
		panelTicket.add(lblGorro);
		
		JLabel lblNube = new JLabel("");
		lblNube.setBounds(40, 710, 199, 119);
		ImageIcon imgNube = new ImageIcon("img/nube.png");
		Icon icoNube = new ImageIcon(imgNube.getImage().getScaledInstance(lblNube.getWidth(), lblNube.getHeight(), Image.SCALE_SMOOTH));
		lblNube.setIcon(icoNube);
		panelTicket.add(lblNube);
		
		btnNuevoPedido = new JButton("Nuevo pedido");
		btnNuevoPedido.setEnabled(false);
		btnNuevoPedido.setForeground(Color.BLACK);
		btnNuevoPedido.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNuevoPedido.setBounds(68, 526, 143, 43);
		panelTicket.add(btnNuevoPedido);
		
		btnBorrarPedido = new JButton("Borrar pedido");
		btnBorrarPedido.setForeground(Color.BLACK);
		btnBorrarPedido.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBorrarPedido.setBounds(68, 580, 143, 43);
		panelTicket.add(btnBorrarPedido);
		
		btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setBounds(68, 634, 143, 43);
		panelTicket.add(btnSalir);
		
		//ACCIONES
		rdbtnTotopos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnQuesadillas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnTlayuda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnEnchiladas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnPozole.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnChilaquiles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnGaseosa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnAguasFrescas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnMargarita.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnMichelada.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnMarquesitas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnPolo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		rdbtnEfectivo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarFormaDePago();
			}
		});
		
		rdbtnTarjeta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarFormaDePago();
			}
		});
		
		chBoxCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chBoxCafe.isSelected()) {
					cBoxCafe.setEnabled(true);
				}
				else {
					cBoxCafe.setEnabled(false);
				}
				
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		cBoxCafe.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularPrecioTotal();
				mostrarPrecio(precioTotal);
				generarTicket();
			}
		});
		
		txtFImporte.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(Pattern.matches(EXP_REG_IMPORTE, txtFImporte.getText())) {
					btnPagarEfectivo.setEnabled(true);
				}
				else {
					txtFImporte.setText("");
					btnPagarEfectivo.setEnabled(false);
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		
		formattedTxtFNumTarjeta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if((formattedTxtFNumTarjeta.getSelectionEnd() == LONG_NUMERO_TARJETA+3) && Pattern.matches(EXP_REG_NUMTARJETA, formattedTxtFNumTarjeta.getText())){
					btnPagarTarjeta.setEnabled(true);
				}
				else {
					btnPagarTarjeta.setEnabled(false);
				}
			}
		});
		
		btnPagarEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double cambio = 0;
				
				double importe = Double.parseDouble(txtFImporte.getText());
				
				if(importe >= precioTotal) {
				
					cambio = importe - precioTotal;
					JOptionPane.showMessageDialog(ventana, String.format("El Cambio es:  %.2f €", cambio), "Caja", JOptionPane.INFORMATION_MESSAGE, imgCheck);
					
					btnNuevoPedido.setEnabled(true);
					btnBorrarPedido.setEnabled(false);
					deshabilitarPanelCarta();
					generarTicket();
					
					txtATicket.append("__________________________________\n\n");
					txtATicket.append(String.format("TOTAL:  %.2f %c\n", precioTotal, MONEDA));
					txtATicket.append(String.format("EFECTIVO:  %.2f %c\n", importe, MONEDA));
					txtATicket.append(String.format("CAMBIO:  %.2f %c\n", cambio, MONEDA));
					txtATicket.append(ASTERISCOS
								    + "                        ¡VUELVA  PRONTO!");

				}
				else {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(ventana, "El importe es inferior al precio", "Advertencia", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		
		btnPagarTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(ventana, "Transaccion completada", "Banco Bankia", JOptionPane.INFORMATION_MESSAGE, imgCheck);
				
				btnNuevoPedido.setEnabled(true);
				btnBorrarPedido.setEnabled(false);
				deshabilitarPanelCarta();
				generarTicket();
				
				txtATicket.append("__________________________________\n\n");
				txtATicket.append(String.format("TOTAL:  %.2f %c\n", precioTotal, MONEDA));
				txtATicket.append(String.format("TARJETA:  %.2f %c\n", precioTotal, MONEDA));
				txtATicket.append(ASTERISCOS
								+ "                        ¡VUELVA  PRONTO!");
			}
		});
		
		btnNuevoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnNuevoPedido.isEnabled()) {
					volverAlEstadoInicial();
					btnNuevoPedido.setEnabled(false);
					btnBorrarPedido.setEnabled(true);
				}
			}
		});
		
		btnBorrarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int borrarPedido = JOptionPane.showConfirmDialog(ventana, "Estas seguro?", "Borrar", JOptionPane.YES_NO_OPTION);
				
				if(borrarPedido == JOptionPane.YES_OPTION) {
					
					volverAlEstadoInicial();
				}
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int salir = JOptionPane.showConfirmDialog(ventana, "Estas seguro?", "Salir", JOptionPane.YES_NO_OPTION);
				
				if(salir == JOptionPane.YES_OPTION) {
					ventana.dispose();
				}
			}
		});
		
		rdbtnCamarero1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				generarTicket();
			}
		});
		
		rdbtnCamarero2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				generarTicket();
			}
		});
		
		rdbtnCamarero3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				generarTicket();
			}
		});
		
		chBoxFondoBasico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambiarColorFondo();
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

