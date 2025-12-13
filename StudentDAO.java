import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // The Logic: Find students who meet the criteria
    public List<Student> getEligibleStudents(double minCPI, int maxBacklogs) {
        List<Student> eligibleList = new ArrayList<>();
        
        // The Query: Filter by CPI and Backlogs
        String query = "SELECT * FROM Student_Master WHERE cpi >= ? AND backlogs <= ?";

        // Try-with-resources (Auto-closes connection)
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Prevent SQL Injection by setting parameters
            pstmt.setDouble(1, minCPI);
            pstmt.setInt(2, maxBacklogs);
            
            ResultSet rs = pstmt.executeQuery();

            // Loop through results and create Student objects
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getDouble("cpi"),
                    rs.getInt("backlogs")
                );
                eligibleList.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eligibleList;
    }
}