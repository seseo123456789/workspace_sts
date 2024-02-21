
function fetch1(){

    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/fetch/fetch1', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
        // 데이터명 : 데이터값
        'id':'java',
        'name' : '홍',
        'age' : 20
        })
    })
    .then((response) => {
        return response.text();
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}



function fetch2(){

    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/fetch/fetch2', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           'id':'java',
           'name' : '홍',
           'age':20
           
        })
    })
    .then((response) => {
        return response.text();
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}


function fetch3(){

    // javascript 의 객체 배열
        const arr = [];

    for(let i = 1; i<=5; i++){
        
        // javascript 의 객체
        const member = {
            'name': `홍_${i}`,
            'age': 20 +i,
            'id': `java_${i}`
        };

        arr.push(member);

    }

    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/fetch/fetch3', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(arr)



    })
    .then((response) => {
        return response.text();
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}



function fetch4(){

    // javascript 의 복잡한 데이터 생성
    // 객체배열 aaa=[{객체1},{객체2},{객체3}]
    const imgList = [{

            'imgName' : 'aaa.jpg',
            'imgCode' : 3

        },
        {
            'imgName' : 'bbb.jpg',
            'imgCode' : 6
        }];

    //객체    
    const cartInfo={
            
            'cartCode' : 1,
            'memberInfo' : {
                'memberId' : 'java',
                'memberName':'홍'
            },
            'itemInfo':{
                'itemCode':5,
                'itemName':'상품1',
                'itemPrice':1000,
                'imgList': imgList
            }
        };

    console.log(cartInfo.itemInfo.imgList[0].imgName);    
    

    
    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/fetch/fetch4', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(cartInfo
                        
        )



    })
    .then((response) => {
        return response.text();
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}