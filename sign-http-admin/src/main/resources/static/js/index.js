function confirmDialogPop(confirm,href,title,data){
    return bootbox.dialog({
        message: confirm,
        title: title,
        buttons: {
            success: {
                label: data.sLabel,
                className: "btn-success",
                callback: data.sCallback
            },
            danger: {
                label: data.cLabel,
                className: "btn-default",
                callback: data.cCallback
            }
        }
    });
}

function getAlertHTML(level, msg)
{
    var html = "<div class=\"alert ";
    if (level == 0)
    {
        html += "alert-success";
    }
    else if (level == 1)
    {
        html += "alert-info";
    }
    else if (level == 2)
    {
        html += "alert-danger";
    }
    html += "\"><a class=\"close\" data-dismiss=\"alert\" href=\"#\">&times;</a>";
    html += msg;
    html += "</div>";
    return html;
}

//用法：第一个参数，是大的div，里面包含了所有分组的div；第二个参数，原始的第一组html的元素
function bindComplexGroupEvent($div_obj, cloned_div_obj)
{
    var first_group_id = cloned_div_obj.attr("group_id");
    $div_obj.each(function(){
        var cur_group_div = $(this);
        cur_group_div.find(".add").click(function(e, f){
            //获取最后一个group的div
            var last_group_item_div = cur_group_div.find(".group-item-div:last");
            var last_group_id = last_group_item_div.attr("group_id");
            //alert("last_group_id" + last_group_id);
            var next_group_id = parseInt(last_group_id) + 1;
            //克隆最后一个
            var cloned_group_item_div = last_group_item_div.clone();
            var cloned_html = cloned_div_obj.html();
            cloned_group_item_div.html(cloned_html);
            cloned_group_item_div.attr("group_id",next_group_id);
            //显示到最后一排，这里还需要做“添加”按钮只在最后一排显示的功能
            cur_group_div.append(cloned_group_item_div);
            cloned_group_item_div.append("<div class=\"clearf\"></div>");
            cloned_group_item_div.find(".add").remove();
            cloned_group_item_div.find(".del").click(function(){
                $(this).parent().parent().remove();
            });

        });

    });
}

function fulfillGroup($div_obj, titles, bodies)
{
    var group_item_div;
    for (var i = 0,count=titles.length; i < count; ++i)
    {
        group_item_div = $div_obj.find(".group-item-div[group_id=" + (i + 1) + "]");

        group_item_div.find(".key").val(titles[i]);
        group_item_div.find(".value").val(bodies[i]);

    }
}