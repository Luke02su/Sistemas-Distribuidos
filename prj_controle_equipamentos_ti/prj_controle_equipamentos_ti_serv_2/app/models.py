class Loja:
    @staticmethod
    def to_dict(data):
        return {
            "pk_loja": int(data.get("pk_loja")),
            "cnpj": data.get("cnpj"),
            "gerente": data.get("gerente"),
            "telefone": data.get("telefone"),
            "cidade": data.get("cidade")
        }

class EnvioEquipamento:
    @staticmethod
    def to_dict(data):
        return {
            "fk_num_serie": data.get("fk_num_serie"),
            "origem": data.get("origem"),
            "fk_loja": data.get("fk_loja"),
            "motivo": data.get("motivo"),
            "data_envio": data.get("data_envio"),
            "usuario_envio": data.get("usuario_envio"),
        }
