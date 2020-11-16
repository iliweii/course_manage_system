package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Teacher;
import xyz.honghong520.service.TeacherService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherManage {

    @Autowired
    private TeacherService action;

    @PostMapping("/TeacherManageDeleteBatchServlet")
    public String TeacherManageDeleteBatchServlet(
            @RequestParam("tid") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Teacher> teachers = new ArrayList<Teacher>();
        for (int i = 0; i < gsonarr.length; i++) {
            Teacher t = new Teacher();
            t.setTid(gsonarr[i]);
            teachers.add(t);
        }

        // 获取返回值
        int back = action.deleteTeachersBatch(teachers);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherManageDeleteServlet")
    public String TeacherManageDeleteServlet(
            @RequestParam("tid") String teacherId
    ) {
        if (teacherId == null) {
            return ("empty");
        }

        Integer tid = Integer.parseInt(teacherId);
        // 获取返回值
        int back = action.deleteTeacher(tid);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherManageInsertServlet")
    public String asdasdasd(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Teacher List
        List<Teacher> teachers = gson.fromJson(json, new TypeToken<List<Teacher>>() {
        }.getType());

        // 获取返回值
        int back = action.insertTeacherBatch(teachers);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherManageListServlet")
    public String TeacherManageListServlet() {
        // 查询所有教师信息
        List<Teacher> list = action.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/TeacherManageQueryServlet")
    public String TeacherManageQueryServlet(
            @RequestParam("tid") String TId
    ) {
        if (TId == null) {
            return ("empty");
        }

        Integer tid = Integer.parseInt(TId);
        // 获取返回值
        Teacher back = action.queryTeacherById(tid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/TeacherManageSearchServlet")
    public String TeacherManageSearchServlet(
            @RequestParam("q") String q
    ) {
        try {
            q = java.net.URLDecoder.decode(q,"utf-8");
            // 获取返回值
            List<Teacher> list = action.searchByKeyword(q);

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

    @PostMapping("/TeacherManageUpdateServlet")
    public String TeacherManageUpdateServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Student对象
        Teacher teacher = gson.fromJson(json, Teacher.class);

        // 把student对象传给updateStudent方法
        int back = action.updateTeacher(teacher);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
