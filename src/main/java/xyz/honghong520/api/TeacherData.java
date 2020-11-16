package xyz.honghong520.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Choose;
import xyz.honghong520.entity.Teaching;
import xyz.honghong520.service.ChooseService;
import xyz.honghong520.service.TeachingService;

import java.util.List;

@RestController
public class TeacherData {

    @Autowired
    private TeachingService teachingService;
    @Autowired
    private ChooseService chooseService;

    @PostMapping("/ApplyTeachingTeacherServlet")
    public String ApplyTeachingTeacherServlet(
            @RequestParam("tid") String tid,
            @RequestParam("cono") String cno
    ) {
        if (tid == null || cno == null) {
            return ("empty");
        }
        // 获取返回值
        int back = teachingService.applyTeachingByTid(tid, cno);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/ResultManageListServlet")
    public String ResultManageListServlet(
            @RequestParam("tid") String tid
    ) {
        // 根据tid查询所有成绩信息
        List<Choose> list = chooseService.queryAllGradeByTid(tid);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/ResultManageSearchServlet")
    public String ResultManageSearchServlet(
            @RequestParam("tid") String tid,
            @RequestParam("q") String q
    ) {
        // 根据tid查询所有成绩信息
        List<Choose> list = chooseService.searchAllGradeByTid(tid, q);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/TeachQueryListByTidServlet")
    public String TeachQueryListByTidServlet(
            @RequestParam("tid") String id
    ) {
        int sid = Integer.parseInt(id);
        // 查询所有授课信息
        List<Teaching> list = teachingService.queryTeachingByTid(sid);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/TeachQueryUpdateBookServlet")
    public String TeachQueryUpdateBookServlet(
            @RequestParam("tcid") String tcId,
            @RequestParam("book") String book
    ) {
        if (tcId == null) {
            return ("empty");
        }

        Integer tcid = Integer.parseInt(tcId);
        // 获取返回值
        Teaching teaching = new Teaching();
        teaching.setTcid(tcid);
        teaching.setBook(book);
        int back = teachingService.updateTeaching(teaching);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
