package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.dao.CourseDao;
import kr.ac.hansung.cse.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    //service -> dao
    @Autowired
    private CourseDao courseDao;

    public List<Course> getAllCoursesByGroup() {
        return courseDao.getCoursesByGroup();
    }

    public List<Course> getAllCoursesByYearSemester(int year, int semester) {
        return courseDao.getAllCoursesByYearSemester(year,semester);
    }

    public void insert(Course course) {
        courseDao.insert(course);
    }
}
