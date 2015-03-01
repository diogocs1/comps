openerp.COMPS = function(openerp){
	
//	openerp.web.WebClient.include({
//		_template: "CompsLayout",
//
//		show_application: function() {
//			var self = this;
//			self.toggle_bars(true);
//			self.update_logo();
//			self.menu = new openerp.web.Menu(self);
//			self.menu.replace(this.$el.find('.oe_menu_placeholder'));
//			self.menu.on('menu_click', this, this.on_menu_action);
//			self.user_menu = new openerp.web.UserMenu(self);
//			self.user_menu.replace(this.$el.find('.oe_user_menu_placeholder'));
//			self.user_menu.on('user_logout', self, self.on_logout);
//			self.user_menu.do_update();
//			self.bind_hashchange();
//			self.set_title();
//			self.check_timezone();
//			if (self.client_options.action_post_login) {
//				self.action_manager.do_action(self.client_options.action_post_login);
//				delete(self.client_options.action_post_login);
//			}
//			// Minha função
//			self.mostra_menu();
//		},
//		mostra_menu : function (){
//			$("#botao-menu-opcoes").click(function() {
//				$("#menu-opcoes").css("left", "0%");
//				setTimeout(function (){
//					$("#menu-opcoes").css("left", "-100%");
//				}, 20000);
//			});
//		},
//	});
////	openerp.web.UserMenu.include({
////		template : "MenuUsuario"
////	});
//	
//	
	
	openerp.web.View.include ({
		start: function () {
			setTimeout(function(self){
				//   Máscaras de campo
				//       setTimeout foi usado para retardar o carregamento da máscara
				//       para o final do ViewLoad. (O tempo foi escolhido aleatoriamente)
				$(".data input").mask("99/99/9999");
				$(".cpf input").mask("999.999.999-99");
				$(".telefone input").mask("(99) 9999-9999");
				$(".data-ano input").mask("9999");
				$(".tempo input").mask("99:99:9");
			}, 2000);
			return this.load_view();
		},
	});
};