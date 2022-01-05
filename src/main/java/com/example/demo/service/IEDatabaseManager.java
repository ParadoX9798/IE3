package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
public class IEDatabaseManager {

    String url = "jdbc:mysql://localhost:3306/classie";
    String user = "";
    String pass = "";


    @Autowired
    public IEDatabaseManager(@Value("${database-username:root}") String user,
                             @Value("${database-password:}") String pass) {
        this.pass = pass;
        this.user = user;
    }

    public String registerInstructor(Instructor instructor) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(instructor.getRegisterQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return "There is an internal error :( call administrator.";
        }
        return "The instructor inserted successfully!!!";
    }

    public String registerStudent(Student student) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(student.getRegisterQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return "There is an internal error :( call administrator.";
        }
        return "The student inserted successfully!!!";
    }

//    public String registerCourse(Course course, Instructor instructor) {
//        try (Connection conn = DriverManager.getConnection(url, user, pass);
//
//        ) {
//            PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM course where name =? ORDER BY ID");
//            stmt.setString(1, course.getName());
//            ResultSet resultSet = stmt.executeQuery();
////            stmt.close();
//
//            PreparedStatement stmt2 = conn.prepareStatement("SELECT ID FROM instructor WHERE NC=? ORDER BY ID", ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            stmt2.setString(1, instructor.getNC());
//            ResultSet resultSet2 = stmt2.executeQuery();
//            resultSet2.next();
//            int insID = resultSet2.getInt("ID");
//            long CID = -1;
////            stmt2.close();
//
//            if (!resultSet.isBeforeFirst()) {
//                Statement stmt3 = conn.createStatement();
//                int rowEffected = stmt3.executeUpdate(course.getRegisterQuery(), Statement.RETURN_GENERATED_KEYS);
//                if (rowEffected == 1) {
//                    ResultSet generatedKeys = stmt3.getGeneratedKeys();
//                    while (generatedKeys.next()) {
//                        CID = generatedKeys.getLong(1);
//                    }
//                }
////                stmt3.close();
//            }
//
//            PreparedStatement stmt4 = conn.prepareStatement("INSERT INTO instructor_course (insID, CID) VALUES (?, ?)");
//            stmt4.setInt(1, insID);
//            if (CID != -1) {
//                stmt4.setInt(2, (int) CID);
//            } else {
//                resultSet.next();
//                stmt4.setInt(2, resultSet.getInt("ID"));
//            }
//            System.out.println(insID);
//            stmt4.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return "There is an internal error :( call administrator.";
//        }
//        return "The course inserted successfully!!!";
//    }

//    public String registerCourse(Course course, Instructor instructor) {
//        try (Connection conn = DriverManager.getConnection(url, user, pass);
//             Statement stmt = conn.createStatement();
//        ) {
//            stmt.executeUpdate("INSERT INTO instructor_course(insID, CID) VALUES ('" + instructor.getId() + "','" + course.getId() + "');");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "There is an internal error :( call administrator.";
//        }
//        return "The course inserted successfully!!!";
//    }

    public String registerCourse(Course course) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(course.getRegisterQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            return "There is an internal error :( call administrator.";
        }
        return "The course inserted successfully!!!";
    }

    public String assignCourse(Course course, Instructor instructor) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate("INSERT INTO instructor_course(insID, CID) VALUES ('" + instructor.getId() + "','" + course.getId() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
            return "There is an internal error :( call administrator.";
        }
        return "The course assigned successfully!!!";
    }

    public List<Course> showCourse(Course course) {
        ResultSet resultSet = null;
        List<Course> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            resultSet = stmt.executeQuery(course.showCoursesQuery());
            while (resultSet.next()) {
                list.add(new Course(resultSet.getInt("id"), resultSet.getString("name").toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Instructor> showInstructors(Instructor instructor) {
        ResultSet resultSet = null;
        List<Instructor> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            resultSet = stmt.executeQuery(instructor.showInstructorsQuery());
            while (resultSet.next()) {
                list.add(new Instructor(resultSet.getInt("id"), resultSet.getString("name").toUpperCase(), resultSet.getString("family").toUpperCase(), resultSet.getString("NC")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<InstructorCourse> showInstructorCourse(InstructorCourse instructor_course) {
        ResultSet resultSet = null;
        List<InstructorCourse> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            resultSet = stmt.executeQuery(instructor_course.showInstructor_courseQuery());
            while (resultSet.next()) {
                list.add(new InstructorCourse(resultSet.getInt(1),
                        resultSet.getString(2).toUpperCase() + " " + resultSet.getString(3).toUpperCase(),
                        resultSet.getString(4).toUpperCase()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public String takeCourse(Student student, int relation_id) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            ResultSet resultSet = stmt.executeQuery("SELECT ID FROM student WHERE SID='" + student.getSID() + "'");
            resultSet.next();
            int student_id = resultSet.getInt(1);
            stmt.executeUpdate("INSERT INTO student_course(SID, instructor_course_id) VALUES ('" + student_id + "','" + relation_id + "');");
        } catch (SQLException e) {
            e.printStackTrace();
            return "There is an internal error :( call administrator.";
        }
        return "The student took course successfully!!!";
    }

    public List<InstructorCourse> showInstructorCourse(String NC) {
        ResultSet resultSet = null;
        List<InstructorCourse> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            resultSet = stmt.executeQuery("SELECT instructor.name,course.name\n" +
                    "                FROM ((instructor_course\n" +
                    "                INNER JOIN instructor ON instructor_course.insID = instructor.id)\n" +
                    "                INNER JOIN course ON instructor_course.CID = course.id) where instructor.NC ='" + NC + "'");
            while (resultSet.next()) {
                list.add(new InstructorCourse(resultSet.getString(1), resultSet.getString(2).toUpperCase()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<StudentCourse> showStudentCourse(String SID) {
        ResultSet resultSet = null;
        List<StudentCourse> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
        ) {
            resultSet = stmt.executeQuery("SELECT course.name from course where id in (SELECT CID FROM \n" +
                    "                ((student_course INNER JOIN student ON student_course.SID = student.id) \n" +
                    "                INNER JOIN instructor_course ON student_course.instructor_course_ID = instructor_course.ID)\n" +
                    "                where student.SID='" + SID + "') ");
            while (resultSet.next()) {
                list.add(new StudentCourse(resultSet.getString(1).toUpperCase()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}

