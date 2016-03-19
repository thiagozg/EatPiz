var buscar = function() {
	
	var nomePizza = $('#pizza-pesquisa').val();
	var url= 'clientes/pizza/'+nomePizza;
	
	$.get(url)
		.success(function(view) {
			$('#secao-pizzarias').html(view);
		});
	
};

$(document).ready(function() {
	$('#btn-buscar').on('click', buscar);
});


