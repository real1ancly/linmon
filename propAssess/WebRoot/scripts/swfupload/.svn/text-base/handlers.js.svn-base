var FeaturesHandlers = {
    /**
    * swf加载成功后
    **/
	swfUploadLoaded : function () {
		Features.start(this); 
	},
    
    /**
    * 选择文件后的回调函数
    **/
	fileQueued : function (file) {
		try {
			var queueString =  file.name + "###上传进度:0%" ;
			//添加至select框
			Features.selQueue.options[Features.selQueue.options.length] = new Option(queueString, file.id);
		} catch (ex) {
			this.debug(ex);
		}
	},

	/**
    * 选择文件失败后的回调函数
    **/
	fileQueueError : function (file, errorCode, message) {
		try {
			var errorName = "";
			switch (errorCode) {
			case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
				errorName = "上传队列超过限制";
				break;
			case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
				errorName = "文件大小超过限制";
				break;
			case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
				errorName = "文件大小为零";
				break;
			case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
				errorName = "无效的文件类型";
				break;
			default:
				errorName = "未知错误";
				break;
			}

			var errorString = errorName + ":\r\n\r\n文件Id: " + (typeof(file) === "object" && file !== null ? file.id : "na") + "\r\n消息提示:" + message;
			
			//提示错误信息
			alert(errorString);
			
		} catch (ex) {
			this.debug(ex);
		}
	},
	
	/**
    * 选择文件完成后的回调函数
    **/
	fileDialogComplete : function (numFilesSelected, numFilesQueued) {
		try {
			
		} catch (ex) {
			this.debug(ex);
		}
	},
	
	/**
    * 上传开始时的回调函数
    **/
	uploadStart : function (file) {
		try {
			
		} catch (ex) {
			this.debug(ex);
		}

		return true;
	},

	/**
    * 上传尽行时的回调函数
    **/
	uploadProgress : function (file, bytesLoaded, totalBytes) {

		try {
			var percent = Math.ceil((bytesLoaded / file.size) * 100);
			if (percent < 10) {
				percent = "  " + percent;
			} else if (percent < 100) {
				percent = " " + percent;
			}

			var queueString =  file.name + "###上传进度:" + percent+ "%" ;
			
			var optionObject = getOption(file.id);
			
			optionObject.text=queueString;
			
		} catch (ex) {
			this.debug(ex);
		}
	},

	/**
    * 上传成功时的回调函数
    **/
	uploadSuccess : function (file, serverData, receivedResponse) {
		try {
		    var queueString = file.name + "###上传进度:完成";
		    
		    var optionObject = getOption(file.id);
			
			optionObject.text=queueString;
			
			Features.selFiles.value += serverData + "***";
        } catch (ex) {
			this.debug(ex);
		}
	},

	/**
    * 上传失败时的回调函数
    **/
	uploadError : function (file, errorCode, message) {
		try {
			var errorName = "";
			var queueString = file.name + "###上传进度:失败";
			var optionObject = getOption(file.id);
			switch (errorCode) {
			case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			   
				optionObject.text=queueString;
				errorName = "HTTP访问失败!";
				break;
			case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
				errorName = "无上传地址";
				break;
			case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			    optionObject.text=queueString;
				errorName = "IO操作失败";
				break;
			case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			    optionObject.text=queueString;
				errorName = "安全错误";
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
				errorName = "上传大小超过限制";
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
				errorName = "上传失败";
				break;
			case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
				errorName = "选择的文件不存在";
				break;
			case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
				errorName = "FILE VALIDATION FAILED";
				break;
			case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
				errorName = "上传文件被取消";
				optionObject.text=file.name + "###上传进度:被取消";
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
				errorName = "上传被停止";
				optionObject.text=file.name + "###上传进度:0%";
				break;
			default:
				errorName = "未知错误";
				break;
			}

			var errorString = errorName + ":\r\n\r\nFile ID: " + (typeof(file) === "object" && file !== null ? file.id : "na") + "\r\n消息提示:" + message;
			
			alert(errorString);

		} catch (ex) {
			this.debug(ex);
		}
	},
	
	/**
    * 上传完成时的回调函数
    **/
	uploadComplete : function (file) {
		try {
			//alert("Upload Complete: " + file.id);
			var stats = this.getStats();
			
			if (stats.files_queued > 0){
			 this.startUpload();
			}
		} catch (ex) {
			this.debug(ex);
		}
	},
	
	// 打印信息函数 
	debug : function (message) {
		try {
		    return ;
			if (window.console && typeof(window.console.error) === "function" && typeof(window.console.log) === "function") {
				if (typeof(message) === "object" && typeof(message.name) === "string" && typeof(message.message) === "string") {
					window.console.error(message);
				} else {
					window.console.log(message);
				}
			}
		} catch (ex) {
		}
		try {
			if (this.settings.debug) {
				this.debugMessage(message);
			}
		} catch (ex1) {
		}
	}

};
