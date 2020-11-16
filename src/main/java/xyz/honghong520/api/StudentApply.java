package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.Choose;
import xyz.honghong520.service.ChooseService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentApply {

    @Autowired
    private ChooseService action;

    @PostMapping("/StudentApplyAllowBatchServlet")
    public String StudentApplyAllowBatchServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Choose> Chooses = new ArrayList<Choose>();
        for (int i = 0; i < gsonarr.length; i++) {
            Choose c = new Choose();
            c.setScid(gsonarr[i]);
            Chooses.add(c);
        }

        // 获取返回值
        int back = action.allowChoosesBatch(Chooses);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentApplyAllowServlet")
    public String StudentApplyAllowServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Choose对象
        Choose choose = gson.fromJson(json, Choose.class);

        // 把Choose对象传给allowStudent方法
        int back = action.allowChoose(choose);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentApplyDeleteBatchServlet")
    public String StudentApplyDeleteBatchServlet(
            @RequestParam("scid") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Choose> Chooses = new ArrayList<Choose>();
        for (int i = 0; i < gsonarr.length; i++) {
            Choose c = new Choose();
            c.setScid(gsonarr[i]);
            Chooses.add(c);
        }

        // 获取返回值
        int back = action.deleteChoosesBatch(Chooses);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentApplyDeleteServlet")
    public String StudentApplyDeleteServlet(
            @RequestParam("scid") String ChooseId
    ) {
        if (ChooseId == null) {
            return ("empty");
        }

        Integer scid = Integer.parseInt(ChooseId);
        // 获取返回值
        int back = action.deleteChoose(scid);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentApplyInsertServlet")
    public String StudentApplyInsertServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Choose List
        List<Choose> Choose = gson.fromJson(json, new TypeToken<List<Choose>>() {
        }.getType());

        // 获取返回值
        int back = action.insertChooseBatch(Choose);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/StudentApplyListServlet")
    public String StudentApplyListServlet() {
        // 查询所有选课信息
        List<Choose> list = action.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/StudentApplyQueryServlet")
    public String StudentApplyQueryServlet(
            @RequestParam("scid") String SCId
    ) {
        if (SCId == null) {
            return ("empty");
        }

        Integer scid = Integer.parseInt(SCId);
        // 获取返回值
        Choose back = action.queryChooseById(scid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/StudentApplySearchServlet")
    public String StudentApplySearchServlet(
            @RequestParam("q") String q
    ) {
        try {
            q = java.net.URLDecoder.decode(q,"utf-8");
            // 获取返回值
            List<Choose> list = action.searchByKeyword(q);

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

    @PostMapping("/StudentApplyUpdateServlet")
    public String StudentApplyUpdateServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Choose对象
        Choose Choose = gson.fromJson(json, Choose.class);

        // 把Choose对象传给updateStudent方法
        int back = action.updateChoose(Choose);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
