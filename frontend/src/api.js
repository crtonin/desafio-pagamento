import { API_BASE_URL } from "./constants";

export async function enqueuePayment(payment) {
  const response = await fetch(`${API_BASE_URL}/payments/enqueue`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payment),
  });

  if (!response.ok) {
    throw new Error("Erro ao enviar pagamento à fila");
  }

  return response.text(); 
}

export async function getPaymentsFiltered({ tipo, inicio, fim }) {
  const params = new URLSearchParams();

  if (tipo) params.append("tipo", tipo);
  if (inicio) params.append("inicio", inicio);
  if (fim) params.append("fim", fim);

  const response = await fetch(`${API_BASE_URL}/payments/filter?${params.toString()}`);

  if (!response.ok) {
    throw new Error("Erro ao buscar pagamentos");
  }

  return response.json();
}
