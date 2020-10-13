var UserAdmin = "admspcm";
var PassAdmin = "teste";
var UserLCW = "testeA";
var PassLCW = "teste";

function getDateToday() {
	var d=new Date();
    return ('0'+d.getDate()).slice(-2)+'/'+('0'+(d.getMonth()+1)).slice(-2) +'/'+d.getFullYear();
}

function getDateAddFromToday(numDay) {
	var d=new Date();
	d.setDate(d.getDate() + numDay);
	return ('0'+d.getDate()).slice(-2)+'/'+('0'+(d.getMonth()+1)).slice(-2) +'/'+d.getFullYear();
}
