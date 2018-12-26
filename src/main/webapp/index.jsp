<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>
    AES在线加密解密
</title>
<body>
    <div style="margin-left: 400px;margin-top: 100px">
        <div>
        <label>密码</label>
        <input type="text" id="password" name="password" style="width: 560px">
        </div>
        <div >
            <label >明文</label></br>
            <textarea  id="plainText" name="plainText" style="width: 600px;height:200px "></textarea></br>
            <input type="file" name="upload" id="upload" accept="text/plain"/>

            下载： <a href="/cryptaes/download">密文.txt</a>
        </div>
    <div >
        <label>密文</label></br>
        <textarea  id="cipherText" name="cipherText" style="width: 600px;height:200px "></textarea></br>
    </div>


<a type="submit" name="action" href="#" onclick="encrypt();return false">
    加密</a>
<a type="submit" name="action" href="#" onclick="decrypt();return false">
    解密</a>
    </div>

<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script src="static/js/a.js"></script>
</body>
</html>
