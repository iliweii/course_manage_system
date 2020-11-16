package xyz.honghong520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.honghong520.dao.ChooseDao;
import xyz.honghong520.dao.StudentDao;
import xyz.honghong520.dao.TeachingDao;
import xyz.honghong520.entity.Choose;
import xyz.honghong520.entity.Student;
import xyz.honghong520.entity.Teaching;
import xyz.honghong520.service.ChooseService;

import java.util.List;

@Service
public class ChooseServiceImpl implements ChooseService {

	@Autowired
	private ChooseDao chooseDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeachingDao teachingDao;

	@Override
	public List<Choose> queryAll() {
		return this.chooseDao.queryAll();
	}

	@Override
	public int insertChoose(Choose choose) {
		return this.chooseDao.insertChoose(choose);
	}

	@Override
	public int insertChooseBatch(List<Choose> chooses) {
		return this.chooseDao.insertChooseBatch(chooses);
	}

	@Override
	public int deleteChoose(Integer scid) {
		return this.chooseDao.deleteChoose(scid);
	}

	@Override
	public int deleteChoosesBatch(List<Choose> chooses) {
		return this.chooseDao.deleteChoosesBatch(chooses);
	}

	@Override
	public Choose queryChooseById(Integer scid) {
		return this.chooseDao.queryChooseById(scid);
	}

	@Override
	public int updateChoose(Choose choose) {
		return this.chooseDao.updateChoose(choose);
	}

	@Override
	public int allowChoose(Choose choose) {
		return this.chooseDao.allowChoose(choose);
	}

	@Override
	public List<Choose> searchByKeyword(String q) {
		return this.chooseDao.searchByKeyword(q);
	}

	@Override
	public int allowChoosesBatch(List<Choose> chooses) {
		return this.chooseDao.allowChooseBatch(chooses);
	}

	@Override
	public int publicChoose(String tno, String clno, String cono) {
		// 先新增教师授课
		Teaching teaching = new Teaching();
		teaching.setCno(cono);
		teaching.setTno(tno);
		teaching.setStatus(1);
		this.teachingDao.insertTeaching(teaching);
		// 再根据clno查询班级内学生信息
		List<Student> student = this.studentDao.queryStudentByCno(clno);
		// 再把所有学生选课
		return this.chooseDao.insertChooseBatchPublic(student, cono);
	}

	@Override
	public List<Choose> queryAllGrade() {
		List<Choose> chooses = this.chooseDao.queryAllGrade();
		List<Teaching> teaching = this.teachingDao.queryAllGroupTeacher();
		// 将授课信息合并
		for (int i = 0; i < chooses.size(); i++) {
			Choose sc = chooses.get(i);
			for (int j = 0; j < teaching.size(); j++) {
				Teaching tc = teaching.get(j);
				if (sc.getCno().equals(tc.getCno())) {
					sc.setTeacher(tc.getTeacher());
					break;
				}
			}
		}
		return chooses;
	}

	@Override
	public int updateChoosesBatch(List<Choose> chooses) {
		return this.chooseDao.updateChoosesBatch(chooses);
	}

	@Override
	public List<Choose> queryAllGradeByQ(String q) {
		List<Choose> chooses = this.chooseDao.queryAllGradeByQ(q);
		List<Teaching> teaching = this.teachingDao.queryAllGroupTeacher();
		// 将授课信息合并
		for (int i = 0; i < chooses.size(); i++) {
			Choose sc = chooses.get(i);
			for (int j = 0; j < teaching.size(); j++) {
				Teaching tc = teaching.get(j);
				if (sc.getCno().equals(tc.getCno())) {
					sc.setTeacher(tc.getTeacher());
					break;
				}
			}
		}
		return chooses;
	}

	@Override
	public List<Choose> queryAllGradeByClno(String clno) {
		List<Choose> chooses = this.chooseDao.queryAllGradeByClno(clno);
		List<Teaching> teaching = this.teachingDao.queryAllGroupTeacher();
		// 将授课信息合并
		for (int i = 0; i < chooses.size(); i++) {
			Choose sc = chooses.get(i);
			for (int j = 0; j < teaching.size(); j++) {
				Teaching tc = teaching.get(j);
				if (sc.getCno().equals(tc.getCno())) {
					sc.setTeacher(tc.getTeacher());
					break;
				}
			}
		}
		return chooses;
	}

	@Override
	public List<Choose> queryAllGradeByCono(String cono) {
		List<Choose> chooses = this.chooseDao.queryAllGradeByCono(cono);
		List<Teaching> teaching = this.teachingDao.queryAllGroupTeacher();
		// 将授课信息合并
		for (int i = 0; i < chooses.size(); i++) {
			Choose sc = chooses.get(i);
			for (int j = 0; j < teaching.size(); j++) {
				Teaching tc = teaching.get(j);
				if (sc.getCno().equals(tc.getCno())) {
					sc.setTeacher(tc.getTeacher());
					break;
				}
			}
		}
		return chooses;
	}

	@Override
	public List<Choose> queryAllGradeBySid(String sid) {
		List<Choose> chooses = this.chooseDao.queryAllGradeBySid(sid);
		List<Teaching> teaching = this.teachingDao.queryAllGroupTeacher();
		// 将授课信息合并
		for (int i = 0; i < chooses.size(); i++) {
			Choose sc = chooses.get(i);
			for (int j = 0; j < teaching.size(); j++) {
				Teaching tc = teaching.get(j);
				if (sc.getCno().equals(tc.getCno())) {
					sc.setTeacher(tc.getTeacher());
					break;
				}
			}
		}
		return chooses;
	}

	@Override
	public List<Choose> queryAllChooseBySid(String sid) {
		return this.chooseDao.queryAllChooseBySid(sid);
	}

	@Override
	public int applyChooseBySid(String sid, String cno) {
		int id = Integer.parseInt(sid);
		Student student = this.studentDao.queryStudentById(id);
		Choose choose = new Choose();
		choose.setCno(cno);
		choose.setSno(student.getSno());
		return this.chooseDao.insertChoose(choose);
	}

	@Override
	public List<Choose> queryAllGradeByTid(String tid) {
		return this.chooseDao.queryAllGradeByTid(tid);
	}

	@Override
	public List<Choose> searchAllGradeByTid(String tid, String q) {
		return this.chooseDao.searchAllGradeByTid(tid, q);
	}

}
