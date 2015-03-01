# encding: UTF-8

def extrai_dados(json):
	string = ""
	for chave in json:
		string += "%s: %s \n" % (chave, json[chave])
	return string