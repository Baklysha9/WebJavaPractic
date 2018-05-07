<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<style>
			table {
				background: #f4f4f4;
				border-collapse: collapse;
				width: 500px;
				margin-top: 25px;
			}
			td{
				padding: 7px;
			}
			.ready{
				background-color: green;
			}
			td:nth-child(odd){
				text-align: center;
				width: 60px;
			}
			.add{
				padding:5px 10px 5px 10px;
				background-color: grey;
				width: 100px;
				color:black;
			}
		</style>
	</head>
	<body>
		<div>
			<a href="\addpage\0"><p class="add">Add new task</p></a>
			<table>
				<#list taskes as task>
				<#if !task.status>
				<tr>
					<td>
						<a href="\ready\${task.id}"><img src="http://sparkysite.ru/medium/check/check01/mcheck11.png" width="25">
						</a>
					</td>
					<td>
						<a href="\addpage\${task.id}">
							<b>${task.title}</b>
							<br>${task.content}
						</a>
					</td>
					<td>
						<a href="\delete\${task.id}" onclick="confirmDelete()">
							<img src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w128h1281338911337cross.png" width="25">
						</a>
					</td>
				</tr>
				</#if>
				</#list>
			</table>
			<table>
				<#list taskes as task>
				<#if task.status>
				<tr class="ready">
					<td>
						<a href="\ready\${task.id}"><img src="http://sparkysite.ru/medium/check/check01/mcheck11.png" width="25">
						</a>
					</td>
					<td>
						<a href="\addpage\${task.id}">
							<b>${task.title}</b>
							<br>${task.content}
						</a>
					</td>
					<td>
						<a href="\delete\${task.id}" onclick="confirmDelete()">
							<img src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w128h1281338911337cross.png" width="25">
						</a>
				</tr>
				</#if>
				</#list>
			</table>
		</div>
		<script type="text/javascript">
			function confirmDelete() {
    			if (!confirm("You confirm deletion?")) 'location="/";';
			}
		</script>
	</body>
</html>
