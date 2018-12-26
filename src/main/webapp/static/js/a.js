function encrypt() {
    var password=$("#password").val();
    var plainText=$("#plainText").val();
    if(password==""||plainText=="")alert("请输入明文和密码！");
    else{
        $.ajax({
            url: 'aes/encrypt',
            type: 'post',
            dataType: 'JSON',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                password:password,
                plainText:plainText
            }),
            success: function (data) {
                console.log(data);
                document.getElementById("cipherText").value=data.cipherText;
            },
            error: function (err) {
                console.log(err)
            }
        })
    }

}

function decrypt() {
    var password=$("#password").val();
    var cipherText=$("#cipherText").val();
    if(password==""||cipherText=="")alert("请输入密文和密码！");
    else{
        $.ajax({
            url: 'aes/decrypt',
            type: 'post',
            dataType: 'JSON',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                password:password,
                cipherText:cipherText
            }),
            success: function (data) {
                document.getElementById("plainText").value=data.plainText;
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
}

var getFileContent = function (fileInput, callback) {
    if (fileInput.files && fileInput.files.length > 0 && fileInput.files[0].size > 0) {
        //下面这一句相当于JQuery的：var file =$("#upload").prop('files')[0];
        var file = fileInput.files[0];
        if (window.FileReader) {
            var reader = new FileReader();
            reader.onloadend = function (evt) {
                if (evt.target.readyState == FileReader.DONE) {
                    callback(evt.target.result);
                }
            };
            // 包含中文内容用gbk编码
            reader.readAsText(file, 'gbk');
        }
    }
};

/**
 * upload内容变化时载入内容
 */
document.getElementById('upload').onchange = function () {
    var plainText = document.getElementById('plainText');

    getFileContent(this, function (str) {
        plainText.value = str;
    });
};
