/**
	2017. 12. 06
	fit frame
	funnysky
	funnysky@fitframe.co.kr
	quiz
	...

**/

document.write('  <link rel="stylesheet" type="text/css" href="../common/css/quiz.css" /> ');

var ansArr = new Array();
var qnsArr = new Array("", 0, 0, 0);
//var questionNum = qnsArr.length - 1;
var questionNum;
var pageInfo = new Array(questionNum);
var lineInfo = new Array();
var pageNum = 1;													// 문제번호
var objArr = new Array();									// 텍스트
var objArr2 = new Array();									// 텍스트
var tryNum = 0;
var tryMax = 2;
var divObj, divObj2;
var isClick = true;
var path = cssPath + "quiz/";
var overColor = "#ff7c1c";
var outColor = "#000";

var P2F_height = 217; //.cssPage2Feed 의 높이값 입력


for (var i = 1; i <= questionNum; i++) {
	pageInfo[i] = new Array();
}

function reInitPage() {
	//motionEvtFlag = false;
	log("reInitPage!!");
	motionEvtFlag = false;
	pageNum = 1;
	qnsArr = new Array("", 0, 0, 0);
}

function startQuiz(_ans, _pageInfo, _line) {
	log("startQuiz!!!");
	motionFlag = false;
	pageInfo = _pageInfo;
	ansArr.push("");
	lineInfo.push("");

	for (var i = 0; i < ans.length; i++) {
		ansArr.push(ans[i]);
		lineInfo.push(_line[i]);
	}
	questionNum = ansArr.length - 1;

	$(".cssPage1Btn").on("click", quizStartClick);
	// 퀴즈 시작
	//quizStartClick();
	//console..../common/("lineInfo : " + lineInfo);

	// 오브젝트 세팅 및 이벤트 등록
	setObj();
	setBtnEvt();
}


function quizStartClick() {
	playSound("button click");
	if (Player.currentTime < Player.duration - 1) {
		Player.currentTime = Player.duration - 1;
	}
	$("#page1").hide();
	$("#page2").show();

	// 문제 세팅
	setQuestion();
}


function setQuestion() {
	tryNum = 0;

	// 버튼 초기화
	$('.cssPage2Btn .btn1').show();
	$('.cssPage2Btn .btn2').hide();
	$('.cssPage2Btn .btn3').hide();

	// 정답 초기화
	qnsArr[pageNum] = 0;

	// 문제 세팅

	$('.quiz').show();
	$('.quiz1').hide();
	$('.ox_quiz').hide();
	//$('.cssPage2Bogi1').css("top", "190px");
	setText();
}

function setText() {
	/** 기존 텍스트 초기화  **/
	$('.cssPage2Quesition .cssPage2Q').empty();
	//$('.cssPage2Quesition .cssPage2T').empty();
	$('.cssPage2Quesition .cssPage2T .textMiddle').empty();

	for (var i = 1; i <= qnsArr.length; i++) {
		$('.cssPage2Bogi1 #bogi-' + i).empty();
		$('.cssPage2Bogi1 #bogi-' + i).css({ "cursor": "pointer", "color": outColor });
		$(".cssPage2Bogi1 #bogiImg-" + i).css("background", "url(" + path + "quiz_num" + i + "_d.png)");

		$("#bogiCheck" + i).hide();
		$("#bogiResult" + i).hide();
	}

	$('.cssPage2Feed').hide();
	$('.cssPage2Feed .feedAns').empty();
	$('.cssPage2Feed .feedTxt').empty();

	//$('.cssPage2Feed2').hide();
	//$('.cssPage2Feed2_bg').hide();
	//$('.cssPage2Feed2 .feedAns').empty();
	//$('.cssPage2Feed2 .feedTxt').empty();

	$('.cssPage2Ox').hide();

	/** 텍스트 세팅 **/
	isClick = true;
	//$('.cssPage2Quesition .cssPage2Q').append("Q"+pageNum);

	$('.cssPage2Quesition .cssPage2Q').css("background", "url(" + path + "q" + pageNum + ".png)");
	//$('.cssPage2Quesition .cssPage2T').append(pageInfo[pageNum][0]);
	$('.cssPage2Quesition .cssPage2T .textMiddle').append(pageInfo[pageNum][0]);

	for (var i = 1; i <= qnsArr.length; i++) {
		$('.cssPage2Bogi1 #bogi-' + i).append(pageInfo[pageNum][i]);
	}

	$('.cssPage2Feed .feedAns').append(ansArr[pageNum]);
	$('.cssPage2Feed .feedTxt').append(pageInfo[pageNum][5]);

	//$('.cssPage2Feed2 .feedAns').append(ansArr[pageNum]);
	//$('.cssPage2Feed2 .feedTxt').append(pageInfo[pageNum][5]);

	/** 예외처리 없을 경우 모두 주석처리
			문제 기본 폰트 사이즈 37px; cssPage2T
			보기 높이 값 262px; .cssPage2Bogi
	**/

	/*
	if (lineInfo[pageNum] == 1) {
		$('.cssPage2T').css("padding-top", "24px");
	} else if (lineInfo[pageNum] == 2) {
		$('.cssPage2T').css("padding-top", "10px");
	}
	*/

	var new_height = P2F_height - lineInfo[pageNum];
	var btnTop = Math.floor(lineInfo[pageNum]/2);
	$('.cssPage2Feed').css("height", new_height+"px");
	$('.cssPage2Btn .btn1').css("top", btnTop+"px");
	$('.cssPage2Btn .btn2').css("top", btnTop+"px");
	$('.cssPage2Btn .btn3').css("top", btnTop+"px");
	//alert(btnTop);
}

function setObj() {
	objArr.push('');
	objArr2.push('');

	for (var i = 1; i <= qnsArr.length; i++) {
		divObj = $('.cssPage2Bogi1 #bogi-' + i);
		divObj.num = i;
		divObj.isClick = true;
		divObj.css("cursor", "pointer");
		objArr.push(divObj);
		_addBtnEvent2(divObj);
	}
}

function setBtnEvt() {
	// 정답 버튼
	$('.cssPage2Btn .btn1').on("click", { data: "answer" }, evtBtnClick);

	// 다시 문제
	$('.cssPage2Btn .btn2').on("click", { data: "next" }, evtBtnClick);

	// 결과 보기
	$('.cssPage2Btn .btn3').on("click", { data: "result" }, evtBtnClick);

	// 다시 풀기
	$('.cssPage3Btn').on("click", { data: "reGame" }, evtBtnClick);

	// 피드백 닫기버튼
	$('.cssPage2Feed2 .feed2_exit').on("click", function () {
		$('.cssPage2Feed2').hide();
		$('.cssPage2Feed2_bg').hide();
	});
}

function evtBtnClick(e) {
	var type = e.data.data;

	if (type == "answer") {
		if (qnsArr[pageNum] == 0) {
			$(".cssPage2Check").fadeIn().delay(1000).fadeOut();
			playSound("chimes");
		} else {
			answer();
		}
	} else if (type == "next") {
		setQuestion();
		playSound("button click");
	} else if (type == "result") {
		setResult();
		playSound("button click");
	} else if (type == "reGame") {
		setReGame();
		playSound("button click");
	}
}

function _addBtnEvent2(_target) {

	_target.on("mouseover", function (evt) {
		var num = _target.num;
		var type = $(this).attr("id").split("-");
		if (isClick) {
			if (_target.isClick) {
				//$(this).css("cursor","pointer");
				if (type[0] == "bogi") {
					$(this).css("color", overColor);
					$('.cssPage2Bogi1 #bogiImg-' + num).css("background", "url(" + path + "quiz_num" + num + "_u.png)");
				}
			}
		}
	});

	_target.on("mouseout", function (evt) {
		var num = _target.num;
		var type = $(this).attr("id").split("-");
		if (isClick) {
			if (_target.isClick) {
				//$(this).css("cursor","default");
				if (type[0] == "bogi") {
					$(this).css("color", outColor);
					$('.cssPage2Bogi1 #bogiImg-' + num).css("background", "url(" + path + "quiz_num" + num + "_d.png)");
				}
			}
		}
	});

	_target.on("click", function (evt) {
		if (isClick) {
			clickInit(_target.num);
			playSound("button click");
		}
	});

}// end function _addBtnEvent2(_target){

function _addBtnEvent3(_target) {
	_target.on("mouseover", function (evt) {
		var num = _target.num;
		var type = $(this).attr("id").split("-");
		log("type : " + type);
		if (isClick) {
			if (_target.isClick) {
				//$(this).css("cursor","pointer");

			}
		}
	});

	_target.on("mouseout", function (evt) {
		var num = _target.num;
		var type = $(this).attr("id").split("-");
		if (isClick) {
			if (_target.isClick) {
				//$(this).css("cursor","default");

			}
		}
	});

	_target.on("click", function (evt) {
		if (isClick) {
			clickInit2(_target.num);
			playSound("button click");
			qnsArr[pageNum] = _target.num;
			//answer();
		}
	});

}// end function _addBtnEvent2(_target){

function clickInit(_num) {
	for (var i = 1; i <= qnsArr.length; i++) {
		if (i != _num) {
			$(".cssPage2Bogi1 #bogi-" + i).css("color", outColor);
			$(".cssPage2Bogi1 #bogi-" + i).css("cursor", "pointer");
			$(".cssPage2Bogi1 #bogiImg-" + i).css("background", "url(" + path + "quiz_num" + i + "_d.png)");
			$("#bogiCheck" + i).hide();
			objArr[i].isClick = true;
		}
	}
	if (_num) {
		objArr[_num].isClick = false;
		qnsArr[pageNum] = _num;
		$(".cssPage2Bogi1 #bogi-" + _num).css("cursor", "default");
		$("#bogiCheck" + _num).show();
	}
}

function clickInit2(_num) {
	for (var i = 1; i <= qnsArr.length; i++) {
		if (i != _num) {
			objArr2[i].isClick = true;
		}
	}
	if (_num) {
		objArr2[_num].isClick = false;
		qnsArr[pageNum] = _num;
	}
}

function answer() {
	log("answer");
	tryNum++;
	if (tryNum < tryMax) {
		/** 정답 **/
		if (ansArr[pageNum] == qnsArr[pageNum]) {
			$(".cssPage2Ox").css({ "background": "url(" + path + "quiz_result_o.png)", "display": "block" });
			playSound("quiz_right");

			feedPage();
		}
		/** 오답 **/
		else if (ansArr[pageNum] != qnsArr[pageNum]) {
			$(".cssPage2Retry").fadeIn().delay(1000).fadeOut();
			playSound("quiz_warning");
			qnsArr[pageNum] = 0;
			clickInit();
		}
	} else if (tryNum == tryMax) {
		/** 정답 **/
		if (ansArr[pageNum] == qnsArr[pageNum]) {
			$(".cssPage2Ox").css({ "background": "url(" + path + "quiz_result_o.png)", "display": "block" });
			playSound("quiz_right");
		}
		/** 오답 **/
		else if (ansArr[pageNum] != qnsArr[pageNum]) {
			$(".cssPage2Ox").css({ "background": "url(" + path + "quiz_result_x.png)", "display": "block" });
			playSound("quiz_wrong");
		}

		feedPage();
	}
}

function feedPage() {
	isClick = false;
	$('.cssPage2Btn .btn1').hide();
	//$("#bogiResult" + ansArr[pageNum]).show();
	$("#bogiCheck" + ansArr[pageNum]).show();

	if (pageNum < questionNum) {
		$('.cssPage2Btn .btn2').show();
		pageNum++;
	} else {
		$('.cssPage2Btn .btn3').show();
	}
	$(".cssPage2Feed").fadeIn();
	//$(".cssPage2Feed2").fadeIn();
	//$('.cssPage2Feed2_bg').show();

	for (var i = 1; i <= qnsArr.length; i++) {
		$("#bogi-" + i).css("cursor", "default");
		objArr[i].isClick = true;
	}
}



function setResult() {
	var score = 0;
	motionEvtFlag = true;
	$("#page2").hide();
	$("#page3").show();
	$('.cssPage3TxtView .css3Txt2').empty();
	
	if(questionNum ==2){
		$('#page3').css("background", "url(../common/img/quiz/quiz_result_bg_02.png)");
		$('.cssPage3OX').css("left","381px");
		$(".cssPage3OX .ox3").hide();
	}

	for (var i = 1; i < ansArr.length; i++) {
		if (ansArr[i] == qnsArr[i]) {
			$('.cssPage3OX .ox' + i).css("background", "url(" + path + "quiz_final_o.png)");
			score++;
		} else if (ansArr[i] != qnsArr[i]) {
			$('.cssPage3OX .ox' + i).css("background", "url(" + path + "quiz_final_x.png)");
		}
	}
	var str = "총 "+questionNum+"문제 중 <span class='css3feedTxt'>" + score + "문제</span> 를 맞히셨습니다!";
	$('.cssPage3TxtView .css3Txt2').append(str);
}

/** 다시 풀기 **/
function setReGame() {
	$("#page2").show();
	$("#page3").hide();

	reInitPage();

	pageNum = 1;
	qnsArr = new Array("", 0, 0, 0);
	setQuestion();
}