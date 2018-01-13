<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Calculator App Using Spring 4 WebSocket</title>
</head>
<body>
<noscript><h2>Enable Java script and reload this page to run Websocket Demo</h2></noscript>
<h1>Calculator App Using Spring 4 WebSocket</h1>
<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button><br/><br/>
    </div>
    <div id="calculationDiv">
        <label>Number One:</label><input type="text" id="num1" /><br/>
        <label>Number Two:</label><input type="text" id="num2" /><br/><br/>
        <button id="sendNum" onclick="sendNum();">Send to Add</button>
        <p id="calResponse"></p>
    </div>
</div>

<script src="${contextPath}/resources/js/core/sockjs-0.3.4.min.js"></script>
<script src="${contextPath}/resources/js/core/stomp.min.js"></script>
<script type="text/javascript">
        var stompClient = null; 
        
        
        
        function connect() {
        	console.log('connect()............. START');
            var socket = new SockJS('${contextPath}/add');
			stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/showResult', function(calResult){
                	showResult(JSON.parse(calResult.body).result);
                });
            });
            console.log('connect()............. END');
        }
        
        function setConnected(connected) {
        	console.log('setConnected()............. START');
        	document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('calculationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('calResponse').innerHTML = '';
            console.log('setConnected()............. END');
        }
        
        function disconnect() {
        	console.log('disconnect()............. START');
            stompClient.disconnect();
            setConnected(false);
            console.log("Disconnected");
            console.log('disconnect()............. END');
        }
        function sendNum() {
        	console.log('sendNum()............. START');
            var num1 = document.getElementById('num1').value;
            var num2 = document.getElementById('num2').value;
            stompClient.send("/calcApp/add", {}, JSON.stringify({ 'num1': num1, 'num2': num2 }));
            console.log('sendNum()............. END');
        }
        function showResult(message) {
        	console.log('showResult()............. START');
            var response = document.getElementById('calResponse');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
            console.log('showResult()............. END');
        }
    </script>
</body>
</html>