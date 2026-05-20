import { Link, useNavigate } from "react-router";
import { executeLogin } from "./auth.service";
import { useAuthContext } from "../../contexts/AuthContext";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

const loginSchema = z.object({
    login: z.string().min(3, "O nome de usuário deve ter pelo menos 3 caracteres"),
    senha: z.string().min(6, "A senha deve ter pelo menos 6 caracteres.")
});

type LoginData = z.infer<typeof loginSchema>;

export default function Login() {
    const navigate = useNavigate();
    const { storeToken } = useAuthContext();
    
    const { register, handleSubmit, setError, formState: { errors, isSubmitting } } = useForm<LoginData>({
        resolver: zodResolver(loginSchema),
    });

    const onSubmit = (data: LoginData) => {
        console.log(data.login);
        console.log(data.senha);
        return executeLogin(String(data.login), String(data.senha)).then((token: string) => {
            storeToken(token);
            navigate("/main/alunos");
        }).catch(() => {
            setError("root", {
                type: "manual",
                message: "Falha na validação do login"
            })
        });
    }

    return (
        <div>
            <h1>Login</h1>
            <p>Faça login para acessar o conteúdo exclusivo.</p>
            <Link to="/main/alunos">Ir para Main</Link>
            {errors.root && errors.root.message}
            <form onSubmit={handleSubmit(onSubmit)}>
                <label htmlFor="login">Login</label>
                <input {...register("login")} name="login" />
                {errors.login && (
                    <p>{errors.login.message}</p>
                )}
                <label htmlFor="senha">Senha</label>
                <input {...register("senha")} type="password" name="senha" id="senha" />
                {errors.senha && (
                    <p>{errors.senha.message}</p>
                )}
                <button type="submit" disabled={isSubmitting} >{isSubmitting ? "Validando..." : "Enviar"}</button>
            </form>
        </div>
    );
}