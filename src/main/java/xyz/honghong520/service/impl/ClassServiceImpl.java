package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.entity.Class;
import xyz.honghong520.dao.ClassDao;
import xyz.honghong520.dao.StudentDao;
import xyz.honghong520.service.ClassService;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Class> queryAll() {
        return this.classDao.queryAll();
    }

    @Override
    public int insertClassBatch(List<Class> classes) {
        return this.classDao.insertClassBatch(classes);
    }

    @Override
    public int deleteClass(Integer cid) {
        // 删除班级，先删除班级里的学生
        this.studentDao.deleteStudentByCid(cid);
        // 再删除班级信息
        return this.classDao.deleteClass(cid);
    }

    @Override
    public int deleteClasssBatch(List<Class> classes) {
        return this.classDao.deleteClasssBatch(classes);
    }

    @Override
    public Class queryClassById(Integer cid) {
        return this.classDao.queryClassById(cid);
    }

    @Override
    public int updateClass(Class aclass) {
        return this.classDao.updateClass(aclass);
    }

    @Override
    public List<Class> searchByKeyword(String q) {
        return this.classDao.searchByKeyword(q);
    }

    @Override
    public Class queryClassByCno(String cno) {
        return this.classDao.queryClassByCno(cno);
    }

}
