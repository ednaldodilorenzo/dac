import { Link, useNavigate } from "react-router";
import { executeLogin } from "./auth.service";
import { useAuthContext } from "../../contexts/AuthContext";

export default function Login() {
    const navigate = useNavigate();
    const { storeToken } = useAuthContext();
    function handleButtonClick() {
        executeLogin("admin", "123456").then((token: string) => {
            navigate("/main/alunos");
            storeToken(token);
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