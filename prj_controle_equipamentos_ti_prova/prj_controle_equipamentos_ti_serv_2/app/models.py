# app/models.py

class Loja:
    @staticmethod
    def to_dict(data):
        pk_loja_val = data.get("pk_loja")
        return {
            "pk_loja": int(pk_loja_val) if pk_loja_val is not None else None,
            "cnpj": data.get("cnpj"),
            "gerente": data.get("gerente"),
            "telefone": data.get("telefone"),
            "cidade": data.get("cidade")
        }

class EnvioEquipamento:
    @staticmethod
    def to_dict(data):
        fk_loja_val = data.get("fk_loja")
        return {
            "fk_num_serie": data.get("fk_num_serie"),
            "origem": data.get("origem"),
            "fk_loja": int(fk_loja_val) if fk_loja_val is not None else None,
            "motivo": data.get("motivo"),
            "data_envio": data.get("data_envio"),
            "usuario_envio": data.get("usuario_envio"),
        }
