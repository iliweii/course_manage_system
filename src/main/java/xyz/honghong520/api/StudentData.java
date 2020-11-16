package xyz.honghong520.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Choose;
import xyz.honghong520.service.ChooseService;

import java.util.List;

@RestController
public class StudentData {

    @Autowired
    private ChooseService action;

    @PostMapping("/ApplyCourseStudentServlet")
    public String ApplyCourseStudentServlet(
        @RequestParam("sid") String sid,
        @RequestParam("cono") String cno
    ) {
        if (sid == null || cno == null) {
            return ("empty");
        }
        // 获取返回值
        int back = action.applyChooseBySid(sid, cno);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/CourseQueryListServlet")
    public String CourseQueryListServlet(
            @RequestParam("sid") String sid
    ) {
        List<Choose> list = action.queryAllChooseBySid(sid);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/ResultQueryListServlet")
    public String ResultQueryListServlet(
            @RequestParam("sid") String sid
    ) {
        List<Choose> list = action.queryAllGradeBySid(sid);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }
}
