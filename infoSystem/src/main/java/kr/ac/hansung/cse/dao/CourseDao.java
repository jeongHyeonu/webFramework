package kr.ac.hansung.cse.dao;

import kr.ac.hansung.cse.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseDao {

    private JdbcTemplate jdbcTemplate;  // psa(portable service abstraction, sql(x) api

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getRowCount() {
        String sqlStatement= "select count(*) from courses";
        return jdbcTemplate.queryForObject(sqlStatement, Integer.class);

    }

    //query and return a single object
    public Course getCourse(String name) {

        String sqlStatement= "select * from courses where name=?";
        return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name},
                new RowMapper<Course>() {

                    @Override
                    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                        Course course= new Course();

                        course.setId(rs.getInt("id"));
                        course.setYear(rs.getInt("year"));
                        course.setSemester(rs.getInt("semester"));
                        course.setCode(rs.getString("code"));
                        course.setName(rs.getString("name"));
                        course.setDivision(rs.getString("division"));
                        course.setProf(rs.getString("prof"));
                        course.setDegree(rs.getInt("degree"));

                        return course;
                    }
                });
    }

    //query and return multiple objects
    // cRud method
    public List<Course> getCourses() {

        String sqlStatement= "select * from courses";
        return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                Course course= new Course();

                course.setYear(rs.getInt("year"));
                course.setSemester(rs.getInt("semester"));
                course.setCode(rs.getString("code"));
                course.setName(rs.getString("name"));
                course.setDivision(rs.getString("division"));
                course.setProf(rs.getString("prof"));
                course.setDegree(rs.getInt("degree"));

                return course;
            }
        });
    }

    public List<Course> getCoursesByGroup() {

        String sqlStatement= "select year, semester, SUM(degree) as totalDegree from courses group by year,semester ORDER BY year ASC, semester ASC";
        return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                Course course= new Course();

                course.setYear(rs.getInt("year"));
                course.setSemester(rs.getInt("semester"));
                course.setTotalDegree(rs.getInt("totalDegree"));

                return course;
            }
        });
    }

    public List<Course> getAllCoursesByYearSemester(int year, int semester) {

        String sqlStatement= "SELECT year, semester, name, division, prof, degree FROM courses WHERE year = ? AND semester = ?";

        return jdbcTemplate.query(sqlStatement, new Object[]{year, semester}, new RowMapper<Course>() {

            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

                Course course= new Course();

                course.setYear(rs.getInt("year"));
                course.setSemester(rs.getInt("semester"));
                course.setName(rs.getString("name"));
                course.setDivision(rs.getString("division"));
                course.setProf(rs.getString("prof"));
                course.setDegree(rs.getInt("degree"));

                return course;
            }
        });
    }


    // Crud method
    public boolean insert(Course course) {

        int year = course.getYear();
        int semester = course.getSemester();
        String code = course.getCode();
        String name = course.getName();
        String division = course.getDivision();
        String prof = course.getProf();
        int degree = course.getDegree();

        String sqlStatement= "insert into courses (year,semester,code,name,division,prof,degree) values (?,?,?,?,?,?,?)";

        return (jdbcTemplate.update(sqlStatement, new Object[] {year,semester,code,name,division,prof,degree}) == 1);
    }

    // crUd method
    public boolean update(Course course) {

        int id = course.getId();
        String name= course.getName();

        String sqlStatement= "update offers set name=?, email=?, text=? where id=?";

        return (jdbcTemplate.update(sqlStatement, new Object[] {name, id}) == 1);
    }

    //cruD method
    public boolean delete(int id) {
        String sqlStatement= "delete from offers where id=?";
        return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1);
    }

}
