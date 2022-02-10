/**
 2. 스토리 페이지
 (1) 스토리 로드하기
 (2) 스토리 스크롤 페이징하기
 (3) 좋아요, 안좋아요
 (4) 댓글쓰기
 (5) 댓글삭제
 */
let page=0;
// (1) 스토리 로드하기
function storyLoad() {
    $.ajax({
        url:`/api/image?page=${page}`,
        dataType:"json"
    }).done(res=>{
        console.log(res)
        res.data.content.forEach((image)=>{
            console.log("image:  ",image)
            let storyItem =getStoryItem(image)
            $("#storyList").append(storyItem)
        })
    }).fail(error=>{console.log("오류 : ",error)})
}

function getStoryItem(image) {
    let item=`
	<div class="story-list__item">
\t\t\t\t<div class="sl__item__header">
\t\t\t\t\t<div>
\t\t\t\t\t\t<img class="profile-image" src="/upload/${image.user.profileImageUrl}"
\t\t\t\t\t\t\tonerror="this.src='/images/person.jpeg'" />
\t\t\t\t\t</div>
\t\t\t\t\t<div>${image.user.username}</div>
\t\t\t\t</div>
\t\t\t\t<div class="sl__item__img">
\t\t\t\t\t<img src="/upload/${image.postImageUrl}" />
\t\t\t\t</div>
\t\t\t\t<div class="sl__item__contents">
\t\t\t\t\t<div class="sl__item__contents__icon">
\t\t\t\t\t\t<button>`
    if(image.likeState){
        item+=`<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`
    }else{
        item+=`<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`
    }
    item+= `\t\t\t\t\t\t</button>
	\t\t\t\t\t</div>
	
	\t\t\t\t\t<span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount}</b>likes</span>
	
	\t\t\t\t\t<div class="sl__item__contents__content">
	\t\t\t\t\t\t<p>${image.caption}</p>
	\t\t\t\t\t</div>
	
	\t\t\t\t\t<div id="storyCommentList-${image.id}">`;

    // image.comments.forEach((comment)=>{
    //     item+=`
	// 		  <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
	// 		    <p>
	// 		      <b>${comment.user.username}</b>
	// 		      ${comment.content}
	// 		    </p>
	// 		    <button><i class="fas fa-times"></i></button>
	// 		  </div>`
    // })
    // item+=`
	// 	\t\t\t\t\t</div>
	// 	\t\t\t\t\t<div class="sl__item__input">
	// 	\t\t\t\t\t\t<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
	// 	\t\t\t\t\t\t<button type="button" onClick="addComment(${image.id})">게시</button>
	// 	\t\t\t\t\t</div>
	// 	\t\t\t\t</div>`
    return item
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
    let checkNum = $(window).scrollTop() - ($(document).height()-$(window).height())
    console.log(checkNum)
    if(-1<checkNum && checkNum<1){
        page++;
        storyLoad();
    }
});
storyLoad();

// (3) 좋아요, 안좋아요
function toggleLike() {
    let likeIcon = $("#storyLikeIcon-1");
    if (likeIcon.hasClass("far")) {
        likeIcon.addClass("fas");
        likeIcon.addClass("active");
        likeIcon.removeClass("far");
    } else {
        likeIcon.removeClass("fas");
        likeIcon.removeClass("active");
        likeIcon.addClass("far");
    }
}

// (4) 댓글쓰기
function addComment() {

    let commentInput = $("#storyCommentInput-1");
    let commentList = $("#storyCommentList-1");

    let data = {
        content: commentInput.val()
    }

    if (data.content === "") {
        alert("댓글을 작성해주세요!");
        return;
    }

    let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-2""> 
			    <p>
			      <b>GilDong :</b>
			      댓글 샘플입니다.
			    </p>
			    <button><i class="fas fa-times"></i></button>
			  </div>
	`;
    commentList.prepend(content);
    commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment() {

}






