if(!sessionStorage.getItem("loggedIn")){

    location.href="login.html";

}
window.onload = initDashboard;

async function initDashboard() {

    await checkSession();

    await loadDashboard();

    await loadTodayEntries();

}

// ---------------- Session Check ----------------

async function checkSession() {

    const response = await fetch(

        API.BASE_URL + API.AUTH.SESSION,

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

        return;

    }

}

// ---------------- Dashboard Summary ----------------

async function loadDashboard() {

    const response = await fetch(

        API.BASE_URL + "/api/dashboard",

        {

            credentials: "include"

        }

    );

    const result = await response.json();

    if (!result.success) {

        alert(result.message);

        return;

    }

    const data = result.data;

    if (!data.name || data.name.trim() === "") {

        location.href = "profile.html";

        return;

    }

    document.getElementById("welcomeText").innerHTML =
        "Welcome, " + data.name;

		const date = new Date(data.today);

		const formattedDate = date.toLocaleDateString("en-GB", {

		    day: "2-digit",

		    month: "short",

		    year: "numeric"

		});

		document.getElementById("todayDate").innerHTML = formattedDate;

		const todayIntake = data.todayIntake ?? 0;

		const dailyGoal = data.dailyGoal ?? 0;

		document.getElementById("todayIntake").innerHTML =
		    todayIntake + " ml";

		document.getElementById("dailyGoal").innerHTML =
		    dailyGoal + " ml";

    const progress =
        Math.min(Math.round(data.progress),100);

    document.getElementById("progressText").innerHTML =
        progress + "%";

    document
        .querySelector(".progress-circle")
        .style.setProperty("--progress", progress * 3.6);

}

// ---------------- Today's Entries ----------------

async function loadTodayEntries() {

    const response = await fetch(

        API.BASE_URL +
        API.WATER.HISTORY +
        "?page=0&size=10",

        {

            credentials: "include"

        }

    );

    const result = await response.json();

    if (!result.success) return;

    const tbody =
        document.getElementById("todayEntries");

    tbody.innerHTML = "";

    const today = new Date()
        .toISOString()
        .split("T")[0];

    result.data.forEach(entry => {

        if (entry.intakeDate !== today) {

            return;

        }

        tbody.innerHTML += `

<tr>

<td>${formatTime(entry.intakeTime)}</td>

<td>${entry.quantity}</td>

<td>${entry.unit}</td>

</tr>

`;

    });

}

function formatTime(time){

    const [hour, minute] = time.split(":");

    const date = new Date();

    date.setHours(hour);

    date.setMinutes(minute);

    return date.toLocaleTimeString("en-US",{

        hour:"numeric",

        minute:"2-digit",

        hour12:true

    });

}

// ---------------- Add Water ----------------

async function addWater() {

    const quantity =
        document.getElementById("quantity").value;

		const unit =
		    document.getElementById("unit").value;

		if (quantity === "") {

		    alert("Please enter quantity.");

		    return;

		}

		if (unit === "") {

		    alert("Please choose a unit.");

		    return;

		}

    const response = await fetch(

        API.BASE_URL +
        API.WATER.ADD,

        {

            method: "POST",

            credentials: "include",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify({

                quantity: quantity,

                unit: unit

            })

        }

    );

    const result =
        await response.json();

    alert(result.message);

    if (result.success) {

        document.getElementById("quantity").value = "";

        await loadDashboard();

        await loadTodayEntries();

    }

}

// ---------------- Logout ----------------

async function logout() {
	    sessionStorage.clear();

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
