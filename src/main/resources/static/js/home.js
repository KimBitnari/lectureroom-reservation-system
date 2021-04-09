function agreeReservation() {
	var agreeContent = document.getElementById("agreeReservation");
	var rejectContent = document.getElementById("rejectReservation");
	var waitingContent = document.getElementById("waitingReservation");
	
	if(rejectContent.style.display = 'block') rejectContent.style.display = 'none';
	if(waitingContent.style.display = 'block') waitingContent.style.display = 'none';
	
	agreeContent.style.display = 'block';
}
function rejectReservation() {
	var agreeContent = document.getElementById("agreeReservation");
	var rejectContent = document.getElementById("rejectReservation");
	var waitingContent = document.getElementById("waitingReservation");
	
	if(agreeContent.style.display = 'block') agreeContent.style.display = 'none';
	if(waitingContent.style.display = 'block') waitingContent.style.display = 'none';
	
	rejectContent.style.display = 'block';
}
function waitingReservation() {
	var agreeContent = document.getElementById("agreeReservation");
	var rejectContent = document.getElementById("rejectReservation");
	var waitingContent = document.getElementById("waitingReservation");
	
	if(agreeContent.style.display = 'block') agreeContent.style.display = 'none';
	if(rejectContent.style.display = 'block') rejectContent.style.display = 'none';
	
	waitingContent.style.display = 'block';
}

