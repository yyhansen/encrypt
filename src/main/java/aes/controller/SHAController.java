package aes.controller;

import aes.bean.RequestBean;
import aes.util.SHA;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/sha")
public class SHAController {

    @RequestMapping(value = "sha1",method = RequestMethod.POST)
    public Map<String,String> sha1(@RequestBody RequestBean reqBean){
        Map<String,String> map=new HashMap<>();
        map.put("hashText", SHA.sha1(reqBean.getPlainText().getBytes()));
        return map;
    }

    @RequestMapping(value = "sha256",method = RequestMethod.POST)
    public Map<String,String> sha256(@RequestBody RequestBean reqBean){
        Map<String,String> map=new HashMap<>();
        map.put("hashText", SHA.sha256(reqBean.getPlainText().getBytes()));
        return map;
    }

    @RequestMapping(value = "sha512",method = RequestMethod.POST)
    public Map<String,String> sha512(@RequestBody RequestBean reqBean){
        Map<String,String> map=new HashMap<>();
        map.put("hashText", SHA.sha512(reqBean.getPlainText().getBytes()));
        return map;
    }
}
