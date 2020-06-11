$(document).ready(
		function() {

			var table = $('#proxylist').DataTable();
			
			generateTable("data/scrap");

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
										[ data[index].ip, data[index].port,
												data[index].location,
												data[index].status ]).draw(
										false);
							});

							$('#overlay').fadeOut();
						}).fail(function() {
					console.log("error");
				}).always(function() {
					console.log("complete");
				});
			}

		});
