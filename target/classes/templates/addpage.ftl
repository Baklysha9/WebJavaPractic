<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	</head>
	<body>
		<form method="POST" action="/add/${id}/${relize}">
			<input onkeyup="checkParams()" type="text" name="title" id="title" value="${title}">
			<br><br>
			<textarea onkeyup="checkParams()" name="content" id="content" cols="30" rows="10">${content}</textarea>
			<br>
			<br>
			<input id="submit" type="submit" value="${check}" onclick="checkFields()">
			<span id="error">&nbsp;</span>
		</form>
		<script type="text/javascript">
			function checkParams() {
    			var title = $('#title').val();
    			var content = $('#content').val();

			    if(title.length != 0 && content.length != 0) {
        			$('#submit').removeAttr('disabled');
        			$('span').text('');
    			} else {
        			$('#submit').attr('disabled', 'disabled');
        			$('span').text('Not all fields are filled out');
    			}
			}
		</script>
	</body>
</html>