// Called instead of the SWFUpload _showUI method
var Features = {
	start: function (swf_upload_instance) {
		
		Features.SU = swf_upload_instance;

		Features.cacheFields();

		Features.btnStartSelectedFile.onclick = function () {
			try {
				Features.startSelectedFile();
			} catch (ex) {
			}
			return false;
		};
		Features.btnStopUpload.onclick = function () {
			try {
				Features.stopUpload();
			} catch (ex) {
			}
			return false;
		};
		Features.btnCancelSelectedFile.onclick = function () {
			try {
				Features.cancelSelectedFile(true);
			} catch (ex) {
			}
			return false;
		};
		Features.btnDelUpload.onclick = function () {
			try {
				Features.delSelectedFile();
			} catch (ex) {
			}
			return false;
		};
		
		//document.getElementById("spanLoadStatus").innerHTML = "已成功加载";
	},
	cacheFields: function () {
		if (Features.is_cached) {
			return;
		}
		
		Features.selQueue = document.getElementById("selQueue");
		Features.selFiles = document.getElementById("selFiles");
		Features.btnStartSelectedFile = document.getElementById("btnStartSelectedFile");
		Features.btnStopUpload = document.getElementById("btnStopUpload");
		Features.btnCancelSelectedFile = document.getElementById("btnCancelSelectedFile");
		Features.btnDelUpload = document.getElementById("btnDelUpload");
		
		Features.is_cached = true;
	},
	
	startSelectedFile: function () {
	    if (Features.selQueue.options.length === 0) {
			alert("上传队列为空!");
			return;
		}
		
		Features.btnDelUpload.disabled = true;

        //开始上传第一个文件
		var file_id = Features.selQueue.options[0].value;
		
		Features.SU.startUpload(file_id);
		
	},
	
	
	stopUpload: function () {
		Features.SU.stopUpload();
	},
	
	cancelSelectedFile: function (triggerEvent) {
		if (Features.selQueue.options.length === 0) {
			alert("上传队列为空!");
			return;
		}
		if (Features.selQueue.selectedIndex === -1) {
			alert("请选择待暂停的文件!");
			return;
		}

		var oOption;
		
		for(var i=Features.selQueue.options.length-1; i>=0; i--)
		{
			oOption = Features.selQueue.options[i];
			if(oOption.selected)
			{ 
			    var file_id = oOption.value;
			    Features.SU.cancelUpload(file_id, triggerEvent); 
			}
		}
		
	},
	
	delSelectedFile: function () {
		if (Features.selQueue.options.length === 0) {
			alert("上传队列为空!");
			return;
		}
		if (Features.selQueue.selectedIndex === -1) {
			alert("请选择待删除的文件!");
			return;
		}
		
		var oOption;
		
		for(var i=Features.selQueue.options.length-1; i>=0; i--)
		{
			oOption = Features.selQueue.options[i];
			if(oOption.selected)
			{ 
			    Features.selQueue.options.remove(i);  
			}
		}
	}
};
