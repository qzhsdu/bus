const server = "http://localhost:8089/server_war_exploded/";
console.log("load");
$(document).ready(function () {
    $("#commit").click(function () {
        $.get(
            server+getChoice(),
            {
                "from":document.getElementById("from-input").value,
                "to":document.getElementById("to-input").value,
                "interval":document.getElementById("interval").value
            },
            function (data) {
                console.log("getData");
                let json = JSON.parse(data);
                $("#detail").empty();
                showStops(json);
                if(json['time']<0){
                    $("#detail").append($("<div class=\"total\"></div>").text("共花费"+json["price"]) );
                }else{
                    $("#detail").append($("<div class=\"toal\"></div>").text("共花费"+json["time"]));
                }
            }
        )
    });
});
function getChoice() {
    let type;
    if(document.getElementById("fast").checked){
        type = "fast";
    }else {
        type = "cheap";
    }
    console.log(type);
    return type;
}
function showStops(data) {
    let path = data['stops'];
    let lines = data['lines'];
    for(let i=0;i<path.length-1;i++){
        let stops = lines[i]["stops"];
        let x = stops.findIndex(function (stop) {
            return stop["stop_name"]===path[i];
        });
        let y = stops.findIndex(function (stop) {
            return stop["stop_name"]===path[i+1];
        });
        if(x>y){
            stops.reverse();
            [x,y]=[y,x]
        }
        $("#detail").
            append( $("<div class=\"black-text\"></div>").text(lines[i]["name"]))
            .append($("<span class=\"not-in\"></span>").text(stops.slice(0,x).map(a=>a.stop_name).join("  ")+"  "))
            .append( $("<span class=\"in\"></span>").text(stops.slice(x,y+1).map(a=>a.stop_name).join("  ")+"  "))
            .append( $("<span class=\"not-in\"></span>").text(stops.slice(y+1).map(a=>a.stop_name).join("  ")+"  "))
    }
}