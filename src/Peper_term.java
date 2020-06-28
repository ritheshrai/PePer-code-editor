import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

// IT IS IN PROGRESSS THIS FILE IS NOT USED BY PEPER EDITOR NOW
public class Peper_term {
	

	public Peper_term() {
		
	}
	 public interface CommandListener {

	        public void commandOutput(String text);

	        public void commandCompleted(String cmd, int result);

	        public void commandFailed(Exception exp);
	    }
	 public interface UserInput {

	        public int getUserInputStart();
	    }

	    public interface Terminal extends UserInput {
	        public void appendText(String text);
	    }

	    public class AppendTask implements Runnable {

	        private Terminal terminal;
	        private String text;

	        public AppendTask(Terminal textArea, String text) {
	            this.terminal = textArea;
	            this.text = text;
	        }

	        @Override
	        public void run() {
	            terminal.appendText(text);
	        }
	    }

	    public class Command {

	        private CommandListener listener;
	        private ProcessRunner runner;

	        public Command(CommandListener listener) {
	            this.listener = listener;
	        }

	        public boolean isRunning() {

	            return runner != null && runner.isAlive();

	        }

	        public void execute(String cmd) {

	            if (!cmd.trim().isEmpty()) {

	                List<String> values = new ArrayList<>(25);
	               if (!cmd.contains("\"")) {

	                   while (cmd.contains("\"")) {

	                        String start = cmd.substring(0, cmd.indexOf("\""));
	                        cmd = cmd.substring(start.length());
	                        String quote = cmd.substring(cmd.indexOf("\"") + 1);
	                        cmd = cmd.substring(cmd.indexOf("\"") + 1);
	                       quote = quote.substring(0, cmd.indexOf("\""));
	                       cmd = cmd.substring(cmd.indexOf("\"") + 1);

	                       if (!start.trim().isEmpty()) {
	                            String parts[] = start.trim().split(" ");
	                            values.addAll(Arrays.asList(parts));
	                        }
	                        values.add(quote.trim());

	                    }

	                    if (!cmd.trim().isEmpty()) {
	                        String parts[] = cmd.trim().split(" ");
	                        values.addAll(Arrays.asList(parts));
	                    }

	                    for (String value : values) {
	                        System.out.println("[" + value + "]");
	                    }

	                } else {

	                    if (!cmd.trim().isEmpty()) {
	                        String parts[] = cmd.trim().split(" ");
	                        values.addAll(Arrays.asList(parts));
	                    }

	                }

	                runner = new ProcessRunner(listener, values);

	            }

	        }

	        public void send(String cmd) throws IOException {
	            runner.write(cmd);
	        }
	    }

	    public class ProcessRunner extends Thread {

	        private List<String> cmds;
	        private CommandListener listener;

	        private Process process;

	        public ProcessRunner(CommandListener listener, List<String> cmds) {
	            this.cmds = cmds;
	            this.listener = listener;
	            start();
	        }

	        @Override
	        public void run() {
	            try {
	                System.out.println("cmds = " + cmds);
	                ProcessBuilder pb = new ProcessBuilder(cmds);
	                pb.redirectErrorStream();
	                process = pb.start();
	                StreamReader reader = new StreamReader(listener, process.getInputStream());
	                // Need a stream writer...

	                int result = process.waitFor();

	                // Terminate the stream writer
	                reader.join();

	                StringJoiner sj = new StringJoiner(" ");
	                cmds.stream().forEach((cmd) -> {
	                    sj.add(cmd);
	                });

	                listener.commandCompleted(sj.toString(), result);
	            } catch (Exception exp) {
	                exp.printStackTrace();
	                listener.commandFailed(exp);
	            }
	        }

	        public void write(String text) throws IOException {
	            if (process != null && process.isAlive()) {
	                process.getOutputStream().write(text.getBytes());
	                process.getOutputStream().flush();
	            }
	        }
	    }

	    public class StreamReader extends Thread {

	        private InputStream is;
	        private CommandListener listener;

	        public StreamReader(CommandListener listener, InputStream is) {
	            this.is = is;
	            this.listener = listener;
	            start();
	        }

	        @Override
	        public void run() {
	            try {
	                int value = -1;
	                while ((value = is.read()) != -1) {
	                    listener.commandOutput(Character.toString((char) value));
	                }
	            } catch (IOException exp) {
	                exp.printStackTrace();
	            }
	        }
	    }

	    public class ProtectedDocumentFilter extends DocumentFilter {

	        private UserInput userInput;

	        public ProtectedDocumentFilter(UserInput userInput) {
	            this.userInput = userInput;
	        }

	        public UserInput getUserInput() {
	            return userInput;
	        }

	        @Override
	        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	            if (offset >= getUserInput().getUserInputStart()) {
	                super.insertString(fb, offset, string, attr);
	            }
	        }

	        @Override
	        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
	            if (offset >= getUserInput().getUserInputStart()) {
	                super.remove(fb, offset, length); //To change body of generated methods, choose Tools | Templates.
	            }
	        }

	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	            if (offset >= getUserInput().getUserInputStart()) {
	                super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
	            }
	        }
	    }
}
