import { createBrowserRouter, Navigate,  } from "react-router";
import { lazy } from "react";

const PrivateRoute = lazy(() => import("./PrivateRoute"));
const Login = lazy(() => import("../pages/auth/Login"));
const Main = lazy(() => import("../pages/main/Main"));
const Alunos = lazy(() => import("../pages/alunos/Alunos"));
const Professores = lazy(() => import("../pages/professores/Professores"));
const Aluno = lazy(() => import("../pages/alunos/Aluno"));

export const router = createBrowserRouter([
    {
        path: "/",
        element: <Navigate to="/login" />,
        errorElement: <h1>404 - Página não encontrada</h1>,
    },
    {
        path: "/login",
        element: <Login />,
        index: true,
    },
    {
        path: "/main",
        element: <Main />,
        children: [
            {
                path: "alunos",
                element: <Alunos />,
                index: true,
            },
            {
                path: "professores",
                element: <Professores />
            },
            {
                path: "aluno/:id",
                element: <Aluno />
            }
        ],
    },
])