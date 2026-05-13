import type { ReactNode } from "react";
import { useNavigate } from "react-router";



export default function PrivateRoute({ children }: { children: ReactNode }) {

    const token = localStorage.getItem("token");
    const navigate = useNavigate();

    if (!token) {
        navigate("/login");
        return null;
    }

    return children;
}