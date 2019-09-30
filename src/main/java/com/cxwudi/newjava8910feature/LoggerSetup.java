package com.cxwudi.newjava8910feature;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.SocketHandler;
import java.util.logging.StreamHandler;


public class LoggerSetup {

	public static void setupGlobalLogger(int protId) {
		LogManager.getLogManager().reset();// remove global logger's default handler
		Logger.getGlobal().setLevel(Level.ALL);
		
		//to customize my own formatter, add millisecond, and make everything in one line
		SimpleFormatter myCustomizeFormatter = new SimpleFormatter() {
			@Override
		    public String format(LogRecord record) {
		        ZonedDateTime zdt = ZonedDateTime.ofInstant(
		                record.getInstant(), ZoneId.systemDefault());
		        String time = zdt.format(DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss.SSS a"));
		        String source;
		        if (record.getSourceClassName() != null) {
		            source = record.getSourceClassName();
		            if (record.getSourceMethodName() != null) {
		               source += " " + record.getSourceMethodName();
		            }
		        } else {
		            source = record.getLoggerName();
		        }
		        String message = formatMessage(record);
		        String throwable = "";
		        if (record.getThrown() != null) {
		            StringWriter sw = new StringWriter();
		            PrintWriter pw = new PrintWriter(sw);
		            pw.println();
		            record.getThrown().printStackTrace(pw);
		            pw.close();
		            throwable = sw.toString();
		        }
		        if (message.equals("\n")) return "\n";
		        return String.format("%-80s%s %s%n",
		        		String.format("%s %s %s:",
		        				time, 
		        				source,
		        				record.getLevel().getName()),
		        		message,
		                throwable);
		    }
		};
		
		StreamHandler handler = new StreamHandler(System.out, myCustomizeFormatter) {
			@Override
			public synchronized void publish(LogRecord record) {
				super.publish(record);
				flush();
			}
		};
		handler.setLevel(Level.ALL);
		Logger.getGlobal().addHandler(handler);
		
		
		try {
			var socketHandler = new SocketHandler("127.0.0.1", protId);
			socketHandler.setFormatter(myCustomizeFormatter);
			socketHandler.setLevel(Level.ALL);

			Logger.getGlobal().addHandler(socketHandler);
		} catch (IOException e) {
			Logger.getGlobal().warning("Socket handler fails to connect project logger, " + e);
		}
		

	}
	
	public void runLog() {
		var serverSocketList = new ArrayList<ServerSocket>();
		var socketList = new ArrayList<Socket>();
		try {
			//WARNING, the order of adding this socket have to been EXACTLY same as three processes run order
			serverSocketList.add(new ServerSocket(0));
			serverSocketList.add(new ServerSocket(1));
			serverSocketList.add(new ServerSocket(2));
			
			for (var serverSocket : serverSocketList) {
				socketList.add(serverSocket.accept());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		Logger logger = Logger.getLogger("SYSC3303");
//		logger.setLevel(Level.ALL);
//		FileHandler handler = null;
//		try {
//			handler = new FileHandler("output.log");
//			handler.setLevel(Level.ALL);
//		} catch (SecurityException | IOException e1) {
//			e1.printStackTrace();
//		}
//		logger.addHandler(handler);
		
		BufferedReader[] readers = new BufferedReader[socketList.size()];
		for (int i = 0; i < readers.length; i++) {
			try {
				readers[i] = new BufferedReader(new InputStreamReader(socketList.get(i).getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PrintStream writer = null;
		try {
			writer = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.log"))),true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < readers.length; i++) {
			writeFromTo(readers[i], writer);
		}
		

		
	}
	
	private void writeFromTo(BufferedReader reader, PrintStream writer) {
		new Thread(() -> {
			while (true) {
				String line;
				try {
					if ((line = reader.readLine()) != null) {
						writer.println(line);
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
