import { Link, useNavigate } from "react-router";
import { executeLogin } from "./auth.service";

export default function Login() {
    const navigate = useNavigate();

    function handleButtonClick() {
        executeLogin("admin", "123456").then(() => {
            navigate("/main/alunos");
        }).catch(err => {
            console.error(err);
        });
        
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