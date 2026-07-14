if(!sessionStorage.getItem("loggedIn")){

    location.href="login.html";

}
window.onload = loadReminder;

async function loadReminder() {

    const response = await fetch(

        API.BASE_URL + "/api/user/reminder",

        {

            credentials: "include"

        }

    );

    if (response.status === 401) {

        location.href = "login.html";

        return;

    }

    const result = await response.json();

    if (!result.success) {

        document.getElementById("message").innerHTML =
                result.message;

        return;

    }

    document.getElementById("reminderTime").value =
            result.data.reminderTime;

}

document.getElementById("saveBtn").onclick = async function () {

    const reminderTime =
            document.getElementById("reminderTime").value;

    const response = await fetch(

        API.BASE_URL + "/api/user/reminder",

        {

            method: "PUT",

            credentials: "include",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify({

                reminderTime: reminderTime

            })

        }

    );

    const result = await response.json();

    const message =
            document.getElementById("message");

    message.innerHTML = result.message;

    message.style.color =
            result.success ? "green" : "red";

};