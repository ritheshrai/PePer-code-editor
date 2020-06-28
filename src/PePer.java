//import java.awt.Desktop;


import java.awt.EventQueue;
import java.awt.FileDialog;
//import java.awt.FileDialog;
////import java.awt.Point;
import java.awt.Rectangle;
//import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
//import javax.swing.JEditorPane;
//import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
import javax.swing.JPanel;
//import javax.swing.Timer;
//import javax.swing.border.TitledBorder;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.CardLayout;
import javax.swing.JLayeredPane;
//import javax.swing.JLabel;
//import java.awt.Component;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import javax.swing.border.LineBorder;
//import java.awt.BorderLayout;
//import java.awt.event.HierarchyBoundsAdapter;
//import java.awt.event.HierarchyEvent;
//import javax.swing.JDesktopPane;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.LayoutStyle.ComponentPlacement;
//import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Insets;
//import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.fife.ui.rtextarea.*;

import org.fife.rsta.ui.*;
//import test.RSTAUIDemoApp.StatusBar;

//import test.RSTAUIDemoApp.StatusBar;

import org.fife.rsta.ui.SizeGripIcon;
import org.fife.ui.rsyntaxtextarea.*;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.border.LineBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
//import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class PePer {
	String filepath=null;
	String temp=null;
	private JFrame frame;
	//	private int menuWidth;
	//	private int menuHeight;
	private boolean isMenuShown;
	private StatusBar statusBar;
	boolean saveAs=false;
	private JPanel menuPanel;
	private JPanel editorPanel;
	//	private StatusBar statusBar;
//	public Object ;

	/**
	 * Launch the application.
	 * @param args
	 */
	 public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					PePer window = new PePer();
					window.frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PePer() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		isMenuShown = true;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 204, 0));
		frame.setForeground(Color.BLACK);
		frame.setBackground(new Color(139, 0, 139));
		frame.setBounds(new Rectangle(0, 0, 600, 400));


		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(Color.BLACK);
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setBounds(new Rectangle(0, 0, 900, 900));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
				);

		menuPanel = new JPanel();
		menuPanel.setAutoscrolls(true);
		layeredPane.setLayer(menuPanel, 0);
		menuPanel.setForeground(Color.ORANGE);
		menuPanel.setBackground(Color.BLACK);

		JButton Save = new JButton("Save");
		Save.setBorderPainted(false);
		Save.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		Save.setHorizontalAlignment(SwingConstants.LEFT);
		Save.setFont(new Font("Script MT Bold", Font.PLAIN, 16));
		Save.setSize(new Dimension(1, 1));
		Save.setIconTextGap(9);
		Save.setForeground(Color.ORANGE);
		Save.setDoubleBuffered(true);
		Save.setContentAreaFilled(false);

		JButton SaveAs = new JButton("Save As");
		SaveAs.setHorizontalTextPosition(SwingConstants.CENTER);
		SaveAs.setHorizontalAlignment(SwingConstants.LEADING);
		SaveAs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		SaveAs.setHideActionText(true);
		SaveAs.setForeground(Color.ORANGE);
		
		SaveAs.setFont(new Font("Script MT Bold", Font.PLAIN, 16));
		SaveAs.setIconTextGap(9);
		SaveAs.setBorderPainted(false);
		SaveAs.setContentAreaFilled(false);

		JButton Open = new JButton("Open");
		Open.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Open.setBorderPainted(false);
		Open.setContentAreaFilled(false);
		
		Open.setFont(new Font("Script MT Bold", Font.PLAIN, 16));
		Open.setForeground(Color.ORANGE);
		Open.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);

		editorPanel = new JPanel();
		editorPanel.setBackground(Color.ORANGE);
		QuicTerminal k =new QuicTerminal();
		
		
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(editorPanel, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
				.addComponent(topPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addComponent(editorPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(2))
						.addComponent(menuPanel, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
		);

		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(SaveAs, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
						.addComponent(Open, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
					.addGap(10))
				.addComponent(Save, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
		);
		gl_menuPanel.setVerticalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addComponent(Save, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SaveAs, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(Open, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(194, Short.MAX_VALUE))
		);
		menuPanel.setLayout(gl_menuPanel);

		RSyntaxTextArea	textArea = new RSyntaxTextArea(20, 60);
		//		textArea.RSyntaxTextArea(20, 60);
		textArea.setSelectionColor(SystemColor.menu);
		textArea.setRows(15);
		textArea.setColumns(48);
		textArea.setLineWrap(true);
		textArea.setEOLMarkersVisible(true);
		textArea.setFadeCurrentLineHighlight(true);
		textArea.setAutoscrolls(false);
		textArea.setForeground(Color.YELLOW);
		textArea.setDisabledTextColor(SystemColor.text);
		textArea.setCurrentLineHighlightColor(Color.GRAY);
		textArea.setBackground(Color.BLACK);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
		textArea.setCodeFoldingEnabled(true);
		editorPanel.setLayout(new BorderLayout(0, 0));

		RTextScrollPane sp = new RTextScrollPane(textArea);
		sp.getTextArea().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		sp.getTextArea().setBackgroundImage(Toolkit.getDefaultToolkit().getImage("image\\circuit orange.png"));
		sp.getGutter().setFoldIndicatorForeground(Color.YELLOW);
		sp.setEnabled(false);
		sp.setAutoscrolls(true);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.getGutter().setLineNumberColor(Color.ORANGE);
		sp.getGutter().setBorderColor(Color.ORANGE);
		sp.setBorder(null);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBackground(Color.ORANGE);
		editorPanel.add(sp);
		ErrorStrip errorStrip = new ErrorStrip(textArea);
		errorStrip.setForeground(Color.ORANGE);
		errorStrip.setCaretMarkerColor(Color.ORANGE);
		errorStrip.setBackground(Color.BLACK);
		editorPanel.add(errorStrip, BorderLayout.LINE_END);
		
		statusBar = new StatusBar();
		statusBar.setFocusTraversalPolicyProvider(true);
		statusBar.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 14));
		statusBar.setBackground(Color.BLACK);
		statusBar.setForeground(Color.ORANGE);
		editorPanel.add(statusBar, BorderLayout.SOUTH);
		
		
		try { //theme changing but shitty gutter border is changing yayh  !!!!!!!
			Theme theme = Theme.load(getClass().getResourceAsStream(
					"/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
			theme.apply(textArea);
		} catch (IOException ioe) { // Never happens
			ioe.printStackTrace();
		}
		textArea.setBackground(Color.BLACK);
		sp.setBackground(Color.ORANGE);
		sp.getGutter().setBackground(Color.BLACK);
		sp.getGutter().setBorderColor(Color.orange);
		sp.getGutter().setLineNumberColor(Color.ORANGE);


		

		//org.fife.rsta.ui.DocumentMap docMap = new org.fife.rsta.ui.DocumentMap(textArea);
		//contentPane.add(docMap, BorderLayout.LINE_END);
		//
		//		statusBar = new StatusBar();
		//		statusBar.setForeground(Color.ORANGE);
		//		statusBar.setBackground(Color.BLACK);
		//		editorPanel.add(statusBar, BorderLayout.SOUTH);

		//		setTitle("RSTAUI Demo Application");
		//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//		pack();
		//		setLocationRelativeTo(null);


		JButton mainLogo = new JButton("");
		mainLogo.setForeground(Color.BLACK);
		mainLogo.setBackground(Color.BLACK);
		mainLogo.setFocusCycleRoot(true);
		mainLogo.setFocusTraversalPolicyProvider(true);
		mainLogo.setSelected(true);
		mainLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainLogo.setBorder(null);
		mainLogo.setContentAreaFilled(false);
		mainLogo.setMargin(new Insets(0, 0, 0, 0));
//		mainLogo.addActionListener(new ActionListener() {
//			//public void actionPerformed(ActionEvent arg0) {
//				showOrHideMenu();
//				isMenuShown=!isMenuShown;
//			}
//		});
		//SAVE BUTTON LISTENERS ( well its here to access the rsyntaxtextarea)
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusBar.setLabel("saving......");

				//				Path loc=FileSystems.getDefault().getpath("").toAbsolutePath();		
				
				if(filepath==null||saveAs==true){
					//saving as
					FileDialog  fd=new FileDialog(frame,"saving a python file", FileDialog.SAVE);
					
					fd.setVisible(true);

					//				String filename=fd.getFile();
					// file Chosen
					if(fd.getFile()!=null){
						filepath=fd.getDirectory()+"\\"+fd.getFile();
					}
					else {
						statusBar.setLabel("directory selection is failed");
					}
				}
				if(textArea.getText() != null) {
				//saving	
				String prg=textArea.getText();
				BufferedWriter out;
				BufferedReader h;
				try{
					out = new BufferedWriter(new FileWriter(filepath));
					out.write(prg);
					out.close();
					h=new BufferedReader(new FileReader(filepath));
					String hello =h.readLine();
					System.out.print(hello);
					h.close();
					
					statusBar.setLabel("saved sucessfully");

				}catch (IOException a) {
					statusBar.setLabel("saving failed error");
					a.printStackTrace();
				}
			}
				else statusBar.setLabel("text area is empty!!");
				}
		});
		//SAVE AS BUTTON LISTENER
		SaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusBar.setLabel("saving As");
				saveAs=true;
				Save.doClick();
				saveAs=false;
			}
		});
		//OPEN BUTTON LISTENER
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusBar.setLabel("opening......");
//				FilenameFilter filter = (dir, name) -> name.endsWith(".py");
				FileDialog  fd=new FileDialog(frame,"SELECT a python file", FileDialog.LOAD);
                fd.setFile("*.py");
				 
//				fd.setFilenameFilter(filter);
				fd.setVisible(true);
				statusBar.setLabel(fd.getFile());
				if(fd.getFile()!=null){
					filepath=fd.getDirectory()+"\\"+fd.getFile();
					textArea.removeAll();
//					String con=null;
					 File file = new File(filepath);
				      FileInputStream fis = null;
					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      byte[] bytesArray = new byte[(int)file.length()];
				      try {
						fis.read(bytesArray);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      String s = new String(bytesArray);
					textArea.setText(s);
				}
				else {
					statusBar.setLabel("FILE selection is failed");
				}
				
			}
		});
//		//RUN BUTTON LISTNER
//		run.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				statusBar.setLabel("Running on python idle......");
//				try
//				{	
//					Path loc=Paths.get("");
//					String location =loc.toAbsolutePath().toString();
//					System.out.println(location);
//					statusBar.setLabel("runnung on  "+location);
//					
//					System.out.println(textArea.getText());
//					if(textArea.getText()!=null){
//					BufferedWriter out;
//						out = new BufferedWriter(new FileWriter(location+"/temp.py"));
////						out.write("import time\n"+textArea.getText()+"\ntime.sleep(5)");
//						out.write(textArea.getText());
//						out.close();
//						String command = "python \""+location+"\\temp.py\"";
//						System.out.println(" peper out :"+command);
//						
////						k.SetText(command,1);
////					File file = new File(location+"/temp.py");   
////					Desktop desktop = Desktop.getDesktop();  
////					desktop.open(file);
////						try
////						{
////							String command = "cmd /c python \""+location+"/temp.py\"";
////						
////					    Process p = Runtime.getRuntime().exec(command);
////					    p.waitFor();
////					    BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
////					    BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
////					          String line;
////					        while ((line = bri.readLine()) != null) {
////					            System.out.println(line);
////					            console.removeAll();
////					            console.setText("\n"+line);
////					          }
////					          bri.close();
////					          while ((line = bre.readLine()) != null) {
////					            System.out.println(line);
////					            console.append("\n"+line);
////
////					          }
////					          bre.close();
////					          p.waitFor();
////					          System.out.println("Done.");
////					            console.append("\n Done");
////
////
////					    p.destroy();
////										} catch (InterruptedException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//					}
//				else{
//					System.out.println("hello");
//					}
//					
//				}catch(IOException a) {
//					statusBar.setLabel("error broooooo!!!!!!!!!!!!!!!!!!!!!!!");
//					System.out.print("error broooooo!!!!!!!!!!!!!!!!!!!!!!!1");
////					a.printStackTrace();
//				}
//			}
//		});
		mainLogo.setActionCommand("");
		mainLogo.setIcon(new ImageIcon("image\\PePer.png"));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setAutoscrolls(true);
		lblNewLabel.setIgnoreRepaint(true);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setLabelFor(frame);
		lblNewLabel.setIcon(new ImageIcon("image\\circute orange.png"));
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(mainLogo, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 411, Short.MAX_VALUE))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(mainLogo, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
		);
		topPanel.setLayout(gl_topPanel);
		gl_layeredPane.setAutoCreateGaps(true);
		layeredPane.setLayout(gl_layeredPane);
		frame.getContentPane().setLayout(groupLayout);

	}

//	public void showOrHideMenu() {
//		int xOffset = 200;
//		int yOffset = 99;
//
//		Rectangle manuFromRect =  new Rectangle(0, yOffset, 200, frame.getHeight());
//		Rectangle manuToRect =  new Rectangle(0, yOffset, 0, frame.getHeight());
//		Rectangle editorToRect =  new Rectangle(xOffset, yOffset, frame.getWidth() - xOffset, frame.getHeight());
//		Rectangle editorFromRect =  new Rectangle(0, yOffset,frame.getWidth()+yOffset,frame.getHeight());
//		if(!isMenuShown)
//		{
//			Rectangle temp;
//			temp=manuFromRect;
//			manuFromRect=manuToRect;
//			manuToRect=temp;
//			temp=editorToRect;
//			editorToRect=editorFromRect;
//			editorFromRect=temp;	
//		}
//		Animate menuAnimate= new Animate(menuPanel, manuFromRect, manuToRect);
//		Animate editorAnimate= new Animate(editorPanel, editorToRect,editorFromRect);
//		menuAnimate.start();
//		editorAnimate.start();
//	}
    /**
     * The status bar for this application.
     */
	private static class StatusBar extends JPanel {

		private JLabel label;

		StatusBar() {
			label = new JLabel("mode:idle");
			setLayout(new BorderLayout());
			add(label, BorderLayout.LINE_START);
			add(new JLabel(new SizeGripIcon()), BorderLayout.LINE_END);
		}

		void setLabel(String label) {
			this.label.setText("status:"+label);
		}

	}
}


