import { useParams } from "react-router";

export default function Aluno() {
    const { id } = useParams();
    return (
        <div>
            <h1>Aluno</h1>
            <p>Detalhes do aluno selecionado.</p>
            <p>ID do aluno: {id}</p>
        </div>
    );
}