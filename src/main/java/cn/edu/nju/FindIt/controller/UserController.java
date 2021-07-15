package cn.edu.nju.FindIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags={"用户接口"})
@RestController
public class UserController {
    @Autowired
    public UserController() {

    }

    //@ApiOperation(value = "打招呼", notes = "打招呼罢了")
    @GetMapping("/user")
    public String welcome() {
        return "welcome to user api!";
    }
}
