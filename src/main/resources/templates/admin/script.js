
/* 會員資料 */
const MemberManage = document.getElementById('member-list');
let MemberData = [];

/* 會員管理 */

var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");

var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
};
function GetData(){
    fetch("http://woodendoor.duckdns.org:8080/member",requestOptions)
        .then((response) => {
            if(!response.ok){
                throw new Error('獲取失敗');
            }
            return response.json();
        })
        .then((data) => {
            MemberData = data;
            UpdataData(MemberData);
        });
}

function UpdataData(memData){
    MemberManage.innerHTML = "";
    if(!Array.isArray(memData)){
        console.log("123",memData);
    }
    memData["data"].forEach(member =>{
        const Data = document.createElement('tr');
        Data.innerHTML = `
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td><span class="toggle-btn" onclick="toggleDetails(this)">View Details</span></td>
        `;
        MemberManage.appendChild(Data);
        const DataDetail = document.createElement('tr');
        DataDetail.classList.add('details')
        DataDetail.innerHTML = `
            <td colspan="3">
                <p><strong>Address:</strong>${member.gender}</p>
                <p><strong>Phone:</strong>${member.email}</p>
                <p><strong>Membership Date:</strong>${member.password}</p>
            </td>
        `;
        MemberManage.appendChild(DataDetail);
    });
    console.log("bbb");
}

function toggleDetails(button) {
    var detailsRow = button.closest('tr').nextElementSibling;
    if (detailsRow.style.display === 'table-row') {
        detailsRow.style.display = 'none';
        button.textContent = 'View Details';
    } else {
        detailsRow.style.display = 'table-row';
        button.textContent = 'Hide Details';
    }
}

/* 電影管理 */