const form=document.getElementById("loginForm");

const message=document.getElementById("message");

document
.getElementById("togglePassword")
.onclick=()=>{

const pass=document.getElementById("password");

pass.type=
pass.type==="password"
?
"text"
:
"password";

};

form.addEventListener("submit",async(e)=>{

e.preventDefault();

const data={

email:
document.getElementById("email").value,

password:
document.getElementById("password").value

};

const response=await fetch(

API.BASE_URL+API.AUTH.LOGIN,

{

method:"POST",

headers:{

"Content-Type":"application/json"

},

credentials:"include",

body:JSON.stringify(data)

}

);

const result=await response.json();

if(result.success){

    sessionStorage.setItem(

        "loggedIn",

        "true"

    );

    location.href="dashboard.html";

}

else{

message.style.color="red";

message.innerHTML=result.message;

}

if (!result.data.name || result.data.name.trim() === "") {
    location.href = "profile.html";
    return;
}

document.getElementById("welcomeText").innerHTML =
    "Welcome, " + result.data.name;


});