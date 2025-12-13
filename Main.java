import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        System.out.println("==========================================");
        System.out.println("   UNIVERSITY PLACEMENT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        
        while(true) {
            System.out.println("\n1. Search Eligible Candidates");
            System.out.println("2. Exit");
            System.out.print("Select Option: ");
            int choice = sc.nextInt();

            if (choice == 2) {
                System.out.println("Exiting system...");
                break;
            }

            if (choice == 1) {
                System.out.print("Enter Min CPI Required (e.g., 7.5): ");
                double minCPI = sc.nextDouble();
                
                System.out.print("Enter Max Backlogs Allowed (e.g., 0): ");
                int maxBacklogs = sc.nextInt();

                System.out.println("\nSearching database...");
                List<Student> students = dao.getEligibleStudents(minCPI, maxBacklogs);

                if(students.isEmpty()) {
                    System.out.println(">> NO CANDIDATES FOUND.");
                } else {
                    System.out.println(">> FOUND " + students.size() + " ELIGIBLE CANDIDATES:");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                }
            }
        }
        sc.close();
    }
}