package server.model;

import java.io.PrintWriter;
import java.util.ArrayList;

public interface ServerIF extends Runnable{
	void setClientOutputStreams(ArrayList<PrintWriter> clientOutputStreams);

}