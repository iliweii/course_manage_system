package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.dao.StudentDao;
import xyz.honghong520.entity.Student;
import xyz.honghong520.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student login(Student student) {
        return this.studentDao.login(student);
    }

    @Override
    public List<Student> queryAll() {
        return this.studentDao.queryAll();
    }

    @Override
    public int deleteStudent(Integer sid) {
        return this.studentDao.deleteStudent(sid);
    }

    @Override
    public int deleteStudentsBatch(List<Student> students) {
        return this.studentDao.deleteStudentsBatch(students);
    }

    @Override
    public Student queryStudentById(Integer sid) {
        return this.studentDao.queryStudentById(sid);
    }

    @Override
    public int updateStudent(Student student) {
        return this.studentDao.updateStudent(student);
    }

    @Override
    public List<Student> searchByKeyword(String q) {
        return this.studentDao.searchByKeyword(q);
    }

    @Override
    public int deleteStudentByCid(Integer cid) {
        return this.studentDao.deleteStudentByCid(cid);
    }

    @Override
    public int insertStudentBatch(List<Student> students) {
        return this.studentDao.insertStudentBatch(students);
    }

}
