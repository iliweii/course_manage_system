package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import xyz.honghong520.service.ClassService;
import xyz.honghong520.entity.Class;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClassManage {

    @Autowired
    private ClassService classService;

    @PostMapping("/ClassManageDeleteBatchServlet")
    public String ClassManageDeleteBatchServlet(
            @RequestParam("cid") String json
    ) {
        // 获取班级id
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<Class> Classes = new ArrayList<Class>();
        for (int i = 0; i < gsonarr.length; i++) {
            Class c = new Class();
            c.setCid(gsonarr[i]);
            Classes.add(c);
        }

        // 获取返回值
        int back = classService.deleteClasssBatch(Classes);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/ClassManageDeleteServlet")
    public String ClassManageDeleteServlet(
            @RequestParam("cid") String ClassId
    ) {
        if (ClassId == null) {
            return ("empty");
        }

        Integer cid = Integer.parseInt(ClassId);
        // 获取返回值
        int back = classService.deleteClass(cid);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/ClassManageInsertServlet")
    public String ClassManageInsertServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Class List
        List<Class> Classes = gson.fromJson(json, new TypeToken<List<Class>>() {
        }.getType());

        // 获取返回值
        int back = classService.insertClassBatch(Classes);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }

    @PostMapping("/ClassManageListServlet")
    public String ClassManageListServlet() {
        // 查询所有班级信息
        List<Class> list = classService.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/ClassManageQueryByCnoServlet")
    public String ClassManageQueryByCnoServlet(
            @RequestParam("cno") String Cno
    ) {
        if (Cno == null) {
            return ("empty");
        }

        // 获取返回值
        Class back = classService.queryClassByCno(Cno);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/ClassManageQueryServlet")
    public String ClassManageQueryServlet(
            @RequestParam("cid") String CId
    ) {
        if (CId == null) {
            return ("empty");
        }

        Integer cid = Integer.parseInt(CId);
        // 获取返回值
        Class back = classService.queryClassById(cid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/ClassManageSearchServlet")
    public String ClassManageSearchServlet(
            @RequestParam("q") String q
    ) {
        try {
            q = URLDecoder.decode(q, "utf-8");
            // 获取返回值
            List<Class> list = classService.searchByKeyword(q);

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

    @PostMapping("/ClassManageUpdateServlet")
    public String ClassManageUpdateServlet(
            @RequestParam("json") String json
    ) {
        if (json == null) {
            return ("empty");
        }

        Gson gson = new Gson();
        // 将json数据转化为Class对象
        Class Class = gson.fromJson(json, Class.class);

        // 把class对象传给updateStudent方法
        int back = classService.updateClass(Class);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
