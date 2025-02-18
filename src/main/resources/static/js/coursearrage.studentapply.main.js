$(function () {

    // 表格数据展示区
    var tbody = $(".tbody");
    // 定义每页最大显示数
    var MAXSHOW = 7;

    // 全选复选框点击事件
    $("body").on("change", "#checkAll", function () {
        var checkuser = $("input[name=checkuser]");
        var checkAll = $("#checkAll");

        // 全选复选框选择状态
        var checked = checkAll.is(':checked');

        if (checked == true) {
            checkuser.prop("checked", true);
        } else {
            checkuser.prop("checked", false);
        }
    });

    // 查询按钮点击事件
    $(".Searchbtn").click(function() {
        var q = $(".Searchtext").val();
        if (q.length == 0) {
            return;
        }
        location.href = "./search?q=" + q;
    });
    
    // 查询全部未通过点击事件
    $(".Noallowbtn").click(function() {
        location.href = "./search?q=" + "ALL-NO-ALLOW";
    });

    // 发送ajax 请求数据
    $.ajax({

        type: "post",
        url: "../../StudentApplyListServlet",

        success: function (e) {

            if (e == "empty") {
                return;
            }

            // 解析选课信息
            var choose = JSON.parse(e);
            // 获取选课总数
            var length = choose.length;
            // 获取分页总数
            var pagenum = Math.ceil(length * 1.0 / MAXSHOW);
            // 获取当前页数
            var pagenow = getQueryVariable("page");
            if (pagenow == false) {
                pagenow = 1; // 默认为第一页
            }

            // 输出本页表格数据
            var maxi = pagenow * MAXSHOW < length ? pagenow * MAXSHOW : length;
            // 对未定义属性值进行补充
            for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
            	if (choose[i]['status'] == 0) choose[i]['status'] = '等待通过';
            	else choose[i]['status'] = '已通过';
            }
            for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                tbody.append('\
            		<tr scid=' + choose[i]['scid'] + ' class="user-item">\
                        <td>\
                            <div class="form-check">\
                                <input class="form-check-input" type="checkbox" name="checkuser">\
                            </div>\
                        </td>\
                        <td>' + choose[i]['sno'] + '</td>\
                        <td>' + choose[i]['sname'] + '</td>\
                        <td>' + choose[i]['cno'] + '</td>\
                        <td>' + choose[i]['cname'] + '</td>\
                        <td>' + choose[i]['status'] + '</td>\
                        <td>\
                            <button type="button" class="dropbtn btn btn-danger btn-sm">删除</button>\
                            <button type="button" class="allowbtn btn btn-success btn-sm">通过</button>\
                        </td>\
                    </tr>\
            	');
            }

            // 表格底部批量操作栏
            tbody.append('\
                <!-- 表格底部批量操作栏 -->\
                <tr class="table-borderless">\
                    <td colspan="3">\
                        <div class="form-check">\
                            <input class="form-check-input" type="checkbox" id="checkAll">\
                            <label class="form-check-label" for="checkAll">全选</label>\
                        </div>\
                    </td>\
                    <td colspan="4">\
                        <button type="button" class="dropallbtn btn btn-danger btn-sm">批量删除</button>\
                        <button type="button" class="btn btn-primary btn-sm" onclick="location.href=\'./insert\'">批量添加</button>\
            			<button type="button" class="allowallbtn btn btn-success btn-sm">批量通过</button>\
                    </td>\
                </tr>\
            ');

            // 定义上一页
            var lastpage = pagenow - 1 > 1 ? pagenow - 1 : 1;
            // 定义下一页
            var nextpage = pagenow + 1 < pagenum ? pagenow + 1 : pagenum;
            // 表格底部分页栏
            tbody.append('\
                <!-- 表格底部分页栏 -->\
                <tr class="table-borderless">\
                    <td colspan="4">\
                        <nav aria-label="Page navigation example">\
                            <ul class="pagination">\
                                <li class="page-item"><a class="page-link" href="./index?page=1">首页</a></li>\
                                <li class="page-item"><a class="page-link" href="./index?page=' + lastpage + '">上一页</a></li>\
                                <li class="page-item"><a class="page-link" href="./index?page=' + nextpage + '">下一页</a></li>\
                                <li class="page-item"><a class="page-link" href="./index?page=' + pagenum + '">尾页</a></li>\
                            </ul>\
                        </nav>\
                    </td>\
                    <td colspan="3">\
                        <span>第<span class="nowpage">' + pagenow + '</span>页/共<span class="allpage">' + pagenum + '</span>页</span>\
                    </td>\
                </tr>\
            ');

        },

        error: function (e) {

            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");

        }

    });

    // 表格栏单项数据删除按钮点击事件
    $("body").on("click", ".dropbtn", function() {
        var index = $(".dropbtn").index(this);
        // 获取选课编号 scid
        var userItem = $(".user-item");
        var scid = userItem.eq(index).attr("scid");

        // 发送ajax请求
        $.ajax({

            type: "post",
            url: "../../StudentApplyDeleteServlet",
            data: {
                scid: scid
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除选课成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除选课失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!" + e, "error");
                }
            },

            error: function (e) {

                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");

            }
        });
    });
    
    // 表格栏单项数据通过按钮点击事件
    $("body").on("click", ".allowbtn", function() {
    	var index = $(".allowbtn").index(this);
        // 获取选课编号 scid
        var userItem = $(".user-item");
        var scid = userItem.eq(index).attr("scid");
        // 获取通过状态
        if ($(".user-item > td:nth-child(6)").eq(index).text() == "已通过") {
        	swal("免了", "该条选课已经通过", "warning");
        	return;
        }
        var choose = new Object();
        choose.scid = scid;
        // 发送ajax请求
        $.ajax({

            type: "post",
            url: "../../StudentApplyAllowServlet",
            data: {
                json: JSON.stringify(choose)
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 通过成功
                    swal("成功", "通过选课成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 通过失败
                    swal("糟糕", "通过选课失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!" + e, "error");
                }
            },

            error: function (e) {

                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");

            }
        });
    });

    // 批量删除按钮点击事件
    $("body").on("click", ".dropallbtn", function() {
        // 获取选中的选课编号 scid
        var userItem = $(".user-item");
        // 定义选课编号数组，数组下标
        var SCid = new Array(), scindex = 0;
        for (var i = 0; i < userItem.length; i++) {
            // 当前单选框
            var checkuser = $("input[name=checkuser]").eq(i);
            // 当前选课编号
            var scid = userItem.eq(i).attr("scid");
            // 当前单选框选择状态
            var checked = checkuser.is(':checked');
            // 若为选中状态，添加至数组
            if (checked == true) {
                SCid[scindex ++] = scid;
            }
        }

        // 判断选中的选课数量 
        if (SCid.length == 0) {
            swal("糟糕", "你好像没有选择任何选课!", "warning");
            return;
        }

        // 发送ajax数组提交要删除的选课信息
        var json = JSON.stringify(SCid);
        $.ajax({

            type: "post",
            url: "../../StudentApplyDeleteBatchServlet",
            data: {
                scid: json
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除所选选课成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除所选选课失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!" + e, "error");
                }
            },

            error: function (e) {

                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");

            }
        });
    });
    
    // 批量通过按钮点击事件
    $("body").on("click", ".allowallbtn", function() {
        // 获取选中的选课编号 scid
        var userItem = $(".user-item");
        // 定义选课编号数组，数组下标
        var SCid = new Array(), scindex = 0;
        for (var i = 0; i < userItem.length; i++) {
            // 当前单选框
            var checkuser = $("input[name=checkuser]").eq(i);
            // 当前选课编号
            var scid = userItem.eq(i).attr("scid");
            // 当前单选框选择状态
            var checked = checkuser.is(':checked');
            // 若为选中状态，添加至数组
            if (checked == true) {
                SCid[scindex ++] = scid;
            }
        }

        // 判断选中的选课数量 
        if (SCid.length == 0) {
            swal("糟糕", "你好像没有选择任何选课!", "warning");
            return;
        }

        // 发送ajax数组提交要通过的选课信息
        var json = JSON.stringify(SCid);
        $.ajax({

            type: "post",
            url: "../../StudentApplyAllowBatchServlet",
            data: {
                json: json
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 通过成功
                    swal("成功", "通过所选选课成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 通过失败
                    swal("糟糕", "通过所选选课失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!" + e, "error");
                }
            },

            error: function (e) {

                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");

            }
        });
    });

});

// 获取get数据
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}