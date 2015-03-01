# -*- encoding: UTF-8 -*-

from openerp.osv import osv, fields
from openerp import tools, api
from datetime import datetime

class Escola (osv.Model):
	_name = "comps.escola"
	
	_inherits = {"res.users":"res_users_id"}
	
	_columns = {
		'telefone': fields.char("Telefone", required=True),
		'email' : fields.char(u"E-mail", required=True),
		'endereco' : fields.char(u"Endereço", required=True),
		'complemento' : fields.char("Complemento"),
		'numero' : fields.char(u"Número", required=True),
		'bairro' : fields.char("Bairro", required=True),
		'cidade' : fields.char("Cidade", required=True),
		'estado' : fields.char("Estado", required=True),
		'turma_ids' : fields.one2many ('comps.turma','escola_id',string="Turmas",ondelete="cascade"),
		"res_users_id" : fields.many2one("res.users", required=True, ondelete="cascade"),
		'avaliador_ids' : fields.many2many("comps.avaliador",string="Avaliador", ondelete='set null'),
		'avaliacao_imc_ids' : fields.one2many('comps.imc', 'escola_id', ondelete='set null'),
		"avaliacao_abdominal_ids" : fields.one2many("comps.av_abdominal", "escola_id", ondelete="set null"),
	}
	@api.onchange("login")
	def on_change_login(self):
		if self.login and tools.single_email_re.match(self.login):
			self.email = self.login

	def create (self, cr,uid,values,context=None):
		cr.execute ("SELECT res_groups.id FROM res_groups WHERE res_groups.name = 'comps_instituicao'")
		group_id = cr.fetchone()[0]
		values['groups_id'][0][2].append(group_id)
		return super(osv.Model, self).create(cr,1,values)

class Turma (osv.Model):
	_name = "comps.turma"
	
	_columns = {
		'name' : fields.char("Turma"),
		'escola_id' : fields.many2one('comps.escola', 'Escola',required=True, ondelete="cascade"),
		'serie_id' : fields.many2one('comps.serie', u'Série', ondelete='set null'),
		'identificador' : fields.char ('Identificador'),
		'turno' : fields.selection (
			(('matutino', u'Matutino'),('vespertino',u'Vespertino'),('noturno',u'Noturno')),
			'Turno', required=True
			),
		'turma_name' : fields.selection(
			(
			('a','A'),('b','B'),('c','C'),('d','D'),('e','E'),('f','F'),
			('g','G'),('h','H'),('i','I'),('j','J'),('k','K'),('l','L'),
			('m','M'),('n','N'),('o','O'),('p','P'),('q','Q'),('r','R'),
			('s','S'),('t','T'),('u','U'),('v','V'),('w','W'),('x','X'),
			('y','Y'),('z','Z')
			)
			,"Turma", required=True),
		'ano' : fields.char("Ano", required=True),
		'avaliador_ids' : fields.many2many('comps.avaliador', ondelete='set null'),
		'avaliacao_imc_ids' : fields.one2many('comps.imc', 'turma_id', ondelete='set null'),
		'aluno_ids' : fields.one2many('comps.aluno', 'turma_ids', ondelete='set null'),
		"avaliacao_abdominal_ids" : fields.one2many("comps.av_abdominal", "turma_id", ondelete="set null"),
	}
	
	def create ( self,cr,uid,values,context ):
		serie = self.pool.get('comps.serie').browse( cr,uid,values['serie_id'] )
		escola = self.pool.get('comps.escola').browse( cr,uid,values['escola_id'] )
		values['name'] = serie.name + " " + values['turma_name'].upper() +" - "+ serie.nivel.capitalize() + " - " + escola.name
		return super(osv.Model,self).create( cr,uid,values,context )
		

class Serie (osv.Model):
	_name = 'comps.serie'
	
	_columns = {
		'name' : fields.char("Ano / Série", required=True),
		u'nivel' : fields.selection (
			(('basico',u'Básico'),('fundamental', 'Fundamental'),("medio",u'Médio')),
			u'Nível', required=True
			),
		'turma_id': fields.one2many('comps.turma', 'serie_id', u'Série', invisible=True, ondelete='set null')
	}

class Avaliador (osv.Model):
	_name = "comps.avaliador"
	
	_inherits = {'res.users':"res_users_id"}
	
	_columns = {
		'escola_ids' : fields.many2many("comps.escola", string="Escolas",ondelete='set null'),
		'turma_ids' : fields.many2many ('comps.turma',string="Turmas", ondelete='set null'),
		"res_users_id" : fields.many2one("res.users", required=True, ondelete="cascade"),
		'avaliacao_imc_ids' : fields.one2many('comps.imc', 'avaliador_id',ondelete='set null'),
		"avaliacao_abdominal_ids" : fields.one2many("comps.av_abdominal", "avaliador_id", ondelete="set null"),
	}
	
	@api.onchange("login")
	def on_change_login(self):
		if self.login and tools.single_email_re.match(self.login):
			self.email = self.login
	def create (self, cr,uid,values,context=None):
		cr.execute ("SELECT res_groups.id FROM res_groups WHERE res_groups.name = 'comps_avaliador'")
		group_id = cr.fetchone()[0]
		values['groups_id'][0][2].append(group_id)
		print uid
		return super(osv.Model, self).create(cr,1,values)
	
	
class Aluno (osv.Model):
	_name = 'comps.aluno'
	
	_inherits = {'res.users':"res_users_id"}
	
	_calcula_idade = lambda self,cr,uid,ids,field,args,context: self.calcula_idade(cr,uid,ids,field,args, context)	
	
	_columns = {
		'responsavel1' : fields.char(u"Responsável 1", required=True),
		'responsavel2' : fields.char(u'Responsável 2'),
		'telefone_responsavel' : fields.char(u'Telefone do responsável', required=True),
		'email_responsavel' : fields.char(u'E-mail do responsável', required=True),
		'data_nascimento' : fields.char('Data de nascimento', required=True),
		'idade': fields.function(_calcula_idade, method=True, type='integer', string='Idade', store=False),
		'observacao' : fields.text(u'Observações'),
		'escola_ids' : fields.many2one("comps.escola", "Escolas", required=True),
		'turma_ids' : fields.many2one ('comps.turma', "Turmas", required=True),
		"res_users_id" : fields.many2one("res.users", required=True, ondelete="cascade"),
		'avaliador_id' : fields.many2one('comps.avaliador', ondelete='set null'),
		'avaliacao_imc_ids' : fields.one2many('comps.imc', 'aluno_id', ondelete='set null'),
		"avaliacao_abdominal_ids" : fields.one2many("comps.av_abdominal", "aluno_id", ondelete="set null"),
	}
	
	@api.onchange("login")
	def on_change_login(self):
		if self.login and tools.single_email_re.match(self.login):
			self.email = self.login
	
	def create (self, cr,uid,values,context=None):
		cr.execute ("SELECT res_groups.id FROM res_groups WHERE res_groups.name = 'comps_aluno'")
		group_id = cr.fetchone()[0]
		values['groups_id'][0][2].append(group_id)
		return super(osv.Model, self).create(cr,1,values)
	
	# Campo Funcional "Idade"
	def calcula_idade (self, cr, uid, ids, field, args, context):
		dados = self.browse(cr, uid, ids, context)[0]
		data = datetime.strptime(str(dados.data_nascimento), "%d/%m/%Y")
		idade = datetime.now().year - data.year
		return {dados.id : idade}