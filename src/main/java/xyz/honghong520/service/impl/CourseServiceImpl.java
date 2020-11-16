package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.dao.CourseDao;
import xyz.honghong520.entity.Course;
import xyz.honghong520.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> queryAll() {
        return this.courseDao.queryAll();
    }

    @Override
    public int insertCourseBatch(List<Course> course) {
        return this.courseDao.insertCourseBatch(course);
    }

    @Override
    public int deleteCourse(Integer cid) {
        return this.courseDao.deleteCourse(cid);
    }

    @Override
    public int deleteCoursesBatch(List<Course> courses) {
        return this.courseDao.deleteCoursesBatch(courses);
    }

    @Override
    public Course queryCourseById(Integer cid) {
        return this.courseDao.queryCourseById(cid);
    }

    @Override
    public Course queryCourseByCno(String cno) {
        return this.courseDao.queryCourseByCno(cno);
    }

    @Override
    public int updateCourse(Course course) {
        return this.courseDao.updateCourse(course);
    }

    @Override
    public List<Course> searchByKeyword(String q) {
        return this.courseDao.searchByKeyword(q);
    }

}
