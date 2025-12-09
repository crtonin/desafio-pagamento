import { useState } from "react";
import { TIPOS } from "../constants";
import { enqueuePayment } from "../api";


export default function PaymentForm() {
  const hoje = new Date().toISOString().split("T")[0];

  const [form, setForm] = useState({
    contaOrigem: "",
    contaDestino: "",
    valor: "",
    tipo: TIPOS[0],
    data: hoje,
  });

  const handleChange = (e) => {
    let { name, value } = e.target;

    // mascara apenas do campo valor
    if (name === "valor") {
      value = value.replace(/\D/g, "");
      value = (value / 100).toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL",
      });
    }

    setForm({ ...form, [name]: value });
  };

  const submitForm = async () => {
  try {
    const valorNumerico = Number(
      form.valor
        .replace("R$", "")
        .replace(/\./g, "")
        .replace(",", ".")
        .trim()
    );

    const payload = {
      contaOrigem: form.contaOrigem,
      contaDestino: form.contaDestino,
      valor: valorNumerico,
      tipo: form.tipo,
      data: form.data,
    };

    await enqueuePayment(payload);

    alert("Transação enviada com sucesso!");
  } catch (err) {
    console.error(err);
    alert("Erro ao enviar a transação");
  }
};

  return (
    <div className="box">
      <h1 className="title">Nova Transação</h1>

      <div className="field">
        <label className="label">Conta Origem</label>
        <input
          className="input"
          type="text"
          name="contaOrigem"
          value={form.contaOrigem}
          onChange={handleChange}
        />
      </div>

      <div className="field">
        <label className="label">Conta Destino</label>
        <input
          className="input"
          type="text"
          name="contaDestino"
          value={form.contaDestino}
          onChange={handleChange}
        />
      </div>

      <div className="field">
        <label className="label">Valor</label>
        <input
          className="input"
          type="text"
          name="valor"
          value={form.valor}
          onChange={handleChange}
        />
      </div>

      <div className="field">
        <label className="label">Tipo</label>
        <div className="control">
          <div className="select">
            <select name="tipo" value={form.tipo} onChange={handleChange}>
              {TIPOS.map((t) => (
                <option key={t} value={t}>{t}</option>
              ))}
            </select>
          </div>
        </div>
      </div>

      <div className="field">
        <label className="label">Data</label>
        <input
          className="input"
          type="date"
          name="data"
          min={hoje}
          value={form.data}
          onChange={handleChange}
        />
      </div>

      <button className="button is-primary" onClick={submitForm}>
        Enviar Transação
      </button>
    </div>
  );
}