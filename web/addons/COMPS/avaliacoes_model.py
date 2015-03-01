# -*- encoding: UTF-8 -*-

from openerp.osv import osv, fields
from openerp.osv.orm import except_orm

class Medidas_corporais (osv.osv):
	_name = 'comps.imc'
	
	_calcula_imc = lambda self,cr,uid,ids,fields,args,context: self.calcula_imc(cr,uid,ids,fields,args,context)
	
	_columns = {
        "outrosDados" : fields.text("Outros dados"),
		'name' : fields.char('Nome'),
		'create_date' : fields.datetime(u'Data/hora da Avaliação'),
		'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
		'escola_id' : fields.many2one('comps.escola', "Escola"),
		'turma_id' : fields.many2one('comps.turma', "Turma"),
		'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
		'massa': fields.float('Massa (Kg)', required=True),
		'estatura' : fields.float('Estatura (cm)', required=True),
		"altura_sentado" : fields.float('Altura sentado (cm)', required=True),
		"envergadura" : fields.float('Envergadura (cm)', required=True),
		'imc' : fields.function(_calcula_imc ,method=True, string='IMC',type='float', store=True ),
		'state' : fields.selection (
			(('magro',"Magro(a)"),('normal','Normal'),('sobrepeso','Sobrepeso'),
			('obesidade','Obeso(a)'),('grave','Obeso(a) grave')),
			'Estado', change_default=True
			),
		'regra_id' : fields.many2one('comps.imc.regras', "Regra"),
	}
	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
		"regra_id": 1,
	}
	
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador
	
	# Campo "function" IMC
	def calcula_imc (self, cr, uid, ids, field, args,context):
		dados = self.browse(cr,uid,ids)[0]
		try:
			imc = dados.massa / ((dados.estatura/100)**2)
			if imc <= dados.regra_id.magreza2 and dados.state != 'magro':
				self.write( cr,uid,ids,{'state':'magro'} )
			elif dados.regra_id.Normal1 <= imc <= dados.regra_id.Normal2 and dados.state != "normal":
				self.write(cr,uid,ids,{"state":"normal"})
			elif dados.regra_id.sobrepeso1 <= imc <= dados.regra_id.sobrepeso2 and dados.state != "sobrepeso":
				self.write(cr,uid,ids,{"state":"sobrepeso"})
			elif dados.regra_id.obesidade1 <= imc <= dados.regra_id.obesidade2 and dados.state != "obesidade":
				self.write(cr,uid,ids,{"state":"obesidade"})
			elif dados.regra_id.grave1 <= imc and dados.state != "grave":
				self.write(cr,uid,ids,{"state":"grave"})
		except ZeroDivisionError:
			raise except_orm("Valores Inválidos","Digite valores válidos para peso e altura")
		return {dados.id: imc}
	
	# Reescrita do método "osv.create"
	#def create (self, cr, uid, values,context=None):
		# Grava o Atributo "name"
	#	uid = 1
	#	aluno = self.pool.get('comps.aluno').browse(cr,1,[values['aluno_id']])[0]
	#	string = aluno.name + " : " + str(values['massa']) +', ' + str(values['estatura'])
	#	values['name'] = string
	#	return super(osv.osv,self).create(cr,uid,values)
		
class regras_imc (osv.osv):
	_name = 'comps.imc.regras'
	
	_columns = {
        "outrosDados" : fields.text("Outros dados"),
		'name' : fields.char('Regra', required=True),
		'magreza1' : fields.float('Valor', required=True),
		'magreza2' : fields.float('Valor', required=True),
		'Normal1' : fields.float('Valor', required=True),
		'Normal2' : fields.float('Valor', required=True),
		'sobrepeso1' : fields.float('Valor', required=True),
		'sobrepeso2' : fields.float('Valor', required=True),
		'obesidade1' : fields.float('Valor', required=True),
		'obesidade2' : fields.float('Valor', required=True),
		'grave1' : fields.float('Valor', required=True),
	}
	_defaults = {
		'name' : 'Classificação infantil',
		'magreza1' : 0,
		'magreza2' : 18.5,
		'Normal1' : 18.6,
		'Normal2' : 24.9,
		'sobrepeso1' : 25.0,
		'sobrepeso2': 29.9,
		'obesidade1' : 30.0,
		'obesidade2' : 39.9,
		'grave1' : 40.0
	}
	
class Av_abdominal (osv.Model):
	_name = 'comps.av_abdominal'
	
	_ab_por_minuto = lambda self, cr, uid, ids, field, args, context: self.calcula_ab_por_minuto(cr, uid, ids, field, args, context)

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			'cod': fields.integer(u'Código'), 
			'name':fields.char('Nome',required=False, readonly=False),
			'quantidade_ab': fields.integer(u'Quantidade', required=True),
			'tempo_ab':fields.char('Tempo (em minutos)', size=7, required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
			"ab_por_minuto" : fields.function(_ab_por_minuto, string="Abdominais por minuto", type="float", store=False),
			'state':fields.selection([
				('fraco','Fraco'),
				('abaixo_da_media',u'Abaixo da Média'),
				("media",u"Média"),
				("acima_da_media",u"Acima da média"),
				("excelente","Excelente"),
				 ],	'Classificação', select=True, readonly=True),
	} 
	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	
	def calcula_ab_por_minuto (self, cr, uid, ids, field, args, context):
		dados = self.browse(cr, uid, ids, context)
		tempo = float(dados.tempo_ab.replace(":",".")[:4])
		result = dados.quantidade_ab / tempo
		return {dados.id: result}
	
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Regra_abdominal(osv.Model):
	_name = 'comps.abdominal.regra'
	
	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			'name':fields.char('Nome', size=64),
			"genero" : fields.selection([("masc","Masculino"),("fem", "Feminino")], "Gênero", required=True),
			"idade_inicial" : fields.integer("De idade: ", required=True),
			"idade_final" : fields.integer(u"Até idade", required=True),
			"fraco" : fields.integer("Menor ou igual a: ", required=True),
			"abaixo_da_media1" : fields.integer("De: ", required=True),
			"abaixo_da_media2" : fields.integer(u"Até", required=True),
			"media1" : fields.integer("De: ", required=True),
			"media2" : fields.integer(u"Até: ", required=True),
			"acima_da_media1" : fields.integer("De: ", required=True),
			"acima_da_media2" : fields.integer(u"Até: ", required=True),
			"excelente" : fields.integer(u"À partir de: ", required=True),
	} 

class Perimetro (osv.Model):
	_name = "comps.perimetro"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"perimetro_cintura" : fields.float("Cintura (cm)", required=True),
			"perimetro_abdomen" : fields.float("Abdômen (cm)", required=True),
			"perimetro_quadril" : fields.float("Quadril (cm)", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Dobras_cutaneas (osv.Model):
	_name = "comps.dobras_cutaneas"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),

			"biceps" : fields.float("Biceps (cm)", required=True),
			"triceps" : fields.float("Triceps (cm)", required=True),
			"subEscapular" : fields.float("Subescapular (cm)", required=True),
			"peitoral" : fields.float("Peitoral (cm)", required=True),
			"axilarMedia" : fields.float("Axilar Média (cm)", required=True),
			"supraIliaca" : fields.float("Suprailíaca (cm)", required=True),
			"supraEspinhal" : fields.float("Supra espinhal (cm)", required=True),
			"supraPatelar" : fields.float("Supra patelar (cm)", required=True),
			"abdominal" : fields.float("Abdominal (cm)", required=True),
			"coxaMedial" : fields.float("Coxa média (cm)", required=True),
			"panturrilhaMedial" : fields.float("", required=True),

			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}

	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Impulsao_horizontal (osv.Model):
	_name = "comps.impulsao_horizontal"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}

	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador
class Sentar_alcancar (osv.Model):
	_name = "comps.sentar_alcancar"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Preensao_manual (osv.Model):
	_name = "comps.preensao_manual"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Lancamento_unilateral (osv.Model):
	_name = "comps.lancamento_unilateral"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Lancamento_simultaneo (osv.Model):
	_name = "comps.lancamento_simultaneo"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class Corrida_25m (osv.Model):
	_name = "comps.corrida_25m"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"tempo" : fields.char("Tempo", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class shutllerun_10x5m (osv.Model):
	_name = "comps.shutllerun_10x5m"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"tempo" : fields.char("Tempo", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class shutllerun_20m (osv.Model):
	_name = "comps.shutllerun_20m"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"tempo" : fields.char("Tempo", required=True),
			"voltas" : fields.char("Voltas", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class equilibrio_retaguarda (osv.Model):
	_name = "comps.equilibrio_retaguarda"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class saltos_laterais (osv.Model):
	_name = "comps.saltos_laterais"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class transposicao_lateral (osv.Model):
	_name = "comps.transposicao_lateral"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class saltos_monopedais_perna_direita (osv.Model):
	_name = "comps.saltos_monopedais_perna_direita"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class saltos_monopedais_perna_esquerda (osv.Model):
	_name = "comps.saltos_monopedais_perna_esquerda"

	_columns = {
        "outrosDados" : fields.text("Outros dados"),
			"name" : fields.char("Nome"),
			"result" : fields.integer("Resultado", required=True),
			'create_date' : fields.datetime(u'Data/hora da Avaliação'),
			'avaliador_id' : fields.many2one('comps.avaliador', "Avaliador",required=True, change_default=True),
			'escola_id' : fields.many2one('comps.escola', "Escola"),
			'turma_id' : fields.many2one('comps.turma', "Turma"),
			'aluno_id' : fields.many2one ('comps.aluno',"Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

class avaliacoes_pendentes (osv.Model):
	_name = "comps.avaliacoes_pendentes"

	_columns = {
			"name" : fields.char("nome"),
			"json" : fields.text("json", required=True),
			"detalhes" : fields.text("Detalhes", required=True),
			"avaliador_id" : fields.many2one("comps.avaliador", "Avaliador"),
			"escola_id" : fields.many2one("comps.escola", "Escola"),
			"turma_id" : fields.many2one("comps.turma", "Turma"),
			"aluno_id" : fields.many2one("comps.aluno", "Aluno"),
	}

	_defaults = {
		'avaliador_id' : lambda self,cr,uid,context: self.busca_avaliador( cr,uid,context ),
	}
	def busca_avaliador ( self,cr, uid,context=None ):
		try:
			avaliador = self.pool.get('comps.avaliador').search( cr,uid,[('res_users_id','=',uid)] )[0]
		except:
			avaliador = None
		return avaliador

	def reenviar (self, cr, ui ,ids):
		dados = self.browse( cr, uid, ids )[0]
		if not dados.aluno_id:
			raise except_orm("Dados inválidos", "Verifique todos os dados e tente novamente")
		values = dict(dados.json)
		values["escola_id"] = dados.escola_id
		values["turma_id"] = dados.turma_id
		values["aluno_id"] = dados.aluno_id
		self.pool.get(values["tipo"]).create( cr, 1, values )
		return True
