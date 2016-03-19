$(document).ready(function() {
	aplicarListeners();
	aplicarListenerBtnSalvar();
	$('.desativarSubmitForm').attr('onsubmit', 'return false');
});

var aplicarListenerBtnSalvar = function() {
	
	$("#btn-salvar-telefone").on('click', function() {
		var url = 'contatos/telefone';
		var dados = $('#form-telefone').serialize();
		
		$.post(url, dados)
			.done(function(pagina) {
				$('#secao-telefones').html(pagina);
				aplicarListeners();
			})
			.fail(function() {
				alert('Erro ao salvar!');
			})
			.always(function() {
				$('#modal-telefone').modal('hide');
			});
	});
	
	$("#btn-salvar-email").on('click', function() {
		var url = 'contatos/email';
		var dados = $('#form-email').serialize();
		
		$.post(url, dados)
			.done(function(pagina) {
				$('#secao-emails').html(pagina);
				aplicarListeners();
			})
			.fail(function() {
				alert('Erro ao salvar!');
			})
			.always(function() {
				$('#modal-email').modal('hide');
			});
	});
	
};

var aplicarListeners = function() {
	/*--------- TELEFONE ---------*/
	$('#modal-telefone').on('hide.bs.modal', limparModal);

	$('.btn-editar-telefone').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'contatos/telefone/'+id;
		
		$.get(url)
			.success(function(telefone) {
				$('#telefone-id').val(telefone.id);
				$('#numero').val(telefone.numero);
				$('#modal-telefone').modal('show');
			});
	});

	$('.btn-deletar-telefone').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "contatos/telefone/"+id,
			type : 'DELETE',
			headers : {'X-CSRF-TOKEN' : csrf},
			success : function(result) {
				$('tr[data-id="'+id+'"]').remove();
			}
		});
	});

	/*--------- EMAIL ---------*/
	$('#modal-email').on('hide.bs.modal', limparModal);
	
	$('.btn-editar-email').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'contatos/email/'+id;
		
		$.get(url)
			.success(function(email) {
				$('#email-id').val(email.id);
				$('#endereco').val(email.endereco);
				$('#modal-email').modal('show');
			});
	});

	$('.btn-deletar-email').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "contatos/email/"+id,
			type : 'DELETE',
			headers : {'X-CSRF-TOKEN' : csrf},
			success : function(result) {
				$('tr[data-id="'+id+'"]').remove();
			}
		});
	});
};

var limparModal = function() {
	$('.valor').val('');
};