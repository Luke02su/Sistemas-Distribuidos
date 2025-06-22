from flask import Blueprint, request, jsonify
from app.database import mongo
from app.models import Loja, EnvioEquipamento

api_bp = Blueprint("api", __name__)

# INDEX
@api_bp.route("/", methods=["GET"])
def index():
    return jsonify({"message": "API de Lojas e Envios de Equipamentos ativa."})

# ----------------- LOJA -----------------

@api_bp.route("/lojas", methods=["POST"])
def create_loja():
    data = request.json
    loja = Loja.to_dict(data)
    result = mongo.db.loja.insert_one(loja)
    return jsonify({"id": str(result.inserted_id), **loja}), 201

@api_bp.route("/lojas", methods=["GET"])
def get_lojas():
    lojas = mongo.db.loja.find()
    return jsonify([Loja.to_dict(loja) for loja in lojas])

@api_bp.route("/lojas/<int:pk_loja>", methods=["GET"])
def get_loja(pk_loja):
    loja = mongo.db.loja.find_one({"pk_loja": pk_loja})
    if loja:
        return jsonify(Loja.to_dict(loja))
    return jsonify({"error": "Loja não encontrada"}), 404

@api_bp.route("/lojas/<int:pk_loja>", methods=["PUT"])
def update_loja(pk_loja):
    data = request.json
    update_data = {"$set": Loja.to_dict(data)}
    result = mongo.db.loja.update_one({"pk_loja": pk_loja}, update_data)
    if result.matched_count:
        updated_loja = mongo.db.loja.find_one({"pk_loja": pk_loja})
        return jsonify(Loja.to_dict(updated_loja))
    return jsonify({"error": "Loja não encontrada"}), 404

@api_bp.route("/lojas/<int:pk_loja>", methods=["DELETE"])
def delete_loja(pk_loja):
    result = mongo.db.loja.delete_one({"pk_loja": pk_loja})
    if result.deleted_count:
        return jsonify({"message": "Loja removida com sucesso"})
    return jsonify({"error": "Loja não encontrada"}), 404

# ----------------- ENVIO DE EQUIPAMENTO -----------------

@api_bp.route("/envios", methods=["POST"])
def create_envio():
    data = request.json
    envio = EnvioEquipamento.to_dict(data)
    result = mongo.db.envio_equipamento.insert_one(envio)
    return jsonify({"id": str(result.inserted_id), **envio}), 201

@api_bp.route("/envios", methods=["GET"])
def get_envios():
    envios = mongo.db.envio_equipamento.find()
    return jsonify([EnvioEquipamento.to_dict(envio) for envio in envios])

@api_bp.route("/envios/<string:fk_num_serie>", methods=["GET"])
def get_envio(fk_num_serie):
    envio = mongo.db.envio_equipamento.find_one({"fk_num_serie": fk_num_serie})
    if envio:
        return jsonify(EnvioEquipamento.to_dict(envio))
    return jsonify({"error": "Envio não encontrado"}), 404

@api_bp.route("/envios/<string:fk_num_serie>", methods=["PUT"])
def update_envio(fk_num_serie):
    data = request.json
    update_data = {"$set": EnvioEquipamento.to_dict(data)}
    result = mongo.db.envio_equipamento.update_one({"fk_num_serie": fk_num_serie}, update_data)
    if result.matched_count:
        updated_envio = mongo.db.envio_equipamento.find_one({"fk_num_serie": fk_num_serie})
        return jsonify(EnvioEquipamento.to_dict(updated_envio))
    return jsonify({"error": "Envio não encontrado"}), 404

@api_bp.route("/envios/<string:fk_num_serie>", methods=["DELETE"])
def delete_envio(fk_num_serie):
    result = mongo.db.envio_equipamento.delete_one({"fk_num_serie": fk_num_serie})
    if result.deleted_count:
        return jsonify({"message": "Envio removido com sucesso"})
    return jsonify({"error": "Envio não encontrado"}), 404
