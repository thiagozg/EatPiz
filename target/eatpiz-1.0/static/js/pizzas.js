$(document).ready(function() {
	aplicatListeners();
	aplicarListenerBtnSalvar();
	$('.desativarSubmitForm').attr('onsubmit', 'return false');
});

var aplicarListenerBtnSalvar = function() {
	
	$("#btn-salvar").on('click', function() {
		var url = 'pizzas';
		var dadosPizza = $('#form-pizza').serialize();
		
		$.post(url, dadosPizza)
			.done(function(pagina) {
				$('#secao-pizzas').html(pagina);
				aplicatListeners();
			})
			.fail(function() {
				alert('Erro ao salvar!');
			})
			.always(function() {
				$('#modal-pizza').modal('hide');
			});
	});
	
};

var limparModal = function() {
	$('#id').val('');
	$('#nome').val('');
	$('#preco').val('');
	$('#categoria').val('');
	$('#ingredientes option').attr('selected', false);
};

var aplicatListeners = function() {
	
	// evento do bootstrap = será chamado sempre que o metodo hide for chamado
	$('#modal-pizza').on('hide.bs.modal', limparModal);
	
	$('.btn-editar').on('click', function() {
		var pizzaId = $(this).parents('tr').data('id');
		var url = 'pizzas/'+pizzaId;
		
		// esse objeto pizza em function, é um ojbeto JSON
		$.get(url)
			.success(function(pizza) {
				$('#id').val(pizza.id);
				$('#nome').val(pizza.nome);
				$('#preco').val(pizza.preco);
				$('#categoria').val(pizza.categoria);
				
				pizza.ingredientes.forEach(function(ingrediente){
					var idIngrediente = ingrediente.id
					$('#ingredientes option[value='+idIngrediente+']').attr('selected', true);
				});
				
				$('#modal-pizza').modal('show');
			});
	});

	$('.btn-deletar').on('click', function(){
		// this = referencia objeto button
		// parents = pega o primeiro tr acima do button
		// data =  a informação do data-id
		var pizzaId = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "pizzas/"+pizzaId,
			type : 'DELETE',
			headers : {'X-CSRF-TOKEN' : csrf},
			success : function(result) {
				$('tr[data-id="'+pizzaId+'"]').remove();
				var qtdPizzas = parseInt( $('#quantidade-pizzas').text() );
				$('#quantidade-pizzas').text(qtdPizzas - 1);
			}
		});
	});
	
};