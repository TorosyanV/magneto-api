<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<title>Magneto v0.1</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />
<base href="/" />
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />

<!-- <link rel="stylesheet" type="text/css"
href="https://bootswatch.com/darkly/bootstrap.min.css"/> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


<link rel="stylesheet" href="/resources/css/style.css">
<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<%--<script type="text/javascript"--%>
<%--src="/webjars/bootstrap/js/bootstrap.min.js"></script>--%>
<script type="text/javascript" src="/resources/app/lib/angular.min.js"></script>
<script type="text/javascript"
	src="/resources/app/lib/angular-ui-router.js"></script>
</head>

<body ng-app="magneto">
	<header>
		<span class="magneto-logo"> <img
			src="/resources/images/magneto-logo.png" alt="magneto-logo"></span> <span>
			<ul class="site-nav mg-display-inline">
				<li ui-sref-active="active"><a ui-sref="home">Home</a></li>
				<li ui-sref-active="active"><a ui-sref="map">Map</a></li>
			</ul>
		</span> <a class="pull-right btn btn-default">${pageContext.request.remoteUser}</a>
	</header>
	<hr class="block-line" />
	<div class="ng-scope main-content" ui-view=""></div>
	<div ng-controller="messengerPanelController">
		<mg-messenger-panel minimized="true"
			class="messenger-panel background-dark" users="users"></mg-messenger-panel>
		<div class="mg-display-inline"
			style="bottom: 0; position: fixed; border: 1px solid red; z-index: 50; right: 200px">
			<mg-chat-dialog mg-highlight-on-creation="highlight"
				style="margin-right: 10px; background-color:#1C2F3D; padding: 10px;  height: 350px; width: 220px"
				class="" remove-dialog="removeDialog(dialog)" user="dialog.user"
				messages="dialog.messages" ng-repeat="dialog in chatDialogs">
			</mg-chat-dialog>
		</div>
	</div>
	<!-- load scripts -->
	<!-- load frameworks -->
	<script type="text/javascript" src="/resources/app/lib/sockjs-0.3.4.js"></script>
	<script type="text/javascript" src="/resources/app/lib/stomp.js"></script>
	<!--  Map-->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD4dQOEmMjoskYoMTPwDwr58fiXG_XZO7s"></script>
	<script src="/resources/app/frameworks/map/moMapModule.js"></script>
	<script src="/resources/app/frameworks/map/moMapDirective.js"></script>
	<%--widgets require--%>
	<script src="/resources/app/widgets/messenger/messenger.js"></script>
	<script
		src="/resources/app/widgets/messenger/panel/messengerPanel.userItemDirective.js"></script>
	<script
		src="/resources/app/widgets/messenger/panel/messengerPanelDirective.js"></script>
	<script
		src="/resources/app/widgets/messenger/chatDialog/chatDialogDirective.js"></script>

	<script src="/resources/app/widgets/widgetsModule.js"></script>
	<script src="/resources/app/widgets/uiDecorators/mgHighlighter.js"></script>
	<script src="/resources/app/widgets/uiDecorators/mgAccordion.js"></script>
	<script src="/resources/app/widgets/uiDecorators/mgScrollable.js"></script>
	<script src="/resources/app/widgets/uiDecorators/mgEnter.js"></script>

	<script type="text/javascript"
		src="/resources/app/config/route.config.js"></script>
	<script type="text/javascript" src="/resources/app/config/constants.js"></script>
	<script type="text/javascript" src="/resources/app/app.js"></script>


	<!-- load services -->

	<script type="text/javascript"
		src="/resources/app/services/geoService.js"></script>
	<script type="text/javascript"
		src="/resources/app/services/messengerService.js"></script>
	<script type="text/javascript"
		src="/resources/app/services/guestService.js"></script>
	<script type="text/javascript"
		src="/resources/app/services/userConnectionService.js"></script>
			<script type="text/javascript"
		src="/resources/app/services/soundService.js"></script>
soundService
	<!--load controllers  -->
	<script type="text/javascript"
		src="/resources/app/controllers/homeController.js"></script>
	<script type="text/javascript"
		src="/resources/app/controllers/messengerPanelController.js"></script>
	<script type="text/javascript"
		src="/resources/app/controllers/mapController.js"></script>
	<script type="text/javascript"
		src="/resources/app/controllers/guestDetailController.js"></script>

</body>

</html>