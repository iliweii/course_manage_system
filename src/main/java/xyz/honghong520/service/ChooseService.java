package xyz.honghong520.service;

import xyz.honghong520.entity.Choose;

import java.util.List;

public interface ChooseService {

    // 查询所有选课信息
    List<Choose> queryAll();

    // 学生申请选课
    int insertChoose(Choose choose);

    // 批量添加选课信息
    int insertChooseBatch(List<Choose> chooses);

    // 删除选课信息
    int deleteChoose(Integer scid);

    // 批量删除选课信息
    int deleteChoosesBatch(List<Choose> chooses);

    // 根据选课id查询选课信息
    Choose queryChooseById(Integer scid);

    // 修改选课信息
    int updateChoose(Choose choose);

    // 通过学生选课
    int allowChoose(Choose choose);

    // 根据学号、课程号查询选课信息
    List<Choose> searchByKeyword(String q);

    int allowChoosesBatch(List<Choose> chooses);

    int publicChoose(String tno, String clno, String cono);

    List<Choose> queryAllGrade();

    int updateChoosesBatch(List<Choose> chooses);

    List<Choose> queryAllGradeByQ(String q);

    List<Choose> queryAllGradeByClno(String clno);

    List<Choose> queryAllGradeByCono(String cono);

    List<Choose> queryAllGradeBySid(String sid);

    List<Choose> queryAllChooseBySid(String sid);

    int applyChooseBySid(String sid, String cno);

    List<Choose> queryAllGradeByTid(String tid);

    List<Choose> searchAllGradeByTid(String tid, String q);

}
