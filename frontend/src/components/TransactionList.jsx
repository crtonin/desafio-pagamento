import { useState, useEffect } from "react";
import { TIPOS } from "../constants";
import { getPaymentsFiltered } from "../api"; 

const getTodayDate = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); 
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
};

export default function TransactionList() {
  const today = getTodayDate(); 

  const [filtros, setFiltros] = useState({
    tipo: "",
    inicio: today, 
    fim: today,    
  });

  const [transacoes, setTransacoes] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
      buscar(); 
  }, []); 

  const handleChange = (e) => {
    setFiltros({ ...filtros, [e.target.name]: e.target.value });
  };

  const buscar = async () => {
    setLoading(true);
    try {
      const data = await getPaymentsFiltered(filtros);
      setTransacoes(data);
    } catch (err) {
      alert("Erro ao buscar transações!");
      console.error(err);
    }
    setLoading(false);
  };

  return (
    <div className="box">
      <h1 className="title">Listagem de Transações</h1>

      <div className="columns">

        <div className="column">
          <label className="label">Tipo</label>
          <div className="select is-fullwidth">
            <select name="tipo" onChange={handleChange} value={filtros.tipo}>
              <option value="">Todos</option>
              {TIPOS.map((t) => (
                <option key={t} value={t}>{t}</option>
              ))}
            </select>
          </div>
        </div>

        <div className="column">
          <label className="label">Data Início</label>
          <input
            className="input"
            type="date"
            name="inicio"
            value={filtros.inicio} 
            onChange={handleChange}
          />
        </div>

        <div className="column">
          <label className="label">Data Fim</label>
          <input
            className="input"
            type="date"
            name="fim"
            value={filtros.fim} 
            onChange={handleChange}
          />
        </div>
      </div>

      <button
        className={`button is-primary ${loading ? "is-loading" : ""}`}
        onClick={buscar}>
        Filtrar
      </button>

      <hr />

      {transacoes.length === 0 ? (
        <p><i>Nenhum registro encontrado.</i></p>
      ) : (
        <table className="table is-fullwidth is-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Tipo</th>
              <th>Data</th>
              <th>Valor</th>
              <th>Conta Origem</th>
              <th>Conta Destino</th>
            </tr>
          </thead>
          <tbody>
            {transacoes.map((t) => (
              <tr key={t.id}>
                <td>{t.id}</td>
                <td>{t.tipo}</td>
                <td>{t.data}</td>
                <td>R$ {t.valor}</td>
                <td>{t.contaOrigem}</td>
                <td>{t.contaDestino}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}