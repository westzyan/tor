
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/StudentLeft.css">
</head>
<body>
<div class="nav">
    <div class="nav-list">
        <div class="nav-tit">
            <a href="#" target="main"> <img
                    src="images/personal-msg.png" style="height: 30px" alt=""> <span>XXXX</span>
            </a>
        </div>

        <div class="nav-tit">
            <a href="first_identity.jsp" target="main"> <img
                    src="images/personal-msg.png" style="height: 30px" alt=""> <span>初步判別</span>
            </a>
        </div>
        <div class="nav-tit">
            <a href="http://127.0.0.1/tor/index.php" target="main"> <img
                    src="images/personal-msg.png" style="height: 30px" alt=""> <span>全球态势</span>
            </a>
        </div>

        <div class="nav-tit">
            <a href="#" target="main"> <img
                    src="images/archives-msg.png" alt=""> <span>分类判别</span>
            </a>
        </div>
        <div class="nav-tit">
            <a href="#" target="main"> <img
                    src="images/job-msg.png" alt=""> <span>XXXXX</span>
            </a>
        </div>
        <div class="nav-tit" id="personal">
            <a href="#" target="main"> <img
                    src="images/PubMed-msg.png" alt=""> <span>XXXX</span>
            </a>
        </div>
        <div class="personal-list" id="personal_child">
            <ul>
                <li><a href="FamilyInformation.html" target="main">学生基本信息</a></li>
                <li><a href="TeacherEmInformation.html" target="main">就业学生信息</a></li>
                <li><a href="Teacherinvestigation.html" target="main">就业信息调查</a></li>
                <li><a href="TeacherPubMed.html" target="main">考研学生信息</a></li>
            </ul>
        </div>
        <div class="nav-tit">
            <a href="#" target="main"> <img
                    src="images/modify-password.png" alt=""> <span>XXXX</span>
            </a>
        </div>
        <div class="nav-tit">
            <a href="https://www.baidu.com" target="main"> <img
                    src="images/modify-password.png" alt=""> <span>XXXX</span>
            </a>
        </div>
        <div class="nav-tit">
            <a href="#" target="main"> <img
                    src="images/soso-white.png" alt=""> <span>XXXX</span>
            </a>
        </div>
    </div>
</div>

<script src="js/jquery-1.9.1.min.js" type="text/javascript"
        charset="utf-8"></script>
<script>
    $(document).ready(
        function () {
            $('#personal').on('click', function () {
                $('#personal_child').fadeToggle(300);
            });
            let aLi = $('#personal_child li');
            aLi.on('click', function () {
                $(this).addClass('active').siblings('li').removeClass(
                    'active');
            })
        });
</script>
</body>
</html>
