# encoding: UTF-8

from openerp.osv import osv, fields

class res_users (osv.osv):
    _name = 'res.users'
    _inherit = ['res.users']
    
    _columns = {
                    u'tipo_usuario' : fields.selection(
                        (('p','Pessoa'),('i',u"Instituição")), 
                        u'Tipo de usuário'
                        ),
                    # Dados Gerais
                    u'genero' : fields.selection(
                                                (('masc', 'Masculino'),('femin','Feminino')),
                                                u'Gênero',
                                                ),
                    u'endereco' : fields.char(u"Endereço"),
                    u'complemento' : fields.char("Complemento"),
                    u'numero' : fields.char(u"Número"),
                    u'bairro' : fields.char("Bairro"),
                    u'cidade' : fields.char("Cidade"),
                    u'estado' : fields.char("Estado"),
                    # Dados pessoais
                    u'cpf' : fields.char("CPF"),
                    u'rg' : fields.char('RG'),
                    u'celular': fields.char("Celular"),
                    }