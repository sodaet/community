/**
 * 提交回复
 */
function post(e) {
    // debugger;
    const questionId = $("#question_id").val();
    const content = $("#comment_content").val();
    if (!isLog(e)) return;
    comment2target(questionId, 1, content);
}

function comment2target(targetId, type, content) {
    // debugger;
    if (!content) {
        alert("不能回复空内容!!!");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                alert("服务器出错了，稍后再试试吧!!");
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    // debugger;
    const commentId = e.getAttribute("data-id");
    const content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    const id = e.getAttribute("data-id");
    const comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    const collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        const subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length !== 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    const mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    const mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    const mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    const commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function deleteComment(e) {
    var isDelete = confirm("确定要删除这个评论吗？");
    console.log(isDelete);
    var targetId = e.getAttribute("data-id");
    var parentId = e.getAttribute("data-parentId");
    if (isDelete) {
        $.ajax({
            type: "POST",
            url: "/delete",
            contentType: 'application/json',
            data: JSON.stringify({
                "id": targetId,
                "parentId": parentId
            }),
            success: function (response) {
                if (response.code === 200) {
                    window.location.reload();
                } else {
                    alert("删除失败，请联系作者解决吧！")
                }
            },
            dataType: "json"
        });
    }
}

function deleteQuestion() {
    alert("删除问题帖子功能还没有实现，期待下一个版本吧！")
}

function isLog(e) {
    var user = e.getAttribute("data-user");
    if (user == null) {
        alert("请登录后再和TA交流吧！")
        return false;
    }
    else return true;
}

function alertMeg() {
    alert('作者还没有做出这个功能，去他的GitHub催他吧。');
}