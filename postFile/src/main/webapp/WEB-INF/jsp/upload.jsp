<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script>
function check() {
    
    fileName = document.querySelector('#myfile').value;
    var ext = fileName.split('.').pop();
    switch(ext)
    {
        case 'xls':
        	$('input:button').removeAttr('disabled');
        	break;
        default:
        	$('input:button').attr('disabled',true);
            alert('This type of file is not allowed!!!-------select an .XLS file');
            document.querySelector('#myfile').value='';
            
    }
}
$(document).ready(function() {
	
	$("#submit").click(function() {
	  $.ajax({
	    url: 'uploadFile',
	    type: "POST",
	    data:  new FormData(document.getElementById("upload-file")),
	    enctype: 'multipart/form-data',
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function () {
	      alert("File uploaded successfully!!!");
	    }
	  });
	});
});
    
</script>
<form id="upload-file">
  <label >Upload your file:</label>
  <input id="myfile" type="file" name="file" onchange="check()"/>
  <input type="button" id="submit" value="submit" disabled />
</form>

</body>
</html>