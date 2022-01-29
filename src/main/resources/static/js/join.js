const signUpUsername= document.querySelector("#username")
const signUpEmail=document.querySelector("#email")
const signUpPassword1=document.querySelector("#password1")
const signUpPassword2=document.querySelector("#password2")
const signUpBtn = document.querySelector("#signupBtn")
const signUpName= document.querySelector("#name")
const emailDupCheckBtn=document.querySelector("#emailDupCheckBtn")
const usernameDupCheckBtn=document.querySelector("#usernameDupCheckBtn")
const signUpEmailChecked=document.querySelector("#emailChecked")
const signUpUsernameChecked=document.querySelector("#usernameChecked")

let regPassword= /^[a-zA-Z0-9]{4,12}$/ // 패스워드가 적합한지 검사할 정규식
let regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일이 적합한지 검사할 정규식
let regUsername=/^[a-z]+[a-z0-9]{5,19}$/g; //아이디 적합한지 검사할 정규식 영문자로 시작하는 영문자 또는 숫자 6~20자

//정규표현식 체크
function regexCheck(reg, what, message) {
    if(reg.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    //return false;
}
//회원가입 진행 함수
function handleSignUpBtnClick(){
    if(signUpUsername.value===""){
        //이름이 비어있는지 검사
        alert("아이디를 입력해주세요.");
        signUpUsername.focus()
        return false;
    }
    if(signUpEmail.value===""){
        //이메일이 비어있는지 검사
        alert("이메일을 입력해주세요.");
        signUpEmail.focus()
        return false;
    }
    if(signUpPassword1.value===""){
        //비밀번호1 비어있는지 검사
        alert("비밀번호를 입력해주세요.");
        signUpPassword1.focus()
        return false;
    }

    if(signUpPassword2.value===""){
        //비밀번호2 비어있는지 검사
        alert("확인 비밀번호를 입력해주세요.");
        signUpPassword2.focus()
        return false;
    }

    if(signUpName.value===""){
        //이름 비어있는지 검사
        alert("사용할 이름을 입력해주세요.");
        signUpName.focus()
        return false;
    }

    if (signUpPassword1.value!==signUpPassword2.value){
        //패스워드 1,2 동일성 검사
        alert("비밀번호가 다릅니다. 다시 확인해주세요.");
        signUpPassword2.value="";
        signUpPassword2.focus();
        return false;
    }
    if(!regexCheck(regPassword,signUpPassword1,"비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력 가능합니다.")){
        //패스워드1 유효성 검사
        return false;
    }


    if(signUpUsernameChecked.value!=="true"){
        alert("아이디 중복확인을 해주세요.");
        return false;
    }
    
    
    if(signUpEmailChecked.value!=="true"){
        alert("이메일 중복확인을 해주세요.");
        return false;
    }


    let data={
        username:signUpUsername.value,
        email:signUpEmail.value,
        password1:signUpPassword1.value,
        password2:signUpPassword2.value,
        name:signUpName.value
    }
    $.ajax({
        type:"POST",
        url:"/auth/api/join",
        data:JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json"
    }).done(function(res){
        if(res.status===500){
            alert("회원가입에 실패하였습니다.")
        }else{
            alert("회원가입에 성공하였습니다. 반갑습니다.")
            location.href="/";
        }
    }).fail(function (e){
        alert(JSON.stringify(e));
    })
}
//이메일 유효성 검사, 중복 체크
function handleEmailDupCheckBtnClick(){
    if(signUpEmail.value===""){
        //비어있으면
        alert("이메일을 입력해주세요.")
        signUpEmail.focus();
        return false;
    }
    if(!regexCheck(regEmail,signUpEmail,"적합하지 않는 이메일 형식입니다.")){
        return false;
    }

    let data={
        email:signUpEmail.value
    };

    $.ajax({
        type:"POST",
        url:"/auth/api/checkEmailUsed",
        data:JSON.stringify(data),
        contentType:"application/json;charset=utf-8",
        dataType:"json"
    }).done(function (res){
        let resData=res.data;
        if(resData===1){
            //존재하지 않는 회원 -> 사용가능한 이메일
            if(confirm("사용 가능한 이메일입니다. 사용하시겠습니까?")){
                $("#email").attr("readonly",true);
                emailDupCheckBtn.removeEventListener("click",handleEmailDupCheckBtnClick);
                signUpEmailChecked.value="true";
            }else{
                signUpEmail.value="";
                signUpEmail.focus();
            }
        }else{
            alert("이미 등록된 이메일입니다.")
            signUpEmail.value=""
            signUpEmail.focus()
        }
    }).fail(function (e){
        console.log(e);
    })
}
//아이디 유효성 검사, 중복 체크
function handleUsernameDupCheckBtnClick(){
    if(signUpUsername.value===""){
        //비어있으면
        alert("아이디를 입력해주세요.")
        signUpUsername.focus();
        return false;
    }
    if(!regexCheck(regUsername,signUpUsername,"적합하지 않는 아이디 형식입니다.")){
        return false;
    }

    let data={
        username:signUpUsername.value
    };

    $.ajax({
        type:"POST",
        url:"/auth/api/checkUsernameUsed",
        data:JSON.stringify(data),
        contentType:"application/json;charset=utf-8",
        dataType:"json"
    }).done(function (res){
        let resData=res.data;
        if(resData===1){
            //존재하지 않는 회원 -> 사용가능한 이메일
            if(confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")){
                $("#username").attr("readonly",true);
                usernameDupCheckBtn.removeEventListener("click",handleUsernameDupCheckBtnClick);
                signUpUsernameChecked.value="true";
            }else{
                signUpUsername.value="";
                signUpUsername.focus();
            }

        }else{
            alert("이미 사용중인 아이디입니다.")
            signUpUsername.value=""
            signUpUsername.focus()
        }
    }).fail(function (e){
        console.log(e);
    })

}







//회원 가입버튼을 눌렀을 때 실행
function signUp(){
    emailDupCheckBtn.addEventListener("click",handleEmailDupCheckBtnClick)
    usernameDupCheckBtn.addEventListener("click",handleUsernameDupCheckBtnClick)
    signUpBtn.addEventListener("click",handleSignUpBtnClick)
}

signUp()
