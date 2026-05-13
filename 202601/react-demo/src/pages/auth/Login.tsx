import { Link, useNavigate } from "react-router";

export default function Login() {
    const navigate = useNavigate();

    function handleButtonClick() {
        navigate("/main");
    }

    return (
        <div>
            <h1>Login</h1>
            <p>Faça login para acessar o conteúdo exclusivo.</p>
            <Link to="/main/alunos">Ir para Main</Link>
            <button onClick={handleButtonClick}>Ir para a Main de forma programática</button>
        </div>
    );
}