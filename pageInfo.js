/**
	2020. 03. 10
	fit frame
	funnysky
	funnysky@fitframe.co.kr
**/

// 차시명 사이즈 
var sizeTitleW = 1000;
var sizeTitleH = 100;

/** ★1차시 페이지 정보★ */
var pageinfo = new Array();
pageinfo[1] = [itostr(curChasi)+"0101", "video", "../vod/intro"];
pageinfo[2] = [itostr(curChasi)+"0102", "audio", "oxquiz"];
pageinfo[3] = [itostr(curChasi)+"0103", "video", "../vod/"+itostr(cal_chasi())+"_03"];
pageinfo[4] = [itostr(curChasi)+"0201", "video", "http://um2mmobile.hscdn.com/h/2019_Stream/epasskorea/Marketing/Instagram/LHJ_5/Instagram_LHJ_190625_14"];
pageinfo[5] = [itostr(curChasi)+"0202", "video", "http://um2mmobile.hscdn.com/h/2019_Stream/epasskorea/Marketing/Instagram/LHJ_5/Instagram_LHJ_190801_19"];
pageinfo[6] = [itostr(curChasi)+"0301", "video", "../vod/quiz"];
pageinfo[7] = [itostr(curChasi)+"0401", "audio", "summary"];
pageinfo[8] = [itostr(curChasi)+"0402", "video", "../vod/outro"];
//alert(itostr(cal_chasi()));


/** 인덱스 정보 */
var indexInfoArr = new Array();
indexInfoArr[0] = 1; //들어가기(고정)
indexInfoArr[1] = 4; //학습하기(고정)
//indexInfoArr[2] = 5; //평가하기★
indexInfoArr[2] = pageinfo.length - 3; //평가하기(뒤에서 세번째 페이지)
indexInfoArr[3] = pageinfo.length - 2; //정리하기(뒤에서 두번째 페이지)


/** 이벤트 페이지  */
var motionTimeArr = new Array();
//이벤트 페이지 자동 입력
for (var i = 1; i < pageinfo.length; i++) {
	if (i == 2) {
		motionTimeArr[i] = 1; //사전퀴즈 이벤트 시작 시간
	} else if (i == indexInfoArr[2]) {
		motionTimeArr[i] = 2; //퀴즈페이지 이벤트 시작 시간
	} else {
		motionTimeArr[i] = 0;
	}
}
/*
//이벤트 페이지 수동 입력
motionTimeArr[1] = 0;
motionTimeArr[2] = 1; //사전퀴즈 이벤트 시작 시간
motionTimeArr[3] = 0;
motionTimeArr[4] = 0;
motionTimeArr[5] = 2; //퀴즈페이지 이벤트 시작 시간
motionTimeArr[6] = 0;
motionTimeArr[7] = 0;
motionTimeArr[8] = 0;
*/

/** 상단 타이틀 size */
function titleSize() {
	$('#header .leftTitle').css("width", sizeTitleW + "px");
	$('#header .leftTitle').css("height", sizeTitleH + "px");
	$('#header .leftTitle').css("background", "url(./img/chasi.png)");
}

