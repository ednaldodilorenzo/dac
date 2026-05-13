import { Navigate, Outlet, useNavigate } from "react-router";

export default function Main() {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    return token ? (
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