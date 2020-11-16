package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.dao.TeacherDao;
import xyz.honghong520.entity.Teacher;
import xyz.honghong520.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher login(Teacher teacher) {
        return this.teacherDao.login(teacher);
    }

    @Override
    public List<Teacher> queryAll() {
        return this.teacherDao.queryAll();
    }

    @Override
    public int insertTeacherBatch(List<Teacher> teachers) {
        return this.teacherDao.insertTeacherBatch(teachers);
    }

    @Override
    public int deleteTeacher(Integer tid) {
        return this.teacherDao.deleteTeacher(tid);
    }

    @Override
    public int deleteTeachersBatch(List<Teacher> teachers) {
        return this.teacherDao.deleteTeachersBatch(teachers);
    }

    @Override
    public Teacher queryTeacherById(Integer tid) {
        return this.teacherDao.queryTeacherById(tid);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return this.teacherDao.updateTeacher(teacher);
    }

    @Override
    public List<Teacher> searchByKeyword(String q) {
        return this.teacherDao.searchByKeyword(q);
    }

}
