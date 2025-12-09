import { useState } from "react";
import PaymentForm from "./components/PaymentForm";
import TransactionList from "./components/TransactionList";
import "./App.css";

export default function App() {
  const [view, setView] = useState("form");

  return (
    <div>
      {/* MENU */}
      <nav className="navbar is-primary">
        <div className="navbar-menu is-active">
          <div className="navbar-start">
            <a className="navbar-item" onClick={() => setView("form")}>
              Nova Transação
            </a>
            <a className="navbar-item" onClick={() => setView("list")}>
              Listagem
            </a>
          </div>
        </div>
      </nav>

      <div className="container mt-5">
        {view === "form" && <PaymentForm />}
        {view === "list" && <TransactionList />}
      </div>
    </div>
  );
}
