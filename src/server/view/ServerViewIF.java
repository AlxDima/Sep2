package server.view;

public interface ServerViewIF extends Runnable {

	void viewUpdate(String data);

	void viewReqest(java.awt.event.ActionEvent evt);

}