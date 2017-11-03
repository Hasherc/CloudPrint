import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qming.dao.FileDao;
import com.qming.dao.UserDao;
import com.qming.service.FileService;
import com.qming.service.OrderService;
import com.qming.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.UUIDUtil;

import java.io.IOException;
import java.util.HashMap;


/**
 * @author qming
 * @ 17-8-7
 */

public class test extends AbstractJunit {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FileDao fileDao;

    @Test
    public void register() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nickName", "崔启明");
        jsonObject.put("phoneNum", "13160080233");
        jsonObject.put("password", "cqm199718");

        String result = userService.register(jsonObject.toJSONString());
        System.out.println(result);
    }

    @Test
    public void Login() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", "cqm199718");
        jsonObject.put("phoneNum", "13160080233");


        String result = userService.checkLogin(jsonObject.toJSONString());
        JSONObject resultObj = JSON.parseObject(result);
        System.out.println(resultObj.remove("status"));
        System.out.println(result);

    }

    @Test
    public void testUUID() {
        System.out.println(UUIDUtil.getUUID().length());
    }

    @Test
    public void file() throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("dasda", "dasdsa");
        hashMap.put("dsadas", "dasdsada");
        JSONObject jsonObject = new JSONObject();
        String a = JSON.toJSONString(hashMap);
        System.out.println(a);

    }
}
