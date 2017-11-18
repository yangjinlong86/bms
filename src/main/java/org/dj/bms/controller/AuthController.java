package org.dj.bms.controller;

import org.dj.bms.service.ResourceService;
import org.dj.bms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 授权相关控制类
 * @author jason
 * @date 17/11/3
 */
@Controller
public class AuthController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/resource")
    public String resource(Model model) {
        model.addAttribute("resources", JsonUtils.obj2json(resourceService.findResources()));
        return "authorize/resource";
    }

	@RequestMapping("/role")
    public String role(Model model) {
        model.addAttribute("resources", JsonUtils.obj2json(resourceService.findResources()));
        return "authorize/role";
	}

	@RequestMapping("/user")
	public String user() {
		return "authorize/user";
	}

    @RequestMapping("/noauth")
    public String noauth() {
        return "noauth";
    }

    // FIXME -.-! 飞哥,把这个方法拿走,这是授权相关的方法的,不要放在这!!!
    @RequestMapping("/customer")
	public String customer() {
		return "customer/customer";
	}
}
