let ManagerData=[];
let TheaterData=[];

var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");
myHeaders.append("Content-Type", "application/json");

function verify(Tusername,Tpassword){
    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify({username:Tusername,password:Tpassword}),
        redirect: 'follow'
    };
    fetch("/manager/login",requestOptions)
        .then((response) => {
            if(!response.ok){
                alert("帳號或密碼錯誤!");
                throw new Error('獲取失敗');
            }
            return response.json();
        })
        .then((data) => {
            ManagerData = data;
            console.log(ManagerData);
            if(ManagerData["data"].isAdmin){
                const fakeID = 9999;
                const fakeNAME = "萎鏽影城";
                window.location.href = `manage.html?theaterid=${fakeID}&theatername=${fakeNAME}`
            }
            else{
                searchForTheaterId(ManagerData["data"]);
            }
        });
}

function ManagerLogin(){
    const username = document.getElementById("UserName").value;
    const password = document.getElementById("Password").value;
    verify(username,password);
}

function searchForTheaterId(mgData){
    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch(`/theater/manager/${mgData.id}`,requestOptions)
        .then((response) => {
            if(!response.ok){
                throw new Error('獲取失敗');
            }
            return response.json();
        })
        .then((data) => {

            TheaterData = data["data"];
            console.log(data,TheaterData);
            const TheID = TheaterData[0].id;
            const TheNAME = TheaterData[0].name;
            window.location.href = `manage.html?theaterid=${TheID}&theatername=${TheNAME}`;
        });

}