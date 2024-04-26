package com.dev.usersweb.controller;

import com.dev.usersweb.data.ResultData;
import com.dev.usersweb.data.UserData;
import com.dev.usersweb.facade.UserFacade;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Scope("session")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final String REDIRECT_PREFIX = "redirect:/user";
    private static final String REDIRECT_SAVE = "/save";

    @Value(value = "${page.user.title}")
    private String title;

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getUsersPage(Model model) {
        addCommonAttributes(model);
        model.addAttribute("users", userFacade.getUsers());
        return "users";
    }

    @GetMapping("user-table")
    public String getUsersTable(Model model) {
        model.addAttribute("users", userFacade.getUsers());
        return "userTable";
    }

    @GetMapping(value = "/save")
    public String getSaveUserPage(@RequestParam(required = false) String id,  Model model) {
        addCommonAttributes(model);
        model.addAttribute("userForm", userFacade.getUserForm(id));
        return "userSave";
    }

    @PostMapping(value = "/save")
    public String saveUser(@Valid @ModelAttribute UserData userData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", true);
            return getRedirectUrl(true, userData.getId());
        }
        if(userFacade.saveUser(userData)){
            return getRedirectUrl(false, userData.getId());
        }
        model.addAttribute("error", true);
        return getRedirectUrl(true, userData.getId());
    }

    @PostMapping(value = "/check-user-exists")
    public @ResponseBody ResultData checkUserExists(@RequestParam String username) {
        return userFacade.checkIfUserExists(username);
    }

    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultData removeUser(@PathVariable String id) {
        return userFacade.removeUser(id);
    }

    private void addCommonAttributes(Model model){
        model.addAttribute("title", title);
    }

    private String getRedirectUrl(boolean hasError, String userId){
        StringBuilder sb = new StringBuilder(REDIRECT_PREFIX);
        if(hasError){
            sb.append(REDIRECT_SAVE);
            if(StringUtils.hasText(userId)){
                sb.append("?id=").append(userId);
            }
        }
        return sb.toString();
    }
}
