<html>
<script type="text/javascript" src=""></script>
<script type="text/javascript" src="static/layui/layui.js"></script>
<link rel="stylesheet" href="static/layui/css/layui.css">
<body>
<script>
	layui.use(['layer','form'],function(){
		var layer = layui.layer , form = layui.form;
		
		layer.msg('hello world');
	});
</script>

	<a href="login">Hello</a>
	<a href="user">User</a>
	<form action="login" method="post">
		<input type="text" name="username"><br>
		<input type="password" name="password">
		<br>
		<input type="submit" value="submit"><input type="reset" value="reset">
	</form>
	<a href="logout">Logout</a>
</body>
</html>
