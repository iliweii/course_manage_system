package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Course;
import xyz.honghong520.service.CourseService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseManage {

    @Autowired
    private CourseService action;

    @PostMapping("/CourseManageDeleteBatchServlet")
    public String CourseManageDeleteBatchServlet(
        @RequestParam("cid") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Course> courses = new ArrayList<Course>();
        for (int i = 0; i < gsonarr.length; i++) {
            Course c = new Course();
            c.setCid(gsonarr[i]);
            courses.add(c);
        }

        // 获取返回值
        int back = action.deleteCoursesBatch(courses);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/CourseManageDeleteServlet")
    public String CourseManageDeleteServlet(
            @RequestParam("cid") String CourseId
    ) {
        if (CourseId == null) {
            return ("empty");
        }

        Integer cid = Integer.parseInt(CourseId);
        // 获取返回值
        int back = action.deleteCourse(cid);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/CourseManageInsertServlet")
    public String CourseManageInsertServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Course List
        List<Course> course = gson.fromJson(json, new TypeToken<List<Course>>() {
        }.getType());

        // 获取返回值
        int back = action.insertCourseBatch(course);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/CourseManageListServlet")
    public String CourseManageListServlet() {
        // 查询所有课程信息
        List<Course> list = action.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/CourseManageQueryByCnoServlet")
    public String CourseManageQueryByCnoServlet(
            @RequestParam("cno") String Cno
    ) {
        if (Cno == null) {
            return ("empty");
        }

        // 获取返回值
        Course back = action.queryCourseByCno(Cno);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/CourseManageQueryServlet")
    public String CourseManageQueryServlet(
            @RequestParam("cid") String CId
    ) {
        if (CId == null) {
            return ("empty");
        }

        Integer cid = Integer.parseInt(CId);
        // 获取返回值
        Course back = action.queryCourseById(cid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/CourseManageSearchServlet")
    public String CourseManageSearchServlet(
            @RequestParam("q") String q
    ) {
        try {
            q = URLDecoder.decode(q,"utf-8");
            // 获取返回值
            List<Course> list = action.searchByKeyword(q);

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

    @PostMapping("/CourseManageUpdateServlet")
    public String CourseManageUpdateServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Course对象
        Course course = gson.fromJson(json, Course.class);

        // 把Course对象传给updateStudent方法
        int back = action.updateCourse(course);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
