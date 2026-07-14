if(!sessionStorage.getItem("loggedIn")){

    location.href="login.html";

}
let page = 0;
const size = 5;

window.onload = initializeHistory;

async function initializeHistory() {

    await checkSession();

    await loadHistory();

}

// ---------------- Session ----------------

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

// ---------------- Load History ----------------

async function loadHistory() {

    const response = await fetch(

        API.BASE_URL +
        API.WATER.HISTORY +
        `?page=${page}&size=${size}`,

        {

            credentials: "include"

        }

    );

    const result = await response.json();

    if (!result.success) {

        alert(result.message);

        return;

    }

    const tbody =
        document.getElementById("historyBody");

    tbody.innerHTML = "";

    result.data.forEach(entry => {

        tbody.innerHTML += `

<tr>

<td>${entry.intakeDate}</td>

<td>${formatTime(entry.intakeTime)}</td>

<td>${entry.quantity}</td>

<td>${entry.unit}</td>

<td>

<button
class="edit"
onclick="editEntry(${entry.id})">

Edit

</button>

<button
class="delete"
onclick="deleteEntry(${entry.id})">

Delete

</button>

</td>

</tr>

`;

    });

    document.getElementById("pageNumber").innerHTML =
        page + 1;

}

// ---------------- Pagination ----------------

function nextPage() {

    page++;

    loadHistory();

}

function previousPage() {

    if (page > 0) {

        page--;

        loadHistory();

    }

}

// ---------------- Delete ----------------

async function deleteEntry(id) {

    if (!confirm("Delete this entry?")) {

        return;

    }

    const response = await fetch(

        API.BASE_URL +
        API.WATER.DELETE +
        id,

        {

            method: "DELETE",

            credentials: "include"

        }

    );

    const result =
        await response.json();

    alert(result.message);

    if (result.success) {

        loadHistory();

    }

}

// ---------------- Edit ----------------

async function editEntry(id) {

    const quantity =
        prompt("Enter new quantity");

    if (quantity == null) {

        return;

    }

    const unit =
        prompt(

            "Enter Unit (ML/LITER/GLASS)",

            "ML"

        );

    if (unit == null) {

        return;

    }

    const response = await fetch(

        API.BASE_URL +
        API.WATER.UPDATE +
        id,

        {

            method: "PUT",

            credentials: "include",

            headers: {

                "Content-Type":
                    "application/json"

            },

            body: JSON.stringify({

                quantity: Number(quantity),

                unit: unit

            })

        }

    );

    const result =
        await response.json();

    alert(result.message);

    if (result.success) {

        loadHistory();

    }

}

// ---------------- Logout ----------------

async function logout() {

    await fetch(

        API.BASE_URL +
        API.AUTH.LOGOUT,

        {

            method: "POST",

            credentials: "include"

        }

    );

    location.href = "login.html";

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