// (1) 회원정보 수정
function update() {
    event.preventDefault()
    let data=$("#profileUpdate").serialize()
    let userId=$("#userId").val()
    console.log("userId :"+userId)
    $.ajax({
        type:"put",
        url:`/api/user/${userId}`,
        data:data,
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        dataType:"json",
        content:"json"
    }).done(res=>{
        console.log("res : ",res);
        location.href=`/user/${userId}`

    }).fail(error=>{
        console.log("error : ",error);
        alert(error.responseJSON.data ? JSON.stringify(error.responseJSON.data) : error.responseJSON.message )// http status 상태코드 200번대가 아닐 때

    })
}