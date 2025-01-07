const moviesContainer = document.getElementById('movies');
const seats = document.querySelectorAll('.seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
let ticketPrice = 0;
let memberId = null;
var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");
myHeaders.append("Content-Type", "application/json");

// 從後端 API 獲取電影資料
async function fetchMovies() {
    var myHeaders = new Headers();

    myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");

    var requestOptions = {

        method: 'GET',

        headers: myHeaders,

        redirect: 'follow'

    };
    try {
        const response = await fetch('/movie', requestOptions); // 後端 API URL
        const movies = await response.json();
        renderMovies(movies["data"]); // 動態渲染電影卡片
        populateDropdown_movie(movies["data"]); // 動態生成下拉選單
    } catch (error) {
        console.error('Error fetching movies:', error);
    }
}

// 插入movie資料到下拉選單
function populateDropdown_movie(movies) {
    const moviesDropdown = document.getElementById('movie'); // 選擇下拉選單
    moviesDropdown.innerHTML = '';
    movies.forEach(movie => {
        const option = document.createElement('option'); // 創建新選項
        option.value = movie.id; // 設置選項的值
        option.textContent = movie.name; // 設置選項的顯示文本
        moviesDropdown.appendChild(option); // 將選項插入到下拉選單中
    });
}


// 從後端 API 獲取影城資料
async function fetchtheater() {
    var myHeaders = new Headers();

    myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");

    var requestOptions = {

        method: 'GET',

        headers: myHeaders,

        redirect: 'follow'

    };
    try {
        const response = await fetch('/theater', requestOptions); // 後端 API URL
        const theater = await response.json();
        populateDropdown_theater(theater["data"]);
    } catch (error) {
        console.error('Error fetching theater:', error);
    }
}

// 插入theater資料到下拉選單
function populateDropdown_theater(theaters) {
    const theaterDropdown = document.getElementById('theater'); // 選擇下拉選單
    theaterDropdown.innerHTML = '';
    theaters.forEach(theater => {
        const option = document.createElement('option'); // 創建新選項
        option.value = theater.id; // 設置選項的值
        option.textContent = theater.name; // 設置選項的顯示文本
        theaterDropdown.appendChild(option); // 將選項插入到下拉選單中
    });
}

let movieId = null;
let theater = null;
// 根據選擇的電影和影城拉取場次資料
function handleSelectionChange() {
    const movieDropdown = document.getElementById('movie');
    const theaterDropdown = document.getElementById('theater');
    const sectionDropdown = document.getElementById('section');
    const dateInput = document.getElementById('date');

    const selectedMovieID = movieDropdown.value;
    const selectedTheaterID = theaterDropdown.value;
    const selectedDate = dateInput.value;
    const selectedSectionID = sectionDropdown.value;

    if (movieId === selectedMovieID && theater === selectedTheaterID) {

    }
    else{
        movieId = selectedMovieID;
        theater = selectedTheaterID;
        fetchSection(selectedMovieID, selectedTheaterID);
    }

    if (selectedMovieID && selectedTheaterID && selectedDate && selectedSectionID) {
        console.log(`選擇的電影 ID：${selectedMovieID}, 影城 ID：${selectedTheaterID}, 日期：${selectedDate}, 場次 ID：${selectedSectionID}`);

        // 發送請求獲取已被訂走的座位
        fetchOccupiedSeats(selectedSectionID, selectedDate);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('movie').addEventListener('change', handleSelectionChange);
    document.getElementById('theater').addEventListener('change', handleSelectionChange);
    document.getElementById('date').addEventListener('change', handleSelectionChange);
    document.getElementById('section').addEventListener('change', handleSelectionChange);
});

async function fetchOccupiedSeats(sessionId, date) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");

    const requestBody = {
        date: date
    };

    const requestOptions = {
        method: 'POST', headers: myHeaders, body: JSON.stringify(requestBody), redirect: 'follow'
    };

    try {
        const response = await fetch(`/seat/session/${sessionId}`, requestOptions);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        const occupiedSeats = result["data"];
        console.log(occupiedSeats);
        // 使用返回的已佔用座位更新座位圖
        generateSeatMap(10, 8, occupiedSeats);
    } catch (error) {
        console.error('Error fetching occupied seats:', error);
        alert('獲取已佔用座位時發生錯誤，請稍後再試！');
    }
}

async function fetchSection(movieId, theaterId) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");
    const GetTargetData = {
        theaterId: theaterId, movieId: movieId
    };
    var requestOptions = {
        method: 'POST', headers: myHeaders, body: JSON.stringify(GetTargetData), redirect: 'follow'
    };

    fetch(`/session/theater/movie`, requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('資料不存在');
            }
            return response.json();
        })
        .then((data) => {
            populateDropdown_section(data["data"]);
        });
}

function populateDropdown_section(sections) {
    const sectionDropdown = document.getElementById('section'); // 選擇下拉選單
    sectionDropdown.innerHTML = '';
    sections.forEach(section => {
        const option = document.createElement('option'); // 創建新選項
        option.value = section.id; // 設置選項的值
        // 格式化時間為 HH:mm
        const [hour, minute] = section.startTime.split(':'); // 分割時間為小時和分鐘
        option.textContent = `${hour}:${minute}`; // 設置選項的顯示文本
        sectionDropdown.appendChild(option); // 將選項插入到下拉選單中
    });
}

// 動態生成電影卡片
function renderMovies(data) {
    moviesContainer.innerHTML = ""; // 清空容器
    data.forEach((movie) => {
        const card = document.createElement('div');
        card.classList.add('movie-card');
        card.innerHTML = `
            <img src="${movie.thumbnailPath}" alt="${movie.name}">
            <h3>${movie.name}</h3>
        `;
        card.addEventListener('click', () => {
            document.querySelectorAll('.movie-card').forEach(card => card.classList.remove('selected'));
            card.classList.add('selected');
            ticketPrice = movie.price;
            updateSummary();
        });
        moviesContainer.appendChild(card);
    });
}

// 更新座位選擇和總計
function updateSummary() {
    const selectedSeats = document.querySelectorAll('.seat.selected');
    const selectedSeatsCount = selectedSeats.length;

    count.textContent = selectedSeatsCount;
    total.textContent = selectedSeatsCount * ticketPrice;
}

// 監聽座位選擇
seats.forEach(seat => {
    seat.addEventListener('click', () => {
        if (!seat.classList.contains('occupied')) {
            seat.classList.toggle('selected');
            updateSummary();
        }
    });
});

// 實現頁面切換功能
function navigateTo(pageId) {
    const pages = document.querySelectorAll('.page');
    pages.forEach(page => page.classList.remove('active'));
    document.getElementById(pageId).classList.add('active');
}


// 在頁面加載時直接生成座位圖
document.addEventListener('DOMContentLoaded', () => {
    generateSeatMap(10, 8); // 生成10行12列的座位圖
});

// 動態生成座位圖的函數
function generateSeatMap(rows, columns, occupiedSeat = []) {
    const occupiedSeats = occupiedSeat.map(seat => seat.number);
    const seatMap = document.querySelector('.seat-map');
    seatMap.innerHTML = ''; // 清空現有座位

    for (let row = 0; row < rows; row++) {
        // 左側座位(2列)
        for (let col = 0; col < 2; col++) {
            const seatId = `${String.fromCharCode(65 + row)}${col + 1}`;
            const seat = createSeat(seatId, occupiedSeats);
            seatMap.appendChild(seat);
        }

        // 中間區域(6行)
        for (let col = 2; col < 8; col++) {
            const seatId = `${String.fromCharCode(65 + row)}${col + 1}`;
            const seat = createSeat(seatId, occupiedSeats);
            seatMap.appendChild(seat);
        }


        // 右側座位(2列)
        for (let col = 8; col < columns; col++) {
            const seatId = `${String.fromCharCode(65 + row)}${col + 1}`;
            const seat = createSeat(seatId, occupiedSeats);
            seatMap.appendChild(seat);
        }
    }
}

// 工具函數：生成單個座位
function createSeat(seatId, occupiedSeats) {
    const seat = document.createElement('div');
    seat.classList.add('seat');
    seat.textContent = seatId;

    // 標記已佔用的座位
    if (occupiedSeats.includes(seatId)) {
        seat.classList.add('occupied');
    }

    // 點擊切換選中狀態
    seat.addEventListener('click', () => {
        if (!seat.classList.contains('occupied')) {
            seat.classList.toggle('selected');
        }
    });

    return seat;
}

// 確認座位選擇
function confirmSeats() {
    const selectedSeats = document.querySelectorAll('.seat.selected');
    const seatNumbers = Array.from(selectedSeats).map(seat => seat.textContent);
    alert(`您已選擇的座位：${seatNumbers.join(', ')}`);
}

// 回傳資料庫，電影、影城、場次、座位資料
async function submitBooking() {
    const movie = document.getElementById('movie').value;
    const theater = document.getElementById('theater').value;
    const section = document.getElementById('section').value;
    const date = document.getElementById('date').value;
    const seats = Array.from(document.querySelectorAll('.seat.selected')).map(seat => seat.textContent);

    if (!movie || !theater || !section || !date || seats.length === 0) {
        alert('請選擇完整的電影、影城、場次和座位！');
        return;
    }

    // 準備要發送的資料
    for (let i = 0; i < seats.length; i++) {
        let bookingData = null;
        if (memberId === null) {
            bookingData = {
                sessionId: section, seatNumber: seats[i], date: date
            };
        } else {
            bookingData = {
                sessionId: section, seatNumber: seats[i], memberId: memberId, date: date
            };
        }

        try {
            const myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");

            const response = await fetch('/ticket', {
                method: 'POST', headers: myHeaders, body: JSON.stringify(bookingData),
            });
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const result = await response.json();
            alert(`訂票成功！ 訂單編號：${result["data"]}`);
        } catch (error) {
            console.error('Error submitting booking:', error);
            alert('提交訂票資料時發生錯誤，請稍後再試！');
        }
    }
}

// 註冊功能
document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault(); // 防止表單提交刷新頁面

    const name = document.getElementById("registerUsername").value.trim();
    const email = document.getElementById("registerEmail").value.trim();
    const password = document.getElementById("registerPassword").value.trim();
    const message = document.getElementById("registerMessage");

    var requestOptions = {
        method: "POST", headers: myHeaders, body: JSON.stringify({name, email, password}), redirect: "follow",
    };

    fetch(`/member/register`, requestOptions)
        .then((response) => {
            if (response.ok || response.created) {
                return response.json();
            } else {
                throw new Error("註冊失敗，請檢查資料！");
            }
        })
        .then((data) => {
            message.textContent = "註冊成功！";
            message.style.color = "green";
            document.getElementById("registerForm").reset();
        })
        .catch((error) => {
            message.textContent = error.message;
            message.style.color = "red";
        });
});

// 登入功能
document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault(); // 防止表單提交刷新頁面

    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value.trim();
    const message = document.getElementById("loginMessage");

    var requestOptions = {
        method: "POST", headers: myHeaders, body: JSON.stringify({email, password}), redirect: "follow",
    };

    fetch(`/member/login`, requestOptions)
        .then((response) => {
            if (response.ok || response.created) {
                return response.json();
            } else {
                throw new Error("登入失敗，請檢查帳號或密碼！");
            }
        })
        .then((data) => {
            message.textContent = `歡迎回來，${data["data"].name}！`;
            message.style.color = "green";
            memberId = data["data"].id;
            fetchTicketHistory(memberId).then();
            // 可在此處儲存 token 或執行其他登入成功後的邏輯
        })
        .catch((error) => {
            message.textContent = error.message;
            message.style.color = "red";
        });
});

async function fetchTicketHistory(memberId) {
    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    try {
        const response = await fetch(`/ticket/member/${memberId}`, requestOptions);
        const tickets = await response.json();
        console.log(tickets);
        renderTicketHistory(tickets["data"]);
    } catch (error) {
        console.error('Error fetching ticket history:', error);
    }
}

function renderTicketHistory(tickets) {
    const ticketList = document.getElementById('ticketList');
    ticketList.innerHTML = ''; // 清空列表

    tickets.sort((a, b) => new Date(b.purchaseTime) - new Date(a.purchaseTime)); // 按日期排序

    tickets.forEach(ticket => {
        const listItem = document.createElement('li');
        listItem.className = 'ticket-item';
        listItem.innerHTML = `
            <span>${ticket.id}</span>
            <span>${ticket.theaterName}</span>
            <span>${ticket.screenNumber}</span>
            <span>${ticket.date}</span>
            <span>${ticket.movieName}</span>
            <span>${ticket.startTime}</span>
            <span>${ticket.seatNumber}</span>
            <span>${ticket.price}</span>`;
        ticketList.appendChild(listItem);
    });
}

// 初始化：拉取電影資料
fetchMovies();
fetchtheater();
