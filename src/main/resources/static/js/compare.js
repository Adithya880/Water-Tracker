if(!sessionStorage.getItem("loggedIn")){

    location.href="login.html";

}
document
.getElementById("compareBtn")
.onclick = compare;

async function compare(){

const date1 =
document.getElementById("date1").value;

const date2 =
document.getElementById("date2").value;

if(date1==="" || date2===""){

alert("Please select both dates");

return;

}

const response = await fetch(

API.BASE_URL+
API.WATER.COMPARE+
`?date1=${date1}&date2=${date2}`,

{

credentials:"include"

}

);

const result = await response.json();

if(!result.success){

alert(result.message);

return;

}

const data=result.data;

document.getElementById("result").style.display="block";
result.innerHTML = `

<h2>Comparison Result</h2>

<p><strong>${data.date1}</strong> : ${data.quantity1} ML</p>

<p><strong>${data.date2}</strong> : ${data.quantity2} ML</p>

<hr>

<h3>Difference : ${data.difference} ML</h3>

`;
document.getElementById("result").innerHTML=`

<h2>Comparison Result</h2>

<p>

<b>${data.date1}</b>

: ${data.intake1} ${data.unit}

</p>

<p>

<b>${data.date2}</b>

: ${data.intake2} ${data.unit}

</p>

<hr>

<h3>

Difference :

${data.difference}

${data.unit}

</h3>

`;

}