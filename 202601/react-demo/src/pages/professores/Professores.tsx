import { Link } from "react-router";

export default function Professores() {
    return (
        <div>
            <h1>Professores</h1>
            <p>Lista de professores disponíveis.</p>
            <Link to="/main/alunos">Ir para Alunos</Link>
        </div>
    );
}