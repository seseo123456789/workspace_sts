
    // 부트스트랩이 제공하는 모달 태그를 선택하는 방법(기능을 포함한것)
    const buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');

    // 모달 열기
    //buy_detail_modal.show();
    // 모달 닫기
    //buy_detail_modal.hide();

// 행 클릭시 구매 상세 내역 조회 및 모달창 띄우기

function showModal(buyCode){


    // ------------------- 첫번째 방식 ---------------//
fetch('/admin/selectBuyDetail', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: new URLSearchParams({
       // 데이터명 : 데이터값
       'buyCode':buyCode


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

    // ------ 모달의 첫번째 테이블 -------
    document.querySelector('#modal-buyCode').textContent= data.buyCode;
    document.querySelector('#modal-memberId').textContent =data.memberId;
    document.querySelector('#modal-buyPrice').textContent =data.buyPrice;
    document.querySelector('#modal-buyDate').textContent =data.buyDate;
    
    
     // ------ 모달의 두번째 테이블 -------
    const modal_tbody  = document.querySelector('#modal-tbody');
    // html 에 그려준 tbody 지우기(빈값초기화)
    modal_tbody.innerHTML='';
    let str='';

    //data.buyDetailList의 데이터 갯수 만큼 {}에서 반복한다 data.buyDetailList.length=3 이면 3번 반복
    //data.buyDetailList.forEach(function(반복할때 꺼내는 객체명, idx){ });
    data.buyDetailList.forEach(function(buyDetail, idx){ 
        str +=`
            <tr>
                <td>${data.buyDetailList.length-idx}</td>
                <td class="text-start">
                    <img width="70px" src="/upload/${buyDetail.itemVo.imgList[0].attachedFileName}">
                    ${buyDetail.itemVo.itemName}
                </td>
                <td>${buyDetail.buyCnt}</td>
                <td>${buyDetail.totalPrice}</td>
            </tr>
        `;

    });
    // 새로그려준 그림을 html 에 뿌려주기
    modal_tbody.insertAdjacentHTML('afterbegin',str);
    // 모달 열기
    buy_detail_modal.show();

})
//fetch 통신 실패 시 실행 영역
.catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
});
 



}