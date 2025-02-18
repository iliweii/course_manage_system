$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    // +按钮
    var Addbtn = $(".Addbtn");
    // -按钮
    var Cutbtn = $(".Cutbtn");
    // 用于记录+按钮点击次数，确保性别单选按钮不相互影响
    var addBtnClickTime = 0;
    
    // 定义学生信息数组
    var students = null;
    // 获取学生信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../StudentManageListServlet",
        success: function(e) {
        	students = JSON.parse(e);
        	// 首先将数据输出到默认首行中
        	for (var i = 0; i < students.length; i++) {
        		$("select[name=Sno]").append('\
                	<option value="' + students[i]["sno"] + '">' + students[i]["sno"] + "  " + students[i]["sname"] + '</option>\
                ');
        	}
        }
    });
    // 定义课程信息数组
    var courses = null;
    // 获取课程信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../CourseManageListServlet",
        success: function(e) {
        	courses = JSON.parse(e);
        	// 首先将数据输出到默认首行中
        	for (var i = 0; i < courses.length; i++) {
        		$("select[name=Cno]").append('\
                	<option value="' + courses[i]["cno"] + '">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
                ');
        	}
        }
    });

    // +按钮点击事件
    Addbtn.click(function () {
        insertGroup.append('\
        	<div class="insert-item">\
        		<div class="col-md-5 mb-3">\
	                <label>学生 <span class="text-danger">*</span></label>\
	                <select class="form-control Sno" name="Sno' + addBtnClickTime + '">\
				    	<option>--请选择学生--</option>\
				    </select>\
				    <div class="valid-feedback SnoFeedback"></div>\
	            </div>\
	            <div class="col-md-5 mb-3">\
	                <label>课程 <span class="text-danger">*</span></label>\
	                <select class="form-control Cno" name="Cno' + addBtnClickTime++ + '">\
				    	<option>--请选择课程--</option>\
				    </select>\
				    <div class="valid-feedback CnoFeedback"></div>\
	            </div>\
                <svg t="1592634353459" class="icon mx-3 Cutbtn" viewBox="0 0 1024 1024" version="1.1"\
                    xmlns="http://www.w3.org/2000/svg" p-id="2123" width="30" height="30" style="cursor: pointer;">\
                    <path\
                        d="M512 1009.6C236.8 1009.6 14.4 785.6 14.4 512S236.8 14.4 512 14.4s497.6 224 497.6 497.6S787.2 1009.6 512 1009.6z m0-932.8C272 76.8 76.8 272 76.8 512S272 947.2 512 947.2 947.2 752 947.2 512 752 76.8 512 76.8z"\
                        fill="#43484D" p-id="2124"></path>\
                    <path\
                        d="M705.6 556.8H313.6c-17.6 0-32-14.4-32-32s14.4-32 32-32h393.6c17.6 0 32 14.4 32 32s-14.4 32-33.6 32z"\
                        fill="#229BFF" p-id="2125"></path>\
                </svg>\
            </div>\
        ');
        var thisSelectStudent = $("select[name=Sno" + (addBtnClickTime - 1).toString() + "]");
        for (var i = 0; i < students.length; i++) {
        	thisSelectStudent.append('\
            	<option value="' + students[i]["sno"] + '">' + students[i]["sno"] + "  " + students[i]["sname"] + '</option>\
            ');
    	}
    	var thisSelectCourse = $("select[name=Cno" + (addBtnClickTime - 1).toString() + "]");
    	for (var i = 0; i < courses.length; i++) {
    		thisSelectCourse.append('\
            	<option value="' + courses[i]["cno"] + '">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
            ');
    	}
    });

    // - 按钮点击事件
    $("body").on("click", ".Cutbtn", function () {
        var Cutbtn = $(".Cutbtn");
        var index = Cutbtn.index(this);
        var insertItem = $(".insert-item");
        insertItem.eq(index).remove();
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Sno = $(".Sno");
        var Cno = $(".Cno");
        var SnoFeedback = $(".SnoFeedback");
        var CnoFeedback = $(".CnoFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何选课信息!", "warning");
            return;
        }

        // 检查所有的课程号，名称
        for (var i = 0; i < insertItem.length; i++) {
        	var sno = Sno.eq(i).val();
            if (sno.length == 0) {
                Sno.eq(i).addClass("is-invalid");
                SnoFeedback.eq(i).addClass("invalid-feedback");
                SnoFeedback.eq(i).text("请选择学生");
                return;
            }
            var cno = Cno.eq(i).val();
            if (cno.length == 0) {
                Cno.eq(i).addClass("is-invalid");
                CnoFeedback.eq(i).addClass("invalid-feedback");
                CnoFeedback.eq(i).text("请选择课程!");
                return;
            }        
        }

        var Choose = new Array();
        // 将数据保存至数组
        for (var i = 0; i < insertItem.length; i++) {
            // 获取学生编号
            var sno = Sno.eq(i).val();
            // 获取课程编号
            var cno = Cno.eq(i).val();
            // 将获取到的数据依次写入Choose[i]中
            Choose[i] = new Object();
            Choose[i].sno = sno;
            Choose[i].cno = cno;
        }

        // 将数据转化为json数据
        var choose = JSON.stringify(Choose);

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../StudentApplyInsertServlet",
            data: {
                json: choose
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增选课信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增选课信息失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!", "error");
                }
            },

            error: function (e) {
                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");
            }

        });

    });


});
