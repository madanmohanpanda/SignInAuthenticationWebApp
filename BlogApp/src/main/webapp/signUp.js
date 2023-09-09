/**
 * 
 */
alert("hello");
const form = document.getElementById('form');

form.addEventListener('submit', e=>{
	let pass = document.getElementById('pass').value;
	let cpass = document.getElementById('cpass').value;
	
	if(pass !== cpass)
	{
		alert("password does not match");
		e.preventDefault();
	}
})


