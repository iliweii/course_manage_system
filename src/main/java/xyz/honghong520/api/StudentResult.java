package xyz.honghong520.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Choose;
import xyz.honghong520.service.ChooseService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentResult {

    @Autowired
    private ChooseService action;

    @PostMapping("/StudentResultDeleteBatchServlet")
    public String StudentResultDeleteBatchServlet(
        @RequestParam("scid") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Choose> chooses = new ArrayList<Choose>();
        for (int i = 0; i < gsonarr.length; i++) {
            Choose sc = new Choose();
            sc.setScid(gsonarr[i]);
            chooses.add(sc);
        }

        // 获取返回值
        int back = action.updateChoosesBatch(chooses);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentResultDeleteServlet")
    public String StudentResultDeleteServlet(
            @RequestParam("scid") String scId
    ) {
        if (scId == null) {
            return ("empty");
        }

        Integer scid = Integer.parseInt(scId);
        // 获取返回值
        Choose choose = new Choose();
        choose.setScid(scid);
        choose.setGrade(0);
        int back = action.updateChoose(choose);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentResultListServlet")
    public String StudentResultListServlet() {
        // 查询所有成绩信息
        List<Choose> list = action.queryAllGrade();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/StudentResultSearchByClnoServlet")
    public String StudentResultSearchByClnoServlet(
            @RequestParam("cno") String clno
    ) {
        List<Choose> list = action.queryAllGradeByClno(clno);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/StudentResultSearchByConoServlet")
    public String StudentResultSearchByConoServlet(
            @RequestParam("cno") String cono
    ) {
        List<Choose> list = action.queryAllGradeByCono(cono);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/StudentResultSearchServlet")
    public String StudentResultSearchServlet(
            @RequestParam("q") String q
    ) {
        List<Choose> list = action.queryAllGradeByQ(q);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/StudentResultUpdateServlet")
    public String StudentResultUpdateServlet(
            @RequestParam("scid") String scId,
            @RequestParam("grade") String Grade
    ) {
        if (scId == null) {
            return ("empty");
        }

        Integer scid = Integer.parseInt(scId);
        Double grade = Double.parseDouble(Grade);
        // 获取返回值
        Choose choose = new Choose();
        choose.setScid(scid);
        choose.setGrade(grade);
        int back = action.updateChoose(choose);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

}
