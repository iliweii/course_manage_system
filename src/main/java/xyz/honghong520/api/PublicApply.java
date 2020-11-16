package xyz.honghong520.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.honghong520.service.ChooseService;

@RestController
public class PublicApply {

    @Autowired
    private ChooseService action;

    @PostMapping("/PublicApplyInsertServlet")
    public String PublicApplyInsertServlet(
        @RequestParam("tno") String tno,
        @RequestParam("clno") String clno,
        @RequestParam("cono") String cono
    ) {
        if (tno == null || clno == null || cono == null) {
            return ("empty");
        }
        // 获取返回值
        int back = action.publicChoose(tno, clno, cono);

        if (back == 0) {
            return ("failed");
        } else {
            return ("success");
        }
    }
}
