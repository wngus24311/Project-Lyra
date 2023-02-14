//체크 박스 개수 제한 
function count_check(obj){
    var checkBox = document.getElementsByName("check"); // name 값 check를 불러옴 
    var checkCount = 0; // checkCount 변수 초기값 설정
    for(var i = 0; i < checkBox.length; i++){
        if(checkBox[i].checked){ // checlBox가 checked 됐을 경우 
            checkCount++; // 1씩 증가
        }
    }
    if(checkCount > 2){ // 조건문으로 checkCount가 2개보다 클 경우
        alert("2개까지 체크할 수 있습니다."); // alert 띄움
        obj.checked = false; // false 주고 이후에 checked되지 않도록 설정
        return false;
    }
}