/**
    * Created by Arthan on 24.12.2016. Project: brainburns
    */

function testFunc(str: string) {
    return "test " + str
}
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("main-title").innerHTML = testFunc(new Date().toLocaleString());
});


