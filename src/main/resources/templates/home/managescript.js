/* 會員資料 */
const MemberManage = document.getElementById('member-list');
const MovieManage = document.getElementById('movie-list');
let MemberData = [];
let MovieData = [];
let SessionData = [];
let ScreenData = [];
let FoundSession;
let isaddFormOpen = false;
let isupdateFormOpen = false;
let ismemberupdateFormOpen = false;
let issessionupdateFormOpen = false;
let issessionaddFormOpen = false;
let theaterId;
let theaterName;
let isGeneralManager = false;
let Selector;

/* 登入的管理者資料 */
function getQueryParams() {
    var params = new URLSearchParams(window.location.search);
    theaterId = params.get("theaterid");
    theaterName = params.get("theatername");
    if (theaterId == '9999') {
        document.getElementById("MbBT").disabled = false;
        document.getElementById("MvBT").disabled = false;
        document.getElementById("SsBT").disabled = true;
        isGeneralManager = true;
    } else {
        document.getElementById("MbBT").disabled = true;
        document.getElementById("MvBT").disabled = true;
        document.getElementById("SsBT").disabled = false;
        isGeneralManager = false;
    }
}

getQueryParams();
/* 會員管理 */

var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer a974f9b8a917f49dd75168ff85072644");
myHeaders.append("Content-Type", "application/json");

function GetMemberData() {
    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch("/member", requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('獲取失敗');
            }
            return response.json();
        })
        .then((data) => {
            MemberData = data;
            UpdateMemberLayout(MemberData["data"]);
        });
}

function UpdateMemberLayout(memData) {
    MemberManage.innerHTML = "";
    memData.forEach(member => {
        const Data = document.createElement('tr');
        Data.innerHTML = `
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td>${member.password}</td>
            <td>
                <button class="toggle-btn" onclick="updatememberform(${member.id})">修改</button>
                <button class="toggle-btn" onclick="DeleteMemberData(${member.id})">刪除</button>
            </td>
        `;
        MemberManage.appendChild(Data);
    });
}

function updatememberform(MbId) {
    const form = document.getElementById("memberContainer");
    const memberID = document.getElementById("memberID");
    memberID.textContent = MbId;
    if (!ismemberupdateFormOpen) {
        form.classList.add('active');
        console.log(form.classList);
        ismemberupdateFormOpen = true;
    } else {
        form.classList.remove('active');
        ismemberupdateFormOpen = false;
    }
}

function UpdateMemberData() {
    const MemberID = Number(document.getElementById("memberID").textContent);
    const form = document.getElementById('memberContainer');
    let newName = document.getElementById("changeMemberName").value;
    let newEmail = document.getElementById("changeEmail").value;
    let newPassword = document.getElementById("changePassword").value;

    for (const member of MemberData["data"]) {
        if (member.id == MemberID) {
            console.log(member);
            if (newName == "") {
                newName = member.name;
            }
            if (newEmail == "") {
                newEmail = member.email;
            }
            if (newPassword == "") {
                newPassword = member.password;
            }
            break;
        }
    }
    ;

    const TargetMember = {
        name: newName,
        email: newEmail,
        password: newPassword
    };
    console.log(TargetMember);
    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: JSON.stringify(TargetMember),
        redirect: 'follow'
    };
    fetch(`/member/${MemberID}`, requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Member changed successfully!");

                document.getElementById("changeMemberForm").reset();
                GetMemberData();

            } else {
                alert("Failed to change member");
            }
        })
        .catch(error => console.error('Error adding movie:', error));

    form.classList.remove('active');
}

function DeleteMemberData(MbId) {
    var requestOptions = {
        method: 'DELETE',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch(`/member/${MbId}`, requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Movie deleted successfully!");
                document.getElementById("addMovieForm").reset();
                GetMemberData();
            } else {
                alert("Failed to delete movie");
            }
        })
        .catch(error => console.error('Error adding movie:', error));
}

/* 電影管理 */
function GetmovieData() {
    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch("/movie", requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('獲取失敗');
            }
            return response.json();
        })
        .then((data) => {
            MovieData = data;
            if (isGeneralManager) {
                UpdateMovieLayout(MovieData["data"]);
            } else {
                GetSessionData(MovieData["data"]);
            }
        });
}

function AddmovieData() {
    const form = document.getElementById('addtheform');
    const newName = document.getElementById("newMovieName").value;
    const newReleaseDate = document.getElementById("newMovieReleaseDate").value;
    const newDurationMin = Number(document.getElementById("newMovieDurationMin").value);
    const newDescription = document.getElementById("newMovieDescription").value;
    const newContentRatingId = Number(document.getElementById("newMovieContentRatingId").value);
    const newThumbnailPath = document.getElementById("newMovieThumbnailPath").value;

    const newMovie = {
        name: newName,
        releaseDate: newReleaseDate,
        durationMin: newDurationMin,
        description: newDescription,
        contentRatingId: newContentRatingId,
        thumbnailPath: newThumbnailPath
    };

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(newMovie),
        redirect: 'follow'
    };
    fetch("/movie", requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Movie added successfully!");

                document.getElementById("addMovieForm").reset();
                GetmovieData();
            } else {
                alert("Failed to add movie");
            }
        })
        .catch(error => console.error('Error adding movie:', error));

    form.classList.remove('active');
}

function addform() {
    const form = document.getElementById('addtheform');
    if (!isaddFormOpen) {
        form.classList.add('active');
        isaddFormOpen = true;
    } else {
        form.classList.remove('active');
        isaddFormOpen = false;
    }
}

function DeleteMovieData(MvId) {
    var requestOptions = {
        method: 'DELETE',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch(`/movie/${MvId}`, requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Movie deleted successfully!");
                document.getElementById("addMovieForm").reset();
                GetmovieData();
            } else {
                alert("Failed to delete movie");
            }
        })
        .catch(error => console.error('Error adding movie:', error));
}

function UpdateMovieLayout(mvData) {
    MovieManage.innerHTML = "";
    for (const movie of mvData) {
        const Data = document.createElement('tr');
        Data.innerHTML = `
            <td class="img"><img src="./${movie.thumbnailPath}" width="50px" height="50px"></td>
            <td>${movie.name}</td>
            <td>${movie.id}</td>
            <td>${movie.durationMin}</td>
            <td>${movie.releaseDate}</td>
            <td>${movie.contentRatingId}</td>
            <td>
                <button class="toggle-btn" onclick="updateform(${movie.id})">修改</button>
                <button class="toggle-btn" onclick="DeleteMovieData(${movie.id})">刪除</button>
            </td>
        `;
        MovieManage.appendChild(Data);
    }
    ;
    updateLayout();
}

function UpdateMovieData() {
    const MovieID = Number(document.getElementById("movieID").textContent);
    const form = document.getElementById('formContainer');
    let newName = document.getElementById("changeName").value;
    let newReleaseDate = document.getElementById("changeReleaseDate").value;
    let newDurationMin = Number(document.getElementById("changeDurationMin").value);
    let newDescription = document.getElementById("changeDescription").value;
    let newContentRatingId = Number(document.getElementById("changeContentRatingId").value);
    let newThumbnailPath = document.getElementById("changeThumbnailPath").value;
    for (const movie of MovieData["data"]) {
        if (movie.id == MovieID) {
            if (newName == "") {
                newName = movie.name;

            }
            if (newReleaseDate == "") {
                newReleaseDate = movie.releaseDate;
            }
            if (newDurationMin == "") {
                newDurationMin = movie.durationMin;
            }
            if (newDescription == "") {
                newDescription = movie.description;
            }
            if (newContentRatingId == "") {
                newContentRatingId = movie.contentRatingId;
            }
            if (newThumbnailPath == "") {
                console.log(typeof newThumbnailPath);
                newThumbnailPath = movie.thumbnailPath;
            }
            break;
        }
    }
    ;

    const TargetMovie = {
        name: newName,
        releaseDate: newReleaseDate,
        durationMin: newDurationMin,
        description: newDescription,
        contentRatingId: newContentRatingId,
        thumbnailPath: newThumbnailPath
    };


    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: JSON.stringify(TargetMovie),
        redirect: 'follow'
    };
    console.log(TargetMovie);
    fetch(`/movie/${MovieID}`, requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Movie changed successfully!");

                document.getElementById("addMovieForm").reset();
                GetmovieData();
            } else {
                alert("Failed to change movie");
            }
        })
        .catch(error => console.error('Error adding movie:', error));

    form.classList.remove('active');
}

function updateform(MvId) {
    const form = document.getElementById("formContainer");
    const MovieID = document.getElementById("movieID");
    MovieID.textContent = MvId;
    if (!isupdateFormOpen) {
        form.classList.add('active');
        console.log(form.classList);
        isupdateFormOpen = true;
    } else {
        form.classList.remove('active');
        isupdateFormOpen = false;
    }
}

/* 場次管理 */

function GetSessionData(MvData) {
    const TTName = document.getElementById("thName");
    TTName.textContent = "場次管理 --- " + theaterName;

    for (const movie of MvData) {
        const GetTargetData = {
            theaterId: Number(theaterId),
            movieId: movie.id
        };
        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: JSON.stringify(GetTargetData),
            redirect: 'follow'
        };

        fetch(`/session/theater/movie`, requestOptions)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('資料不存在');
                }
                return response.json();
            })
            .then((data) => {
                SessionData.push(data["data"]);
                console.log(SessionData);
            });
    }
    SelectMovieForSession(MvData);
    GetScreenData();

}

function GetScreenData() {
    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch(`/screen/theater/${Number(theaterId)}`, requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('資料不存在');
            }
            return response.json();
        })
        .then((data) => {
            ScreenData = data["data"];
            console.log(ScreenData);
        });
}

function SelectMovieForSession(MvData) {
    const SelectElement = document.getElementById("SelectMovie");

    MvData.forEach(movie => {
        const newOption = document.createElement('option');
        newOption.value = movie.id;
        newOption.textContent = movie.name;
        SelectElement.appendChild(newOption);
    })
}

function UpdateSessionLayout() {
    const theadElement = document.getElementById("Session-thd");
    const tbodyElement = document.getElementById("Session-tbd");
    theadElement.innerHTML = ``;
    tbodyElement.innerHTML = ``;
    Selector = document.getElementById("SelectMovie").value;
    const FoundMovie = MovieData["data"].find(movie => movie.id === Number(Selector));
    console.log(FoundMovie);
    const headrow = document.createElement("tr");
    headrow.classList.add("blackbar");
    headrow.innerHTML = `
        <td>${FoundMovie.name}</td>
        <td>ID</td>
        <td>ScreenID</td>
        <td>Start Time</td>
        <td>Actions</td>
    `;
    theadElement.appendChild(headrow);
    FoundSession = null;
    for (const session of SessionData) {

        const target = session.find(session => session.movieId === Number(Selector));
        if (target != undefined) {
            FoundSession = session;
            break;
        }
    }
    console.log(FoundSession);
    FoundSession.forEach(perSession => {
        const bodyrow = document.createElement("tr");
        bodyrow.innerHTML = `
            <td></td>
            <td>${perSession.id}</td>
            <td>${perSession.screenId}</td>
            <td>${perSession.startTime}</td>
            <td>
                <button class="toggle-btn" onclick="updatesessionform(${perSession.id})">修改</button>
                <button class="toggle-btn" onclick="DeleteSessionData(${perSession.id})">刪除</button>
            </td>
        `;
        tbodyElement.appendChild(bodyrow);
    })
}

function UpdateSessionData() {
    const SessionID = Number(document.getElementById("sessionID").textContent);
    const form = document.getElementById('sessionContainer');
    let newStartTime = document.getElementById("timeSelect").value;
    let TargetSession;
    if (newStartTime == "") {
        return;
    }
    for (const session of FoundSession) {
        if (session.id == SessionID) {
            TargetSession = {
                movieId: Number(Selector),
                screenId: session.screenId,
                startTime: newStartTime + ":00"
            };
            break;
        }
    }
    ;

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: JSON.stringify(TargetSession),
        redirect: 'follow'
    };
    fetch(`/session/${SessionID}`, requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Session changed!");
                document.getElementById("changeSessionForm").reset();
            } else {
                alert("Failed to change session");
            }
        })
        .catch(error => console.error('Error change session:', error));

    form.classList.remove('active');
}

function updatesessionform(SsId) {
    const form = document.getElementById("sessionContainer");
    const ssID = document.getElementById("sessionID");
    ssID.textContent = SsId;
    if (!issessionupdateFormOpen) {
        form.classList.add('active');
        issessionupdateFormOpen = true;
    } else {
        form.classList.remove('active');
        issessionupdateFormOpen = false;
    }
}

function addSsform() {
    const form = document.getElementById('addsessionform');
    if (!isaddFormOpen) {
        form.classList.add('active');
        isaddFormOpen = true;
    } else {
        form.classList.remove('active');
        isaddFormOpen = false;
    }
    const SelectScreen = document.getElementById("newScreenID");
    SelectScreen.innerHTML = "";
    ScreenData.forEach(screen => {
        const newOption = document.createElement('option');
        newOption.value = screen.id;
        newOption.textContent = screen.id;
        SelectScreen.appendChild(newOption);
    })
}

function AddsessionData() {
    const form = document.getElementById('addsessionform');

    const newScreen = document.getElementById("newScreenID").value;
    const newStartTime = document.getElementById("newtimeSelect").value;


    const newSession = {
        screenId: Number(newScreen),
        movieId: Number(Selector),
        startTime: newStartTime + ":00"
    };

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(newSession),
        redirect: 'follow'
    };
    fetch("/session", requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Session added!");
                document.getElementById("addSessionForm").reset();
            } else {
                alert("Failed to add session");
            }
        })
        .catch(error => console.error('Error:', error));
    form.classList.remove('active');

}

function DeleteSessionData(SsId) {
    var requestOptions = {
        method: 'DELETE',
        headers: myHeaders,
        redirect: 'follow'
    };
    fetch(`/session/${SsId}`, requestOptions)
        .then(response => {
            if (response.ok) {
                alert("Session deleted!");
            } else {
                alert("Failed to delete session");
            }
        })
        .catch(error => console.error('Error:', error));

}

/* 頁面切換 */

function targetPage(PageID) {
    const pages = document.querySelectorAll('.changePage');
    pages.forEach(page => page.classList.remove('active'));
    document.getElementById(PageID).classList.add('active');
}

function updateLayout() {
    const container = document.getElementById("movieTable");
    container.style.display = "none";
    container.offsetHeight;
    container.style.display = "block";
}
