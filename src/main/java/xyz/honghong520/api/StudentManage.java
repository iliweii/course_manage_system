package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Student;
import xyz.honghong520.service.StudentService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentManage {

    @Autowired
    private StudentService action;

    @PostMapping("/StudentManageDeleteBatchServlet")
    public String StudentManageDeleteBatchServlet(
            @RequestParam("sid") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < gsonarr.length; i++) {
            Student s = new Student();
            s.setSid(gsonarr[i]);
            students.add(s);
        }

        // 获取返回值
        int back = action.deleteStudentsBatch(students);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentManageDeleteServlet")
    public String StudentManageDeleteServlet(
            @RequestParam("sid") String studentId
    ) {
        if (studentId == null) {
            return ("empty");
        }

        Integer sid = Integer.parseInt(studentId);
        // 获取返回值
        int back = action.deleteStudent(sid);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentManageInsertServlet")
    public String StudentManageInsertServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Student List
        List<Student> students = gson.fromJson(json, new TypeToken<List<Student>>() {
        }.getType());

        // 获取返回值
        int back = action.insertStudentBatch(students);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentManageListServlet")
    public String StudentManageListServlet() {
        // 查询所有学生信息
        List<Student> list = action.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/StudentManageQueryServlet")
    public String StudentManageQueryServlet(
            @RequestParam("sid") String SId
    ) {
        if (SId == null) {
            return ("empty");
        }

        Integer sid = Integer.parseInt(SId);
        // 获取返回值
        Student back = action.queryStudentById(sid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/StudentManageSearchServlet")
    public String StudentManageSearchServlet(
            @RequestParam("q") String q
    ) {
        try {
            q = java.net.URLDecoder.decode(q,"utf-8");
            // 获取返回值
            List<Student> list = action.searchByKeyword(q);

            // 将信息集合转化为数组
            Gson gson = new Gson();
            String json = gson.toJson(list);
            // 输出信息
            return (json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/StudentManageUpdateServlet")
    public String StudentManageUpdateServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Student对象
        Student student = gson.fromJson(json, Student.class);

        // 把student对象传给updateStudent方法
        int back = action.updateStudent(student);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

}
