package server.view;




import server.controler.controler;

public class ServerView extends javax.swing.JFrame implements ServerViewIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea outputPane;
	private javax.swing.JButton startButton;
	private javax.swing.JButton stopButton;
	private controler ctrl;

	/** Creates new form ServerWindow */
	public ServerView(controler ctrl) {
		this.ctrl =ctrl;
		initComponents();
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		outputPane = new javax.swing.JTextArea();
		startButton = new javax.swing.JButton();
		stopButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("House Server");

		outputPane.setColumns(20);
		outputPane.setEditable(false);
		outputPane.setLineWrap(true);
		outputPane.setRows(5);
		outputPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		jScrollPane1.setViewportView(outputPane);

		startButton.setText("Start");
		startButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewReqest(evt);
			}
		});

		stopButton.setText("Stop");
		stopButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewReqest(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(startButton).addComponent(stopButton))
						.addContainerGap(19, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.ServerViewIF#viewUpdate(java.lang.String)
	 */
	@Override
	public void viewUpdate(String data) {
		outputPane.append(data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.ServerViewIF#viewReqest(java.awt.event.ActionEvent)
	 */
	@Override
	public void viewReqest(java.awt.event.ActionEvent evt) {
		switch (evt.getActionCommand()) {
		case "Start": { ctrl.startServer();
			outputPane.append("Server started. \n");
			break;
		}
		case "Stop": {// ServerModel.tellEveryone("Server:is stopping and all users will be
						// disconnected.\n:Chat");
			outputPane.append("Server stopping... \n");
			break;
		}
		}
	}
	
	public void run() {
		new ServerView(ctrl).setVisible(true);
	}

}