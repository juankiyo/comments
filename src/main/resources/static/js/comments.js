var stompClient = null;

function connect() {
    var socket = new SockJS('/add');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/channel/allcomments', function(comment){
            showComment(JSON.parse(comment.body).name,JSON.parse(comment.body).comment);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function validateComment(name, comment) {
    if (name == "" || comment == "") {
        return false;
    }
    return true;
}


function sendComment() {
    var fieldName = document.getElementById('name');
    var fieldComment = document.getElementById('comment');
    var name = fieldName.value.trim();
    var comment = fieldComment.value.trim();
    if (!validateComment(name, comment)) {
        fieldName.value = "";
        fieldComment.value = "";
        alert("Please, enter your name and comment.");
    } else {
        stompClient.send("/comments/add", {}, JSON.stringify({ 'name': name, 'comment' : comment }));
        fieldName.value = "";
        fieldComment.value = "";
    }

}

function showComment(name, comment) {
    var div = document.getElementById('commentsDiv');
    var spanName = document.createElement('span');
    var spanComment = document.createElement('span');
    var br = document.createElement('br');
    spanName.className = "list_comment_name";
    spanComment.className = "list_comment_comment";
    spanName.innerHTML = name
    spanComment.innerHTML = comment;
    div.appendChild(spanName);
    div.appendChild(spanComment);
    div.appendChild(br)
}