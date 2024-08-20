package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.model.Course;
import kr.ac.hansung.cse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    // Controller -> Service -> Dao
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String showCourses(@RequestParam(value="year", required = false) Integer year,
                              @RequestParam(value="semester", required = false) Integer semester,
                              Model model) {

        List<Course> courses = courseService.getAllCoursesByGroup();
        model.addAttribute("id_courses", courses);

        if(year!=null && semester!=null){
            List<Course> courses_detail = courseService.getAllCoursesByYearSemester(year,semester);
            model.addAttribute("course_detail", courses_detail);
        }
        return "courses";
    }

    @GetMapping("/courseEnroll")
    public String showCourses(Model model) {

        model.addAttribute("course", new Course());

        return "courseEnroll";
    }

    @PostMapping("/doEnroll")
    public String doEnroll(Model model, @Valid Course course, BindingResult result) {

        // System.out.println(offer);
        if(result.hasErrors()) {
            System.out.println("== Form data does not validated ==");

            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }

            return "courseEnroll";
        }

        // Controller -> Service -> Dao
        courseService.insert(course);

        return "coursecreated";
    }

    @GetMapping("/courseEnrollList")
    public String showCourseEnrolled(Model model) {

        List<Course> courses_2024_2 = courseService.getAllCoursesByYearSemester(2024,2);
        model.addAttribute("courses_2024_2", courses_2024_2);

        return "courseEnrollList";
    }
}
