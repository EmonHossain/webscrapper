$(document).ready(
		function() {

			var table = $('#proxylist').DataTable();
			
			generateTable("data/local");

			$('.btn-refresh').click(function(event) {
				event.preventDefault();
				table.clear().draw();
				generateTable("data/scrap");
			});

			function generateTable(link) {
				$.ajax({
					url : link,
					type : 'GET',
					dataType : 'JSON',
					beforeSend : function() {
						$('#overlay').fadeIn();
					}
				}).done(
						function(data) {
							console.log(data);
							$.each(data, function(index, val) {
								
								table.row.add(
										[ val.ip, val.port,
											formatDate(val.testDate),
											formatDate(val.firstFound),
											formatDate(val.lastFound),
											formatDate(val.testUrlDate),
											"<a id='"+val.ip+"' class='btn'  >Details</a>"]).draw(
										false);
							});

							$('#overlay').fadeOut();
						}).fail(function() {
					console.log("error");
				}).always(function() {
					console.log("complete");
				});
			}
			
			function formatDate(date){
				if(date != null){
					var fd = date.split("-");
					return fd[2]+"-"+fd[1]+"-"+fd[0];
				}else{
					return "";
				}
			}


			$.on('click', '.view-details', function(event) {
				event.preventDefault();
				console.log(this.id);
			});

			
		});

		
