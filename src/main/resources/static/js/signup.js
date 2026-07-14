const form = document.getElementById("signupForm");

form.addEventListener("submit", signup);

async function signup(e) {

    e.preventDefault();

    const data = {

        email: document.getElementById("email").value,

        password: document.getElementById("password").value

    };

    const response = await fetch(

        API.BASE_URL + API.AUTH.SIGNUP,

        {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            credentials: "include",

            body: JSON.stringify(data)

        }

    );

    const result = await response.json();

    const message = document.getElementById("message");

    if (result.success) {
		sessionStorage.setItem(

		    "loggedIn",

		    "true"

		);
        message.style.color = "green";

        message.innerHTML = "✅ Account Created Successfully";

        setTimeout(() => {

            window.location.href = "profile.html";

        }, 1000);

    } else {

        message.style.color = "red";

        message.innerHTML = result.message;

    }
	
	const password = document.getElementById("password").value;
	const confirmPassword = document.getElementById("confirmPassword").value;

	if (password !== confirmPassword) {

	    document.getElementById("message").style.color = "red";
	    document.getElementById("message").innerHTML = "Passwords do not match";

	    return;
	}

}