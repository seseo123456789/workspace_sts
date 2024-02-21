function goSelect(){            
    const classCode = document.querySelector('#class_select').value;
     location.href =`/?classCode=${classCode}`;
 }





 function fetchSelect(){

    const classCode = document.querySelector('#class_select').value; 

    // ------------------- 첫번째 방식 ---------------//
fetch('/fetchSelect', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: new URLSearchParams({
       // 데이터명 : 데이터값
       classCode: classCode
    })
})
.then((response) => {
    if(!response.ok){
        alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
        return ;
    }

   // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
    return response.json(); //나머지 경우에 사용
})
//fetch 통신 후 실행 영역
.then((data) => {//data -> controller에서 리턴되는 데이터!
    console.log(data);
    // 기존테이블 내용 삭제
    const tbodyTag=document.querySelector('#stu-list-div > tbody');
    tbodyTag.innerHTML='';

    // 새로 조회한 데이터로 tbody 안에 채워주기
        let str='';
        // for(const stu of data){
        //         str = str+ `<tr>
        //                         <td></td>
        //                         <td>${stu.className}</td>
        //                         <td>${stu.stuNum}</td>
        //                         <td>${stu.stuName}</td>
        //                     </tr>`; 
                
        // } 
         //     for(let i =0; i<data.lenth; ++i){ 
         //                     str = str+ `<tr>
         //                                 <td>${data[i].className}</td> </tr>}
         
         data.forEach(function(stu, i){
                 str = str+ `<tr>
                                <td>${data.length-i}</td>
                                <td>${stu.className}</td>
                                <td>${stu.stuNum}</td>
                                <td>
                                    <span id="name-span" onclick="goDetail(${stu.stuNum})">
                                        ${stu.stuName}
                                    </span>
                                </td>
                            </tr>`; 
         });


        tbodyTag.insertAdjacentHTML('afterbegin',str)

           
})
//fetch 통신 실패 시 실행 영역
.catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
});

 }





 function goDetail(stuNum){
   
// ------------------- 첫번째 방식 ---------------//
fetch('/stuDetail', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: new URLSearchParams({
       // 데이터명 : 데이터값
        'stuNum': stuNum
       
    })
})
.then((response) => {
    if(!response.ok){
        alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
        return ;
    }

    //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
    return response.json(); //나머지 경우에 사용
})
//fetch 통신 후 실행 영역
.then((data) => {//data -> controller에서 리턴되는 데이터!
    console.log(data);
    
    // 조회된 데이터로 그림 그리기
   
    const detail_div = document.querySelector('.stu-detail-div');
    detail_div.innerHTML='';

    let str=`
    <div>
                                <div>학생 기본 정보</div>
                                <div>
                                    <table>
                                        <thead>
                                            <tr>
                                                <td>학번</td>
                                                <td>소속반</td>
                                                <td>학생명</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="stuNumTd">${data.stuVo.stuNum}</td>
                                                <td>${data.stuVo.className}</td>
                                                <td>${data.stuVo.stuName}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div>
                                <div>학생점수 정보</div>
                                <div>
                                    <table>
                                        <thead>
                                            <tr>
                                                <td>국어점수</td>
                                                <td>영어점수</td>
                                                <td>수학점수</td>
                                                <td>평균</td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="scoreTd">${data.scoreVo==null? 0 : data.scoreVo.korScore}</td>
                                                <td class="scoreTd">${data.scoreVo==null? 0 : data.scoreVo.engScore}</td>
                                                <td class="scoreTd">${data.scoreVo==null? 0 : data.scoreVo.mathScore}</td>
                                                <td >${data.scoreVo==null? 0.0 : (data.scoreVo.korScore + data.scoreVo.engScore + data.scoreVo.mathScore)/3}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                           <div>                               
                                    <input type="button" value="점수입력" id="btn" onclick="setInput()">
                              
                           </div>

    </div>
    `;

    detail_div.insertAdjacentHTML('afterbegin',str);  
    
})
//fetch 통신 실패 시 실행 영역
.catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
});


 }
    function setInput() {

        document.querySelector('#btn');
        if(btn.value=='점수입력'){
            

            const tds= document.querySelectorAll('.scoreTd');
                
            //  for(let i=0; i<tds.length; i++){
            //     tds[i].innerHTML = '<input type="text">'; }  

            // for(const e of tds){
            //     e.innerHTML='<input type="text">'; } 

            tds.forEach(function(e,i){e.innerHTML='<input type="text" class="scoreInput">'});
            document.querySelector('#btn').value='저장';

        }
        else if(btn.value=='저장'){
            const result=confirm('입력한 정보로 점수를 등록할까요?'); // 확인 :ture,  취소:false
           
            // 확인: true 일때
            if(result){
                insertScore();
            }

        }
    }


function insertScore(){
    const inputs = document.querySelectorAll('.scoreInput'); //input 태그들 이니깐 태그 하나씩 꺼내야되어서 for문써야됨 
    const stuNum = document.querySelector('.stuNumTd').textContent;
    fetch('/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
        
           'korScore': inputs[0].value
           , 'engScore': inputs[1].value
           , 'mathScore': inputs[2].value
           , 'stuNum': stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('점수가 등록되었습니다!');
        goDetail(stuNum);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}