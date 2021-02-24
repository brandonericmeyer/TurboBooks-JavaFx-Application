package com.turbobooks.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.turbobooks.model.business.manager.TurbobooksManager;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.itemservice.IItemManagerService;

/**
 * Class that connects TurbobooksServer to the TurbobooksManager
 * 
 * @author bmeyer
 *
 */

public class TurbobooksServerHandler extends Thread {

	private Socket incomingSocket;
	private final int threadNumber;

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	/**
	 * 
	 * @param _incomingSocket
	 */
	public TurbobooksServerHandler(Socket _incomingSocket, int _threadNumber) {
		incomingSocket = _incomingSocket;
		threadNumber = _threadNumber;
	}

	@Override
	public void run() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean status = false;
		TurbobooksManager turbobooksServerManager = TurbobooksManager.getInstance();
		Item item = null;

		try {
			in = new ObjectInputStream(incomingSocket.getInputStream());
			out = new ObjectOutputStream(incomingSocket.getOutputStream());

			String commandString = (String) in.readObject();

			log.info("TurbobooksServerHandler::run:Received command to execute service: " + commandString);

			// retrieve object sent by Client over the ObjectInputStream
			item = (Item) in.readObject();

			if (commandString.equals(TurbobooksManager.Services.ADDITEM.getLabel())) {
				status = turbobooksServerManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, "");
				out.writeObject(status);
			} else if (commandString.equals(TurbobooksManager.Services.REMOVEITEM.getLabel())) {
				status = turbobooksServerManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item,
						"");
				out.writeObject(status);
			} else if (commandString.equals(TurbobooksManager.Services.CHECKOUTITEM.getLabel())) {
				status = turbobooksServerManager.performAction(TurbobooksManager.Services.CHECKOUTITEM.getLabel(), item,
						"");
				out.writeObject(status);
			} else if (commandString.equals(TurbobooksManager.Services.UPDATEITEM.getLabel())) {
				status = turbobooksServerManager.performAction(TurbobooksManager.Services.UPDATEITEM.getLabel(), item,
						"");
				out.writeObject(status);
			} else if (commandString.equals(TurbobooksManager.Services.ISITEMAVAILABLE.getLabel())) {
				status = turbobooksServerManager.performAction(TurbobooksManager.Services.ISITEMAVAILABLE.getLabel(),
						item, "");
				out.writeObject(status);
			} else if (commandString.equals(TurbobooksManager.Services.GETITEM.getLabel())) {
				item = turbobooksServerManager.getItem(IItemManagerService.NAME, item.getUuid());
				out.writeObject(item);

			}

			out.flush();

		} catch (Exception e) {
			log.error("Error processing request from client", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				if (incomingSocket != null) {
					incomingSocket.close();
				}
			} catch (IOException e) {
				log.error(e.getClass() + ": " + e.getMessage(), e);
			}
			log.info(threadNumber + " exiting");
		} // end try/catch/finally
	}// end run
}