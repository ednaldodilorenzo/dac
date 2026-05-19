import { Navigate, Outlet, useNavigate } from "react-router";
import { useAuthContext } from "../../contexts/AuthContext";

export default function Main() {
    const navigate = useNavigate();
    const { isAuthenticated } = useAuthContext();
    return isAuthenticated ? (
        <>
            <div>
                <h1>Main</h1>
                <p>Bem-vindo à página principal!</p>
                <button onClick={() => navigate(-1)}>Voltar</button>
            </div>
            <div>
                <Outlet />
            </div>
        </>
    ) : <Navigate to="/login" />;
}