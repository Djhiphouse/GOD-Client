package me.bratwurst.news.rcon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RconClientCli {
    private static final int EXIT_CODE_SUCCESS = 0;
    private static final int EXIT_CODE_INVALID_ARGUMENTS = 1;
    private static final int EXIT_CODE_AUTH_FAILURE = 2;
    private static final int DEFAULT_PORT = 25575;
    private static final String QUIT_COMMAND = "\\quit";

    public static void main(String[] args) {
        int exitCode = RconClientCli.run(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    private static int run(String[] args) {
        block21: {
            if (args.length < 3) {
                return RconClientCli.printUsage();
            }
            String[] hostAndPort = args[0].split(":");
            if (hostAndPort.length > 2) {
                return RconClientCli.printUsage();
            }
            String host = hostAndPort[0];
            int port = hostAndPort.length == 2 ? Integer.parseInt(hostAndPort[1]) : 25575;
            String password = args[1];
            ArrayList<String> commands = new ArrayList<String>(Arrays.asList(args).subList(2, args.length));
            boolean terminalMode = commands.contains("-t");
            if (terminalMode && commands.size() != 1) {
                return RconClientCli.printUsage();
            }
            try (RconClient client = RconClient.open(host, port, password);){
                Runtime.getRuntime().addShutdownHook(new Thread(client::close));
                if (terminalMode) {
                    System.out.println("Authenticated. Type \"\\quit\" to quit.");
                    System.out.print("> ");
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.trim().equals(QUIT_COMMAND)) {
                            break block21;
                        }
                        String response = client.sendCommand(line);
                        System.out.println("< " + (response.isEmpty() ? "(empty response)" : response));
                        System.out.print("> ");
                    }
                    break block21;
                }
                for (String command : commands) {
                    System.out.println("> " + command);
                    String response = client.sendCommand(command);
                    System.out.println("< " + (response.isEmpty() ? "(empty response)" : response));
                }
            }
            catch (AuthFailureException e) {
                System.err.println("Authentication failure");
                return 2;
            }
        }
        return 0;
    }

    private static int printUsage() {
        System.out.println("Usage: java -jar minecraft-rcon-client-<version>.jar <host[:port]> <password> <-t|commands>");
        System.out.println();
        System.out.println("Example 1: java -jar minecraft-rcon-client-1.0.0.jar localhost:12345 hunter2 'say Hello, world' 'teleport Notch 0 0 0'");
        System.out.println("Example 2: java -jar minecraft-rcon-client-1.0.0.jar localhost:12345 hunter2 -t");
        System.out.println();
        System.out.println("The port can be omitted, the default is 25575.");
        System.out.println("\"-t\" enables terminal mode, to enter commands in an interactive terminal.");
        return 1;
    }
}


