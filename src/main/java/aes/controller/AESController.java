package aes.controller;

import aes.bean.RequestBean;
import aes.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static aes.util.AES.aesDecrypt;
import static aes.util.AES.aesEncrypt;

/**
 * Created by chenhansen on 2018/5/18.
 */
@RestController
@RequestMapping(value = "/aes")
public class AESController {

    @RequestMapping(value = "/encrypt",method = RequestMethod.POST)
    public Map<String,String> encrypt(@RequestBody RequestBean reqBean){
        Map<String,String> maps=new HashMap<>();
        String cipherText="";
        try {
            cipherText=aesEncrypt(reqBean.getPlainText(),reqBean.getPassword());
        }catch (Exception e){
            cipherText="";
            maps.put("cipherText","");
            return maps;
        }
//        String path="D:\\var\\file\\ciphertext.txt";
//        FileUtil.write(path,cipherText);
        maps.put("cipherText",cipherText);
        return maps;

    }

    @RequestMapping(value = "/decrypt",method = RequestMethod.POST)
    public Map<String, String> decrypt(@RequestBody RequestBean reqBean){
        String plainText="";
        try{
            plainText=aesDecrypt(reqBean.getCipherText(),reqBean.getPassword());
        }catch (Exception e){
            plainText="";
        }
        Map<String,String> maps=new HashMap<>();
        maps.put("plainText",plainText);
        return maps;
    }
    @RequestMapping(value = "/showFile",method = RequestMethod.POST)
    public Map<String,String> showFile(@RequestParam("file") CommonsMultipartFile file)throws IOException{
        InputStream input=file.getInputStream();
        byte[] bytes = new byte[input.available()];
        input.read(bytes);
        String plainText = new String(bytes);
        Map<String,String> maps=new HashMap<>();

        maps.put("plainText",plainText);
        return maps;
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download() throws IOException {
        String filepath="D:\\var\\file\\ciphertext.txt";
        File file = new File(filepath);
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("plainText-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }


}
