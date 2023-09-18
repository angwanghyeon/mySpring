console.log("reply module.....");

const replyService = (function(){

	function add(reply, callback, error){
	console.log("........add reply......");
	
	$.ajax({
		type : 'post',
		url : '/reply/new',
		data : JSON.stringify(reply),
		contentType : "application/json; charset=UTF-8",
		success : function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
	})
	}

	function read(rno, callback, error){
			
		console.log("get 할 번호......"+rno);

		$.getJSON({
			url : '/reply/'+rno,
			success : function(data){
				if(callback){
					callback(data);
				}
			},
			error : function(xhr, status, err){
				if(error){
					error(err);
				}
			}
		})
	}

	function getList(param, callback, error){
			const bno = param.bno;
			const page = param.page || 1;

			$.getJSON("/reply/pages/"+bno+"/"+page+".json",
				function(data){
					if(callback){
						callback(data);
					}
				}

			).fail(function(xhr, status, err){
				if(error){
					error(err);
				}
			});
		}
	
	function remove(rno, callback, error){
		$.ajax({
			type:'delete',
			url: '/reply/'+rno,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}

	function update(reply, callback, error){
		console.log("update......"+reply.rno);
		$.ajax({
			type : 'put',
			url : '/reply/'+reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=UTF-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, erro){
				if(error){
					error(erro);
				}
			}
		})
	}



	return {
		add:add,
		getList:getList,
		remove:remove,
		update:update,
		read:read
	};

})();

