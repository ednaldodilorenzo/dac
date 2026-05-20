import { Link, useNavigate } from "react-router";
import { executeLogin } from "./auth.service";
import { useAuthContext } from "../../contexts/AuthContext";
import { useActionState, useEffect } from "react";
import { z } from "zod";

const loginSchema = z.object({
    login: z.string().min(3, "O nome de usuário deve ter pelo menos 3 caracteres"),
    senha: z.string().min(6, "A senha deve ter pelo menos 6 caracteres.")
})

type LoginData = z.infer<typeof loginSchema>;

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

     function handleSubmit(e: React.SubmitEvent<HTMLFormElement>) {
         e.preventDefault();
         const form = e.currentTarget as HTMLFormElement;
         const formData = new FormData(form);
         const payload = Object.fromEntries(formData);
         console.log(payload.login);
         console.log(payload.senha);
         executeLogin(payload.login + "", payload.senha + "").then((token: string) => {
             navigate("/main/alunos");
             storeToken(token);
         }).catch(err => {
             console.error(err);
         });
     }

    function handleFormAction(formData: FormData) {
        const payload = Object.fromEntries(formData);
        console.log(payload.login);
        console.log(payload.senha);
    }

    //type FieldError = { field: string, message: string };

    type FormState = {
        success: boolean;
        message: string;
        errors?: { [K in keyof LoginData]?: string[] };
    }

    function loginAction(_: FormState, formData: FormData): Promise<FormState> {
        const payload = Object.fromEntries(formData.entries());
        //const errors: FieldError[] = [];

        const result = loginSchema.safeParse(payload);

        if (!result.success) {
            return Promise.resolve({
                success: false,
                message: "Falha na validação de campos",
                errors: result.error.flatten().fieldErrors,
            });
        }

        // if (!payload.login || String(payload.login).trim() === "") {
        //     errors.push({
        //         field: "login",
        //         message: "Login deve ser informado",
        //     })
        // }

        // if (!payload.senha || String(payload.senha).trim() === "") {
        //     errors.push({
        //         field: "senha",
        //         message: "Senha deve ser informada",
        //     });
        // }

        // if (errors.length > 0) {
        //     return Promise.resolve({
        //         success: false,
        //         message: "Falha na validação do formulário",
        //         errors,
        //     })
        // }

        return executeLogin(String(payload.login), String(payload.senha)).then((token: string) => {
            //navigate("/main/alunos");
            storeToken(token);
            return {
                success: true,
                message: "ok"
            }
        });
    }

    const initialState: FormState = {
        success: false,
        message: ""
    }

    const [state, formAction, isPending] = useActionState(loginAction, initialState);
    
    useEffect(() => {
        if (state.success) {
            navigate("/main/alunos");
        }
    }, [state.success, navigate]);
  
    return (
        <div>
            <h1>Login</h1>
            <p>Faça login para acessar o conteúdo exclusivo.</p>
            <Link to="/main/alunos">Ir para Main</Link>
            <form action={formAction}>
                <label htmlFor="login">Login</label>
                <input name="login" />
                {state.errors?.login && (
                    <p>{state.errors.login}</p>
                )}
                <label htmlFor="senha">Senha</label>
                <input type="password" name="senha" id="senha" />
                {state.errors?.senha && (
                    <p>{state.errors.senha}</p>
                )}
                <button type="submit" disabled={isPending}>{isPending ? "Validando..." : "Enviar"}</button>
            </form>
        </div>
    );
}