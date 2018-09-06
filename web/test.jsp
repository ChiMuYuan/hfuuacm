<%--
  Created by IntelliJ IDEA.
  User: PearFL
  Date: 2018/9/4
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Bootstrap core JavaScript
================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">成员榜</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">返回主页</a></li>
                <li><a href="#">设置</a></li>
                <li><a href="#">个人信息</a></li>
                <li><a href="#">帮助</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="搜索...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul id="member" class="nav nav-sidebar">
                <li id="all"><a>总览</a></li>
                <li id="admin"><a href="#">系统管理员</a></li>
                <li id="column"><a>栏目管理员</a></li>
                <li id="common"><a>普通成员</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">rating榜</a></li>
                <li><a href="">历年成绩</a></li>
                <li><a href="">组内通知</a></li>
                <li><a href="">本周工作</a></li>
                <li><a href="">更多栏目</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">hfuu开源社区</a></li>
                <li><a href="">hfuu练习测试平台</a></li>
                <li><a href="">队内博客链接</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header" id ="category"></h1>
            <div class="table-responsive">
                <ul class="pagination" id="fenye">

                </ul>
                <input type="hidden" value="1" id="page" />
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Username</th>
                        <th>email</th>
                        <th>permission</th>
                    </tr>
                    </thead>
                    <tbody id="display_tbody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    $(document).ready(function(e){
        var zys = 0;
        var index = "#all";
        $("#category").html("总览");
        $(index).addClass("active");
        JiaZai();

    })

    $.ajax("#all").click(function(e){
        $("#category").html("总览");
        $(index).removeClass("active");
        index = "#all"
        $(index).addClass("active");
    })

    $.ajax("#admin").click(function(e){
        $("#category").html("系统管理员");
        $(index).removeClass("active");
        index = "#admin";
        $(index).addClass("active");
    })

    $.ajax("#column").click(function(e){
        $("#category").html("栏目管理员");
        $(index).removeClass("active");
        index = "#column";
        $(index).addClass("active");
    })

    $.ajax("#common").click(function(e){
        $("#category").html("普通成员");
        $(index).removeClass("active");
        index = "#common";
        $(index).addClass("active");

    })


    function JiaZai(){
        var page = $("#page").val();
        var category = $("#category").text();
        $.ajax({
            url:"",
            data:{page:page,category:category},
            type:"post",
            dataType:"json",
            success: function(data){
                var s = "";
                for(var i in data){
                    s+="<tr><td>" + data[i].id + "</td><td>"+ data[i].username +"</td><td>" + data[i].email + "</td><td>" + data[i].permission + "</td></tr>";
                }
                $("#display_tbody").html(s);
            }
        });

        $.ajax({
            url:"",
            type:"post",
            dataType:"json",
            success:function(data){
                //总页数
                var ys = Math.ceil(data/10);
                zys = ys;
                var s = "<li class=\"paginate_button page-item previous\" id=\"listContest_previous\">\n" +
                    "                        <a href=\"#\" aria-controls=\"listContest\" data-dt-idx=\"0\" tabindex=\"0\" class=\"page-link\">上一页</a>\n" +
                    "                    </li>";
                var current_page = $("page").val();
                for(var i = current_page - 2;i<=current_page + 2;i++){
                    if(i > 0 && i <= ys){
                        if(current_page == i){
                            s+="<li class=\"listContest\" class=\"paginate_button page-item\" class=\"action\">\n" +
                                "                        <a href=\"#\" aria-controls=\"listContest\" data-dt-idx="+i+ "tabindex=\"0\" class=\"page-link\">i</a>\n" +
                                "                    </li>";
                        }else{
                            s+="<li class=\"listContest\" class=\"paginate_button page-item\">\n" +
                                "                        <a href=\"#\" aria-controls=\"listContest\" data-dt-idx="+i+ "tabindex=\"0\" class=\"page-link\">i</a>\n" +
                                "                    </li>";
                        }
                    }
                }
                s+="<li class=\"paginate_button page-item next\" id=\"listContest_next\">\n" +
                    "                        <a href=\"#\" aria-controls=\"listContest\" data-dt-idx=\"8\" tabindex=\"0\" class=\"page-link\">Next</a>\n" +
                    "                    </li>";
                $("#fenye").html(s);
                //给分页列表加事件
                JiaShiJian();
            }
        })
        function JiaShiJian(){
            $("#listContest_previous").click(function(){
                var page = $("#page").val();
                if(page > 1){
                    page--;
                }else{
                    page = 1;
                }
                $("#page").val(page);

                JiaZai();
            })


            $("#listContest_next").click(function(){
                var page = $("#page").val();
                if(page < zys){
                    page++;
                }else{
                    page = zys;
                }
                $("#page").val(page);

                JiaZai();
            })

            $(".listContest").click(function(){
                var page = $(this).text();
                $("#page").val(page);

                JiaZai();
            })
        }

    }
</script>
</html>

