if(!sessionStorage.getItem("loggedIn")){

    location.href="login.html";

}
window.onload = initializeProfile;

async function initializeProfile() {

    await checkSession();

    await loadProfile();

}

// ---------------- Session Check ----------------

async function checkSession() {

    const response = await fetch(

        API.BASE_URL +
        API.AUTH.SESSION,

        {

            credentials: "include"

        }

    );

    if (!response.ok) {

        location.href = "login.html";

        return;

    }

    const result = await response.json();

    if (!result.success) {

        location.href = "login.html";

    }

}

// ---------------- Load Profile ----------------

async function loadProfile() {

    const response = await fetch(

        API.BASE_URL +
        "/api/user/profile",

        {

            credentials: "include"

        }

    );

    const result = await response.json();

    if (!result.success) {

        alert(result.message);

        return;

    }

    const user = result.data;

    document.getElementById("name").value =
        user.name ?? "";

    document.getElementById("dailyGoal").value =
        user.dailyGoal ?? "";

    if(user.reminderTime){

        document.getElementById("reminderTime").value =
            user.reminderTime.substring(0,5);

    }

}

// ---------------- Save Profile ----------------

async function saveProfile(){

    const name =
        document.getElementById("name").value.trim();

    const dailyGoal =
        document.getElementById("dailyGoal").value;

    const reminderTime =
        document.getElementById("reminderTime").value;

    if(name===""){

        alert("Enter your name");

        return;

    }

    if(dailyGoal===""){

        alert("Enter daily goal");

        return;

    }

    if(reminderTime===""){

        alert("Select reminder time");

        return;

    }

    const response = await fetch(

        API.BASE_URL +
        "/api/user/profile",

        {

            method:"PUT",

            credentials:"include",

            headers:{

                "Content-Type":"application/json"

            },

            body:JSON.stringify({

                name:name,

                dailyGoal:Number(dailyGoal),

                reminderTime:reminderTime

            })

        }

    );

    const result =
        await response.json();

    if(result.success){

        alert("Profile Updated Successfully");

        location.href="dashboard.html";

    }

    else{

        alert(result.message);

    }

}

// ---------------- Logout ----------------

async function logout(){

    await fetch(

        API.BASE_URL+
        API.AUTH.LOGOUT,

        {

            method:"POST",

            credentials:"include"

        }

    );

    location.href="login.html";

}