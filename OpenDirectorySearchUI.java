import java.io.File;
import java.util.Scanner;

public class OpenDirectorySearchUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message and directory input
        System.out.println("Welcome to Open Directory Search!");
        System.out.println("Enter the directory path to search in:");
        String directoryPath = scanner.nextLine().trim();

        // Validate directory
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Invalid directory. Exiting program.");
            scanner.close();
            return;
        }

        // Search loop
        while (true) {
            System.out.println("Enter your search query (or type 'exit' to quit):");
            String query = scanner.nextLine().trim();
            if (query.equalsIgnoreCase("exit")) break;

            searchFiles(directory, query);
        }

        scanner.close();
    }

    // Search method
    private static void searchFiles(File directory, String query) {
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("Directory is empty or unreadable.");
            return;
        }

        boolean found = false;
        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(file, query);
            } else if (file.getName().toLowerCase().contains(query.toLowerCase())) {
                System.out.println("Found: " + file.getPath());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No files found for \"" + query + "\".");
        }
    }
}
