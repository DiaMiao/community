/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentToTarget(questionId, 1, content);
}

function commentToTarget(targetId, type, content) {

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "text": content,
            "parentType": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=d374568ca306b58c199f&redirect_uri=http://localhost:8080/callback&state=haha");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });

}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    commentToTarget(commentId, 2, content);
}



/**
 * 展开二级评论
 */

function collapseComments(e) {

    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    //获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comments.removeClass("show");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {

            //展开
            comments.addClass("show");
            //标记二级评论展开状态
            e.setAttribute("data-collapse", "show");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "mr-3 userAvatar rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h6/>", {
                        "class": "mt-0",
                        html:comment.user.name
                    })).append($("<div/>", {
                        html:comment.text
                    })).append($("<div/>", {
                        "class": "menu",
                    }).append($("<div/>", {
                        "class": "float-right",
                        html: moment(comment.gmtModified).format('DD/MM/YYYY')
                    })));


                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);


                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });
                //展开
                comments.addClass("show");
                //标记二级评论展开状态
                e.setAttribute("data-collapse", "show");
                e.classList.add("active");
            });
        }
    }
}