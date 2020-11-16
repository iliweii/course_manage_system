package xyz.honghong520.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.entity.User;
import xyz.honghong520.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminManage {

    @Autowired
    private UserService userService;

    @PostMapping("/AdminManageCheckuserServlet")
    public String AdminManageCheckuserServlet(
            @RequestParam("tbuser") String tbuser,
            @RequestParam("self") String self) {
        if (tbuser == null) {
            return "empty";
        }
        if ("self".equals(self)) {
            // 获取返回值
            User back = userService.queryUserByUser(tbuser);
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            // 输出信息
            return json;
        }
        // 获取返回值
        User back = userService.queryUserByUser(tbuser);
        if (back == null) {
            return ("norepeat");
        } else {
            return ("repeat");
        }
    }

    @PostMapping("/AdminManageDeleteBatchServlet")
    public String AdminManageCheckuserServlet(
            @RequestParam("tbid") String json) {
        if (json == null) {
            return "empty";
        }
        Gson gson = new Gson();
        int[] gsonarr = gson.fromJson(json, int[].class);
        List<User> user = new ArrayList<User>();
        for (int i = 0; i < gsonarr.length; i++) {
            User u = new User();
            u.setTbid(gsonarr[i]);
            user.add(u);
        }

        // 获取返回值
        int back = userService.deleteUsersBatch(user);

        if (back == 0) {
            return "failed";
        } else {
            return "success";
        }
    }

    @PostMapping("/AdminManageDeleteServlet")
    public String AdminManageDeleteServlet(
            @RequestParam("tbid") String adminId) {
        if (adminId == null) {
            return "empty";
        }
        Integer tbid = Integer.parseInt(adminId);
        // 获取返回值
        int back = userService.deleteUser(tbid);

        if (back == 0) {
            return "failed";
        } else {
            return "success";
        }
    }

    @PostMapping("/AdminManageInsertServlet")
    public String AdminManageInsertServlet(
            @RequestParam("json") String json) {
        if (json == null) {
            return "empty";
        }
        Gson gson = new Gson();
        // 将json数据转化为Tbuser List
        List<User> user = gson.fromJson(json, new TypeToken<List<User>>() {
        }.getType());

        // 获取返回值
        int back = userService.insertUsersBatch(user);

        if (back == 0) {
            return "failed";
        } else {
            return "success";
        }
    }

    @PostMapping("/AdminManageListServlet")
    public String AdminManageListServlet() {
        // 查询所有管理员信息
        List<User> list = userService.queryAll();
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return json;
    }

    @PostMapping("/AdminManageQueryServlet")
    public String AdminManageQueryServlet(
            @RequestParam("tbid") String adminId
    ) {
        if (adminId == null) {
            return "empty";
        }

        Integer tbid = Integer.parseInt(adminId);
        // 获取返回值
        User back = userService.queryUserById(tbid);

        if (back == null) {
            return ("failed");
        } else {
            // 将信息转化为json
            Gson gson = new Gson();
            String json = gson.toJson(back);
            return (json);
        }
    }

    @PostMapping("/AdminManageSearchServlet")
    public String AdminManageSearchServlet(
            @RequestParam("q") String q
    ) {
        // 获取返回值
        List<User> list = userService.searchByKeyword(q);
        // 将信息集合转化为数组
        Gson gson = new Gson();
        String json = gson.toJson(list);
        // 输出信息
        return (json);
    }

    @PostMapping("/AdminManageUpdateServlet")
    public String AdminManageUpdateServlet(
            @RequestParam("tbuser") String tbuser,
            @RequestParam("tbname") String tbname,
            @RequestParam("tbpwd") String tbpwd,
            @RequestParam("tbid") String tbid
    ) {
        if (tbuser == null || tbname == null || tbpwd == null || tbid == null) {
            return ("empty");
        }
        int id = Integer.parseInt(tbid);

        User User = new User();
        // 把用户名、密码封装在Tbuser对象中
        User.setTbuser(tbuser);
        User.setTbname(tbname);
        User.setTbpwd(tbpwd);
        User.setTbid(id);
        // 把user对象传给updateUser方法
        int back = userService.updateUser(User);
        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
