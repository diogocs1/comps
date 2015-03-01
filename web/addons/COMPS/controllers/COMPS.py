# encoding: UTF-8

from openerp.http import route, Controller, request
from openerp.addons.COMPS.funcoes import utils

class Mobile_proxy (Controller):

	'''
	Responsável por disponibilizar dados JSON para o cliente Android
	def mobile_login (self)
	def recebe_dados (self)
	'''

	tipos_avaliacoes = {
		"MEDIDAS_CORPORAIS"					: "comps.imc",
		"PERIMETROS" 						: "comps.perimetro",
		"DOBRAS_CUTANEAS" 					: "comps.dobras_cutaneas",
		"IMPULSAO_HORIZONTAL" 				: "comps.impulsao_horizontal",
		"SENTAR_ALCANCAR" 					: "comps.sentar_alcancar",
		"PREENSAO_MANUAL" 					: "comps.preensao_manual",
		"ABDOMINAIS"						: "comps.av_abdominal",
		"LANCAMENTO_UNILATERAL"				: "comps.lancamento_unilateral",
		"LANCAMENTO_SIMULTANEO"				: "comps.lancamento_simultaneo",
		"CORRIDA_25M"						: "comps.corrida_25m",
		"SHUTLLERUN_10x5M"					: "comps.shutllerun_10x5m",
		"SHUTLLERUN_20M"					: "comps.shutllerun_20m",
		"EQUILIBRIO_RETAGUARDA"				: "comps.equilibrio_retaguarda",
		"SALTOS_LATERAIS"					: "comps.saltos_laterais",
		"TRANSPOSICAO_LATERAL"				: "comps.transposicao_lateral",
		"SALTOS_MONOPEDAIS_PERNA_DIREITA" 	: "comps.saltos_monopedais_perna_direita",
		"SALTOS_MONOPEDAIS_PERNA_ESQUERDA"	: "comps.saltos_monopedais_perna_esquerda",
	}

	@route("/sync/login", type="json", auth="none")
	def mobile_login (self):
		'''
		Inicia a sessão para o cliente Android
		@return: dict / JSON
				"res": {
						"avaliador_id" : id,
						"turmas" : [
										{
											"name" : name,
											"id" : id,
											"alunos" : [
														{"name" : name, "id" : id}
														]
										}
									]
						}
		'''
		response = {}
		json = request.jsonrequest
		uid = request.session.authenticate(json["db"], json["usuario"], json["senha"])
		if uid:
			# Variáveis dinâmicas
			turmas = []
			obj_avaliadores = request.registry.get("comps.avaliador")
			avaliadores = obj_avaliadores.browse( request.cr, uid, obj_avaliadores.search( request.cr, uid, [("res_users_id","=",uid)] ) )
			# Criação dos dados de retorno em JSON
			try:
				# Recupera os dados de cada turma - INICIO
				for turma in avaliadores[0].turma_ids:
					dados_turma = {
									"name" : turma.name,
									"id" : turma.id,
									"alunos" : []
									}
					# Adiciona os alinos a lista de alunos - INICIO
					for aluno in turma.aluno_ids:
						aluno = {"name" : aluno.name, "id" : aluno.id}
						dados_turma["alunos"].append( aluno )
					# Adiciona os alinos a lista de alunos - FIM
					turmas.append( dados_turma )
				# Recupera os dados de cada turma - FIM
				uid = avaliadores[0].id
				###########################
				response["turmas"] = turmas
				###########################
			except IndexError:
				pass
		# endif
		########################
		response["uid"] = uid
		########################
		print response
		return response
	
	@route("/sync", type="json", auth="user")
	def recebe_dados (self):
		'''
		Recebe, valida e armazena dados de avaliações enviados do cliente mobile
		'''
		cr, uid = request.cr, request.uid
		json = request.jsonrequest
		# Percorre todas as avaliações recebidas
		for av in json:
			values = json[av]
			try:
				# Recupera os dados da avaliação
				turma_id = request.registry.get("comps.turma").search( cr, uid, [('avaliador_ids.id','=', json[av]["avaliador_id"]), ('aluno_ids.id', '=', json[av]["aluno_id"]) ])[0]
				escola_id = request.registry.get("comps.escola").search( cr, uid, [("turma_ids.id", "=", turma_id) ])[0]
				# Adiciona informações de turma e escola
				values["turma_id"] = turma_id
				values["escola_id"] = escola_id
			except:
				novos_valores = {}
				novos_valores["json"] = values
				novos_valores["detalhes"] = utils.extrai_dados(values)
				request.registry.get("comps.avaliacoes_pendentes").create( cr, 1, novos_valores )
			# Cria o registro no sistema
			request.registry.get( self.tipos_avaliacoes[ json[av]["tipo"] ] ).create( cr, 1, values )
