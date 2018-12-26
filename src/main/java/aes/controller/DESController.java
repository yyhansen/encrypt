package aes.controller;

import aes.bean.RequestBean;
import aes.util.DES;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value="/des")
public class DESController {

    @RequestMapping(value = "/encrypt",method= RequestMethod.POST)
    public Map<String,String> encrypt(@RequestBody RequestBean reqBean){
        Map<String,String> maps=new HashMap<>();
        String cipherText="";
        try {
            cipherText= DES.desEncrypt(reqBean.getPlainText(),reqBean.getPassword());
        }catch (Exception e){
            cipherText="";
            maps.put("cipherText",cipherText);
            return maps;
        }
        maps.put("cipherText",cipherText);
        return maps;
    }

    @RequestMapping(value = "/decrypt",method = RequestMethod.POST)
    public Map<String,String> decrypt(@RequestBody RequestBean reqBean){
        Map<String,String> maps=new HashMap<>();
        String plainText="";
        try {
            plainText= DES.desDecrypt(reqBean.getCipherText(),reqBean.getPassword());
        }catch (Exception e){
            plainText="";
            maps.put("plainText",plainText);
            return maps;
        }
        maps.put("plainText",plainText);
        return maps;
    }
}
