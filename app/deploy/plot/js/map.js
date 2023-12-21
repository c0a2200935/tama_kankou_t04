// これはマップを表示するためのJavaScript

var mymap = L.map('map');

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
  maxZoom: 20,
  attribution: '<a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
}).addTo(mymap);

mymap.setView([35.62575, 139.34153], 15);

