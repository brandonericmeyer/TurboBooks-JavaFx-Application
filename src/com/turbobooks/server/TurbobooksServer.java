package com.turbobooks.server;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * 
 * @author brandonmeyer
 *
 */
public class TurbobooksServer {

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	private static int threadsCreated = 1;

	private TurbobooksServer() {
		// construct object . . .
	}

	public static void startServer() {
		try {
			log.info("Turbobooks Server Started");

			ServerSocket s = new ServerSocket(17000);

			for (;;) {
				Socket socket = s.accept();
				TurbobooksServerHandler turbobooksServerHandler = new TurbobooksServerHandler(socket, threadsCreated);
				turbobooksServerHandler.start();
				log.info("Threads created: " + threadsCreated);
				threadsCreated++;
			}
		} catch (Exception e) {
			log.error("Issue starting Turbobooks Server ", e);
		}

	} // end startServer

	/**
	 * Starts the server
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TurbobooksServer.startServer();
	}// end main
}