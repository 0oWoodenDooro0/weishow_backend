const moviesContainer = document.getElementById('movies');
const seats = document.querySelectorAll('.seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
let ticketPrice = 0;

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
        const response = await fetch('http://woodendoor.duckdns.org:8080/movie', requestOptions); // 後端 API URL
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
        const response = await fetch('http://woodendoor.duckdns.org:8080/theater', requestOptions); // 後端 API URL
        const theater = await response.json();
        populateDropdown_theater(theater["data"]);
    } catch (error) {
        console.error('Error fetching theater:', error);
    }
}


// 插入theater資料到下拉選單
function populateDropdown_theater(locations) {
    const theaterDropdown = document.getElementById('location'); // 選擇下拉選單
    locations.forEach(location => {
        const option = document.createElement('option'); // 創建新選項
        option.value = location.id; // 設置選項的值
        option.textContent = location.name; // 設置選項的顯示文本
        locationDropdown.appendChild(option); // 將選項插入到下拉選單中
    });
}

document.addEventListener('DOMContentLoaded', () => {
    fetchMovies(); // 獲取並填充電影資料
    fetchLocations(); // 獲取並填充影城資料

    // 添加事件監聽器
    document.getElementById('movie').addEventListener('change', handleSelectionChange);
    document.getElementById('location').addEventListener('change', handleSelectionChange);
});

async function fetchSection(movieID, theaterID) {
    var myHeaders = new Headers();

    myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    try {
        const url = `http://woodendoor.duckdns.org:8080/?movieID=${movieID}&theaterID=${theaterID}`;
        const response = await fetch(url, requestOptions); // 後端 API URL
        if (!response.ok) {
            throw new Error('HTTP error! status : ${response.status}');
        }
        const section = await response.json();
        populateDropdown_section(section["data"]);
    } catch (error) {
        console.error('Error fetching theater:', error);
    }
}

function populateDropdown_section(section) {
    const sectionDropdown = document.getElementById('time'); // 選擇下拉選單
    sectionDropdown.innerHTML = '<option value="">請選擇時間</option>';
    section.forEach(section => {
        const option = document.createElement('option'); // 創建新選項
        option.value = section.id; // 設置選項的值
        option.textContent = section.time; // 設置選項的顯示文本
        sectionDropdown.appendChild(option); // 將選項插入到下拉選單中
    });
}

function handleSelectionChange() {
    // 獲取當前選中的 movieID 和 theaterID
    const movieDropdown = document.getElementById('movie');
    const theaterDropdown = document.getElementById('location');

    const selectedMovieID = movieDropdown.value; // 獲取選中的電影 ID
    const selectedTheaterID = theaterDropdown.value; // 獲取選中的影城 ID

    // 確保兩者都有值後，調用 fetchSection
    if (selectedMovieID && selectedTheaterID) {
        fetchSection(selectedMovieID, selectedTheaterID);
    }
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

// 搜尋功能
function filterMovies() {
    const query = document.getElementById('searchInput').value.trim().toLowerCase();
    const filteredMovies = movies.filter((movie) =>
        movie.title.toLowerCase().includes(query)
    );
    renderMovies(filteredMovies);
}

// 實現頁面切換功能
function navigateTo(pageId) {
    const pages = document.querySelectorAll('.page');
    pages.forEach(page => page.classList.remove('active'));
    document.getElementById(pageId).classList.add('active');
}


// 購票功能
function goToBooking() {
    const location = document.getElementById("location").value;
    const movie = document.getElementById("movie").value;
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;

    if (!location || !movie || !date || !time) {
        alert("請完整填寫所有欄位！");
        return;
    }

    alert(`前往訂票：
影城: ${location}
影片: ${movie}
日期: ${date}
場次: ${time}`);
}


// 在頁面加載時直接生成座位圖
document.addEventListener('DOMContentLoaded', () => {
    generateSeatMap(10, 8, ['H8', 'H9', 'I8', 'I9']); // 生成10行12列的座位圖
});

// 動態生成座位圖的函數
function generateSeatMap(rows, columns, occupiedSeats = []) {
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

// 註冊功能
document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault(); // 防止表單提交刷新頁面

    const name = document.getElementById("registerUsername").value.trim();
    const email = document.getElementById("registerEmail").value.trim();
    const password = document.getElementById("registerPassword").value.trim();
    const message = document.getElementById("registerMessage");

    var requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({name, email, password}),
        redirect: "follow",
    };

    fetch(`${API_URL}/member/register`, requestOptions)
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
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({email, password}),
        redirect: "follow",
    };

    fetch(`${API_URL}/member/login`, requestOptions)
        .then((response) => {
            if (response.ok || response.created) {
                return response.json();
            } else {
                throw new Error("登入失敗，請檢查帳號或密碼！");
            }
        })
        .then((data) => {
            message.textContent = `歡迎回來，${data.name}！`;
            message.style.color = "green";
            // 可在此處儲存 token 或執行其他登入成功後的邏輯
        })
        .catch((error) => {
            message.textContent = error.message;
            message.style.color = "red";
        });
});

// 初始化：拉取電影資料
fetchMovies();
fetchtheater();
fetchSection();