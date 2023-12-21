
var startplot = null;
var startflag = false;
var goalplot = null;
var goalflag = false;
var route = null;
var mymap = L.map('map');

var redIcon = L.icon({
    iconUrl: "../image/map-pin_02_red.png",
    iconRetinaUrl: "../image/map-pin_02_red.png",
    shadowUrl: "https://esm.sh/leaflet@1.9.2/dist/images/marker-shadow.png",
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    tooltipAnchor: [16, -28],
    shadowSize: [41, 41],
    className: "icon-red", // <= ここでクラス名を指定
  });
var blueIcon = L.icon({
    iconUrl: "../image/map-pin_02_blue.png",
    iconRetinaUrl: "../image/map-pin_02_blue.png",
    shadowUrl: "https://esm.sh/leaflet@1.9.2/dist/images/marker-shadow.png",
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    tooltipAnchor: [16, -28],
    shadowSize: [41, 41],
    className: "icon-blue", // <= ここでクラス名を指定
});

// マップを表示する
function initialize() {

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 20,
    attribution: '<a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(mymap);

    mymap.setView([35.62575, 139.34153], 15);
}

// AJAXを使用してサーバーにcoordinatesデータを送信
function sendCoordinatesToServlet(coordinates, start, goal) {
    var xhr = new XMLHttpRequest();
    var url = "/plot/catch-route"; // 送信先のURL
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // サーバーからの応答を必要に応じて処理
            console.log("応答1");
            var response = xhr.responseText;
            console.log(response);
            console.log("応答2");

            window.location.href="/plot/search";
        } else {
            console.error("エラー:"+ xhr.status);
        }
    };

    // 送信するデータオブジェクトを作成
    var data = {
        coordinates: coordinates,
        start: start,
        goal: goal
    };

    // データをJSON文字列に変換
    var jsonData = JSON.stringify(data);
    // データ確認
    console.log(jsonData);

    // データをサーバーに送信
    xhr.send(jsonData);
}

// スタートボタンとゴールボタンを取得
startbtn = document.getElementById("startbtn");
goalbtn = document.getElementById("goalbtn");
searchbtn = document.getElementById("search");

startbtn.addEventListener("click", function(){
    startflag = true;
    goalflag = false;
});

goalbtn.addEventListener("click", function(){
    startflag = false;
    goalflag = true;
});

searchbtn.addEventListener("click", function(){
    startflag = false;
    goalflag = false;

    if (startplot && goalplot){ // スタートとゴールが設定されていたら経路探索
        if (route){ // 既に経路探索されていたらその結果表示を消去
            mymap.removeControl(route);
        }

        route = L.Routing.control({ // 探索開始
            waypoints: [
                L.latLng(startplot.getLatLng().lat, startplot.getLatLng().lng),
                L.latLng(goalplot.getLatLng().lat, goalplot.getLatLng().lng)
            ],
            createMarker: function(i, waypoint, n) {
                // nullを返すことで新しいマーカーを作成せずに非表示にする
                return null;
            },
            
            routeWhileDragging: false,
            show: false
        }).addTo(mymap);
        mymap.removeControl(route);

        // 経路の座標情報取得
        route.on("routesfound", function(e){
            var route1 = e.routes[0]; // 最初の経路を取得
            var coordinates = route1.coordinates; // 経路の座標を取得
            console.log(coordinates);
            console.log(coordinates.contentType);

            var start = {lat: startplot.getLatLng().lat, lng: startplot.getLatLng().lng};
            var goal = {lat: goalplot.getLatLng().lat, lng: goalplot.getLatLng().lng};

            // coordinatesの経路情報をServletに送信
            sendCoordinatesToServlet(coordinates, start, goal);
        })
    }


    
    
});
    


// マップをクリックした時のイベント
mymap.on('click', function (e) {
    if (startflag){ //スタート地点入力モードか判定
        if (mymap && startplot){
            // もしスタート地点がプロットされていたら消す
            mymap.removeLayer(startplot);
            startplot = null;
        }
        if (mymap && !startplot){
            startplot = L.marker([e.latlng.lat, e.latlng.lng], {icon: blueIcon}).addTo(mymap).bindPopup("スタート地点");
        }
    }

    if (goalflag){ // ゴール地点入力モードか判定
        if (mymap && goalplot){
            // もしスタート地点がプロットされていたら消す
            mymap.removeLayer(goalplot);
            goalplot = null;
        }
        if (mymap && !goalplot){
            goalplot = L.marker([e.latlng.lat, e.latlng.lng], {icon: redIcon}).addTo(mymap).bindPopup("ゴール地点");
        }
    }
    
    });




window.onload = function(){ // initialize関数を呼び出して地図を表示
    initialize();
}
