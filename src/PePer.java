import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import org.fife.rsta.ui.SizeGripIcon;
import org.fife.ui.rsyntaxtextarea.ErrorStrip;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;
import javax.swing.BoxLayout;
import java.awt.ComponentOrientation;

public class PePer {
	String filepath=null;
	String temp=null;
	private JFrame frame;
	//	private boolean isMenuShown;
	private StatusBar statusBar;
	private Boolean themedark = false;
	boolean saveAs=false;
	private JPanel menuPanel;
	private JPanel editorPanel;
	/**
	 * Launch the application.
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
		String relativepath =Paths.get("").toAbsolutePath().toString();
		//		isMenuShown = true;
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
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
		topPanel.setBorder(null);
		topPanel.setBackground(Color.BLACK);

		editorPanel = new JPanel();
		editorPanel.setBackground(Color.ORANGE);

		JPanel panel = new JPanel();
		panel.setToolTipText("CONSOLE : paste the content to the console and hit enter");
		panel.setBackground(Color.BLACK);
		QuicTerminal k =new QuicTerminal();
		panel.add( k.new ConsolePane());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);


		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(editorPanel, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
				.addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(menuPanel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(editorPanel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("CONSOLE");
		lblNewLabel.setToolTipText("CONSOLE: please paste clipboard contents( hit Ctrl + V )  in console after RUN button");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		panel_1.add(lblNewLabel);
		panel.setLayout(new CardLayout(0, 0));

		JButton run = new JButton("Run");
		run.setToolTipText("UNDER DEVELOPMENT : commond is copied to the clipboard");
		run.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		run.setBorderPainted(false);
		run.setHorizontalAlignment(SwingConstants.LEFT);
		run.setFont(new Font("Script MT Bold", Font.PLAIN, 16));
		run.setForeground(Color.ORANGE);
		run.setContentAreaFilled(false);

		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(SaveAs, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
						.addComponent(run, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(run)
					.addContainerGap(170, Short.MAX_VALUE))
		);
		menuPanel.setLayout(gl_menuPanel);

		RSyntaxTextArea	textArea = new RSyntaxTextArea(20, 60);
		textArea.setRoundedSelectionEdges(true);
		textArea.setSelectionColor(SystemColor.menu);
		textArea.setRows(15);
		textArea.setColumns(15);
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
		sp.getTextArea().setRows(10);
		sp.getTextArea().setText("#Start your Python advancture");
		sp.getTextArea().setRoundedSelectionEdges(true);
		sp.getTextArea().setColumns(15);
		sp.getTextArea().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
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


		try { 
			Theme theme = Theme.load(getClass().getResourceAsStream(
					"/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
			theme.apply(textArea);
		} catch (IOException ioe) { 
			ioe.printStackTrace();
		}
		textArea.setBackground(Color.BLACK);
		sp.setBackground(Color.ORANGE);
		sp.getGutter().setBackground(Color.BLACK);
		sp.getGutter().setBorderColor(Color.orange);
		sp.getGutter().setLineNumberColor(Color.ORANGE);



		JButton mainLogo = new JButton("");
		mainLogo.setIcon(new ImageIcon("C:\\Users\\Rakshith Rai\\git\\beta\\PePer\\images\\PePer.png"));
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
		//			public void actionPerformed(ActionEvent arg0) {
		//			//	showOrHideMenu();
		//			//	isMenuShown=!isMenuShown;
		//			}
		//		});
		/** 
		 * *SAVE BUTTON LISTENERS ( well its here to access the rsyntaxtextarea)
		  */
		Save.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String data=textArea.getText().trim(); //quick check
				  if(!data.equals("")&&!data.equals("#Start your Python advancture")) {
				statusBar.setLabel("saving......");

				if(filepath==null||saveAs==true){
					//saving as
					FileDialog  fd=new FileDialog(frame,"saving a python file", FileDialog.SAVE);
					fd.setVisible(true);
					if(fd.getFile()!=null){
						filepath=fd.getDirectory()+"\\"+fd.getFile()+".py";
					}
					else {
						statusBar.setLabel("directory selection is failed");
					}
				}				
					/**
					 * saving
					 */	
					String prg=textArea.getText();
					BufferedWriter out;
					try{
						out = new BufferedWriter(new FileWriter(filepath));
						out.write(prg);
						out.close();
						statusBar.setLabel("saved sucessfully");

					}catch (IOException a) {
						statusBar.setLabel("saving failed error");
						a.printStackTrace();
					}
				}
				else {
					statusBar.setLabel("Text area is empty!!");
				}
			}
		});
		/**
		 * SAVE AS BUTTON LISTENER
		 */

		SaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusBar.setLabel("saving As");
				saveAs=true;
				Save.doClick();
				saveAs=false;
			}
		});
		
		
		/** 
		 * this is for accidental close operation
		 */
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				String data=textArea.getText().trim(); //quick check
				  if(!data.equals("")&&!data.equals("#Start your Python advancture")) {
				int result = JOptionPane.showConfirmDialog(frame,"SAVE YOUR WORK", "ALERT!",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					Save.doClick();
					frame.dispose();

				}else if (result == JOptionPane.NO_OPTION){
					frame.dispose();
				}else {
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

				}
			}
				  else {
					  frame.dispose();
					
				}
			} 

		});

		/**
		 * OPEN BUTTON LISTENER
		 */
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusBar.setLabel("opening......");
				FileDialog  fd=new FileDialog(frame,"SELECT a python file", FileDialog.LOAD);
				fd.setFile("*.py");
				fd.setVisible(true);
				statusBar.setLabel(fd.getFile());
				if(fd.getFile()!=null){
					filepath=fd.getDirectory()+"\\"+fd.getFile();
					textArea.removeAll();

					File file = new File(filepath);
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					byte[] bytesArray = new byte[(int)file.length()];
					try {
						fis.read(bytesArray);
					} catch (IOException e) {
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
		/**
		 * RUN BUTTON LISTNER 
		 */
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp=textArea.getText().trim(); //quick check on contents
				  if(!temp.equals("")&&!temp.equals("#Start your Python advancture")) {
				
				statusBar.setLabel("Running on python idle......");
				try
				{	System.out.print(temp);
//					Path loc=Paths.get("");
//					String location =loc.toAbsolutePath().toString();

					System.out.println(relativepath);
					statusBar.setLabel("runnung on  "+relativepath);

					System.out.println(textArea.getText());
					if(textArea.getText()!=null){
						BufferedWriter out;
						out = new BufferedWriter(new FileWriter(relativepath+"/temp.py"));
						out.write(textArea.getText());
						out.close();
						String command = "python \""+relativepath+"\\temp.py\"";
						statusBar.setLabel("command is copied to clipboard Please paste in console below");
						StringSelection data = new StringSelection(command);
						Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
						cb.setContents(data,null);
						/** 
						 * weak run code
						 * this below commented code is not efficient but works
						 * it uses python IDLE installed in your pc and flashes answer 
						 */

						//					File file = new File(location+"/temp.py");   
						//					Desktop desktop = Desktop.getDesktop();  
						//					desktop.open(file);
						/**
						 * this is run code
						 *  Below commented code is use to interact with the CMD promt in background and get the out put from it
						 *  						       
						 */
						//						  	k.SetText(command,1);
						//						try
						//						{
						//							String command = "cmd /c python \""+location+"/temp.py\"";
						//						
						//					    Process p = Runtime.getRuntime().exec(command);
						//					    p.waitFor();
						//					    BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
						//					    BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
						//					          String line;
						//					        while ((line = bri.readLine()) != null) {
						//					            System.out.println(line);
						//					            console.removeAll();
						//					            console.setText("\n"+line);
						//					          }
						//					          bri.close();
						//					          while ((line = bre.readLine()) != null) {
						//					            System.out.println(line);
						//					            console.append("\n"+line);
						//
						//					          }
						//					          bre.close();
						//					          p.waitFor();
						//					          System.out.println("Done.");
						//					            console.append("\n Done");
						//
						//
						//					    p.destroy();
						//										} catch (InterruptedException e) {
						//					
						//						e.printStackTrace();
						//					}
					}
					else{
						statusBar.setLabel("error!");
						}

				}catch(IOException a) {
					statusBar.setLabel("error!");
					a.printStackTrace();
				}
				  }
				  else {
					statusBar.setLabel(" Editor is Empty");
				}	
			}});
		mainLogo.setActionCommand("");
		mainLogo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							k.chngTheme(themedark);
							if(!themedark) {
							menuPanel.setBackground(Color.WHITE);
							topPanel.setBackground(Color.WHITE);
							panel_1.setBackground(Color.white);
							panel.setBackground(Color.white);
							lblNewLabel.setForeground(Color.BLACK);
							lblNewLabel.setBackground(Color.WHITE);
							errorStrip.setBackground(Color.WHITE);
							statusBar.setBackground(Color.WHITE);
							try { 
								Theme theme = Theme.load(getClass().getResourceAsStream(
										"/org/fife/ui/rsyntaxtextarea/themes/vs.xml"));
								theme.apply(textArea);
								sp.getGutter().setBorderColor(Color.orange);
								sp.getGutter().setLineNumberColor(Color.ORANGE);
							} catch (IOException ioe) { 
								ioe.printStackTrace();
							}
							themedark=true;
							}
							else {
								menuPanel.setBackground(Color.black);
								topPanel.setBackground(Color.BLACK);
								panel_1.setBackground(Color.black);
								panel.setBackground(Color.BLACK);
								lblNewLabel.setForeground(Color.white);
								lblNewLabel.setBackground(Color.black);
								errorStrip.setBackground(Color.black);
								statusBar.setBackground(Color.black);
								try { 
									Theme theme = Theme.load(getClass().getResourceAsStream(
											"/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
									theme.apply(textArea);
									textArea.setBackground(Color.BLACK);
									sp.getGutter().setBackground(Color.black);
									sp.getGutter().setBorderColor(Color.orange);
									sp.getGutter().setLineNumberColor(Color.ORANGE);
								} catch (IOException ioe) { 
									ioe.printStackTrace();
								}
								themedark=false;
								}
								
							}
							
							
					
					});
		/**
		 *logo in images folder
		 */
		
		
		
		JLabel pythonlabl = new JLabel("");
		pythonlabl.setHorizontalTextPosition(SwingConstants.LEADING);
		pythonlabl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pythonlabl.setIcon(new ImageIcon("C:\\Users\\Rakshith Rai\\git\\beta\\PePer\\images\\Python.png"));
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_topPanel.createSequentialGroup()
					.addComponent(mainLogo)
					.addPreferredGap(ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
					.addComponent(pythonlabl, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(mainLogo))
						.addGroup(gl_topPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(pythonlabl, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(gl_topPanel);
		gl_layeredPane.setAutoCreateGaps(true);
		layeredPane.setLayout(gl_layeredPane);
		frame.getContentPane().setLayout(groupLayout);

	}
	/**
	 * this is the animation funtion for the manu hiding , this is under construction but works , 
	 * you can clear the comment below funtion and callling this funtion from topmain panel
	 */

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

	static class StatusBar extends JPanel {

		private JLabel label;

		StatusBar() {
			label = new JLabel("MODE : IDLE");
			setLayout(new BorderLayout());
			add(label, BorderLayout.LINE_START);
			add(new JLabel(new SizeGripIcon()), BorderLayout.LINE_END);
			
		}

		void setLabel(String label) {
			this.label.setText("status: "+label);
		}

	}
}


