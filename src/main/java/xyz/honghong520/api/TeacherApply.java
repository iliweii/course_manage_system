package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.dao.TeachingDao;
import xyz.honghong520.entity.Teaching;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherApply {

    @Autowired
    private TeachingDao action;

    @PostMapping("/TeacherApplyAllowBatchServlet")
    public String TeacherApplyAllowBatchServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Teaching> teachings = new ArrayList<Teaching>();
        for (int i = 0; i < gsonarr.length; i++) {
            Teaching t = new Teaching();
            t.setTcid(gsonarr[i]);
            teachings.add(t);
        }

        // 获取返回值
        int back = action.allowTeachingsBatch(teachings);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherApplyAllowServlet")
    public String TeacherApplyAllowServlet(
        @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Teaching对象
        Teaching teaching = gson.fromJson(json, Teaching.class);

        // 把Teaching对象传给allowStudent方法
        int back = action.allowTeaching(teaching);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherApplyDeleteBatchServlet")
    public String TeacherApplyDeleteBatchServlet(
        @RequestParam("tcid") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Teaching> teachings = new ArrayList<Teaching>();
        for (int i = 0; i < gsonarr.length; i++) {
            Teaching t = new Teaching();
            t.setTcid(gsonarr[i]);
            teachings.add(t);
        }

        // 获取返回值
        int back = action.deleteTeachingsBatch(teachings);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherApplyDeleteServlet")
    public String TeacherApplyDeleteServlet(
    @RequestParam("tcid") String TeachingId
    ) {
        if (TeachingId == null) {
            return ("empty");
        }

        Integer tcid = Integer.parseInt(TeachingId);
        // 获取返回值
        int back = action.deleteTeaching(tcid);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherApplyInsertServlet")
    public String TeacherApplyInsertServlet(
    @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Teaching List
        List<Teaching> teaching = gson.fromJson(json, new TypeToken<List<Teaching>>() {
        }.getType());

        // 获取返回值
        int back = action.insertTeachingBatch(teaching);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/TeacherApplyListServlet")
    public String TeacherApplyListServlet() {
        // 查询所有授课信息
        List<Teaching> list = action.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/TeacherApplyQueryServlet")
    public String TeacherApplyQueryServlet(
    @RequestParam("tcid") String TCId
    ) {
        if (TCId == null) {
            return ("empty");
        }

        Integer tcid = Integer.parseInt(TCId);
        // 获取返回值
        Teaching back = action.queryTeachingById(tcid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/TeacherApplySearchServlet")
    public String TeacherApplySearchServlet(
    @RequestParam("q") String q
    ) {
        try {
            q = java.net.URLDecoder.decode(q,"utf-8");
            // 获取返回值
            List<Teaching> list = action.searchByKeyword(q);

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

    @PostMapping("/TeacherApplyUpdateServlet")
    public String TeacherApplyUpdateServlet(
    @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Teaching对象
        Teaching teaching = gson.fromJson(json, Teaching.class);

        // 把Teaching对象传给updateStudent方法
        int back = action.updateTeaching(teaching);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
