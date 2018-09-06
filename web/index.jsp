<%--
  Created by IntelliJ IDEA.
  User: PearFL
  Date: 2018/9/3
  Time: 14:38
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
  <link rel="icon" href="../../favicon.ico">

  <title>ACM信息发布系统</title>

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

  <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
  <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
  <!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
  <link href="carousel.css" rel="stylesheet">

  <%--<script type="text/javascript" src="resources/js/jquery1.4.2.js"></script>--%>
</head>
<!-- NAVBAR
================================================== -->
<div class="navbar-wrapper">
  <div class="container">

    <nav class="navbar navbar-inverse navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">ACM实验室</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">首页</a></li>
            <li><a href="#about">组内通知</a></li>
            <li><a href="#contact">成绩公告</a></li>
            <li><a href="#">本周工作</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">竞赛历程 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">rating榜</a></li>
                <li><a href="User_all.jsp">成员榜</a></li>
                <li><a href="#">历年成绩</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">相关网站</li>
                <li><a href="https://hfuuosc.org/">hfuu开源社区</a></li>
                <li><a href="https://oj.chimuyuan.cn/">hfuu练习测试平台</a></li>
                <li><a href="https://pearfl.github.io/">队内博客链接</a></li>
              </ul>
            </li>
            <li><a href="#">更多栏目</a></li>
          </ul>
          <div style="display: flex;justify-content: flex-end;">
            <ul class="nav navbar-nav">
              <%
                if(session.getAttribute("uname") != null){
              %>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("uname")%><span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">账户管理</a></li>
                  <li><a href="#">历史成绩</a></li>
                  <li><a href="logout.jsp">注销</a></li>
                </ul>
              </li>
              <%}else{ %>
              <li data-toggle="modal" data-target="#loginModal" cursor:pointer;><a>登录</a></li>
              <li data-toggle="modal" data-target="#registerModal" cursor:pointer;><a>注册</a></li>
              <% } %>
            </ul>
          </div>
        </div>
      </div>
    </nav>

  </div>
</div>

<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="first-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="First slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>开学纳新，刻不容缓</h1>
          <p>欢迎所有对<code>ACM</code>感兴趣的新生加入我们！群号：108951936</p>
          <p><a class="btn btn-lg btn-primary" href="http://shang.qq.com/wpa/qunwpa?idkey=92df32a46d33de262f14169e5eac5d26cbf356c111b53e1e7963b44ff9bd153d" role="button">点击加入18级acm招新群</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="second-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Second slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>区域赛来袭，谁与争锋</h1>
          <p>祝愿我校在2018年ACM/ICPC各大区域赛中斩获佳绩</p>
          <p><a class="btn btn-lg btn-primary" href="http://acmicpc.info/" role="button">了解更多</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="third-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Third slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>12月csp认证，请各位做好准备</h1>
          <p>近期内将进行组内测试选拔，请各位好好加油，努力应对</p>
          <p><a class="btn btn-lg btn-primary" href="http://www.cspro.org/lead/application/ccf/login.jsp" role="button">csp认证官网</a></p>
        </div>
      </div>
    </div>
  </div>
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div><!-- /.carousel -->


<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

  <!-- Three columns of text below the carousel -->
  <div class="row">
    <div class="col-lg-4">
      <img class="img-circle" src="https://i.loli.net/2018/09/03/5b8c8e61641fc.jpg" alt="Generic placeholder image" width="140" height="140">
      <h2>组内通知</h2>
      <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>
      <p><a class="btn btn-default" href="#" role="button">more &raquo;</a></p>
    </div><!-- /.col-lg-4 -->
    <div class="col-lg-4">
      <img class="img-circle" src="https://i.loli.net/2018/09/03/5b8c8e9eb5bf6.png" alt="Generic placeholder image" width="140" height="140">
      <h2>成绩公告</h2>
      <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>
      <p><a class="btn btn-default" href="#" role="button">more &raquo;</a></p>
    </div><!-- /.col-lg-4 -->
    <div class="col-lg-4">
      <img class="img-circle" src="https://i.loli.net/2018/09/03/5b8c8ee59f4ce.jpg" alt="Generic placeholder image" width="140" height="140">
      <h2>本周工作</h2>
      <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
      <p><a class="btn btn-default" href="#" role="button">more &raquo;</a></p>
    </div><!-- /.col-lg-4 -->
  </div><!-- /.row -->


  <!-- FOOTER -->
  <footer>
    <p class="pull-right"><a href="#">返回顶部</a></p>
    <p>&copy; 2018 hfuuacm,<br>Powered by mxh | ysl</p>
  </footer>
</div><!-- /.container -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 模态框（loginModal） -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog"  style="width: 500px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title" id="myModalLabel" align="center">登录</h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label for="uname" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-9">
              <input type="text" id="uname" name="uname" class="form-control well" placeholder="请输入用户名/邮箱"/>
            </div>
          </div>
          <div class="form-group">
            <label for="upwd" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-9">
              <input type="password" id="upwd" name="upwd" class="form-control well" placeholder="请输入密码"/>
            </div>

          </div>

          <div class="modal-footer">
            <button type="button"  class="btn btn-secondary" id="btn-forget-password" style="float: left;">
              忘记密码？
            </button>
              <span id="loginMsg" class="tip_login" style="opacity: 1;color:red;"></span>
            <button type="button" class="btn btn-primary" id="btn-login">登录</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>

      </form>

    </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>

<!-- 模态框（registerModal） -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog"  style="width: 500px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title" id="myModalLabel2" align="center">注册</h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label for="uname2" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-9">
              <input type="text" id="uname2" name="uname2" class="form-control well" placeholder="请输入用户名"/>
            </div>
          </div>
          <div class="form-group">
            <label for="uemail2" class="col-sm-2 control-label" placeholder="Email address">邮箱</label>
            <div class="col-sm-9">
              <input type="email" id="uemail2" name="uemail2" class="form-control well" placeholder="请输入邮箱"/>
            </div>
          </div>
          <div class="form-group">
            <label for="upwd2" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-9">
              <input type="password" id="upwd2" name="upwd2" class="form-control well" placeholder="请输入密码"/>
            </div>
          </div>
          <div class="form-group">
            <label for="upwd3" class="col-sm-2 control-label">再次确认密码</label>
            <div class="col-sm-9">
              <input type="password" id="upwd3" name="upwd3" class="form-control well" placeholder="请再次输入密码"/>
            </div>
          </div>
          <div class="modal-footer">
              <span id="registerMsg" class="tip_login" style="opacity: 1;color:red;"></span>
              <button type="submit" class="btn btn-primary">注册</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </form>
      </div>

    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>


</body>
<script type="text/javascript">
    $("#btn-login").click(function(){
        //
        $.ajax({
            url:"http://localhost:8080/loggin",
            type:"post",
            data:{
                uname:$("input[name=uname]").val(),
                upwd:$("input[name=upwd]").val()
            },
            dataType:"json",
            success:function(result){
                //var flag = result.flag;
                //alert(result);
                if(result == true){
                    //如果登录成功则跳转到成功页面
                    window.location.href="index.jsp";
                }else{
                    //跳回到Index.jsp登录页面，同时在登录页面给用户一个友好的提示
                    $(".tip_login").text("密码不正确");
                    //alert("您输入的用户名或者密码不正确");
                }
            }

        });
    });
</script>
</html>
