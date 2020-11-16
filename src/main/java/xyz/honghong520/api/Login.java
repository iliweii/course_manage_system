package xyz.honghong520.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Admin;
import xyz.honghong520.entity.Student;
import xyz.honghong520.entity.Teacher;
import xyz.honghong520.entity.User;
import xyz.honghong520.service.AdminService;
import xyz.honghong520.service.StudentService;
import xyz.honghong520.service.TeacherService;
import xyz.honghong520.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class Login {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 登录
     * @param Name
     * @param Pwd
     * @param Identity
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/LoginServlet")
    public String login(@RequestParam("tbuser") String Name,
                        @RequestParam("tbpwd") String Pwd,
                        @RequestParam("identity") String Identity,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (Name == null || Pwd == null || Identity == null) {
            return "empty";
        }
        // 高级管理员身份登录
        if ("admin".equals(Identity)) {
            Admin admin = new Admin();
            // 把用户名、密码封装在Admin对象中
            admin.setAname(Name);
            admin.setApwd(Pwd);
            // 把Admin对象传给login方法
            Admin a = adminService.login(admin);
            if (a != null) {
                // 存储在Session中
                session.setAttribute("username", "admin" + Name);
                response.addCookie(new Cookie("identity", "admin".toString()));
                response.addCookie(new Cookie("aid", a.getAid().toString()));
                return "success";
            } else {
                return "failed";
            }
        }
        // 管理员身份登录
        else if ("user".equals(Identity)) {
            User user = new User();
            // 把用户名、密码封装在Tbuser对象中
            user.setTbuser(Name);
            user.setTbpwd(Pwd);
            // 把user对象传给login方法
            User u = userService.login(user);
            if (u != null) {
                session.setAttribute("username", "user" + Name);
                response.addCookie(new Cookie("identity", "user".toString()));
                response.addCookie(new Cookie("uid", u.getTbid().toString()));
                return ("success");
            } else {
                return ("failed");
            }
        }
        // 学生身份登录
        else if ("student".equals(Identity)) {
            Student student = new Student();
            // 把学号、密码封装在Student对象中
            student.setSno(Name);
            student.setSpwd(Pwd);
            // 把student对象传给login方法
            Student s = studentService.login(student);
            if (s != null) {
                session.setAttribute("username", "student" + Name);
                response.addCookie(new Cookie("identity", "student".toString()));
                response.addCookie(new Cookie("sid", s.getSid().toString()));
                return "success";
            } else {
                return "failed";
            }
        }
        // 教师身份登录
        else if ("teacher".equals(Identity)) {
            Teacher teacher = new Teacher();
            // 把教师号、密码封装在Teacher对象中
            teacher.setTno(Name);
            teacher.setTpwd(Pwd);
            // 把student对象传给login方法
            Teacher t = teacherService.login(teacher);
            if (t != null) {
                session.setAttribute("username", "teacher" + Name);
                response.addCookie(new Cookie("identity", "teacher".toString()));
                response.addCookie(new Cookie("tid", t.getTid().toString()));
                return "success";
            } else {
                return "failed";
            }
        }
        // 无法识别的身份
        else {
            return "login";
        }
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/LogoutServlet")
    public String loginout(HttpServletRequest request,
                           HttpServletResponse response) {
        // 将Session 对象中的username 对象移除
        request.getSession().removeAttribute("username");
        // 清除本次使用的Cookie
        Cookie cookie = new Cookie("identity", null);
        cookie.setMaxAge(0);
        Cookie aid = new Cookie("aid", null);
        aid.setMaxAge(0);
        Cookie uid = new Cookie("uid", null);
        uid.setMaxAge(0);
        Cookie sid = new Cookie("sid", null);
        sid.setMaxAge(0);
        Cookie tid = new Cookie("tid", null);
        tid.setMaxAge(0);
        response.addCookie(cookie);
        response.addCookie(aid);
        response.addCookie(uid);
        response.addCookie(sid);
        response.addCookie(tid);
        return "success";
    }

}
