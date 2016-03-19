$(document).ready(function() {
	aplicatListeners();
	aplicarListenerBtnSalvar();
	$('.desativarSubmitForm').attr('onsubmit', 'return false');
});

var aplicarListenerBtnSalvar = function() {
	
	$("#btn-salvar").on('click', function() {
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();
		
		$.post(url, dadosIngrediente)
			.done(function(pagina) {
				$('#secao-ingredientes').html(pagina);
				aplicatListeners();
			})
			.fail(function() {
				alert('Erro ao salvar!');
			})
			.always(function() {
				$('#modal-ingrediente').modal('hide');
			});
	});
	
};

var limparModal = function() {
	$('#id').val('');
	$('#nome').val('');
	$('#categoria').val('');
};

var aplicatListeners = function() {
	
	// evento do bootstrap = será chamado sempre que o metodo hide for chamado
	$('#modal-ingrediente').on('hide.bs.modal', limparModal);
	
	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'ingredientes/'+id;
		
		$.get(url)
			.success(function(ingrediente) {
				$('#id').val(ingrediente.id);
				$('#nome').val(ingrediente.nome);
				$('#categoria').val(ingrediente.categoria);
				
				$('#modal-ingrediente').modal('show');
			});
	});

	$('.btn-deletar').on('click', function(){
		// this = referencia objeto button
		// parents = pega o primeiro tr acima do button
		// data =  a informação do data-id
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "ingredientes/"+id,
			type : 'DELETE',
			headers : {'X-CSRF-TOKEN' : csrf},
			success : function(result) {
				$('tr[data-id="'+id+'"]').remove();
				var ingredientes = parseInt( $('#quantidade-ingredientes').text() );
				$('#quantidade-ingredientes').text(ingredientes - 1);
			}
		});
	});
	
};