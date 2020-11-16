package xyz.honghong520.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    // 从 application.properties 中读取配置，如取不到默认值为Hello Shanhy
    @Value("${application.yml:Hello Shanhy}")
    private String hello = "Hello Shanhy";

    // 基本页面

    /**
     * 默认页
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 404错误界面
     */
    @RequestMapping("/404")
    public String error() {
        return "404";
    }

    // 主界面

    /**
     * 管理员基本数据管理主页面
     *
     * @return
     */
    @RequestMapping(value = {"/basedata/index", "/basedata/"})
    public String basedataIndex() {
        return "basedata/index";
    }

    /**
     * 管理员课程安排管理主页面
     *
     * @return
     */
    @RequestMapping(value = {"/coursearrange/index", "/coursearrange/"})
    public String coursearrangeIndex() {
        return "coursearrange/index";
    }

    /**
     * 管理员学生成绩管理主页面
     *
     * @return
     */
    @RequestMapping(value = {"/studentscore/index", "/studentscore/"})
    public String studentscoreIndex() {
        return "studentscore/index";
    }

    /**
     * 个人信息管理主页面
     *
     * @return
     */
    @RequestMapping(value = {"/selfinfo/index", "/selfinfo/"})
    public String selfinfoIndex() {
        return "selfinfo/index";
    }

    /**
     * 学生基本信息管理主页面
     *
     * @return
     */
    @RequestMapping(value = {"/studentdata/index", "/studentdata/"})
    public String studentdataIndex() {
        return "studentdata/index";
    }

    /**
     * 教师基本信息管理主页面
     *
     * @return
     */
    @RequestMapping(value = {"/teacherdata/index", "/teacherdata/"})
    public String teacherdataIndex() {
        return "teacherdata/index";
    }

    // 二级界面
    // 基本数据管理 basedata ->

    /**
     * 基本数据管理 管理员维护界面
     *
     * @return
     */
    @RequestMapping(value = {"/basedata/adminmanage/index", "/basedata/adminmanage/"})
    public String adminmanageIndex() {
        return "/basedata/adminmanage/index";
    }

    @RequestMapping("/basedata/adminmanage/insert")
    public String adminmanageInsert() {
        return "/basedata/adminmanage/insert";
    }

    @RequestMapping("/basedata/adminmanage/detail")
    public String adminmanageDetail() {
        return "/basedata/adminmanage/detail";
    }

    @RequestMapping("/basedata/adminmanage/search")
    public String adminmanageSearch() {
        return "/basedata/adminmanage/search";
    }

    /**
     * 基本数据管理 学生信息维护界面
     *
     * @return
     */
    @RequestMapping(value = {"/basedata/studentmanage/index", "/basedata/studentmanage/"})
    public String studentmanageIndex() {
        return "/basedata/studentmanage/index";
    }

    @RequestMapping("/basedata/studentmanage/insert")
    public String studentmanageInsert() {
        return "/basedata/studentmanage/insert";
    }

    @RequestMapping("/basedata/studentmanage/detail")
    public String studentmanageDetail() {
        return "/basedata/studentmanage/detail";
    }

    @RequestMapping("/basedata/studentmanage/search")
    public String studentmanageSearch() {
        return "/basedata/studentmanage/search";
    }

    /**
     * 基本数据管理 教师信息维护界面
     *
     * @return
     */
    @RequestMapping(value = {"/basedata/teachermanage/index", "/basedata/teachermanage/"})
    public String teachermanageIndex() {
        return "/basedata/teachermanage/index";
    }

    @RequestMapping("/basedata/teachermanage/insert")
    public String teachermanageInsert() {
        return "/basedata/teachermanage/insert";
    }

    @RequestMapping("/basedata/teachermanage/detail")
    public String teachermanageDetail() {
        return "/basedata/teachermanage/detail";
    }

    @RequestMapping("/basedata/teachermanage/search")
    public String teachermanageSearch() {
        return "/basedata/teachermanage/search";
    }

    /**
     * 基本数据管理 班级信息维护界面
     *
     * @return
     */
    @RequestMapping(value = {"/basedata/classmanage/index", "/basedata/classmanage/"})
    public String classmanageIndex() {
        return "/basedata/classmanage/index";
    }

    @RequestMapping("/basedata/classmanage/insert")
    public String classmanageInsert() {
        return "/basedata/classmanage/insert";
    }

    @RequestMapping("/basedata/classmanage/detail")
    public String classmanageDetail() {
        return "/basedata/classmanage/detail";
    }

    @RequestMapping("/basedata/classmanage/search")
    public String classmanageSearch() {
        return "/basedata/classmanage/search";
    }

    /**
     * 基本数据管理 课程信息维护界面
     *
     * @return
     */
    @RequestMapping(value = {"/basedata/coursemanage/index", "/basedata/coursemanage/"})
    public String coursemanageIndex() {
        return "/basedata/coursemanage/index";
    }

    @RequestMapping("/basedata/coursemanage/insert")
    public String coursemanageInsert() {
        return "/basedata/coursemanage/insert";
    }

    @RequestMapping("/basedata/coursemanage/detail")
    public String coursemanageDetail() {
        return "/basedata/coursemanage/detail";
    }

    @RequestMapping("/basedata/coursemanage/search")
    public String coursemanageSearch() {
        return "/basedata/coursemanage/search";
    }

    // 课程安排管理 coursearrange ->

    /**
     * 课程安排管理 学生选课安排页面
     *
     * @return
     */
    @RequestMapping(value = {"/coursearrange/studentapply/index", "/coursearrange/studentapply/"})
    public String studentapplyIndex() {
        return "/coursearrange/studentapply/index";
    }

    @RequestMapping("/coursearrange/studentapply/insert")
    public String studentapplyInsert() {
        return "/coursearrange/studentapply/insert";
    }

    @RequestMapping("/coursearrange/studentapply/search")
    public String studentapplySearch() {
        return "/coursearrange/studentapply/search";
    }

    /**
     * 课程安排管理 教师授课安排页面
     *
     * @return
     */
    @RequestMapping(value = {"/coursearrange/teacherapply/index", "/coursearrange/teacherapply/"})
    public String teacherapplyIndex() {
        return "/coursearrange/teacherapply/index";
    }

    @RequestMapping("/coursearrange/teacherapply/insert")
    public String teacherapplyInsert() {
        return "/coursearrange/teacherapply/insert";
    }

    @RequestMapping("/coursearrange/teacherapply/search")
    public String teacherapplySearch() {
        return "/coursearrange/teacherapply/search";
    }

    /**
     * 课程安排管理 公选课安排页面
     *
     * @return
     */
    @RequestMapping(value = {"/coursearrange/publicapply/index", "/coursearrange/publicapply/"})
    public String publicapplyIndex() {
        return "/coursearrange/publicapply/index";
    }

    // 学生成绩管理 studentscore ->

    /**
     * 学生成绩管理 学生成绩管理
     *
     * @return
     */
    @RequestMapping(value = {"/studentscore/studentresult/index", "/studentscore/studentresult/"})
    public String studentresultIndex() {
        return "/studentscore/studentresult/index";
    }

    // 个人信息管理 selfinfo ->

    /**
     * 个人信息管理 修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/selfinfo/update/index", "/selfinfo/update/"})
    public String updateIndex() {
        return "/selfinfo/update/index";
    }

    // 学生:基本信息管理 studentdata ->

    /**
     * 学生:基本信息管理 我的成绩查询
     *
     * @return
     */
    @RequestMapping(value = {"/studentdata/resultquery/index", "/studentdata/resultquery/"})
    public String resultqueryIndex() {
        return "/studentdata/resultquery/index";
    }

    /**
     * 学生:基本信息管理 我的选课查询
     *
     * @return
     */
    @RequestMapping(value = {"/studentdata/coursequery/index", "/studentdata/coursequery/"})
    public String coursequeryIndex() {
        return "/studentdata/coursequery/index";
    }

    /**
     * 学生:基本信息管理 申请新课程
     *
     * @return
     */
    @RequestMapping(value = {"/studentdata/applycourse/index", "/studentdata/applycourse/"})
    public String applycourseIndex() {
        return "/studentdata/applycourse/index";
    }

    // 教师:基本信息管理 teacherdata ->

    /**
     * 教师:基本信息管理 学生成绩管理
     *
     * @return
     */
    @RequestMapping(value = {"/teacherdata/resultmanage/index", "/teacherdata/resultmanage/"})
    public String resultmanageIndex() {
        return "/teacherdata/resultmanage/index";
    }

    /**
     * 教师:基本信息管理 我的授课查询
     *
     * @return
     */
    @RequestMapping(value = {"/teacherdata/teachquery/index", "/teacherdata/teachquery/"})
    public String teachqueryIndex() {
        return "/teacherdata/teachquery/index";
    }

    /**
     * 教师:基本信息管理 申请新授课
     *
     * @return
     */
    @RequestMapping(value = {"/teacherdata/teachapply/index", "/teacherdata/teachapply/"})
    public String teachapplyIndex() {
        return "/teacherdata/teachapply/index";
    }

}
