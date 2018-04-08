layui.use(["jquery","layer"],function(){
    window.jQuery = window.$ = layui.jquery;
    var layer=layui.layer;
    $("#btn").click(function(){
    var username=$("#username").val();//获取用户名
    var password=$("#password").val();//获取密码
    if(username=="" || password==""){
        layer.open({content:'登录账号和登录密码不能为空'});
        return false;
    }
    $.ajax({
        type:"post",//提交类型
        url:"/login",//请求的地址
        data:{
            username:username,
            password:password,
        },//提交的数据
        dataType:"json",//返回的类型 json
        success:function(data){
           console.log(data);
            console.log(typeof(data));
            var jsondata = data;
            console.log(jsondata);
            if(jsondata.type==='student' && jsondata.flag){
                layer.msg('登入成功',{
                    time:1000,icon:1
                },function(){
                    window.open('student','_self');
                })                            
            }else if(jsondata.type==='teacher'  && jsondata.flag){
                layer.msg('登入成功',{
                    time:1000,icon:1
                },function(){
                    window.open('teacher','_self');
                })
            }else if(jsondata.type==='hr' && jsondata.flag){
                layer.msg('登入成功',{
                    time:1000,icon:1
                },function(){
                    window.open('hr','_self');
                })
            }else{
                layer.msg('用户名或密码错误，请重新输入！')
            }
        },
        error:function(){
            layer.msg("请求异常，请重试");
        }
    });
});
});
